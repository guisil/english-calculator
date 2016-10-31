package exercises.calculator;

import java.util.regex.Pattern;

/**
 * Class which contains some of the
 * regular expression patterns used in the application.
 *
 * Created by guisil on 11/08/2016.
 */
class CalculatorPatterns {

    /**
     * Regular Expression pattern that matches a calculation,
     * extracting the first operand, the operator and the second operand.
     */
    static final Pattern operationPattern = Pattern.compile(
            "^(?<first>[a-z\\-\\s]+)" +
                    "(?<operator>plus|minus|divided by|multiplied by)" +
                    "(?<second>[a-z\\-\\s]+)$");

    /**
     * Regular expression pattern that matches an operand,
     * extracting separately the parts containing the thousands, the hundreds and the rest.
     */
    static final Pattern operandPattern = Pattern.compile(
            "^(?<thousands>[a-z\\s]+thousand(?:\\sand\\s)?)?" +
                    "(?<hundreds>[a-z\\s]+hundred(?:\\sand\\s)?)?" +
                    "(?<rest>[a-z\\s\\-]+)?$");
}
