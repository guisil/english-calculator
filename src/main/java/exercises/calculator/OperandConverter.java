package exercises.calculator;

import java.util.regex.Matcher;

/**
 * Class used to performs the conversions of the operands.
 *
 * Created by guisil on 11/08/2016.
 */
class OperandConverter {

    /**
     * Converts the given operand (as words)
     * into its numerical value.
     *
     * @param operandAsWords String containing the operand (as words)
     * @return numerical value of the given operand
     */
    int wordsToNumber(String operandAsWords) {

        Matcher operandMatcher = CalculatorPatterns.operandPattern.matcher(operandAsWords);

        if (!operandMatcher.matches()) {
            throw new IllegalArgumentException("invalid operand");
        }

        String thousands = operandMatcher.group("thousands");
        String hundreds = operandMatcher.group("hundreds");
        String rest = operandMatcher.group("rest");

        return convertToTotal(thousands, hundreds, rest);
    }

    private int convertToTotal(String thousands, String hundreds, String rest) {
        int total = 0;
        if (thousands != null) {
            total += convertHundredsOrThousandsToNumber(thousands.trim(), 1000);
        }
        if (hundreds != null) {
            int hundredsTotal = convertHundredsOrThousandsToNumber(hundreds.trim(), 100);
            if (hundredsTotal / 100 > 9) {
                throw new IllegalArgumentException("illegal hundreds: " + hundredsTotal);
            }
            total += hundredsTotal;
        }
        if (rest != null) {
            total += convertRestToNumber(rest.trim());
        }
        return total;
    }

    private int convertHundredsOrThousandsToNumber(String trimmedPartialOperand, int times) {

        int partialValue = 0;
        if (!trimmedPartialOperand.isEmpty()) {
            // this will actually leave out the 'and' in 'thousand', but that was already matched before
            String[] split = trimmedPartialOperand.split("\\s|and");
            if (split.length != 2) {
                throwException(CalculatorMapper.wordForNumberHundredOrThousand(times) + "s", trimmedPartialOperand);
            }
            Integer numberBelowTwenty = CalculatorMapper.numberForWordBelowTwenty(split[0]);
            if (numberBelowTwenty == null) {
                throwException(CalculatorMapper.wordForNumberHundredOrThousand(times) + "s", trimmedPartialOperand);
            }
            partialValue = numberBelowTwenty * times;
        }

        return partialValue;
    }

    private int convertRestToNumber(String trimmedPartialOperand) {

        int partialValue = 0;
        if (!trimmedPartialOperand.isEmpty() && !trimmedPartialOperand.equals(CalculatorMapper.ZERO)) {
            partialValue = convertWholeRestToNumber(trimmedPartialOperand);
            if (partialValue == 0) {
                partialValue = convertSplitRestToNumber(trimmedPartialOperand);
            }
        }

        return partialValue;
    }

    private int convertWholeRestToNumber(String trimmedPartialOperand) {
        Integer numberBelowTwenty = CalculatorMapper.numberForWordBelowTwenty(trimmedPartialOperand);
        Integer numberOverTwenty = CalculatorMapper.numberForWordOverTwenty(trimmedPartialOperand);
        if (numberBelowTwenty != null) {
            return numberBelowTwenty;
        }
        if (numberOverTwenty != null) {
            return numberOverTwenty;
        }
        return 0;
    }

    private int convertSplitRestToNumber(String trimmedPartialOperand) {
        String[] split = trimmedPartialOperand.split("[\\s-]");
        if (split.length != 2) {
            throwException("rest", trimmedPartialOperand);
        }
        Integer firstPartOverTwenty = CalculatorMapper.numberForWordOverTwenty(split[0]);
        Integer secondPartBelowTen = CalculatorMapper.numberForWordBelowTen(split[1]);
        if (firstPartOverTwenty == null
                || secondPartBelowTen == null) {
            throwException("rest", trimmedPartialOperand);
        }
        return firstPartOverTwenty + secondPartBelowTen;
    }

    private void throwException(String description, String value) {
        throw new IllegalArgumentException("illegal " + description + ": " + value);
    }


    /**
     * Converts the given operand (as a number)
     * into the equivalent words.
     *
     * @param operandAsNumber operand as a number
     * @return words representing the given number
     */
    String numberToWords(int operandAsNumber) {

        if (operandAsNumber == 0) {
            return CalculatorMapper.ZERO;
        }

        String wordBelowTwenty = CalculatorMapper.wordForNumberBelowTwenty(operandAsNumber);
        if (wordBelowTwenty != null) {
            return wordBelowTwenty;
        }

        return appendAllNumbersConverted(operandAsNumber);
    }

    private String appendAllNumbersConverted(int operandAsNumber) {
        StringBuilder words = new StringBuilder();
        appendSign(words, operandAsNumber);
        int remainder = Math.abs(operandAsNumber);
        remainder = appendHundredsOrThousands(words, remainder, 1000);
        remainder = appendHundredsOrThousands(words, remainder, 100);
        remainder = appendTens(words, remainder);
        appendRest(words, remainder);
        return words.toString();
    }

    private void appendSign(StringBuilder words, int value) {
        if (value < 0) {
            words.append("minus").append(" ");
        }
    }

    private int appendHundredsOrThousands(StringBuilder words, int remainder, int lowerLimit) {
        if (remainder < lowerLimit) {
            return remainder;
        }
        int amount = remainder / lowerLimit;
        remainder = remainder % lowerLimit;
        words.append(CalculatorMapper.wordForNumberBelowTwenty(amount))
                .append(" ").append(CalculatorMapper.wordForNumberHundredOrThousand(lowerLimit));

        appendAnd(words, remainder);

        return remainder;
    }

    private void appendAnd(StringBuilder words, int remainder) {
        if (remainder > 0) {
            if (remainder < 100) {
                words.append(" and ");
            } else {
                words.append(" ");
            }
        }
    }

    private int appendTens(StringBuilder words, int remainder) {
        if (remainder < 20) {
            return remainder;
        }
        int tens = remainder / 10;
        remainder = remainder % 10;
        words.append(CalculatorMapper.wordForNumberOverTwenty(tens * 10));
        if (remainder != 0) {
            words.append(" ");
        }

        return remainder;
    }

    private void appendRest(StringBuilder words, int remainder) {
        if (remainder != 0) {
            words.append(CalculatorMapper.wordForNumberBelowTwenty(remainder));
        }
    }
}
