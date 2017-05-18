package ru.sberbank.mobile.common.rate;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import ru.sberbank.mobile.common.rate.entity.Rate;
import ru.sberbank.mobile.core.bean.money.Currency;
import ru.sberbank.mobile.core.format.DecimalFormatter;

/**
 * @author QuickNick
 */
@RunWith(AndroidJUnit4.class)
public class ConvertUtilsTest {

    @Test
    public void testConvertFromEuroToUsd() {
        BigDecimal sourceAmount = BigDecimal.valueOf(5);
        Rate euro = createEuroRate();
        Rate usd = createUsdRate();
        BigDecimal targetAmount = ConverterUtils.convert(sourceAmount, euro, usd);
        BigDecimal expectedAmount = BigDecimal.valueOf(6).setScale(DecimalFormatter.DEFAULT_SCALE);
        assertThat(targetAmount, is(expectedAmount));
    }

    private static Rate createEuroRate() {
        Rate rate = new Rate().setCurrency(Currency.EUR)
                .setRelation(BigDecimal.valueOf(60))
                .setScale(java.math.BigDecimal.ONE);
        return rate;
    }

    private static Rate createUsdRate() {
        Rate rate = new Rate().setCurrency(Currency.USD)
                .setRelation(BigDecimal.valueOf(50))
                .setScale(java.math.BigDecimal.ONE);
        return rate;
    }
}
