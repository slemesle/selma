package fr.xebia.extras.selma.codegen;


import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 25/11/2013
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public class AnnotationWrapper {
    private final AnnotationMirror annotationMirror;
    private final HashMap<String, AnnotationValue> map;
    private final MapperGeneratorContext context;


    public AnnotationWrapper(MapperGeneratorContext context, AnnotationMirror annotationMirror) {
        this.annotationMirror = annotationMirror;
        this.map = new HashMap<String, AnnotationValue>();
        this.context = context;

        for (ExecutableElement executableElement : context.elements.getElementValuesWithDefaults(annotationMirror).keySet()) {
            this.map.put(executableElement.getSimpleName().toString(), context.elements.getElementValuesWithDefaults(annotationMirror).get(executableElement));
        }
    }

    public static AnnotationWrapper buildFor(MapperGeneratorContext context, Element method, Class<?> annot) {


        AnnotationMirror annotationMirror = null;

        for (AnnotationMirror mirror : method.getAnnotationMirrors()) {

            if (mirror.getAnnotationType().toString().equals(annot.getCanonicalName())) {
                annotationMirror = mirror;
                break;
            }
        }


//        Map<? extends ExecutableElement, ? extends AnnotationValue> values = context.elements().getElementValuesWithDefaults(findMapperAnnotation(method.getAnnotationMirrors()));

      /*  for (ExecutableElement element : values.keySet()) {
            if("ignoreMissingProperties".equals(element.getSimpleName().toString())){
                ignoreMissingProperties = (Boolean)values.get(element).getValue();
            } else if ("ignoreNotSupported".equals(element.getSimpleName().toString())){
                ignoreNotSupported = (Boolean)values.get(element).getValue();
            } else if ("useDuplicate".equals(element.getSimpleName().toString())){
                useDuplicate = (Boolean)values.get(element).getValue();
            }
        }
        */

        if (annotationMirror != null) {
            return new AnnotationWrapper(context, annotationMirror);
        } else {
            return null;
        }

    }

    public List<String> getAsStrings(String parameterName) {

        List<String> res = new ArrayList<>();
        AnnotationValue myValue = map.get(parameterName);
        if (myValue.getValue() instanceof List) {
            List<? extends AnnotationValue> values = (List<? extends AnnotationValue>) myValue.getValue();
            for (AnnotationValue value : values) {
                res.add(value.toString());
            }
        }

        return res;
    }

    public boolean getAsBoolean(String ignoreMissingProperties) {
        return (Boolean) map.get(ignoreMissingProperties).getValue();

    }
}
