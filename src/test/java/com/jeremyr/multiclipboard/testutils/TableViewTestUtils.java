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
package com.jeremyr.multiclipboard.testutils;

import java.util.ArrayList;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.control.TableView;

/**
 *
 * @author jeremyr
 */
public class TableViewTestUtils {
  
  /**
   * Gets the Node (with CSS class .table-cell) from the TableView
   * at the given row and column.
   * 
   * NOTE.  This assumes that JavaFX's CSS class for TableCells will continue to be '.table-cell'
   *        If this code breaks, check to see if this has changed first.
   *        It'd be great to be able to get a collection of TableCells out of the TableView directly...
   *
   * @param row   Which Row.
   * @param col   Which column.
   * @param table TableView containing our Nodes.
   * @return The Node (.table-cell) in the TableView at the given row and column.
  */
  public static Node getNodeAt(int row, int col, TableView table) {
    Node retval = null;

    if (table != null) {
      int numRows = table.getItems().size();
      int numCols = table.getColumns().size();

      if (row >= 0 && col >= 0 && row < numRows && col < numCols) {
        Set<Node> nodes = table.lookupAll(".table-cell");
        ArrayList<Node> nodesList = new ArrayList<>();
        nodesList.addAll(nodes);
        int index = (row * numCols) + col;
        retval = nodesList.get(index);
      } else {
        throw new IllegalArgumentException("row and/or col are out of bounds!");
      }
    } else {
      throw new NullPointerException("table is null!");
    }

    return retval;
  }
}
