package train.section;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import train.specification.BuildersPlate;
import train.specification.TechnicalSpecification;
import train.specification.WagonType;

import static org.junit.jupiter.api.Assertions.*;

class WagonTest {

    static Object[] illegalArgumentException() throws Exception {
        BuildersPlate typ = new BuildersPlate("EFA", "ÖBB", 1999, "2781277");
        TechnicalSpecification tech = new TechnicalSpecification("42", "35", 13, "4527");
        return new Object[]{
                new Object[]{null, tech, WagonType.GUETERWAGEN},
                new Object[]{typ, null, WagonType.PERSONENWAGEN},
                new Object[]{typ, tech, null}
        };
    }

    @ParameterizedTest
    @MethodSource("illegalArgumentException")
    void createWagon_IllegalArgumentException(BuildersPlate buildersPlate, TechnicalSpecification technicalSpecification, WagonType wagonType) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Wagon(buildersPlate, technicalSpecification, wagonType);
        });
    }

    @Test
    void createWagon() throws Exception {
        BuildersPlate typ = new BuildersPlate("EFA", "ÖBB", 1999, "2781277");
        TechnicalSpecification tech = new TechnicalSpecification("42", "35", 13, "4527");
        Wagon wagon = new Wagon(typ, tech, WagonType.SCHLAFWAGEN);
        assertNotNull(wagon);
    }
}