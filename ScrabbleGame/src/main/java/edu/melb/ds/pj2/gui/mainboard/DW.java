package edu.melb.ds.pj2.gui.mainboard;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;

/**
 * Cell that with text "DW" on it, which means every words which have
 * letter be placed on this cell will get doubled score
 */
public class DW extends BoardCell {
    public static final Color COLOR = Color.LIGHT_GRAY;

    public DW(Point point) {
        super(point, COLOR);
        this.drawText("DW");
    }
    public void drawText(String text) {
        label = new JLabel(text);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(font.deriveFont(Font.PLAIN, 10));
        this.add(label);
    }
}