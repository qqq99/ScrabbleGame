package edu.melb.ds.pj2.gui;

import edu.melb.ds.pj2.gui.mainboard.GameBoard;
import edu.melb.ds.pj2.gui.control.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import javax.swing.*;

public class ScrabbleGame extends JFrame implements Runnable {
    public static ScrabbleGame gameInstance;
    public static int cellsUsed = 0;

    public static GameBoard mainGameBoard;
    public static StatusBar statusBar;
    public static JPanel bottom;
    public static LetterBoard letterBoard;
    public static PlayerBoard playerBoard;

    public static OperationBoard operationBoard;
    public static JMenuBar menuBar;

    public Player p1, p2;

    public ScrabbleGame() {
        super("ScrabbleGame");

        this.mainGameBoard = new GameBoard();
        this.bottom = new JPanel(new BorderLayout());
        this.letterBoard = new LetterBoard();
        this.statusBar = new StatusBar(400);
        this.operationBoard = new OperationBoard();
        this.menuBar = new JMenuBar();

        this.p1 = new Player("p1", true);
        this.p2 = new Player("p2", false);
        this.playerBoard = new PlayerBoard(Arrays.asList(this.p1, this.p2));

        // add menu
        this.setJMenuBar(menuBar);

        // set layout
        this.setWindowLayout();
    }

    public static void main(String[] args) {
        gameInstance = new ScrabbleGame();
        SwingUtilities.invokeLater(gameInstance);
    }

    public void run() {
        setWindowSize();
        addMenuItems();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setWindowSize() {
        setSize(1000, 1000);
        setMinimumSize(new Dimension(700, 700));
    }

    private void setWindowLayout() {
        getContentPane().add(BorderLayout.CENTER, mainGameBoard);
        getContentPane().add(BorderLayout.EAST, operationBoard);

        getContentPane().add(BorderLayout.NORTH, this.letterBoard);
        bottom.add(BorderLayout.CENTER, playerBoard);
        bottom.add(BorderLayout.SOUTH, statusBar);
        getContentPane().add(BorderLayout.SOUTH, bottom);
    }

    private void addMenuItems() {
        JMenu controlMenu = new JMenu("Operation");
        menuBar.add(controlMenu);
        JMenuItem newItem = new JMenuItem("New Game");
        JMenuItem joinItem = new JMenuItem("Join Game");
        JMenuItem exitItem = new JMenuItem("Exit Game");
        controlMenu.add(newItem);
        controlMenu.add(joinItem);
        controlMenu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));
    }
}
