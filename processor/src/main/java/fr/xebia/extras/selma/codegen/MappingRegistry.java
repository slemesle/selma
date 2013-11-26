package fr.xebia.extras.selma.codegen;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MappingRegistry {

    final Map<InOutType, MappingBuilder> registryMap;
    final MapperGeneratorContext context;


    public MappingRegistry(MapperGeneratorContext context) {
        this.registryMap = new HashMap<InOutType, MappingBuilder>();
        this.context = context;
    }

    public MappingBuilder findMappingFor(InOutType inOutType) {

        MappingBuilder res;
        // First look in registry
        if (registryMap.get(inOutType) != null) {
            res = registryMap.get(inOutType);
        } else { // look in chain

            res = MappingBuilder.getBuilderFor(context, inOutType);
            if (res != null && !inOutType.areDeclared()) {
                registryMap.put(inOutType, res);
            }

        }

        return res;
    }

    /**
     * Adds a custom mapping method to the registry for later use at codegen.
     *
     * @param customMapper
     * @param method
     */
    public void pushCustomMapper(String customMapper, MethodWrapper method) {


        InOutType inOutType = method.inOutType();
        MappingBuilder res = MappingBuilder.newCustomMapper(inOutType, String.format("%s.%s", customMapper, method.getSimpleName()));

        registryMap.put(inOutType, res);
    }
}
