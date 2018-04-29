/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeremyr.multiclipboard.timemanager;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author jeremyr
 */
public class TimeManager {
  public String getFormattedDate(String zone) {
    try {
      ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of(zone));
      return this.formatDigit(dateTime.getDayOfMonth()) + "/" + this.formatDigit(dateTime.getMonthValue()) + "/" + this.formatDigit(dateTime.getYear())
              + "  " + this.formatDigit(dateTime.getHour()) + ":" + this.formatDigit(dateTime.getMinute()) + ":" + this.formatDigit(dateTime.getSecond());

    } catch (DateTimeException dateTimeException) {
      System.out.println("Caught DateTimeException: " + dateTimeException);
      return "---";
    }
  }

  /**
   * Given a positive integer in the range 0 <= digit <= INT_MAX: if the given
   * integer is negative: - Throw an exception. if the given integer is in the
   * range 0 <= digit <= 9 - Convert digit to String, prepend a leading zero,
   * and return. else (10 <= digit <= INT_MAX): - Convert digit to String, and
   * return. @param digit @return Formatted String representa
   *
   * t
   * ion of digit.
   */
  private String formatDigit(int digit) {

    if (digit < 0) {
      throw new IllegalArgumentException("Digit cannot be negative");
    } else if (0 <= digit && digit <= 9) {
      String retval = "0" + digit;
      return retval;
    } else if (10 <= digit && digit <= Integer.MAX_VALUE) {
      String retval = "" + digit;
      return retval;
    }

    throw new IllegalStateException("formatDigit failed to match any clause in if statement.  Something strange happened.");
  }
}
