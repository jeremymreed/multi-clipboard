/*
 * The MIT License
 *
 * Copyright 2018 Jeremy M. Reed.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
      return this.formatDigit(dateTime.getMonthValue()) + "/" + this.formatDigit(dateTime.getDayOfMonth()) + "/" + this.formatDigit(dateTime.getYear())
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
