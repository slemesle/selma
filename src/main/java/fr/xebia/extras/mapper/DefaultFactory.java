package fr.xebia.extras.mapper;

/**
 * This is the default factory that creates bean using the default constructor
 */
public class DefaultFactory implements Factory {

    @Override
     public <T> T newInstance(Class<T> aClass) {
        try {
            return aClass.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(String.format("Unable to instantiate class %s with default no args constructor (Constructor does not exist ADD DEFAULT CONSTRUCTOR !)", aClass.getName()), e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(String.format("Unable to instantiate class %s with default no args constructor (Constructor is not accessible FIX MODIFIERS !)", aClass.getName()), e);
        }
    }
}
