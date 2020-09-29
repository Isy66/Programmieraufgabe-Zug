package train.factory;

import org.junit.jupiter.api.*;
import train.section.Locomotive;
import train.section.Wagon;
import train.specification.BuildersPlate;
import train.specification.DriveType;
import train.specification.TechnicalSpecification;
import train.specification.WagonType;

import javax.naming.NameNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainTest {

    TrainFactory factory;
    Locomotive locomotive1;
    Locomotive locomotive2;
    Wagon wagon1;
    Wagon wagon2;
    Train train4;
    String[] leergewicht = {"112.75", "90.8", "198.6", "145"};
    String[] laenge = {"19.8", "20.64", "12.6", "24"};
    String[] zuladungsgewicht = {"15.7", "23.5", "25", "10.42"};
    String[] zugkraft = {"2600.86", "1850"};
    int[] passagiere = {8,15,7,20};

    @BeforeEach
    void setup() throws Exception {
        BuildersPlate buildersPlate = new BuildersPlate("65 CH","Siemens",1965,"45151465");
        TechnicalSpecification technicalSpecification = new TechnicalSpecification(leergewicht[0], laenge[0],passagiere[0], zuladungsgewicht[0]);
        locomotive1 = new Locomotive(buildersPlate, technicalSpecification, DriveType.DAMPF, zugkraft[0]);

        buildersPlate = new BuildersPlate("454E","Siemens",1980,"2367273");
        technicalSpecification = new TechnicalSpecification(leergewicht[1], laenge[1], passagiere[1], zuladungsgewicht[1]);
        wagon1 = new Wagon(buildersPlate, technicalSpecification, WagonType.PERSONENWAGEN);

        buildersPlate = new BuildersPlate("192S","ÖBB",2004,"2157511");
        technicalSpecification = new TechnicalSpecification(leergewicht[2], laenge[2], passagiere[2], zuladungsgewicht[2]);
        locomotive2 = new Locomotive(buildersPlate, technicalSpecification, DriveType.DIESEL, zugkraft[1]);

        buildersPlate = new BuildersPlate("5CS5","ÖBB",2000,"7857528");
        technicalSpecification = new TechnicalSpecification(leergewicht[3], laenge[3], passagiere[3], zuladungsgewicht[3]);
        wagon2 = new Wagon(buildersPlate, technicalSpecification, WagonType.GUETERWAGEN);

        factory = TrainFactory.getInstance();
        train4 = factory.createTrain("train4", locomotive1);
        train4.getMaxLoadGoods();
        factory.addToTrain("train4", wagon1);
        factory.addToTrain("train4", locomotive2);
        factory.addToTrain("train4", wagon2);
    }

    @Test
    void zug_ZusammenstellungAendern() throws NameNotFoundException {
        Assertions.assertEquals(4, train4.getTrainSections().size());

        factory.removeFromTrain("train4", wagon1);
        factory.removeFromTrain("train4", locomotive1);

        Train train = factory.createTrain("train", locomotive1);
        Assertions.assertEquals(1, train.getTrainSections().size());

        factory.removeFromTrain("train4", wagon2);
        factory.addToTrain("train", wagon1);
        factory.addToTrain("train", wagon2);

        Assertions.assertEquals(1, train4.getTrainSections().size());
        Assertions.assertEquals(3, train.getTrainSections().size());
    }

    @Test
    void zugConstructor_ThrowError() {
        assertThrows(IllegalArgumentException.class, () -> {
            Train train1 = factory.createTrain("train", null);
        });
    }

    @Test
    void remove_ShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () ->{
            Train train = factory.createTrain("train", locomotive1);
            factory.removeFromTrain("train", locomotive2);
        });
    }

    @Test
    void getLeergewicht() {
        Assertions.assertEquals(sumOfStrings(leergewicht), train4.getTareWeigth());
    }

    @Test
    void getLeergewichtLokomotive() {
        Assertions.assertEquals(sumOfStrings(leergewicht[0], leergewicht[2]), train4.getTareWeightForLocomotives());
    }

    @Test
    void getMaxAnzahlPassagiere() {
        Assertions.assertEquals(Arrays.stream(passagiere).sum(), train4.getMaxNumberOfPassengers());
    }

    @Test
    void getMaxZuladunsgewicht() {
        Assertions.assertEquals(sumOfStrings(zuladungsgewicht), train4.getMaxLoadGoods());
    }

    @Test
    void getMaxZuladung() {
        //maximale Anzahl der Passagiere im zug.Zug x 75kg + maximales Zuladungsgewicht für Güter
        int p = Arrays.stream(passagiere).sum()*75;
        Assertions.assertEquals(sumOfStrings(sumOfStrings(zuladungsgewicht).toString(), ""+p), train4.getMaxLoad());
    }

    @Test
    void getMaxGewicht() {
        int p = Arrays.stream(passagiere).sum()*75;
        Assertions.assertEquals(sumOfStrings(sumOfStrings(zuladungsgewicht).toString(), sumOfStrings(leergewicht).toString(), ""+ p), train4.getMaxWeight());
    }

    @Test
    void getLaenge() {
        Assertions.assertEquals(sumOfStrings(laenge), train4.getLength());
    }

    @Test
    void getMaxZugkraft() {
        Assertions.assertEquals(sumOfStrings(zugkraft), train4.getMaxTraction());
    }

    @Test
    void isTrainDrivable_ShouldBeTrue() {
        int p = Arrays.stream(passagiere).sum()*75;
        BigDecimal maxGewicht = sumOfStrings(sumOfStrings(zuladungsgewicht).toString(), sumOfStrings(leergewicht).toString(), ""+ p);
        Assertions.assertTrue(maxGewicht.subtract(sumOfStrings(leergewicht[0], leergewicht[2])).compareTo(sumOfStrings(zugkraft)) <= 0);
    }

    @Test
    void getMaxSchaffner() {
        int extra = 0;
        if(Arrays.stream(passagiere).sum()%50>0){
            extra = 1;
        }
        Assertions.assertEquals(Arrays.stream(passagiere).sum()/50 + extra, train4.getMaxConductor());
    }

    private BigDecimal sumOfStrings(String... values){
        final BigDecimal[] sum = {BigDecimal.ZERO};
        List<BigDecimal> list = new ArrayList<>();
        Arrays.stream(values).forEach(s -> list.add(sum[0] = sum[0].add(new BigDecimal(s))));
        return sum[0];
    }

}