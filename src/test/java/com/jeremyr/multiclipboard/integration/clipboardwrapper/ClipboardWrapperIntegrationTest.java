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
package com.jeremyr.multiclipboard.integration.clipboardwrapper;

import com.jeremyr.multiclipboard.wrappers.JavaFXClipboardWrapper;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import com.jeremyr.multiclipboard.junitrules.JavaFXThreadingRule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 * This is an integration test for the ClipboardWrapper class.
 *
 * TODO: Needs a few more tests, specifically what if the JavaFX System Clipboard contains non-string data?
 * TODO: Without mocking, it'll be difficult to test the unhappy cases...  Needs more thought.
 *
 * @author jeremyr
 */
public class ClipboardWrapperIntegrationTest {
  
  public ClipboardWrapperIntegrationTest() {
  }

  /**
   * JUnit rule that allows us to run these tests on the JavaFX Application Thread.
   *
   * See:
   *  https://stackoverflow.com/questions/18429422/basic-junit-test-for-javafx-8 and
   *  http://andrewtill.blogspot.com/2012/10/junit-rule-for-javafx-controller-testing.html
   */
  @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of readClipboard method, of class ClipboardWrapper.
   */
  @Test
  public void testReadClipboard() {
    System.out.println("readClipboard");
    Clipboard javaFXClipboard = Clipboard.getSystemClipboard();
    ClipboardContent content = new ClipboardContent();
    String expected = "Foo Bar";
    content.putString(expected);
    javaFXClipboard.setContent(content);

    JavaFXClipboardWrapper clipboardInterface = new JavaFXClipboardWrapper();
    String actual = clipboardInterface.readClipboard();
    assertEquals(expected, actual);
  }

  /**
   * Test of writeClipboard method, of class ClipboardWrapper.
   */
  @Test
  public void testWriteClipboard() {
    System.out.println("writeClipboard");
    String expected = "Hello World";
    JavaFXClipboardWrapper clipboardInterface = new JavaFXClipboardWrapper();
    clipboardInterface.writeClipboard(expected);

    Clipboard javaFXClipboard = Clipboard.getSystemClipboard();
    assertEquals(true, javaFXClipboard.hasString());

    String actual = javaFXClipboard.getString();
    assertEquals(expected, actual);
  }

  /**
   * Test of emptyClipboard method, of class ClipboardWrapper.
   */
  @Test
  public void testEmptyClipboard() {
    System.out.println("emptyClipboard");
    JavaFXClipboardWrapper clipboardInterface = new JavaFXClipboardWrapper();
    clipboardInterface.emptyClipboard();

    Clipboard javaFXClipboard = Clipboard.getSystemClipboard();
    assertEquals(true, javaFXClipboard.getContentTypes().isEmpty());
  }

  /**
   * Test of isClipboardEmpty method, of class ClipboardWrapper.
   */
  @Test
  public void testIsClipboardEmpty() {
    System.out.println("isClipboardEmpty");
    Clipboard javaFXClipboard = Clipboard.getSystemClipboard();
    javaFXClipboard.setContent(null);

    JavaFXClipboardWrapper clipboardInterface = new JavaFXClipboardWrapper();

    assertEquals(true, clipboardInterface.isClipboardEmpty());
  }
}
