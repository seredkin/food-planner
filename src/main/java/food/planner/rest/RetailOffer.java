package food.planner.rest;

import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class RetailOffer {
    private String id;
    private DateTime created;
    private DateTime modified;
    private DateTime deleted;
    private DateTime beginDate;
    private DateTime endDate;
    private String articleName;
    private BigDecimal listPrice;
    private BigDecimal offerPrice;

    public BigDecimal getMarginPercent(){
        return BigDecimal.valueOf(100).subtract(offerPrice.divide(listPrice, RoundingMode.CEILING)
                .multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.CEILING));
    }

    public BigDecimal getMarginMoney(){
        return listPrice.subtract(offerPrice);
    }
}
