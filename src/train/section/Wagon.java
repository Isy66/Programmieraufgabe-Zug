package train.section;

import helper.Helper;
import train.specification.BuildersPlate;
import train.specification.TechnicalSpecification;
import train.specification.WagonType;

public class Wagon extends TrainSection {
    private WagonType wagonType;

    public Wagon(BuildersPlate buildersPlate, TechnicalSpecification technicalSpecification, WagonType wagonType) throws Exception {
        super(buildersPlate, technicalSpecification);
        if(wagonType==null)
            throw new IllegalArgumentException ("wagonType cant be null");
        this.wagonType = wagonType;
    }
}
