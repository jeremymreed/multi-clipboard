/*
 * The MIT License
 *
 * Copyright 2018 jeremyr.
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
package com.jeremyr.multiclipboard.junitrules;

import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.swing.SwingUtilities;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * See: https://stackoverflow.com/questions/18429422/basic-junit-test-for-javafx-8 and
 * http://andrewtill.blogspot.com/2012/10/junit-rule-for-javafx-controller-testing.html
 * @author Andy Till
 */
public class JavaFXThreadingRule implements TestRule {

  /**
   * Flag for setting up the JavaFX, we only need to do this once for all tests.
   */
  private static boolean jfxIsSetup;

  @Override
  public Statement apply(Statement statement, Description description) {

    return new OnJFXThreadStatement(statement);
  }

  private static class OnJFXThreadStatement extends Statement {

    private final Statement statement;

    public OnJFXThreadStatement(Statement aStatement) {
      statement = aStatement;
    }

    private Throwable rethrownException = null;

    @Override
    public void evaluate() throws Throwable {

      if (!jfxIsSetup) {
        setupJavaFX();

        jfxIsSetup = true;
      }

      final CountDownLatch countDownLatch = new CountDownLatch(1);

      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          try {
            statement.evaluate();
          } catch (Throwable e) {
            rethrownException = e;
          }
          countDownLatch.countDown();
        }
      });

      countDownLatch.await();

      // if an exception was thrown by the statement during evaluation,
      // then re-throw it to fail the test
      if (rethrownException != null) {
        throw rethrownException;
      }
    }

    protected void setupJavaFX() throws InterruptedException {

      long timeMillis = System.currentTimeMillis();

      final CountDownLatch latch = new CountDownLatch(1);

      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          // initializes JavaFX environment
          new JFXPanel();

          latch.countDown();
        }
      });

      System.out.println("javafx initialising...");
      latch.await();
      System.out.println("javafx is initialised in " + (System.currentTimeMillis() - timeMillis) + "ms");
    }
  }
}
