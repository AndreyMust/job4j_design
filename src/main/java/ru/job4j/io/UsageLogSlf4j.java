package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLogSlf4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {

        String stringValue = "Andrew";
        boolean boolValue = true;
        int intValue = 333;
        byte byteValue = (byte) 333;
        char charValue = 'x';
        short shortValue = (short) 15000;
        long longValue = 1000000;
        float floatValue = 990000f;
        double doubleValue = 9900000;

        LOG.debug("sting: {}, boolean: {}, int: {}, byte: {}, "
                       + "char: {}, short: {}, long: {}, float: {}, double: {}",
                stringValue, boolValue, intValue, byteValue,
                charValue, shortValue, longValue, floatValue, doubleValue);
    }
}
