package food.planner.data;

import food.planner.rest.RetailOffer;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MockDataSource {

    private final Random r = new Random();
    public Page<RetailOffer> currentOffers(){
        String[] food = {"Ice Cream", "Carrot", "Banana", "Beef", "Yogurt"};
        return new PageImpl<>(Stream.of(food).map(this::mockOffer)
                .sorted(this::marginDescCompare).collect(Collectors.toList()));
    }

    private int marginDescCompare(RetailOffer retailOffer, RetailOffer retailOffer1) {
        return retailOffer1.getMarginMoney().compareTo(retailOffer.getMarginMoney());
    }

    private RetailOffer mockOffer(String name){
        RetailOffer offer = new RetailOffer();
        offer.setId(UUID.randomUUID().toString());
        offer.setArticleName(name);
        final DateTime now = new DateTime();
        offer.setCreated(now.minusDays(r.nextInt(10)+4));
        offer.setBeginDate(new DateTime(offer.getCreated()).plusDays(r.nextInt(4)).plusMinutes(600));
        offer.setEndDate(now.plusDays(r.nextInt(10)+4).plusMinutes(600));
        offer.setListPrice(new BigDecimal(r.nextInt(25))
                .add(BigDecimal.valueOf(r.nextFloat() % 1))
                .setScale(2, RoundingMode.CEILING));
        offer.setOfferPrice(offer.getListPrice()
                .multiply(BigDecimal.valueOf(r.nextFloat()%1)).setScale(2, RoundingMode.CEILING));
        return offer;
    }
}
