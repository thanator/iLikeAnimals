package ru.sberbank.mobile.common.rate.entity;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

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
public class RateTest {

    @Test
    public void testGetScaledRelation() {
        Rate rate = new Rate()
                .setCurrency(Currency.AMD)
                .setRelation(BigDecimal.valueOf(11.5741).setScale(4, BigDecimal.ROUND_HALF_UP))
                .setScale(BigDecimal.valueOf(100));
        BigDecimal expectedScaledRelation = BigDecimal.valueOf(0.115741);
        assertThat(rate.getScaledRelation(), is(expectedScaledRelation));
    }

    @Test
    public void testParseRate() throws Exception {
        Rate expected = new Rate()
                .setCurrency(Currency.AMD)
                .setRelation(BigDecimal.valueOf(11.5741))
                .setScale(BigDecimal.valueOf(100));
        Rate actual = TestUtils.parseFromRawResource(InstrumentationRegistry.getContext(),
                R.raw.test_single_rate, new SimpleXMLParser(), Rate.class);
        assertThat(actual, is(expected));
    }
}
