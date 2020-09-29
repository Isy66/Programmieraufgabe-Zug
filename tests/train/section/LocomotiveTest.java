package train.section;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import train.specification.DriveType;
import train.specification.TechnicalSpecification;
import train.specification.BuildersPlate;

import static org.junit.jupiter.api.Assertions.*;

class LocomotiveTest {

    static Object[] illegalArgumentException() throws Exception {
        BuildersPlate typ = new BuildersPlate("EFA", "ÖBB", 1999, "2781277");
        TechnicalSpecification tech = new TechnicalSpecification("42", "35", 13, "4527");
        return new Object[]{
                new Object[]{null, tech, DriveType.DAMPF, "2131"},
                new Object[]{typ, null, DriveType.ELEKTRISCH, "1313"},
                new Object[]{typ, tech, null, "1321"},
                new Object[]{typ, tech, DriveType.DIESEL, null}
        };
    }

    static Object[] numberFormatException() throws Exception {
        BuildersPlate typ = new BuildersPlate("EFA", "ÖBB", 1999, "2781277");
        TechnicalSpecification tech = new TechnicalSpecification("42", "35", 13, "4527");
        return new Object[]{
                new Object[]{typ, tech, DriveType.DAMPF, "-2131"},
                new Object[]{typ, tech, DriveType.ELEKTRISCH, "asva"},
        };
    }

    @ParameterizedTest
    @MethodSource("illegalArgumentException")
    void createLocomotive_IllegalArgumentException(BuildersPlate buildersPlate, TechnicalSpecification technicalSpecification, DriveType driveType, String zugkraft) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Locomotive(buildersPlate, technicalSpecification, driveType, zugkraft);
        });
    }

    @ParameterizedTest
    @MethodSource("numberFormatException")
    void createLocomotive_NumberFormatException(BuildersPlate buildersPlate, TechnicalSpecification technicalSpecification, DriveType driveType, String zugkraft){
        assertThrows(NumberFormatException.class, () -> {
            new Locomotive(buildersPlate, technicalSpecification, driveType, zugkraft);
        });
    }
}