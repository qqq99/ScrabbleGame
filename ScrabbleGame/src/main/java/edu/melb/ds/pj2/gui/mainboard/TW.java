package edu.melb.ds.pj2.gui.mainboard;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;

/**
 * Cell that with text "TW" on it, which means every words which have
 * letter be placed on this cell will get doubled score
 */
public class TW extends BoardCell {
    public static final Color COLOR = Color.WHITE;

    public TW(Point point) {
        super(point, COLOR);
        drawText("TW");
    }

    public void drawText(String text) {
        label = new JLabel(text);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(font.deriveFont(Font.PLAIN, 10));
        this.add(label);
    }
}