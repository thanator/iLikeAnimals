package ru.sberbank.mobile.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ru.sberbank.mobile.common.rate.entity.Rate;
import ru.sberbank.mobile.common.rate.entity.RatesBundle;
import ru.sberbank.mobile.core.bean.money.Currency;

/**
 * @author QuickNick
 */
public class CoreEntitiesGenerator extends BaseEntitiesGenerator {

    public static Rate createRate() {
        Rate rate = new Rate()
                .setCurrency(createEnumValue(Currency.values()))
                .setRelation(new BigDecimal(RANDOM.nextDouble()))
                .setScale(new BigDecimal(RANDOM.nextInt()));
        return rate;
    }

    public static RatesBundle createRatesBundle() {
        RatesBundle bundle = new RatesBundle();
        int size = RANDOM.nextInt(MAX_TEST_LIST_SIZE) + 1;
        List<Rate> rates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            rates.add(createRate());
        }
        bundle.setRates(rates);
        return bundle;
    }
}
