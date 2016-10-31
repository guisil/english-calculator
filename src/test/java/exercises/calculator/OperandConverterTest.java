package exercises.calculator;

import exercises.calculator.OperandConverter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Test class for OperandConverter.
 *
 * Created by guisil on 11/08/2016.
 */
public class OperandConverterTest {

    private static List<String> noConversionsExpectedFromWordToNumber = new ArrayList<>();
    private static Map<String, Integer> expectedConversionsFromWordToNumber = new HashMap<>();

    private static Map<Integer, String> expectedConversionsFromNumberToWord = new HashMap<>();

    private OperandConverter operandConverter;

    @BeforeClass
    public static void setUpClass() throws Exception {

        noConversionsExpectedFromWordToNumber.addAll(Arrays.asList(
                "onee",
                "one two",
                "five ducks",
                "twenty zero",
                "twenty ten",
                "zero hundred",
                "one hundred and",
                "two hundred and one thousand",
                "twelve hundred",
                "zero thousand",
                "one thousand and",
                "two thousand and fifty seven elfs",
                "nine thousand and five two",
                "and three thousand"));

        expectedConversionsFromWordToNumber.put("zero", 0);
        expectedConversionsFromWordToNumber.put("one", 1);
        expectedConversionsFromWordToNumber.put("seven", 7);
        expectedConversionsFromWordToNumber.put("eighteen", 18);

        expectedConversionsFromWordToNumber.put("twenty-five", 25);
        expectedConversionsFromWordToNumber.put("fifty seven", 57);
        expectedConversionsFromWordToNumber.put("ninety", 90);

        expectedConversionsFromWordToNumber.put("one hundred", 100);
        expectedConversionsFromWordToNumber.put("one hundred and two", 102);
        expectedConversionsFromWordToNumber.put("six hundred and twenty five", 625);

        expectedConversionsFromWordToNumber.put("one thousand", 1000);
        expectedConversionsFromWordToNumber.put("two thousand five hundred and six", 2506);
        expectedConversionsFromWordToNumber.put("five thousand and one", 5001);
        expectedConversionsFromWordToNumber.put("six thousand and six hundred seven", 6607);
        expectedConversionsFromWordToNumber.put("nine thousand and seven hundred and eighty-six", 9786);
        expectedConversionsFromWordToNumber.put("ten thousand", 10000);



        expectedConversionsFromNumberToWord.put(-2, "minus two");
        expectedConversionsFromNumberToWord.put(0, "zero");
        expectedConversionsFromNumberToWord.put(1, "one");
        expectedConversionsFromNumberToWord.put(9, "nine");
        expectedConversionsFromNumberToWord.put(15, "fifteen");
        expectedConversionsFromNumberToWord.put(24, "twenty four");
        expectedConversionsFromNumberToWord.put(50, "fifty");
        expectedConversionsFromNumberToWord.put(99, "ninety nine");
        expectedConversionsFromNumberToWord.put(102, "one hundred and two");
        expectedConversionsFromNumberToWord.put(222, "two hundred and twenty two");
        expectedConversionsFromNumberToWord.put(900, "nine hundred");
        expectedConversionsFromNumberToWord.put(1056, "one thousand and fifty six");
        expectedConversionsFromNumberToWord.put(2288, "two thousand two hundred and eighty eight");
        expectedConversionsFromNumberToWord.put(8000, "eight thousand");
        expectedConversionsFromNumberToWord.put(9999, "nine thousand nine hundred and ninety nine");
        expectedConversionsFromNumberToWord.put(10000, "ten thousand");
    }

    @Before
    public void setUp() throws Exception {
        operandConverter = new OperandConverter();
    }

    @Test
    public void shouldConvertToNumber() throws Exception {
        for (String operandAsString : expectedConversionsFromWordToNumber.keySet()) {
            assertEquals(expectedConversionsFromWordToNumber.get(operandAsString).intValue(),
                    operandConverter.wordsToNumber(operandAsString));
        }
    }

    @Test
    public void shouldNotConvertToNumber() throws Exception {
        for (String operandAsString : noConversionsExpectedFromWordToNumber) {
            try {
                operandConverter.wordsToNumber(operandAsString);
                fail("should have thrown an exception: " + operandAsString);
            } catch (IllegalArgumentException ex) {
            }
        }
    }

    @Test
    public void shouldConvertToWords() throws Exception {
        for (int operandAsInt : expectedConversionsFromNumberToWord.keySet()) {
            assertEquals(expectedConversionsFromNumberToWord.get(operandAsInt),
                    operandConverter.numberToWords(operandAsInt));
        }
    }
}