package edu.melb.ds.pj2.gui.mainboard;

import edu.melb.ds.pj2.gui.Cell;
import edu.melb.ds.pj2.gui.GameStatusCenter;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class BoardCell extends Cell {

    public BoardCell(Point point, Color color) {
        super(point, "", color, Color.WHITE);
    }

    public BoardCell(Point point, String text, Color color, Color borderColor) {
        super(point, text, color, borderColor);
    }

    /**
     * When mouse click on the cell of the gameBoard, if there is active letter
     * for the moment, just place this letter on current gameBoard cell
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        GameStatusCenter.addLetterToBoard(this);
    }

    @Override
    public String toString() {
        return "BoardCell Coordinates: [" + point.getX() + ", " + point.getY() + "]";
    }
}