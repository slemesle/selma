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

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Wrapper for specific type
 * builds a graph of fields and getter/setter methods for a type.
 */
public class BeanWrapper {

    final MapperGeneratorContext context;
    final TypeElement typeElement;
    final Map<String, FieldItem> fieldsGraph;

    public BeanWrapper(MapperGeneratorContext context, TypeElement typeElement) {
        this.context = context;
        this.typeElement = typeElement;

        fieldsGraph = buildFieldGraph();
    }

    private Map<String, FieldItem> buildFieldGraph() {
        HashMap<String, FieldItem> result = new HashMap<String, FieldItem>();

        final List<? extends Element> elementInList = context.elements.getAllMembers(typeElement);

        final List<VariableElement> fields = ElementFilter.fieldsIn(elementInList);

        final List<ExecutableElement> methods = ElementFilter.methodsIn(elementInList);

        for (VariableElement field : fields) {

            ExecutableElement setter = null;
            ExecutableElement getter = null;

            for (Iterator<ExecutableElement> it = methods.iterator(); it.hasNext(); ) {
                ExecutableElement method = it.next();
                if (isGetterFor(method, field)) {
                    getter = method;
                    it.remove();
                } else if (isSetterFor(method, field)) {
                    setter = method;
                    it.remove();
                }

                if (getter != null && setter != null) {
                    break;
                }
            }

            result.put(field.getSimpleName().toString(), new FieldItem(field, setter, getter));
        }

        return result;
    }


    private boolean isGetterFor(ExecutableElement method, VariableElement field) {

        String methodName = method.getSimpleName().toString();
        String getterName = (field.asType().getKind() == TypeKind.BOOLEAN ? "is" : "get") + field.getSimpleName().toString();

        return method.getParameters().size() == 0 && method.getReturnType().toString().equals(field.asType().toString()) && methodName.equalsIgnoreCase(getterName);
    }


    private static boolean isSetterFor(ExecutableElement method, VariableElement field) {

        String methodName = method.getSimpleName().toString();
        String getterName = "set" + field.getSimpleName().toString();

        return method.getParameters().size() == 1 && method.getReturnType().getKind() == TypeKind.VOID && methodName.equalsIgnoreCase(getterName) && method.getParameters().get(0).asType().toString().equals(field.asType().toString());
    }

    public Iterable<? extends String> getFields() {
        return fieldsGraph.keySet();
    }

    public boolean hasFieldAndSetter(String field) {
        boolean res = false;
        FieldItem item = fieldsGraph.get(field);

        if (item != null && item.setter != null) {
            res = true;
        }

        return res;
    }

    public String getSetterFor(String field) {
        String res = null;
        FieldItem item = fieldsGraph.get(field);

        if (item != null && item.setter != null) {
            res = item.setter.getSimpleName().toString();
        }
        return res;
    }

    public String getOutSetterPathFor(String field) {
        return String.format("out.%s", getSetterFor(field));
    }

    public String getGetterFor(String field) {
        String res = null;
        FieldItem item = fieldsGraph.get(field);

        if (item != null && item.getter != null) {
            res = item.getter.getSimpleName().toString();
        }
        return res;
    }

    public TypeMirror getTypeFor(String field) {
        TypeMirror result = null;
        FieldItem item = fieldsGraph.get(field);

        if (item != null && item.getter != null) {
            result = item.field.asType();
        }

        return result;
    }

    public String getInGetterFor(String field) {
        return String.format("in.%s()", getGetterFor(field));
    }

    public Element getFieldElement(String field) {
        return fieldsGraph.get(field).field;
    }


    class FieldItem {

        final VariableElement field;

        final ExecutableElement setter;

        final ExecutableElement getter;


        FieldItem(VariableElement field, ExecutableElement setter, ExecutableElement getter) {
            this.field = field;
            this.setter = setter;
            this.getter = getter;
        }

    }


}
