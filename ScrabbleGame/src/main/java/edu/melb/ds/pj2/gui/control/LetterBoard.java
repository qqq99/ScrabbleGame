package edu.melb.ds.pj2.gui.control;

import edu.melb.ds.pj2.gui.Util;

import java.awt.*;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class LetterBoard extends JPanel {
    public LetterCell[][] letterCells;
    private List<String> letters;

    public LetterBoard() {
        super(new GridLayout(2, 13));

        this.setBackground(Color.WHITE);

        this.letterCells = new LetterCell[2][13];
        this.letters = Util.getLetters();

        this.initLetterCells();
        this.addLetterCells();
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, Color.WHITE));
    }

    private void initLetterCells() {
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 13; j++) {
                letterCells[i][j] = new LetterCell(new Point(i, j), letters.get(k++));
            }
        }
    }

    private void addLetterCells() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 13; j++) {
                this.add(letterCells[i][j]);
            }
        }
    }

    public void refresh() {
        this.removeAll();
        this.addLetterCells();
        this.revalidate();
    }
}