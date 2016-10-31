package exercises.calculator;

import exercises.calculator.Calculator;
import exercises.calculator.CalculatorImpl;
import exercises.calculator.OperandConverter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class CalculatorTest {


  private Calculator calculator;

  @Before
  public void setup() {
    calculator = new CalculatorImpl(new OperandConverter());
  }


  /**
   * Validate that when null is passed-in, we get an illegal argument exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidationNull() {
    calculator.calculate(null);
  }

  /**
   * Validate that when an invalid calculation is passed-in, we get an illegal argument exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidationNotValidRequest() {
    calculator.calculate("not a calculation");
  }

  /**
   * Validate that when an invalid calculation is passed-in, we get an illegal argument exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidationNearlyValidRequest() {
    calculator.calculate("seven apples plus nine oranges");
  }




  /**
   * Validate we only accept args up to 100 for subtraction
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSubtractionBreachesArgLimit() {
    calculator.calculate("one hundred and one minus one");
  }


  /**
   * Validate we only accept args up to 100 for product
   */
  @Test(expected = IllegalArgumentException.class)
  public void testProductBreachesArgLimit() {
    calculator.calculate("one hundred and one multiplied by one");
  }


  /**
   * Validate we only accept args up to 10,000 for division
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDivisionBreachesArgLimit() {
    calculator.calculate("ten thousand and one divided by one");
  }
  /**
   * Validate we only accept args up to 100 for addition
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAdditionBreachesArgLimit() {
    calculator.calculate("one hundred and one plus one");
  }
  /**
   * Validate that a simple addition works ok
   */
  @Test
  public void testAdditionUnits() {
    testEquals("one plus one", "two");
  }

  /**
   * Validate that addition works when carrying values over to higher values of ten
   */
  @Test
  public void testAdditionWithCarryOvers() {
    testEquals("eighty seven plus forty six", "one hundred and thirty three");
  }


  /**
   * Validate that a simple addition works
   */
  @Test
  public void testSubtractionUnits() {
    testEquals("two minus one", "one");
  }

  /**
   * Validate that the subtraction works when carrying over unites of ten
   */
  @Test
  public void testSubtractionWithCarryOvers() {
    testEquals("seventy six minus twenty seven", "forty nine");
  }

  /**
   * Validate that subtraction works to carry over to negative values
   */
  @Test
  public void testSubtractionToNegative() {
    testEquals("forty three minus fifty six", "minus thirteen");
  }

  /**
   * Validate that simple multiplication works
   */
  @Test
  public void testProductUnits() {
    testEquals("seven multiplied by nine", "sixty three");
  }

  /**
   * Validate that a simple product works
   */
  @Test
  public void testProductWithCarryOver() {
    testEquals("seventy six multiplied by ninety nine", "seven thousand five hundred and twenty four");
  }

  /**
   * Validate that the edge case of limit multiplication works
   */
  @Test
  public void testProductLimit() {
    testEquals("one hundred multiplied by one hundred", "ten thousand");
  }


  /**
   * Validate that a simple division works
   */
  @Test
  public void testDivisionWholeNumbers() {
    testEquals("one hundred and twenty five divided by five", "twenty five");
  }

  /**
   * Validate that a division with a remainder works
   */
  @Test
  public void testDivisionWithRemainder() {
    testEquals("sixty three divided by twelve", "five with a remainder of three");
  }


  /**
   * A simple calculation validation
   * @param calculation The calculation
   * @param expectedResult the expected value
   */
  private void testEquals(String calculation, String expectedResult) {
    assertEquals(expectedResult, calculator.calculate(calculation));
  }
}
