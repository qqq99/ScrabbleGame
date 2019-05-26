package edu.melb.ds.pj2.gui.mainboard;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;

/**
 * Cell that with text "DL" on it, which means the letter which is placed on
 * this cell will get doubled score
 */
public class DL extends BoardCell {
    public static final Color COLOR = Color.GRAY;

    public DL(Point point) {
        super(point, COLOR);
        this.drawText("DL");
    }

    public void drawText(String text) {
        label = new JLabel(text);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(font.deriveFont(Font.PLAIN, 10));
        this.add(label);
    }
}