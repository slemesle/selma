package fr.xebia.extras.it;

import fr.xebia.extras.it.mappers.NotSupportedMapping;
import fr.xebia.extras.it.utils.Compile;
import fr.xebia.extras.it.utils.IntegrationTestBase;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 22/11/2013
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */
@Compile(withClasses = NotSupportedMapping.class, shouldFail = true)
@Ignore
public class NotSupportedMappingIT extends IntegrationTestBase {

    @Test
    public void not_supported_mapping_without_ignore_should_fail_compilation() throws Exception {

        assertCompilationError(NotSupportedMapping.class, "String[][] map(String[][] in);", "Failed to generate mapping method for type java.lang.String[][] to java.lang.String[][] ");
    }
}
