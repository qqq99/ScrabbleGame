package edu.melb.ds.pj2.gui.mainboard;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;

/**
 * Cell that with text "TL" on it, which means the letter which is placed on
 * this cell will get tripled score
 */
public class TL extends BoardCell {
    public static final Color COLOR = Color.YELLOW;

    public TL(Point p) {
        super(p, COLOR);
        drawText("TL");
    }
    public void drawText(String text) {
        label = new JLabel(text);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(font.deriveFont(Font.PLAIN, 10));
        this.add(label);
    }
}