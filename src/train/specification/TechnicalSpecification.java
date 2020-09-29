package train.specification;

import helper.Helper;

import java.math.BigDecimal;

public class TechnicalSpecification {
    private BigDecimal tareWeight; //Tonnen
    private BigDecimal length; //Meter
    private int maxNumberOfPassengers;
    private BigDecimal maxLoadGoods; //maximales Zuladungsgewicht für Güter

    public TechnicalSpecification(String tareWeight, String length, int maxNumberOfPassengers, String maxLoadGoods) {
        if(Helper.isNull(tareWeight, length, maxLoadGoods)){
            throw new IllegalArgumentException ("the params of TechnicalSpecification can not be null");
        }
        if(!Helper.isPositiveNumeric(tareWeight, length, maxLoadGoods) || maxNumberOfPassengers <0){
            throw new NumberFormatException ("the params of TechnicalSpecification can not a negative number");
        }
        this.tareWeight = new BigDecimal(tareWeight);
        this.length = new BigDecimal(length);
        this.maxNumberOfPassengers = maxNumberOfPassengers;
        this.maxLoadGoods = new BigDecimal(maxLoadGoods);
    }

    public BigDecimal getTareWeight() {
        return tareWeight;
    }

    public BigDecimal getLength() {
        return length;
    }

    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    public BigDecimal getMaxLoadGoods() {
        return maxLoadGoods;
    }

}
