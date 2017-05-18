package ru.sberbank.mobile.core.network;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import ru.sberbank.mobile.core.bean.io.Encoding;

/**
 * Tests for {@link Request}
 */
@RunWith(AndroidJUnit4.class)
public class RequestTest {

    @Test
    public void testComposeURL() {
        Request request = new Request(Method.GET, "example.com", Encoding.UTF_8);
        request.setPort(80);
        request.setPath("docs/books/tutorial/index.html");
        request.addQueryKeyValue("section", "develop").
                addQueryKeyValue("profile", "java").
                addQueryKeyValue("type", "performance");
        assertEquals(Method.GET, request.getMethod());
        assertEquals(Encoding.UTF_8, request.getEncoding());
        assertEquals(80, request.getPort());
        assertEquals("example.com", request.getHost());
        assertEquals("example.com:80/docs/books/tutorial/index.html?section=develop&profile=java&type=performance", request.composeURL());

        request.setPort(Request.UNDEFINED_PORT);
        assertEquals("example.com/docs/books/tutorial/index.html?section=develop&profile=java&type=performance", request.composeURL());

        request.setPath(null);
        assertEquals("example.com?section=develop&profile=java&type=performance", request.composeURL());
    }

    @Test
    public void testComposeQueryAsString() {
        Request request = new Request(Method.GET, "example.com", Encoding.UTF_8);

        request.addQueryKeyValue("section", "develop");
        assertEquals("section=develop", request.composeQueryAsString());

        request.addQueryKeyValue("profile", "java");
        assertEquals("section=develop&profile=java", request.composeQueryAsString());

        request.addQueryKeyValue("type", "performance");
        assertEquals("section=develop&profile=java&type=performance", request.composeQueryAsString());
    }
}
