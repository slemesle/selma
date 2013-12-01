/*
 * Copyright 2013  Séven Le Mesle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package fr.xebia.extras.selma.codegen;

import com.squareup.javawriter.JavaWriter;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.io.IOException;

import static fr.xebia.extras.selma.codegen.MappingSourceNode.*;

/**
 *
 */
public class MapperMethodGenerator {


    private final JavaWriter writer;
    private final MethodWrapper mapperMethod;
    private final MapperGeneratorContext context;
    private final MappingRegistry mappingRegistry;
    private final SourceConfiguration configuration;

    public MapperMethodGenerator(JavaWriter writer, ExecutableElement mapperMethod, MapperGeneratorContext context, MappingRegistry mappingRegistry, SourceConfiguration configuration) {
        this.writer = writer;
        this.mapperMethod = new MethodWrapper(mapperMethod, context);
        this.context = context;
        this.configuration = configuration;
        this.mappingRegistry = mappingRegistry;
    }

    public static MapperMethodGenerator create(JavaWriter writer, ExecutableElement mapperMethod, MapperGeneratorContext context, MappingRegistry mappingRegistry, SourceConfiguration configuration) {
        return new MapperMethodGenerator(writer, mapperMethod, context, mappingRegistry, configuration);
    }

    public void build() throws IOException {


        buildMappingMethod(writer, mapperMethod.inOutType(), mapperMethod.getSimpleName(), true);

        // Do we need to build other methods for this mapping ?
        if (context.hasMappingMethods()) {
            buildMappingMethods(writer);
        }

    }

    private void buildMappingMethods(JavaWriter writer) throws IOException {

        MapperGeneratorContext.MappingMethod method;
        while ((method = context.popMappingMethod()) != null) {
            buildMappingMethod(writer, method.inOutType(), method.name(), false);
        }

    }

    private void buildMappingMethod(JavaWriter writer, InOutType inOutType, String name, boolean override) throws IOException {

        MappingSourceNode methodNode = null;
        if(configuration.isFinalMappers()){
            methodNode = mapMethod(inOutType.in().toString(), inOutType.out().toString(), name, override);
        } else {
            methodNode = mapMethodNotFinal(inOutType.in().toString(), inOutType.out().toString(), name, override);
        }

        MappingSourceNode ptrBis = blank(), ptrBisRoot = ptrBis;
        MappingSourceNode ptr = methodNode;


        TypeMirror typeElement = inOutType.in();

        ptr = methodNode.body(declareOut(inOutType.out()));

        MappingBuilder mappingBuilder = findBuilderFor(inOutType);

        if (mappingBuilder != null) {
            ptrBis = ptrBis.body(mappingBuilder.build(context, new SourceNodeVars().withInOutType(inOutType).withAssign(true)));

            generateStack(context);

        } else if (inOutType.areDeclared()) {
            ptrBis = ptrBis.body(instantiateOut(inOutType.out().toString(), context.newParams()));
            context.depth++;
            ptrBis = ptrBis.child(generate(inOutType));
            context.depth--;
        } else {
            handleNotSupported(inOutType, ptrBis);
        }

        if (!inOutType.inIsPrimitive()) {
            ptr = ptr.child(controlNull("in"));
            ptr.body(ptrBisRoot.body);
        } else {
            ptr.child(ptrBisRoot.body);
        }

        // Give it a try
        methodNode.write(writer);
    }

    private void handleNotSupported(InOutType inOutType, MappingSourceNode ptr) {
        final String message = String.format("Failed to generate mapping method for type %s to %s not supported on %s.%s", inOutType.in(), inOutType.out(), mapperMethod.element().getEnclosingElement(), mapperMethod.element().toString());
        ptr.body(notSupported(message));
        if (configuration.isIgnoreNotSupported()) {
            context.warn(message, mapperMethod.element());
        } else {
            context.error(mapperMethod.element(), message);
        }
    }

    private MappingSourceNode generateStack(MapperGeneratorContext context) throws IOException {

        MappingSourceNode ptr = blank();
        MappingSourceNode root = ptr;
        MapperGeneratorContext.StackElem stackElem;
        while ((stackElem = context.popStack()) != null) {
            InOutType inOutType = stackElem.sourceNodeVars().inOutType;
            context.depth++;
            MappingBuilder mappingBuilder = findBuilderFor(inOutType);
            if (stackElem.child) {

                lastChild(stackElem.lastNode).child(mappingBuilder.build(context, stackElem.sourceNodeVars()));
            } else {
                stackElem.lastNode.body(mappingBuilder.build(context, stackElem.sourceNodeVars()));
            }
            context.depth--;
        }

        return root;
    }

    MappingBuilder findBuilderFor(InOutType inOutType) {
        return mappingRegistry.findMappingFor(inOutType);
    }

    private MappingSourceNode generate(InOutType inOutType) throws IOException {

        MappingSourceNode root = blank();
        MappingSourceNode ptr = root;


        TypeElement outTypeElement = (TypeElement) context.type.asElement(inOutType.out());


        BeanWrapper outBean = new BeanWrapper(context, outTypeElement);
        BeanWrapper inBean = new BeanWrapper(context, (TypeElement) context.type.asElement(inOutType.in()));

        for (String field : inBean.getFields()) {

            boolean isMissingInDestination = !outBean.hasFieldAndSetter(field);
            if (isMissingInDestination && configuration.isIgnoreMissingProperties()) {
                continue;
            } else {
                if (isMissingInDestination) {
                    context.error(inBean.getFieldElement(field), String.format("getter for field %s from in bean %s is missing in destination bean %s", field, inOutType.in(), inOutType.out()));
                    break;
                }

                try {
                    MappingBuilder mappingBuilder = findBuilderFor(new InOutType(inBean.getTypeFor(field), outBean.getTypeFor(field)));
                    if (mappingBuilder != null) {
                        ptr = ptr.child(mappingBuilder.build(context, new SourceNodeVars(field, inBean, outBean)
                                .withInOutType(new InOutType(inBean.getTypeFor(field), outBean.getTypeFor(field))).withAssign(false)));

                        generateStack(context);
                    } else {
                        handleNotSupported(inOutType, ptr);
                    }
                } catch (Exception e) {
                    System.out.printf("Error while searching builder for field %s on %s mapper", field, inOutType.toString());
                    e.printStackTrace();
                }


                ptr = lastChild(ptr);
            }

        }
        return root.child;
    }

    private MappingSourceNode lastChild(MappingSourceNode ptr) {
        while (ptr.child != null) {
            ptr = ptr.child;
        }
        return ptr;
    }


}
