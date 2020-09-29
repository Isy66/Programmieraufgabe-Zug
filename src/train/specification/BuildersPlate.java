package train.specification;

import helper.Helper;

public class BuildersPlate {
    private final String typeDesignation;
    private final String manufacturer;
    private final int constructionYear;
    private final String serialNumber;

    public BuildersPlate(String typeDesignation, String manufacturer, int constructionYear, String serialNumber) throws Exception {
        if(Helper.isNull(typeDesignation, manufacturer, serialNumber) || constructionYear <0){
            throw new IllegalArgumentException ("the params of BuildersPlate can not be null or a negative number");
        }
        this.typeDesignation = typeDesignation;
        this.manufacturer = manufacturer;
        this.constructionYear = constructionYear;
        this.serialNumber = serialNumber;
    }

    public String getTypeDesignation() {
        return typeDesignation;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
