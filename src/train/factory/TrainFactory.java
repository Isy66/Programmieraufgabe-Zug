package train.factory;

import helper.Helper;
import train.section.Locomotive;
import train.section.TrainSection;

import javax.naming.NameNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * TrainFactory for creating, deleting and changing sections of trains
 *
 * @author Ismail Inan
 */

public class TrainFactory {

    private static TrainFactory instance;

    private Map<String, Train> trains = new HashMap<>();

    private TrainFactory() {
    }

    public static TrainFactory getInstance() {
        if(instance==null)
            instance = new TrainFactory();
        return instance;
    }

    public Train createTrain(String name, Locomotive locomotive){
        if(Helper.isNull(name, locomotive) || name.isEmpty()){
            throw new IllegalArgumentException("parameter cant be null or empty");
        }
        Train train = new Train(locomotive);
        trains.put(name, train);
        return train;
    }

    public void deleteTrain(String name) throws NameNotFoundException {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException("name cant be null or empty");
        }
        if(!trains.containsKey(name)){
            throw new NameNotFoundException("Train does not exist.");
        }
        trains.get(name).delete();
    }

    public Train getTrain(String name) throws NameNotFoundException {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException("name cant be null or empty");
        }
        if(!trains.containsKey(name)){
            throw new NameNotFoundException("Train does not exist.");
        }
        return trains.get(name);
    }

    public void addToTrain(String trainName, TrainSection trainSection) throws NameNotFoundException {
        if(Helper.isNull(trainName, trainSection) || trainName.isEmpty()){
            throw new IllegalArgumentException("name cant be null or empty");
        }
        if(!trains.containsKey(trainName)){
            throw new NameNotFoundException("Train does not exist.");
        }
        trains.get(trainName).add(trainSection);
    }

    public void removeFromTrain(String trainName, TrainSection trainSection) throws NameNotFoundException {
        if(Helper.isNull(trainName, trainSection)){
            throw new IllegalArgumentException("name cant be null or empty");
        }
        if(!trains.containsKey(trainName)){
            throw new NameNotFoundException("Train does not exist.");
        }
        trains.get(trainName).remove(trainSection);
        if(trains.get(trainName).getTrainSections().size()==0){
            trains.remove(trainName);
        }
    }

    /**
     * Returns the toString() method of the trains-HashMap
     * @return trains.toString()
     */
    public String getAllTrains() {
        return trains.toString();
    }
}
