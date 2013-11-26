package fr.xebia.extras.selma.it.mappers;

import fr.xebia.extras.selma.it.utils.Compile;
import fr.xebia.extras.selma.it.utils.IntegrationTestBase;
import org.junit.Test;

/**
 *
 */
@Compile(withClasses = BadMapperSignature.class, shouldFail= true)
public class BadMapperSignatureIT extends IntegrationTestBase {

    @Test
    public void bad_mapper_signature_compilation_should_fail_on_2_in_parameters() throws Exception {

        assertCompilationError(BadMapperSignature.class,"String mapTwoParameters (String in, String inBis);", "@Mapper method mapTwoParameters can not have more than one parameter");

    }

    @Test
    public void bad_mapper_signature_compilation_should_fail_on_parameters_type_differs() throws Exception {

        assertCompilationError(BadMapperSignature.class,"String mapDifferentTypes (boolean in);", "differs and this kind of conversion is not supported here");

    }


}
