package edu.melb.ds.pj2.gui.mainboard;

import java.awt.*;
import static java.awt.Font.PLAIN;

public class OccupiedCell extends BoardCell {

    public OccupiedCell(Point point, String text) {
        super(point, text, Color.ORANGE, Color.GRAY);
        this.drawText();
    }

    public OccupiedCell(Point point, String text, Color color) {
        super(point, text, color, Color.GRAY);
        this.drawText();
    }
        @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(color);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.setFont(new Font("", PLAIN, 32));
        graphics.setColor(Color.CYAN);
    }
}
