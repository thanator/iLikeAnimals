package ru.sberbank.mobile.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * Класс для генерации рандомных сущностей.
 *
 * @author QuickNick
 */
public class BaseEntitiesGenerator {

    protected static final Random RANDOM = new Random();
    protected static final int MAX_TEST_LIST_SIZE = 10;
    private static final char FIRST_ACCEPTIBLE_LETTER = 'A';
    private static final char LAST_ACCEPTIBLE_LETTER = 'z';

    private static final Date MIN_DATE;
    private static final Date MAX_DATE;

    static {
        Calendar calendar = new GregorianCalendar(1900, Calendar.JANUARY, 1, 0, 0, 0);
        MIN_DATE = calendar.getTime();
        calendar.set(2100, Calendar.DECEMBER, 31, 23, 59, 59);
        MAX_DATE = calendar.getTime();
    }

    private static char[] createCharArrayForString() {
        int size = MAX_TEST_LIST_SIZE + Math.abs(RANDOM.nextInt(MAX_TEST_LIST_SIZE));
        char[] result = new char[size];
        for (int i = 0; i < size; i++) {
            int firstLetterCode = FIRST_ACCEPTIBLE_LETTER;
            int lastLetterCode = LAST_ACCEPTIBLE_LETTER;
            int letter = firstLetterCode + Math.abs(RANDOM.nextInt(lastLetterCode - firstLetterCode));
            result[i] = (char) letter;
        }
        return result;
    }

    public static long createRandomId() {
        return Math.abs(RANDOM.nextLong());
    }

    public static float createRandomFloat() {
        return Math.abs(RANDOM.nextFloat());
    }

    public static int createRandomIntId() {
        return Math.abs(RANDOM.nextInt());
    }

    public static List<Long> createLongList() {
        int size = 1 + Math.abs(RANDOM.nextInt(MAX_TEST_LIST_SIZE));
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(createRandomId());
        }
        return list;
    }

    public static String createString() {
        String result = new String(createCharArrayForString());
        return result;
    }

    public static List<String> createStringsList() {
        int size = 1 + Math.abs(RANDOM.nextInt(MAX_TEST_LIST_SIZE));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(createString());
        }
        return list;
    }

    public static Date createDate() {
        long millis = MIN_DATE.getTime() + Math.round((MAX_DATE.getTime() - MIN_DATE.getTime()) * RANDOM.nextDouble());
        return new Date(millis);
    }

    public static <T extends Enum<?>> T createEnumValue(T[] values) {
        int index = RANDOM.nextInt(values.length);
        return values[index];
    }
}
