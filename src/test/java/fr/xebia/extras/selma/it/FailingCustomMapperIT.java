package fr.xebia.extras.selma.it;

import fr.xebia.extras.selma.it.mappers.BadCustomMapper;
import fr.xebia.extras.selma.it.mappers.EmptyCustomMapper;
import fr.xebia.extras.selma.it.mappers.FailingCustomMapper;
import fr.xebia.extras.selma.it.utils.Compile;
import fr.xebia.extras.selma.it.utils.IntegrationTestBase;
import fr.xebia.extras.selma.Factory;
import org.junit.Test;

/**
 * Verifies compilation time validation for CustomMapping
 */
@Compile(withClasses = {FailingCustomMapper.class, BadCustomMapper.class, EmptyCustomMapper.class}, shouldFail = true)
public class FailingCustomMapperIT extends IntegrationTestBase {

    @Test
    public void should_raise_compilation_error_when_no_valid_mapping_method_found() throws Exception {

        assertCompilationError(EmptyCustomMapper.class, "public class EmptyCustomMapper {", "No valid mapping method found in custom selma class ");

    }

    @Test
    public void should_raise_warning_for_not_public_static_valid_method() throws Exception {

        assertCompilationError(BadCustomMapper.class, "public class BadCustomMapper {", "No valid mapping method found in custom selma class ");
        assertCompilationWarning(BadCustomMapper.class, "CityOut notPublicNotStaticMethod(CityIn in, Factory factory){", "Custom mapping method should be *public static* (Fix modifiers of the method) on notPublicNotStaticMethod");

    }

    @Test
    public void should_raise_warning_for_void_method() throws Exception {

        assertCompilationError(BadCustomMapper.class, "public class BadCustomMapper {", "No valid mapping method found in custom selma class ");
        assertCompilationWarning(BadCustomMapper.class, "void voidMethod(CityIn in, Factory factory){", "Custom mapping method can not be void (Add the targeted return type) on voidMethod");
    }

    @Test
    public void should_raise_warning_for_method_not_having_2_parameters() throws Exception {

        assertCompilationError(BadCustomMapper.class, "public class BadCustomMapper {", "No valid mapping method found in custom selma class ");
        assertCompilationWarning(BadCustomMapper.class, "public static CityOut noParameterMethod(){", "Custom mapping method should take two parameters but there is 0 on noParameterMethod");
        assertCompilationWarning(BadCustomMapper.class, "public static CityOut oneParameterMethod( CityIn in){", "Custom mapping method should take two parameters but there is 1 on oneParameterMethod");
        assertCompilationWarning(BadCustomMapper.class, "public static CityOut threeParameterMethod( CityIn in, CityIn in2, CityIn in3){", "Custom mapping method should take two parameters but there is 3 on threeParameterMethod");
    }

    @Test
    public void should_raise_warning_for_method_having_second_parameter_not_a_factory() throws Exception {

        assertCompilationError(BadCustomMapper.class, "public class BadCustomMapper {", "No valid mapping method found in custom selma class ");
        assertCompilationWarning(BadCustomMapper.class, "public static CityOut withoutFactoryMethod( CityIn in, CityIn in2){", String.format("Custom mapping method second parameter should be of type %s on withoutFactoryMethod", Factory.class.getName()));
    }

}
