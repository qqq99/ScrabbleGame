package edu.melb.ds.pj2.gui.control;

import javax.swing.*;
import java.awt.*;

public class Player extends JLabel {
    private boolean active;
    private String name;
    private int score = 0;

    public Player(String name, boolean active) {
        this.name = name;
        this.active = active;

        setPreferredSize(new Dimension(100, 20));
        this.setMsg();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMsg() {
        if (this.active) {
           this.setForeground(Color.BLUE);
        } else {
            this.setForeground(Color.GRAY);
        }
        super.setText("Player: " + this.name + "    Status: "
                + (this.active ? "active" : "inactive") + "    Score: " + this.score);
    }
}


