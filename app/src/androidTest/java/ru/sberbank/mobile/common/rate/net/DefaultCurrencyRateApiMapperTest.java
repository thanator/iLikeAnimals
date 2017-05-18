package ru.sberbank.mobile.common.rate.net;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import ru.sberbank.mobile.common.rate.entity.RatesBundle;
import ru.sberbank.mobile.core.CoreEntitiesGenerator;
import ru.sberbank.mobile.core.network.ConnectorStatus;
import ru.sberbank.mobile.core.network.Method;
import ru.sberbank.mobile.core.network.Request;
import ru.sberbank.mobile.core.network.StubHttpConnector;
import ru.sberbank.mobile.core.parser.ExtendedParser;
import ru.sberbank.mobile.core.parser.SimpleXMLParser;

/**
 * @author QuickNick.
 */
@RunWith(AndroidJUnit4.class)
public class DefaultCurrencyRateApiMapperTest {

    private static final String RATES_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private static final ConnectorStatus BAD_STATUS = ConnectorStatus.NETWORK_UNAVAILABLE;
    private DefaultCurrencyRateApiMapper mApiMapper;
    private StubHttpConnector mHttpConnector;

    @Before
    public void setUp() {
        mHttpConnector = new StubHttpConnector();
        mApiMapper = new DefaultCurrencyRateApiMapper(mHttpConnector, new ExtendedParser(new SimpleXMLParser()));
    }

    @Test
    public void testGetRatesBundleWithSuccess() {
        RatesBundle expected = CoreEntitiesGenerator.createRatesBundle();
        mHttpConnector.setResponse(expected);

        RatesBundle actual = mApiMapper.getRatesBundle();
        assertThat(actual, is(expected));
        assertGetRatesBundleRequestIsValid(mHttpConnector.getRequest());
    }

    @Test
    public void testGetRatesBundleWithError() {
        RatesBundle expected = new RatesBundle();
        expected.setConnectorStatus(BAD_STATUS);

        mHttpConnector.setConnectorStatus(BAD_STATUS);
        RatesBundle actual = mApiMapper.getRatesBundle();
        assertThat(actual, is(expected));
        assertGetRatesBundleRequestIsValid(mHttpConnector.getRequest());
    }

    private static void assertGetRatesBundleRequestIsValid(Request actual) {
        assertThat(actual.composeURL(), is(RATES_URL));
        assertThat(actual.getMethod(), is(Method.GET));
    }
}
