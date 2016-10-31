package exercises.calculator;

import java.util.HashMap;
import java.util.Map;

/**
 * Class containing maps used to convert words to numbers and vice-versa.
 *
 * Created by guisil on 11/08/2016.
 */
class CalculatorMapper {

    static final String ZERO = "zero";

    private static final Map<String, Integer> wordsToNumbersBelowTen = createWordsToNumbersBelowTen();
    private static final Map<String, Integer> wordsToNumbersBelowTwenty = createWordsToNumbersBelowTwenty();
    private static final Map<String, Integer> wordsToNumbersOverTwenty = createWordsToNumbersOverTwenty();

    private static final Map<Integer, String> numbersToWordsBelowTen = createNumbersToWordsBelowTen();
    private static final Map<Integer, String> numbersToWordsBelowTwenty = createNumbersToWordsBelowTwenty();
    private static final Map<Integer, String> numbersToWordsOverTwenty = createNumbersToWordsOverTwenty();
    private static final Map<Integer, String> numbersToWordsHundredOrThousand = createNumbersToWordsHundredOrThousand();


    private static Map<String, Integer> createWordsToNumbersBelowTen() {

        final Map<String, Integer> wordsMap = new HashMap<>();

        wordsMap.put("one", 1);
        wordsMap.put("two", 2);
        wordsMap.put("three", 3);
        wordsMap.put("four", 4);
        wordsMap.put("five", 5);
        wordsMap.put("six", 6);
        wordsMap.put("seven", 7);
        wordsMap.put("eight", 8);
        wordsMap.put("nine", 9);

        return wordsMap;
    }

    private static Map<String, Integer> createWordsToNumbersBelowTwenty() {

        final Map<String, Integer> wordsMap = new HashMap<>();

        wordsMap.putAll(wordsToNumbersBelowTen);

        wordsMap.put("ten", 10);
        wordsMap.put("eleven", 11);
        wordsMap.put("twelve", 12);
        wordsMap.put("thirteen", 13);
        wordsMap.put("fourteen", 14);
        wordsMap.put("fifteen", 15);
        wordsMap.put("sixteen", 16);
        wordsMap.put("seventeen", 17);
        wordsMap.put("eighteen", 18);
        wordsMap.put("nineteen", 19);

        return wordsMap;
    }

    private static Map<String, Integer> createWordsToNumbersOverTwenty() {

        final Map<String, Integer> wordsMap = new HashMap<>();

        wordsMap.put("twenty", 20);
        wordsMap.put("thirty", 30);
        wordsMap.put("forty", 40);
        wordsMap.put("fifty", 50);
        wordsMap.put("sixty", 60);
        wordsMap.put("seventy", 70);
        wordsMap.put("eighty", 80);
        wordsMap.put("ninety", 90);

        return wordsMap;
    }

    private static Map<Integer, String> createNumbersToWordsBelowTen() {

        final Map<Integer, String> numbersMap = new HashMap<>();

        numbersMap.put(1, "one");
        numbersMap.put(2, "two");
        numbersMap.put(3, "three");
        numbersMap.put(4, "four");
        numbersMap.put(5, "five");
        numbersMap.put(6, "six");
        numbersMap.put(7, "seven");
        numbersMap.put(8, "eight");
        numbersMap.put(9, "nine");

        return numbersMap;
    }

    private static Map<Integer, String> createNumbersToWordsBelowTwenty() {

        final Map<Integer, String> numbersMap = new HashMap<>();

        numbersMap.putAll(numbersToWordsBelowTen);

        numbersMap.put(10, "ten");
        numbersMap.put(11, "eleven");
        numbersMap.put(12, "twelve");
        numbersMap.put(13, "thirteen");
        numbersMap.put(14, "fourteen");
        numbersMap.put(15, "fifteen");
        numbersMap.put(16, "sixteen");
        numbersMap.put(17, "seventeen");
        numbersMap.put(18, "eighteen");
        numbersMap.put(19, "nineteen");

        return numbersMap;
    }

    private static Map<Integer, String> createNumbersToWordsOverTwenty() {

        final Map<Integer, String> numbersMap = new HashMap<>();

        numbersMap.put(20, "twenty");
        numbersMap.put(30, "thirty");
        numbersMap.put(40, "forty");
        numbersMap.put(50, "fifty");
        numbersMap.put(60, "sixty");
        numbersMap.put(70, "seventy");
        numbersMap.put(80, "eighty");
        numbersMap.put(90, "ninety");

        return numbersMap;
    }

    private static Map<Integer, String> createNumbersToWordsHundredOrThousand() {

        final Map<Integer, String> numbersMap = new HashMap<>();

        numbersMap.put(100, "hundred");
        numbersMap.put(1000, "thousand");

        return numbersMap;
    }
    

    static Integer numberForWordBelowTen(String word) {
        return wordsToNumbersBelowTen.get(word);
    }

    static Integer numberForWordBelowTwenty(String word) {
        return wordsToNumbersBelowTwenty.get(word);
    }

    static Integer numberForWordOverTwenty(String word) {
        return wordsToNumbersOverTwenty.get(word);
    }


    static String wordForNumberBelowTwenty(int number) {
        return numbersToWordsBelowTwenty.get(number);
    }

    static String wordForNumberOverTwenty(int number) {
        return numbersToWordsOverTwenty.get(number);
    }

    static String wordForNumberHundredOrThousand(int number) {
        return numbersToWordsHundredOrThousand.get(number);
    }
}
