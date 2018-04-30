/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeremyr.multiclipboard.timemanager;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 *
 * @author jeremyr
 */
public class TimeManager {

  /**
   * Get current time in a formatted string, for the given time zone.
   *
   * @param timeZone The desired time zone.
   * @return Formatted String with the current time, in the given time zone.
   */
  public String getFormattedDateWithZone(String timeZone) {
    try {
      ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of(timeZone));
      return this.formatDigit(dateTime.getDayOfMonth()) + "/" + this.formatDigit(dateTime.getMonthValue()) + "/" + this.formatDigit(dateTime.getYear())
              + "  " + this.formatDigit(dateTime.getHour()) + ":" + this.formatDigit(dateTime.getMinute()) + ":" + this.formatDigit(dateTime.getSecond())
              + " [" + dateTime.getZone().toString() + "]";

    } catch (DateTimeException dateTimeException) {
      System.out.println("Caught DateTimeException: " + dateTimeException);
      return "---";
    }
  }

  /**
   * Get current time in a formatted string, for the default time zone.
   *
   * @return Formatted String with the current time, in the default time zone.
   */
  public String getFormattedDateForLocalZone() {
    return this.getFormattedDateWithZone(TimeZone.getDefault().toZoneId().toString());
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
