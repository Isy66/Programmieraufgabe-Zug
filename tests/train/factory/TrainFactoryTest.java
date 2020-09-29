package train.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import train.section.Locomotive;
import train.section.Wagon;
import train.specification.BuildersPlate;
import train.specification.DriveType;
import train.specification.TechnicalSpecification;
import train.specification.WagonType;

import javax.naming.NameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TrainFactoryTest {

    Locomotive locomotive;
    Wagon wagon;

    @BeforeEach
    void setup() throws Exception {
        BuildersPlate buildersPlate = new BuildersPlate("65 CH","Siemens",1965,"45151465");
        TechnicalSpecification technicalSpecification = new TechnicalSpecification("15.7", "15.7",15 , "15.7");
        locomotive = new Locomotive(buildersPlate, technicalSpecification, DriveType.DAMPF, "15.7");

        buildersPlate = new BuildersPlate("65 CH","Siemens",1965,"45151465");
        technicalSpecification = new TechnicalSpecification("15.7", "15.7",15 , "15.7");
        wagon = new Wagon(buildersPlate, technicalSpecification, WagonType.PERSONENWAGEN);

    }

    @Test
    void getInstance() {
        TrainFactory factory = TrainFactory.getInstance();
        TrainFactory factory1 = TrainFactory.getInstance();
        Assertions.assertEquals(factory, factory1);
    }

    @Test
    void createTrain() {
        TrainFactory factory = TrainFactory.getInstance();
        Train train = factory.createTrain("train4", locomotive);
        assertNotNull(train);
    }

    @Test
    void deleteTrain() throws Exception {
        TrainFactory factory = TrainFactory.getInstance();
        Train train = factory.createTrain("train4", locomotive);
        factory.deleteTrain("train4");
    }

    @Test
    void deleteTrain_ExceptionCheck() throws Exception {
        TrainFactory factory = TrainFactory.getInstance();

        assertThrows(NameNotFoundException.class, () -> {
            factory.deleteTrain("trainDoesNotExist");
        });
    }

    @Test
    void getTrain() throws NameNotFoundException {
        TrainFactory factory = TrainFactory.getInstance();
        factory.createTrain("train4", locomotive);
        Train train = factory.getTrain("train4");
        assertNotNull(train);
    }

    @Test
    void getTrain_ExceptionCheck() throws NameNotFoundException {
        TrainFactory factory = TrainFactory.getInstance();
        factory.createTrain("train4", locomotive);

        assertThrows(Exception.class, () -> {
            factory.getTrain("train45161");
        });
    }

    @Test
    void addToTrain() throws NameNotFoundException {
        TrainFactory factory = TrainFactory.getInstance();
        Train train = factory.createTrain("train4", locomotive);
        factory.addToTrain("train4", wagon);
        assertTrue(train.getTrainSections().contains(wagon));
    }

    @Test
    void addToTrain_NullCheck() {
        TrainFactory factory = TrainFactory.getInstance();
        Train train = factory.createTrain("train4", locomotive);

        assertThrows(Exception.class, () -> {
            factory.addToTrain("train4", null);
        });
    }

    @Test
    void addToTrain_SameObjectTwice() throws NameNotFoundException {
        TrainFactory factory = TrainFactory.getInstance();
        Train train = factory.createTrain("train4", locomotive);
        factory.addToTrain("train4", wagon);

        assertThrows(Exception.class, () -> {
            factory.addToTrain("train4", wagon);
        });
    }

    @Test
    void removeFromTrain() throws NameNotFoundException {
        TrainFactory factory = TrainFactory.getInstance();
        Train train = factory.createTrain("train4", locomotive);
        factory.addToTrain("train4", wagon);
        factory.removeFromTrain("train4", wagon);
        assertFalse(train.getTrainSections().contains(wagon));
    }

    @Test
    void removeFromTrain_NullCheck() {
        TrainFactory factory = TrainFactory.getInstance();
        Train train = factory.createTrain("train4", locomotive);

        assertThrows(Exception.class, () -> {
            factory.removeFromTrain("train4", null);
        });
    }

    @Test
    void removeFromTrain_DoesNotExist() {
        TrainFactory factory = TrainFactory.getInstance();
        Train train = factory.createTrain("train4", locomotive);

        assertThrows(Exception.class, () -> {
            factory.removeFromTrain("train4", wagon);
        });
    }
}