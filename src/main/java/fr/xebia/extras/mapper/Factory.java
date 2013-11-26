package fr.xebia.extras.mapper;

/**
 *
 *
 */
public interface Factory {

    /**
     * This method is intended to return a new instance of the targetted bean
     * @param aClass
     * @param <T>
     * @return
     */
   <T> T newInstance(Class<T> aClass);

}
