package fr.xebia.extras.codegen;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Source code generation context
 */
public class MapperGeneratorContext {

    private final ProcessingEnvironment processingEnv;
    private final SourceConfiguration configuration;
    int depth = 0;

    Elements elements;

    Types type;

    // Handle nesting on source node
    LinkedList<StackElem> stack;

    // Maintain a registry of known mapping methods
    private HashMap<InOutType, MappingMethod> mappingRegistry;

    // Handle method stack to build all mapping method not already built
    LinkedList<MappingMethod> methodStack;

    public MapperGeneratorContext(ProcessingEnvironment processingEnvironment, SourceConfiguration configuration) {
        this.elements = processingEnvironment.getElementUtils();
        this.type = processingEnvironment.getTypeUtils();
        this.stack = new LinkedList<>();
        this.processingEnv = processingEnvironment;
        mappingRegistry = new HashMap<>();
        methodStack = new LinkedList<>();

        this.configuration = configuration;
    }


    public void error(Element element, String message, Object... args) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format(message, args), element);
    }

    public void pushStackForBody(MappingSourceNode node, SourceNodeVars vars) {
        this.stack.push(new StackElem(node, vars));
    }

    public void pushStackForChild(MappingSourceNode node, SourceNodeVars vars) {
        this.stack.push(new StackElem(node, vars).withChild(true));
    }

    public StackElem popStack() {
        if (stack.size() > 0) {
            return stack.pop();
        }
        return null;
    }

    public void warn(String s, ExecutableElement element) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, s, element);
    }

    /**
     * Push a custom mapping to the registry for later use
     *
     * @param inOutType
     * @param name
     */
    public void mappingMethod(InOutType inOutType, String name) {

        getMappingMethod(inOutType, new MappingMethod(inOutType, name));
    }


    /**
     * return mappingMethod name and push it inType registry if not already done
     *
     * @param inOutType
     * @return
     */
    public String mappingMethod(InOutType inOutType) {

        MappingMethod method;
        method = getMappingMethod(inOutType, new MappingMethod(inOutType));

        return method.name();
    }

    public Collection<MappingMethod> mappingMethods() {
        return mappingRegistry.values();
    }

    private MappingMethod getMappingMethod(InOutType inOutType, MappingMethod mappingMethod) {

        if (mappingRegistry.containsKey(inOutType)) {
            return mappingRegistry.get(inOutType);
        }
        // This is a new mapping method we should ensure it will be built and present in registry for later use
        methodStack.push(mappingMethod);
        mappingRegistry.put(inOutType, mappingMethod);

        return mappingMethod;
    }

    public boolean hasMappingMethods() {
        return mappingRegistry.size() > 0;
    }

    public MappingMethod popMappingMethod() {
        MappingMethod mappingMethod = null;
        if (methodStack.size() > 0) {

            mappingMethod = methodStack.pop();

            while (methodStack.size() > 0 && mappingMethod.built) {
                mappingMethod = methodStack.pop();
            }
            if (mappingMethod.built) {
                mappingMethod = null;
            }
        }
        return mappingMethod;
    }

    public ProcessingEnvironment processingEnv() {
        return processingEnv;
    }

    public Elements elements() {
        return elements;
    }

    public void info(Element typeElement, String templateMessage, Object... args) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, String.format(templateMessage, args), typeElement);
    }

    public void warn(ExecutableElement element, String templateMessage, Object... args) {
        //To change body of created methods use File | Settings | File Templates.
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, String.format(templateMessage, args), element);
    }


    class StackElem {
        final MappingSourceNode lastNode;
        private final SourceNodeVars vars;
        boolean child = false;

        private StackElem(MappingSourceNode lastNode, SourceNodeVars vars) {
            this.lastNode = lastNode;
            this.vars = vars;
        }

        public SourceNodeVars sourceNodeVars() {
            return vars;
        }

        public StackElem withChild(boolean isChild) {
            this.child = isChild;
            return this;
        }
    }

    public class MappingMethod {


        private final InOutType inOutType;
        private final String name;
        private boolean built;

        public MappingMethod(InOutType inOutType) {
            //To change body of created methods use File | Settings | File Templates.
            this.inOutType = inOutType;

            this.name = String.format("as%s", inOutType.outAsTypeElement().getSimpleName());
            this.built = false;
        }

        public MappingMethod(InOutType inOutType, String name) {
            this.inOutType = inOutType;
            this.name = name;
            built = true;
        }

        public String name() {
            return name;
        }

        public boolean isBuilt() {
            return built;
        }

        public void build() {
            built = true;
        }

        public String inType() {
            return inOutType.in().toString();
        }

        public String outType() {
            return inOutType.out().toString();
        }

        public TypeMirror in() {
            return inOutType.in();
        }

        public InOutType inOutType() {
            return inOutType;
        }
    }
}
