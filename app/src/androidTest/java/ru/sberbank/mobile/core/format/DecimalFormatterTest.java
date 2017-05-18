package ru.sberbank.mobile.core.format;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ru.sberbank.mobile.core.utils.LocaleUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class DecimalFormatterTest {

    @Test
    public void testParseBigDecimal() {
        BigDecimal expected = new BigDecimal(-100500.1260).setScale(3, RoundingMode.HALF_DOWN);

        BigDecimal actual = DecimalFormatter.parseBigDecimal("-100 500,126", LocaleUtils.getRussianLocale());
        assertThat(actual, is(expected));

        actual = DecimalFormatter.parseBigDecimal("-100,500.126", LocaleUtils.getEnglishLocale());
        assertThat(actual, is(expected));

        actual = DecimalFormatter.parseBigDecimal("-1+00 ,50*0.1/26", LocaleUtils.getEnglishLocale());
        assertThat(actual, is(expected));

        expected = expected.abs();

        actual = DecimalFormatter.parseBigDecimal("100 500,126", LocaleUtils.getRussianLocale());
        assertThat(actual, is(expected));

        actual = DecimalFormatter.parseBigDecimal("100,500.126", LocaleUtils.getEnglishLocale());
        assertThat(actual, is(expected));
    }

    @Test
    public void testFormatBigDecimal() {
        BigDecimal decimal = new BigDecimal(-100500.1260).setScale(3, RoundingMode.HALF_DOWN);

        String actual = DecimalFormatter.format(decimal, LocaleUtils.getRussianLocale(), DecimalFormatter.DEFAULT_PROPERTIES);
        assertThat(actual, is("-100 500,13"));

        actual = DecimalFormatter.format(decimal, LocaleUtils.getRussianLocale(), DecimalFormatter.NO_GROUPING_PROPERTIES);
        assertThat(actual, is("-100500,13"));

        actual = DecimalFormatter.format(decimal, LocaleUtils.getEnglishLocale(), DecimalFormatter.DEFAULT_PROPERTIES);
        assertThat(actual, is("-100,500.13"));

        actual = DecimalFormatter.format(decimal, LocaleUtils.getEnglishLocale(), DecimalFormatter.NO_GROUPING_PROPERTIES);
        assertThat(actual, is("-100500.13"));

        actual = DecimalFormatter.format(decimal, LocaleUtils.getRussianLocale(), DecimalFormatter.DEFAULT_ABS_PROPERTIES);
        assertThat(actual, is("100 500,13"));

        actual = DecimalFormatter.format(decimal, LocaleUtils.getEnglishLocale(), DecimalFormatter.DEFAULT_ABS_PROPERTIES);
        assertThat(actual, is("100,500.13"));

        decimal = new BigDecimal(123.5d);
        actual = DecimalFormatter.format(decimal, LocaleUtils.getRussianLocale(), DecimalFormatter.DEFAULT_PROPERTIES);
        assertThat(actual, is("123,50"));

        decimal = new BigDecimal(123d);
        actual = DecimalFormatter.format(decimal, LocaleUtils.getRussianLocale(), DecimalFormatter.DEFAULT_PROPERTIES);
        assertThat(actual, is("123"));
    }
}