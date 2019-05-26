package edu.melb.ds.pj2.gui.control;

import edu.melb.ds.pj2.gui.Cell;
import edu.melb.ds.pj2.gui.GameStatusCenter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LetterCell extends Cell {
    private String letter;

    public LetterCell(Point point, String letter) {
        super(point, letter, Color.ORANGE, Color.WHITE);
        this.setPreferredSize(new Dimension(40, 40));
        this.letter = letter;
        this.drawText();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Clicked " + this.letter);
        GameStatusCenter.setActiveLetter(this);
    }

    public String toString() {
        return "Location of" + letter + ": [" + this.getXLoc() + ", " + this.getYLoc() + "]";
    }
}