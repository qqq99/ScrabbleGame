package edu.melb.ds.pj2.gui.control;

import edu.melb.ds.pj2.gui.GameStatusCenter;
import edu.melb.ds.pj2.gui.Util;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class OperationBoard extends JPanel{
    private JButton pass;
    private JButton play;
    private JButton agree;
    private JButton disagree;
    private JPanel logoPanel;
    private JLabel picLabel;

    public OperationBoard() {
        super(new BorderLayout());
        // set the color of border to white with width 4
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // add operation buttons
        this.addButtons();

        // load and show the logo
        this.logoPanel = new JPanel();
        this.add(BorderLayout.CENTER, logoPanel);
        this.setBackground(Color.PINK);

        this.loadLogoPicture();
    }

    public void addButtons() {
        this.play = new JButton("Play Turn");
        this.pass = new JButton("Pass Turn");
        this.agree = new JButton("Agree");
        this.disagree = new JButton("Disagree");
        this.agree.setEnabled(false);
        this.disagree.setEnabled(false);

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.PINK);
        top.add(BorderLayout.NORTH, this.play);
        top.add(BorderLayout.SOUTH, this.pass);

        this.add(BorderLayout.NORTH, top);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(Color.PINK);
        bottom.add(BorderLayout.NORTH, this.agree);
        bottom.add(BorderLayout.SOUTH, this.disagree);

        this.add(BorderLayout.SOUTH, bottom);

        this.pass.addActionListener(e -> GameStatusCenter.passTurn());
        this.play.addActionListener(e -> GameStatusCenter.playTurn());
        this.agree.addActionListener(e -> GameStatusCenter.agree());
        this.disagree.addActionListener(e -> GameStatusCenter.disagree());
    }

    public void finishCurrentTurn() {
        this.play.setEnabled(false);
        this.pass.setEnabled(false);

        this.agree.setEnabled(true);
        this.disagree.setEnabled(true);
    }

    public void startNewTurn() {
        this.play.setEnabled(true);
        this.pass.setEnabled(true);

        this.agree.setEnabled(false);
        this.disagree.setEnabled(false);
    }

    public void loadLogoPicture() {
        try {
            Image logo = ImageIO.read(new File(Util.getResourcePath("logo.png")));
            this.picLabel = new JLabel(new ImageIcon(logo));
            this.picLabel.setBackground(Color.PINK);
            this.logoPanel.add(this.picLabel);
        } catch (IOException e) {
        }
        this.logoPanel.setLayout(new GridBagLayout());
        this.picLabel.setVerticalAlignment(SwingConstants.CENTER);
    }
}
