package edu.melb.ds.pj2.gui.control;

import edu.melb.ds.pj2.gui.ScrabbleGame;

import javax.swing.JLabel;
import java.awt.Dimension;

public class StatusBar extends JLabel {

    private static final long serialVersionUID = 1L;
    private int cellsLeft;
    private String player;

    public StatusBar(int left) {
        cellsLeft = left;

        setPreferredSize(new Dimension(100, 40));
        setPlayer("p1");
        setMessage();
    }

    public void setMessage() {
        setText(cellsLeft + " cells left in total, " + player + "'s turn. "
                + ScrabbleGame.gameInstance.cellsUsed + " cells have been used.");
    }

    public void setPlayer(String name) {
        this.player = name;
    }
}