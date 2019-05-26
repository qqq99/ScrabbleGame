package edu.melb.ds.pj2.gui.control;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerBoard extends JPanel {
    private List<Player> players;

    public PlayerBoard(List<Player> players) {
        super(new GridLayout(players.size(), 1));

        this.setBackground(Color.ORANGE);
        this.players = players;
        this.addPlayers();
        }

    private void addPlayers() {
        for (Player player : this.players) {
            this.add(player);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void refresh() {
        this.removeAll();
        this.addPlayers();
        this.revalidate();
    }
}
