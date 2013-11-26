package fr.xebia.extras.it.utils;

import fr.xebia.extras.codegen.MapperProcessor;
import fr.xebia.extras.it.mappers.BadMapperSignature;
import fr.xebia.extras.it.mappers.MissingPropertyMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 */
@Compile(withPackage = {"fr.xebia.extras.beans"})
public class IntegrationTestBase {
   private  String SRC_DIR;
   private  String OUT_DIR;
   private  String GEN_DIR;
   private  List<File> classPath;
   private  List<File> classes;

    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private String TARGET_DIR;

    private DiagnosticCollector<JavaFileObject> diagnostics;
    private boolean compilationResult;



    @Before
    public void setup() throws Exception {
        TestCompiler.getInstance().compileFor(getClass());
        TestCompiler.getInstance().compileFor(getClass()).assertCompilation();
    }



    protected boolean compilationSuccess() throws Exception {
        return TestCompiler.getInstance().compileFor(getClass()).compilationSuccess();
    }


    protected DiagnosticCollector<JavaFileObject> getDiagnostics() throws Exception {

        return TestCompiler.getInstance().compileFor(getClass()).diagnostics();
    }

    protected void assertCompilationError(Class<?> aClass,String signature, String message) throws Exception {
        assertCompilationKind( Diagnostic.Kind.ERROR, aClass, signature, message);
    }

    protected void assertCompilationWarning(Class<?> aClass,String signature, String message) throws Exception {
        assertCompilationKind( Diagnostic.Kind.WARNING, aClass, signature, message);
    }


    protected void assertCompilationKind(Diagnostic.Kind kind, Class<?> aClass,String signature, String message) throws Exception {
        DiagnosticCollector<JavaFileObject> dc = getDiagnostics();

        dc.getDiagnostics();
        Diagnostic<? extends JavaFileObject> res = null;
        for (Diagnostic<? extends JavaFileObject> diagnostic : dc.getDiagnostics()) {

            if (diagnostic.getKind() == kind){
                if (diagnostic.getSource() != null && diagnostic.getSource().getName().contains(aClass.getSimpleName())){


                    String srcLine = diagnostic.toString();


                    if (srcLine.contains(signature)){
                        if (res == null){
                            res = diagnostic;
                        }  else {
                            org.junit.Assert.fail("Only one error for method is expected");
                        }
                    }


                }
            }
        }

        org.junit.Assert.assertNotNull(res);
        org.junit.Assert.assertThat(res.getMessage(null), JUnitMatchers.containsString(message));

    }

}
