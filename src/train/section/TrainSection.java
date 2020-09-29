package train.section;

import train.specification.BuildersPlate;
import train.specification.TechnicalSpecification;

public abstract class TrainSection {

    private final BuildersPlate buildersPlate;

    private TechnicalSpecification technicalSpecification;

    private boolean isAssignedToTrain;

    public TrainSection(BuildersPlate buildersPlate, TechnicalSpecification technicalSpecification) throws Exception {
        if(buildersPlate ==null || technicalSpecification ==null){
            throw new IllegalArgumentException ("buildersPlate and technicalSpecification can not be null");
        }
        this.technicalSpecification = technicalSpecification;
        this.buildersPlate = buildersPlate;
    }

    public BuildersPlate getBuildersPlate() {
        return buildersPlate;
    }

    public TechnicalSpecification getTechnicalSpecification() {
        return technicalSpecification;
    }

    public boolean isAssignedToTrain() {
        return isAssignedToTrain;
    }

    public void setAssignedToTrain(boolean assignedToTrain) {
        isAssignedToTrain = assignedToTrain;
    }
}
