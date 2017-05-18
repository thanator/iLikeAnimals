package ru.sberbank.mobile.common.rate.entity;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import ru.sberbank.mobile.core.bean.money.Currency;
import ru.sberbank.mobile.core.parser.SimpleXMLParser;
import ru.sberbank.mobile.core.utils.TestUtils;
import ru.sberbank.backgroundtaskssample.test.R;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author QuickNick.
 */
@RunWith(AndroidJUnit4.class)
public class RatesBundleTest {

    @Test
    public void testParse() throws Exception {
        RatesBundle expected = createExpected();
        RatesBundle actual = TestUtils.parseFromRawResource(InstrumentationRegistry.getContext(), R.raw.test_rates_bundle,
                new SimpleXMLParser(), RatesBundle.class);
        assertThat(actual, is(expected));
    }

    private static RatesBundle createExpected() {
        RatesBundle bundle = new RatesBundle();
        List<Rate> list = Arrays.asList(
                new Rate().setCurrency(Currency.GBP).setRelation(BigDecimal.valueOf(69.7605))
                    .setScale(BigDecimal.ONE),
                new Rate().setCurrency(Currency.HUF).setRelation(BigDecimal.valueOf(19.3803))
                    .setScale(BigDecimal.valueOf(100))
        );
        bundle.setRates(list);
        return bundle;
    }
}
