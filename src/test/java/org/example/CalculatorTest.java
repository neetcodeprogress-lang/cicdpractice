package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Deliberately partial: divide is covered, classify only partly, max not at all.
 */
class CalculatorTest {

  private final Calculator calculator = new Calculator();

  @Test
  void dividesTwoNumbers() {
    assertEquals(5, calculator.divide(10, 2));
  }

  @Test
  void rejectsDivisionByZero() {
    assertThrows(IllegalArgumentException.class, () -> calculator.divide(1, 0));
  }

  @Test
  void classifiesPositive() {
    assertEquals("positive", calculator.classify(7));
  }

  @Test
  void classifiesZero() {
    assertEquals("zero", calculator.classify(0));
  }

  @Test
  void describesAnExcellentScore() {
    assertEquals("excellent", calculator.describe(95));
  }

   @Test
   void describesGoodScore() {
     assertEquals("good", calculator.describe(80));
   }

   @Test
   void describesPassingScore() {
     assertEquals("pass", calculator.describe(65));
   }

   @Test
   void describesFailingScore() {
     assertEquals("fail", calculator.describe(30));
   }

   @Test
   void rejectsNegativeScore() {
     assertThrows(IllegalArgumentException.class, () -> calculator.describe(-1));
   }
}
