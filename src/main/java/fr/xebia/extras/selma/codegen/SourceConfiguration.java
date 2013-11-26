package fr.xebia.extras.selma.codegen;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 21/11/2013
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public class SourceConfiguration {


    private boolean ignoreMissingProperties;
    private boolean ignoreNotSupported;

    private SourceConfiguration(){}

    public static SourceConfiguration buildFrom(AnnotationWrapper annotationWrapper) {
        SourceConfiguration res = new SourceConfiguration();

        res.ignoreMissingProperties(annotationWrapper.getAsBoolean("ignoreMissingProperties"));
        res.ignoreNotSupported(annotationWrapper.getAsBoolean("ignoreNotSupported"));
        return res;
    }

    private SourceConfiguration ignoreNotSupported(boolean b) {
        this.ignoreNotSupported = b;
        return this;
    }

    private SourceConfiguration ignoreMissingProperties(boolean b) {
        this.ignoreMissingProperties = b;
        return this;
    }

    public boolean isIgnoreMissingProperties() {
        return ignoreMissingProperties;
    }

    public boolean isIgnoreNotSupported() {
        return ignoreNotSupported;
    }

}
