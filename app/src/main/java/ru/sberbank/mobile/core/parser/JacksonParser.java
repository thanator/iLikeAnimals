package ru.sberbank.mobile.core.parser;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Trikhin P O on 08.06.2016.
 */
public class JacksonParser implements IParser {

    protected final ObjectMapper mObjectMapper;

    public JacksonParser() {
        this(false);
    }

    public JacksonParser(boolean failOnUnknownProperties) {
        mObjectMapper = new ObjectMapper();
        mObjectMapper.setAnnotationIntrospector( new JacksonAnnotationIntrospector())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties)
                .configure(MapperFeature.AUTO_DETECT_CREATORS, false)
                .configure(MapperFeature.AUTO_DETECT_FIELDS, false)
                .configure(MapperFeature.AUTO_DETECT_GETTERS, false)
                .configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false)
                .configure( MapperFeature.AUTO_DETECT_SETTERS, false)
                .configure(MapperFeature.USE_GETTERS_AS_SETTERS, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public <T> T parse(@NonNull InputStream source, @NonNull Class<T> clazz) throws IOException, ParserException {
        T result = null;
        try {
            result = mObjectMapper.readValue(source, clazz);
        } catch (JsonParseException | JsonMappingException e) {
            throw new ParserException(e);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new ParserException(e);
        }
        return result;
    }

    @Override
    public void serialize(@NonNull OutputStream sink, @NonNull Object object) throws IOException, SerializeException {
        try {
            mObjectMapper.writeValue(sink, object);
            String logString = mObjectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new SerializeException(e);
        }
    }
}
