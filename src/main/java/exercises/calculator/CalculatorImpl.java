package exercises.calculator;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.regex.Matcher;

/**
 *
 */
public class CalculatorImpl implements Calculator {

    private final OperandConverter operandConverter;

    public CalculatorImpl(OperandConverter operandConverter) {
        this.operandConverter = operandConverter;
    }

    /**
     * {@inheritDoc}
     */
    public String calculate(String request) {

        if (request == null) {
            throw new IllegalArgumentException("null calculation");
        }

        Matcher operationMatcher = CalculatorPatterns.operationPattern.matcher(request);

        if (!operationMatcher.matches()) {
            throw new IllegalArgumentException("invalid calculation");
        }

        String operator = operationMatcher.group("operator");
        String first = operationMatcher.group("first");
        String second = operationMatcher.group("second");

        int firstOperand = operandConverter.wordsToNumber(first);
        int secondOperand = operandConverter.wordsToNumber(second);

        Pair<Integer, Integer> result = performCalculation(firstOperand, secondOperand, operator);

        String main = operandConverter.numberToWords(result.getLeft());
        if (result.getRight() <= 0) {
          return main;
        }
        String remainder = operandConverter.numberToWords(result.getRight());
        return constructNumberWithRemainder(main, remainder);
    }

    /**
     * @return pair containing the result of the operation (left) and, if applicable, the remainder (right)
     */
    private Pair<Integer, Integer> performCalculation(int firstOperand, int secondOperand, String operator) {

        switch (operator) {
            case "plus":
                if (firstOperand > 100 || secondOperand > 100) {
                    throw new IllegalArgumentException("illegal operand for addition");
                }
                return new ImmutablePair<>(firstOperand + secondOperand, 0);
            case "minus":
                if (firstOperand > 100 || secondOperand > 100) {
                    throw new IllegalArgumentException("illegal operand for subtraction");
                }
                return new ImmutablePair<>(firstOperand - secondOperand, 0);
            case "multiplied by":
                if (firstOperand > 100 || secondOperand > 100) {
                   throw new IllegalArgumentException("illegal operand for multiplication");
                }
                return new ImmutablePair<>(firstOperand * secondOperand, 0);
            case "divided by":
                if (firstOperand > 10000 || secondOperand > 10000) {
                    throw new IllegalArgumentException("illegal operand for division");
                }
                return new ImmutablePair<>(firstOperand / secondOperand, firstOperand % secondOperand);
            default:
                throw new IllegalArgumentException("unsupported operator");
        }
    }

    private String constructNumberWithRemainder(String main, String remainder) {
      return main + " with a remainder of " + remainder;
    }
}
