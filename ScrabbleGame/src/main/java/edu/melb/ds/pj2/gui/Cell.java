package edu.melb.ds.pj2.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import static java.awt.Font.PLAIN;

public class Cell extends JPanel implements MouseListener {
    protected Font font;
    protected Point point;
    protected JLabel label;
    protected String text;
    protected Color color, borderColor;

    public Cell(Point point, String text, Color color, Color borderColor) {
        this.point = point;
        this.color = color;
        this.text = text;
        this.borderColor = borderColor;
        this.font = new Font("default", PLAIN, 16);

        this.setLayout(new GridBagLayout());
        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(50, 50));
        this.setCellBorder(this.borderColor);
    }

    public void drawText() {
        this.label = new JLabel("<html><font size=5>" + this.text + "</font></html>");
        this.label.setVerticalAlignment(SwingConstants.CENTER);
        this.add(label);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setCellBorder(Color color) {
        this.borderColor = color;
        this.setBorder(BorderFactory.createLineBorder(borderColor, 2));
    }

    public int getXLoc() {
        return (int) point.getX();
    }

    public int getYLoc() {
        return (int) point.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        System.out.println("Cell Coordinates: [" + point.getX() + ", " + point.getY() + "]");
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(color);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.setFont(new Font("", PLAIN, 14));
        graphics.setColor(Color.BLACK);
    }
}