package edu.melb.ds.pj2.gui.mainboard;

import edu.melb.ds.pj2.gui.Cell;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Point;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard extends JPanel {
    public Cell[][] gameBoard;
    public List<Cell> dlCells, tlCells;
    public List<Cell> dwCells, twCells;

    public GameBoard() {
        super(new GridLayout(20, 20));
        this.setBackground(Color.WHITE);
        this.gameBoard = new Cell[20][20];
        this.initBoardCells();
        this.addSpecialCells();
        this.addGameCells();
        this.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 4, Color.WHITE));
    }

    public void initBoardCells() {
        for (int k = 0; k < 400; k++) {
            gameBoard[k / 20][k % 20] = new BoardCell(new Point(k / 20, k % 20), Color.PINK);
        }
    }

    public void addGameCells() {
        for (int a = 0; a < 20; a++) {
            for (int l = 0; l < 20; l++) {
                this.add(gameBoard[l][a]);
            }
        }
    }

    public void addGameCell(int x, int y, String letter) {
        Cell oldCell = this.gameBoard[x][y];

        BoardCell newCell;
        if (oldCell instanceof DL) {
            newCell = new OccupiedCell(new Point(x, y), letter, DL.COLOR);
        } else if (oldCell instanceof TL) {
            newCell = new OccupiedCell(new Point(x, y), letter, TL.COLOR);
        } else if (oldCell instanceof DW) {
            newCell = new OccupiedCell(new Point(x, y), letter, DW.COLOR);
        }  else if (oldCell instanceof TW) {
            newCell = new OccupiedCell(new Point(x, y), letter, TW.COLOR);
        }else{
            newCell = new OccupiedCell(new Point(x, y), letter);
        }

        this.gameBoard[x][y] = newCell;
        this.refresh();
    }

    public void removeGameCell(int x, int y) {
        Cell oldCell = this.gameBoard[x][y];
        BoardCell newCell;
        if (oldCell.getColor().equals(DL.COLOR)) {
            newCell = new DL(new Point(x, y));
        } else if (oldCell.getColor().equals(TL.COLOR)) {
            newCell = new TL(new Point(x, y));
        } else if (oldCell.getColor().equals(DW.COLOR)) {
            newCell = new DW(new Point(x, y));
        } else if (oldCell.getColor().equals(TW.COLOR)) {
            newCell = new TW(new Point(x, y));
        } else {
            newCell = new BoardCell(new Point(x, y), Color.PINK);
        }
        this.gameBoard[x][y] = newCell;
        this.refresh();
    }

    public void addSpecialCells() {
        this.setSpecialCells();

        this.dlCells.forEach(e -> {
            int x = e.getXLoc();
            int y = e.getYLoc();
            gameBoard[x][y] = new DL(new Point(x, y));
        });
        this.tlCells.forEach(e -> {
            int x = e.getXLoc();
            int y = e.getYLoc();
            gameBoard[x][y] = new TL(new Point(x, y));
        });
        this.dwCells.forEach(e -> {
            int x = e.getXLoc();
            int y = e.getYLoc();
            gameBoard[x][y] = new DW(new Point(x, y));
        });
        this.twCells.forEach(e -> {
            int x = e.getXLoc();
            int y = e.getYLoc();
            gameBoard[x][y] = new TW(new Point(x, y));
        });
    }

    /**
     * return the type of the board cell
     * @return 0: DL 1: TL 2: DW 3: TW 4: other
     */
    public int getCellType(int x, int y) {
        Cell cell = this.gameBoard[x][y];
        if (cell.getColor().equals(DL.COLOR)) {
            return 0;
        } else if (cell.getColor().equals(TL.COLOR)) {
            return 1;
        } else if (cell.getColor().equals(DW.COLOR)) {
            return 2;
        } else if (cell.getColor().equals(TW.COLOR)) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * add some special cells for the game gameBoard
     */
    public void setSpecialCells() {
        this.dlCells = Arrays.asList(gameBoard[0][4], gameBoard[0][15],
                gameBoard[3][9], gameBoard[3][11],
                gameBoard[5][0], gameBoard[5][10], gameBoard[5][19],
                gameBoard[7][2], gameBoard[7][8], gameBoard[7][14], gameBoard[7][19],
                gameBoard[9][4], gameBoard[9][15],
                gameBoard[12][2], gameBoard[12][8], gameBoard[12][14], gameBoard[12][19],
                gameBoard[14][0], gameBoard[14][10], gameBoard[14][19],
                gameBoard[16][9], gameBoard[16][11], gameBoard[19][4], gameBoard[19][15]);
        this.tlCells = new ArrayList<>(Arrays.asList(gameBoard[1][7], gameBoard[1][12],
                gameBoard[8][1], gameBoard[8][7], gameBoard[8][13], gameBoard[8][18],
                gameBoard[11][1], gameBoard[11][7], gameBoard[11][13], gameBoard[11][18],
                gameBoard[18][7], gameBoard[18][12]));
        this.dwCells = new ArrayList<>(Arrays.asList(gameBoard[1][1], gameBoard[2][2], gameBoard[3][3], gameBoard[4][4],
                gameBoard[5][5], gameBoard[6][6], gameBoard[7][7], gameBoard[8][8], gameBoard[9][9], gameBoard[10][10],
                gameBoard[11][11], gameBoard[12][12], gameBoard[13][13], gameBoard[14][14], gameBoard[15][15], gameBoard[16][16],
                gameBoard[17][17], gameBoard[18][18], gameBoard[19][19], gameBoard[1][18], gameBoard[2][17], gameBoard[3][16],
                gameBoard[4][15], gameBoard[5][14], gameBoard[6][13], gameBoard[7][12], gameBoard[8][11],
                gameBoard[11][8], gameBoard[12][7], gameBoard[13][6], gameBoard[14][5], gameBoard[15][4],
                gameBoard[16][3], gameBoard[17][2], gameBoard[18][1]));
        this.twCells = Arrays.asList(gameBoard[0][0], gameBoard[0][19], gameBoard[19][0], gameBoard[19][19],
                gameBoard[0][10], gameBoard[10][0], gameBoard[19][10], gameBoard[10][19]);
    }

    public void refresh() {
        removeAll();
        addGameCells();
        revalidate();
    }
}