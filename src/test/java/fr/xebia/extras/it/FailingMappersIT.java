package fr.xebia.extras.it;

import fr.xebia.extras.beans.PersonIn;
import fr.xebia.extras.it.mappers.MissingPropertyMapper;
import fr.xebia.extras.it.utils.Compile;
import fr.xebia.extras.it.utils.IntegrationTestBase;
import org.junit.Test;

/**
 *
 */
@Compile(withClasses = MissingPropertyMapper.class, shouldFail = true)
public class FailingMappersIT extends IntegrationTestBase {



    @Test
    public void compilation_should_fail_on_missing_property_without_ignore() throws Exception {

        assertCompilationError(PersonIn.class, "private boolean male;", "from in bean is missing in destination bean");

    }



}
