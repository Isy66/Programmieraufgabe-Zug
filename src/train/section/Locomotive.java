package train.section;

import helper.Helper;
import train.specification.BuildersPlate;
import train.specification.DriveType;
import train.specification.TechnicalSpecification;

import java.math.BigDecimal;

public class Locomotive extends TrainSection {

    private DriveType driveType;
    private BigDecimal traction; //gibt an wie viel Gewicht die zug.Lokomotive, zusätzlich zu ihrem eigenen Leergewicht, ziehen kann

    public Locomotive(BuildersPlate buildersPlate, TechnicalSpecification technicalSpecification, DriveType driveType, String traction) throws Exception {
        super(buildersPlate, technicalSpecification);
        if(Helper.isNull(driveType, traction))
            throw new IllegalArgumentException ("the parameter of Locomotive cant be null");
        if(!Helper.isPositiveNumeric(traction))
            throw new NumberFormatException ("the traction cant be negative");
        this.traction = new BigDecimal(traction);
        this.driveType = driveType;
    }

    public DriveType getDriveType() {
        return driveType;
    }

    public BigDecimal getTraction() {
        return traction;
    }
}
