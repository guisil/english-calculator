package exercises.calculator;
/**
 * The interface for the calculator
 */
public interface Calculator {
  /**
   * Derive an english language result from an english language request.
   * Read the source README.MD for the full description.
   *
   * @param request the request
   * @return the response
   * @throws IllegalArgumentException On any malformed or excessive request
   */
  String calculate (String request) throws IllegalArgumentException;
}
