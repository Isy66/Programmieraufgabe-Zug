package helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HelperTest {

    @Test
    void isNumeric() {
        Assertions.assertTrue(Helper.isNumeric("15"));
        Assertions.assertFalse(Helper.isNumeric("asdc"));

        Assertions.assertTrue(Helper.isNumeric("15", "42"));
        Assertions.assertFalse(Helper.isNumeric("asdc", "-15"));
    }

    @Test
    void isPositiveNumeric() {
        Assertions.assertTrue(Helper.isPositiveNumeric("15"));
        Assertions.assertFalse(Helper.isPositiveNumeric("asdf"));
        Assertions.assertFalse(Helper.isPositiveNumeric("-15"));

        Assertions.assertTrue(Helper.isPositiveNumeric("15", "6512"));
        Assertions.assertFalse(Helper.isPositiveNumeric("asdf", "iubin"));
        Assertions.assertFalse(Helper.isPositiveNumeric("-15", "ouibo", "15"));
    }

    static Object isNull_TestParameter() {
        return new Object[]{
                new Object[]{"sdv"},
                new Object[]{"42"},
                new Object[]{13},
                new Object[]{new Object()},
                new Object[]{'a', 561, "sg"}
        };
    }

    @ParameterizedTest
    @MethodSource("isNull_TestParameter")
    void isNull_False(Object obj) {
        Assertions.assertFalse(Helper.isNull(obj));
    }

    @Test
    void isNull_True() {
        Assertions.assertTrue(Helper.isNull(null));
        Assertions.assertTrue(Helper.isNull("ljn", null, 14));
    }
}