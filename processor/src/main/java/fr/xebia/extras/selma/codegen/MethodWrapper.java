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

import fr.xebia.extras.selma.Factory;
import fr.xebia.extras.selma.Mapper;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import java.util.List;

/**
 * This class wraps an ExecutableElement representing a Method
 * it gives convenience method to simplify method manipulation
 */
public class MethodWrapper {

    private final ExecutableElement method;
    private final ProcessingEnvironment processingEnv;
    private boolean ignoreNotSupported = false;
    boolean ignoreMissingProperties = false;
    private boolean useDuplicate;

    public MethodWrapper(ExecutableElement method, MapperGeneratorContext context) {
        this.method = method;
        this.processingEnv = context.processingEnv();

        AnnotationWrapper annotationWrapper = AnnotationWrapper.buildFor(context, method, Mapper.class);
        if (annotationWrapper != null) {
            ignoreMissingProperties = annotationWrapper.getAsBoolean("ignoreMissingProperties");
            ignoreNotSupported = annotationWrapper.getAsBoolean("ignoreNotSupported");
            useDuplicate = annotationWrapper.getAsBoolean("useDuplicate");
        }
    }

    public String firstParameterTypeString() {
        return firstParameterType().toString();
    }

    public TypeMirror firstParameterType() {

        if (method.getParameters().size() > 0){
            return method.getParameters().get(0).asType();
        } else {
            return null;
        }
    }

    public String getReturnType() {
        return returnType().toString();
    }

    public TypeMirror returnType() {
        return method.getReturnType();
    }

    public String getSimpleName() {
        return method.getSimpleName().toString();
    }

    public InOutType inOutType() {
        return new InOutType(firstParameterType(), returnType());

    }

    private AnnotationMirror findMapperAnnotation(List<? extends AnnotationMirror> annotationMirrors) {
        return annotationMirrors.get(0);
    }

    public ExecutableElement element() {
        return method;
    }

    public boolean ignoreMissingProperties() {
        return ignoreMissingProperties;
    }

    public boolean ignoreNotSupported() {
        return ignoreNotSupported;
    }

    public boolean isUseDuplicate() {
        return useDuplicate;
    }

    public boolean hasOneParameter() {
        return method.getParameters().size() == 1;
    }

    public boolean hasTwoParameter() {
        return method.getParameters().size() == 2;
    }

    public int parameterCount() {
        return method.getParameters().size();
    }

    public boolean secondParameterIsFactory() {
        if (hasTwoParameter()){
            TypeMirror typeMirror = method.getParameters().get(1).asType();
            return Factory.class.getName().equals(typeMirror.toString());
        } else {
            return false;
        }
    }

    public boolean hasReturnType() {

        return method.getReturnType() != null && method.getReturnType().getKind() != TypeKind.VOID;
    }
}
