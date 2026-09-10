package org.example;

/**
 * A few small operations with real branches, so coverage means something.
 */
public class Calculator {

  /**
   * Divides two numbers.
   *
   * @param a the dividend
   * @param b the divisor, which must not be zero
   * @return the quotient
   */
  public int divide(int a, int b) {
    if (b == 0) {
      throw new IllegalArgumentException("cannot divide by zero");
    }
    return a / b;
  }

  /**
   * Describes a number's sign.
   *
   * @param n the number to classify
   * @return "negative", "zero", or "positive"
   */
  public String classify(int n) {
    if (n < 0) {
      return "negative";
    }
    if (n == 0) {
      return "zero";
    }
    return "positive";
  }

  /**
   * Returns the larger of two numbers.
   *
   * @param a the first number
   * @param b the second number
   * @return the larger of the two
   */
  public int max(int a, int b) {
    if (a > b) {
      return a;
    }
    return b;
  }

  /**
   * Describes a score band.
   *
   * @param score the score, which must not be negative
   * @return a word describing the band
   */

  public String describe(int score) {
    if (score < 0) {
      throw new IllegalArgumentException("score must not be negative");
    }
    if (score >= 90) {
      return "excellent";
    }
    if (score >= 75) {
      return "good";
    }
    if (score >= 60) {
      return "pass";
    }
    return "fail";
  }
}
