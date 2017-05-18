package ru.sberbank.mobile.core.parser;

/**
 * Created by Trikhin P O on 08.06.2016.
 */
public class DefaultParserFactory {

    public ExtendedParser newXMLParser() {
        return new ExtendedParser(new SimpleXMLParser());
    }

    public ExtendedParser newXMLParser(boolean failOnUnknownProperties) {
        return new ExtendedParser(new SimpleXMLParser(failOnUnknownProperties));
    }

    public ExtendedParser newJSONParser() {
        return new ExtendedParser(new JacksonParser());
    }

    public ExtendedParser newJSONParser(boolean failOnUnknownProperties) {
        return new ExtendedParser(new JacksonParser(failOnUnknownProperties));
    }
}
