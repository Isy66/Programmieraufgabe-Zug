package train.specification;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class TechnicalSpecificationTest {

    static Object[] illegalArgumentException() {
       return new Object[]{
                new Object[]{null, "35", 13, "4527"},
                new Object[]{"42", null, 13, "4527"},
               new Object[]{"42", "35", -13, "4527"},
                new Object[]{"42", "35", Integer.MAX_VALUE+1, "4527"},
                new Object[]{"42", "35", 13, null}
        };
    }

    static Object[] numberFormatException() {
       return new Object[]{
                new Object[]{"-42", "35", 13, "4527"},
                new Object[]{"42", "-35", 13, "4527"},
                new Object[]{"42", "35", 0, "-4527"},
               new Object[]{"sdv", "35", 0, "4527"},
               new Object[]{"42", "sdv", 13, "4527"},
               new Object[]{"42", "35", 13, "sdv"}
        };
    }

    @ParameterizedTest
    @MethodSource("illegalArgumentException")
    void TestCreateLokomotive_IllegalArgumentException(String leergewicht, String laenge, int maxAnzahlPassagiere, String maxZuladungsgewicht) {
        assertThrows(IllegalArgumentException.class, () -> {
            new TechnicalSpecification(leergewicht, laenge, maxAnzahlPassagiere, maxZuladungsgewicht);
        });
    }

    @ParameterizedTest
    @MethodSource("numberFormatException")
    void TestCreateLokomotive_NumberFormatException(String leergewicht, String laenge, int maxAnzahlPassagiere, String maxZuladungsgewicht) {
        assertThrows(NumberFormatException.class, () -> {
            new TechnicalSpecification(leergewicht, laenge, maxAnzahlPassagiere, maxZuladungsgewicht);
        });
    }
}