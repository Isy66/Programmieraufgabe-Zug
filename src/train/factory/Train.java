package train.factory;

import train.section.Locomotive;
import train.section.Wagon;
import train.section.TrainSection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Represents a train and can only be initialized within the TrainFactory
 *
 * @author Ismail Inan
 */

public class Train {

    private static final int DECIMALS = 2;

    private ArrayList<TrainSection> trainSections = new ArrayList<>();

    public ArrayList<TrainSection> getTrainSections() {
        return trainSections;
    }

    protected Train(final Locomotive locomotive) {
        if(locomotive ==null)
            throw new IllegalArgumentException("locomotive can not be null");

        if(locomotive.isAssignedToTrain())
            throw new IllegalArgumentException("locomotive is assigned to another train");

        trainSections.add(locomotive);
        locomotive.setAssignedToTrain(true);
    }

    protected Train add(TrainSection trainSection){
        if(trainSections.contains(trainSection) || trainSection.isAssignedToTrain()){
            throw new IllegalArgumentException("train already contains this section");
        }
        trainSections.add(trainSection);
        trainSection.setAssignedToTrain(true);
        return this;
    }

    protected Train remove(TrainSection trainSection){
        if(!trainSections.contains(trainSection)){
            throw new IllegalArgumentException("section not found");
        }else {
            if (trainSections.indexOf(trainSection) == 0 && trainSections.size() > 1 && trainSections.get(1) instanceof Wagon) {
                throw new IllegalStateException("can not remove a locomotive when a wagon is following next");
            }
            trainSections.remove(trainSection);
            trainSection.setAssignedToTrain(false);
        }
        return this;
    }

    public BigDecimal getTareWeigth(){
        BigDecimal sum = BigDecimal.ZERO;
        for (TrainSection trainSection : trainSections) {
            sum = sum.add(trainSection.getTechnicalSpecification().getTareWeight());
        }
        return roundUp(sum);
    }

    public BigDecimal getTareWeightForLocomotives(){
        BigDecimal sum = BigDecimal.ZERO;
        for (TrainSection trainSection : trainSections) {
            if(trainSection instanceof Locomotive)
                sum = sum.add(trainSection.getTechnicalSpecification().getTareWeight());
        }
        return roundUp(sum);
    }

    public int getMaxNumberOfPassengers(){
        int sum = 0;
        for (TrainSection trainSection : trainSections) {
            sum += trainSection.getTechnicalSpecification().getMaxNumberOfPassengers();
        }
        return sum;
    }

    public BigDecimal getMaxLoadGoods(){
        BigDecimal sum = BigDecimal.ZERO;
        for (TrainSection trainSection : trainSections) {
            sum = sum.add(trainSection.getTechnicalSpecification().getMaxLoadGoods());
        }
        return roundDown(sum);
    }

    /**
     * Returns the weight of the passengers plus goods
     * MaxLoad = MaxNumberOfPassengers * 75 + maxLoad
     * @return the maxLoad
     */
    public BigDecimal getMaxLoad(){
        BigDecimal sum = new BigDecimal(getMaxNumberOfPassengers()*75).add(getMaxLoadGoods());
        return roundDown(sum);
    }

    public BigDecimal getMaxWeight(){
        return getTareWeigth().add(getMaxLoad());
    }

    public BigDecimal getLength(){
        BigDecimal sum = BigDecimal.ZERO;
        for (TrainSection trainSection : trainSections) {
            sum = sum.add(trainSection.getTechnicalSpecification().getLength());
        }
        return roundUp(sum);
    }

    public BigDecimal getMaxTraction(){
        BigDecimal sum = BigDecimal.ZERO;
        for (TrainSection trainSection : trainSections) {
            if(trainSection instanceof Locomotive)
                sum = sum.add(((Locomotive) trainSection).getTraction());
        }
        return roundDown(sum);
    }

    /**
     * Tests whether the train is driveable with the maxWeight.
     * @return true if MaxTraction >= (MaxWeight - TareWeightForLocomotives)
     */
    public boolean isTrainDrivable(){
        if(getMaxWeight().subtract(getTareWeightForLocomotives()).compareTo(getMaxTraction()) > 0)
            return false;
        return true;
    }

    /**
     * One conductor for at least 1 and max 50 passengers
     * @return the max number of conductor
     */
    public int getMaxConductor(){
        return (int) Math.ceil((double) getMaxNumberOfPassengers()/50);
    }

    protected void delete() {
        for (TrainSection trainSection : trainSections) {
            trainSection.setAssignedToTrain(false);
        }
        trainSections = new ArrayList<>();
    }

    private BigDecimal roundUp(BigDecimal bigDecimal){
        return bigDecimal.setScale(DECIMALS, RoundingMode.UP);
    }

    private BigDecimal roundDown(BigDecimal bigDecimal){
        return bigDecimal.setScale(DECIMALS, RoundingMode.DOWN);
    }
}
