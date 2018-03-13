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
package clipboardinterface;

import com.jeremyr.multiclipboard.clipboardinterface.ClipboardInterface;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import junitrules.JavaFXThreadingRule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author jeremyr
 */
public class ClipboardInterfaceTest {
  
  public ClipboardInterfaceTest() {
  }

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
   * Test of readClipboard method, of class ClipboardInterface.
   */
  @Test
  public void testReadClipboard() {
    System.out.println("readClipboard");
    Clipboard javaFXClipboard = Clipboard.getSystemClipboard();
    ClipboardContent content = new ClipboardContent();
    String expected = "Foo Bar";
    content.putString(expected);
    javaFXClipboard.setContent(content);

    ClipboardInterface clipboardInterface = new ClipboardInterface();
    String actual = clipboardInterface.readClipboard();
    assertEquals(expected, actual);
  }

  /**
   * Test of writeClipboard method, of class ClipboardInterface.
   */
  @Test
  public void testWriteClipboard() {
    System.out.println("writeClipboard");
    String expected = "Hello World";
    ClipboardInterface clipboardInterface = new ClipboardInterface();
    clipboardInterface.writeClipboard(expected);

    Clipboard javaFXClipboard = Clipboard.getSystemClipboard();
    assertEquals(true, javaFXClipboard.hasString());

    String actual = javaFXClipboard.getString();
    assertEquals(expected, actual);
  }

  /**
   * Test of emptyClipboard method, of class ClipboardInterface.
   */
  @Test
  public void testEmptyClipboard() {
    System.out.println("emptyClipboard");
    ClipboardInterface clipboardInterface = new ClipboardInterface();
    clipboardInterface.emptyClipboard();

    Clipboard javaFXClipboard = Clipboard.getSystemClipboard();
    assertEquals(true, javaFXClipboard.getContentTypes().isEmpty());
  }

  /**
   * Test of isClipboardEmpty method, of class ClipboardInterface.
   */
  @Test
  public void testIsClipboardEmpty() {
    System.out.println("isClipboardEmpty");
    Clipboard javaFXClipboard = Clipboard.getSystemClipboard();
    javaFXClipboard.setContent(null);

    ClipboardInterface clipboardInterface = new ClipboardInterface();

    assertEquals(true, clipboardInterface.isClipboardEmpty());
  }
}
