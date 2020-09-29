package train.specification;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class BuildersPlateTest {

    static Object[] TypenschildParameter() {
        return new Object[]{
                new Object[]{null, "vzv", 612, "vzvav"},
                new Object[]{"kmadsv", null, 468, "vzascv"},
                new Object[]{"kasvm", "kvadm", -162, "sv"},
                new Object[]{"kasvm", "kvadm", 468, null}
        };
    }

    @ParameterizedTest
    @MethodSource("TypenschildParameter")
    void TestCreateTypenschild_NullParameter_ShouldThrowException(String typenbezeichnung, String hersteller, int baujahr, String seriennummer) {
        assertThrows(IllegalArgumentException.class, () -> {
            new BuildersPlate(typenbezeichnung, hersteller, baujahr, seriennummer);
        });
    }

}