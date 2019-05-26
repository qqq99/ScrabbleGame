package edu.melb.ds.pj2.gui;

import edu.melb.ds.pj2.gui.control.LetterCell;
import edu.melb.ds.pj2.gui.control.Player;
import edu.melb.ds.pj2.gui.mainboard.BoardCell;
import java.awt.*;
import java.util.List;

public class GameStatusCenter {
    private static String activePlayerName = "p1";
    private static boolean isActive = true;
    private static boolean firstLetter = true;

    /**
     * The letters have been placed on board
     */
    public static String[][] boardLetters = new String[20][20];

    public static int totalPlacedLetters = 0;

    public static BoardCell previousPlacedCell = null;
    public static LetterCell activeLetter = null;

    static {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                boardLetters[i][j] = "";
            }
        }
    }

    public static void addLetterToBoard(BoardCell boardCell) {
        if (isActive && activeLetter != null) {
            int x = boardCell.getXLoc();
            int y = boardCell.getYLoc();

            if (!checkValidPlace(x, y)) {
                return;
            }
            boardLetters[x][y] = activeLetter.getText();

            if (previousPlacedCell != null) {
                int px = previousPlacedCell.getXLoc();
                int py = previousPlacedCell.getYLoc();

                ScrabbleGame.mainGameBoard.removeGameCell(px, py);
                boardLetters[px][py] = "";
                ScrabbleGame.mainGameBoard.addGameCell(x, y, boardLetters[x][y]);
                previousPlacedCell = boardCell;
            }
            else {
                totalPlacedLetters += 1;
                previousPlacedCell = boardCell;
                ScrabbleGame.mainGameBoard.addGameCell(x, y, boardLetters[x][y]);
            }
        }
    }


    public static void setActiveLetter(LetterCell cell) {
        if (isActive) {
            if (GameStatusCenter.activeLetter != null && GameStatusCenter.activeLetter == cell) {
                cell.setCellBorder(Color.WHITE);
                cell.setColor(Color.ORANGE);
                GameStatusCenter.activeLetter = null;
                cell.repaint();
            } else {
                if (GameStatusCenter.activeLetter != null) {
                    GameStatusCenter.activeLetter.setCellBorder(Color.WHITE);
                    GameStatusCenter.activeLetter.setColor(Color.ORANGE);
                    GameStatusCenter.activeLetter.repaint();
                }
                if (cell != null) {
                    cell.setCellBorder(Color.GRAY);
                    cell.setColor(new Color(255, 230, 50));
                    GameStatusCenter.activeLetter = cell;
                    cell.repaint();
                }
            }
        }
    }

    public static void setIsActive(boolean isActive) {
        setActiveLetter(null);
        GameStatusCenter.isActive = isActive;
    }

    public static void playTurn() {
        setIsActive(false);
        ScrabbleGame.operationBoard.finishCurrentTurn();

        if (previousPlacedCell != null) {
            firstLetter = false;
        }
    }

    public static void passTurn() {
        if (previousPlacedCell != null) {
            boardLetters[previousPlacedCell.getXLoc()][previousPlacedCell.getYLoc()] = "";
            ScrabbleGame.mainGameBoard.removeGameCell(previousPlacedCell.getXLoc(), previousPlacedCell.getYLoc());
            totalPlacedLetters -= 1;
            if (totalPlacedLetters == 0) {
                firstLetter = true;
            }
        }
        previousPlacedCell = null;
        setIsActive(false);

        ScrabbleGame.operationBoard.finishCurrentTurn();
    }

    public static void agree() {
        refreshPlayerStatus(true);
        ScrabbleGame.operationBoard.startNewTurn();
        previousPlacedCell = null;
    }

    public static void disagree() {
        refreshPlayerStatus(false);
        ScrabbleGame.operationBoard.startNewTurn();
        previousPlacedCell = null;
    }

    private static void refreshPlayerStatus(boolean agree) {
        List<Player> allPlayers = ScrabbleGame.gameInstance.playerBoard.getPlayers();
        allPlayers.forEach(e -> {
            if (e.getName().equals(activePlayerName)) {
                if (agree) {
                    e.setScore(e.getScore() + getScore());
                }
                e.setActive(false);
                e.setMsg();
            }
        });

        if (activePlayerName.equals("p1")) {
            activePlayerName = "p2";
        } else {
            activePlayerName = "p1";
        }

        allPlayers.forEach(e -> {
            if (e.getName().equals(activePlayerName)) {
                e.setActive(true);
                e.setMsg();
            }
        });

        ScrabbleGame.playerBoard.refresh();
        activeLetter = null;
        setIsActive(true);
    }

    private static int getScore() {
        if (previousPlacedCell != null) {
            int x = previousPlacedCell.getXLoc();
            int y = previousPlacedCell.getYLoc();

            int score = verticalScore(x, y) + horizontalScore(x, y);
            return score;
        } else {
            return 0;
        }
    }

    private static boolean checkValidPlace(int x, int y) {
        if (!boardLetters[x][y].isEmpty()) {
            return false;
        } else {
            if (firstLetter) {
                return true;
            }
            return (x - 1 >=0 && !boardLetters[x - 1][y].isEmpty() && !isPreviousOne(x - 1, y)) ||
                    (y - 1 >= 0 && !boardLetters[x][y - 1].isEmpty() && !isPreviousOne(x, y - 1)) ||
                    (x + 1 < 20 && !boardLetters[x + 1][y].isEmpty() && !isPreviousOne(x + 1, y)) ||
                    (y + 1 < 20 && !boardLetters[x][y + 1].isEmpty()  && !isPreviousOne(x, y + 1));
        }
    }

    private static boolean isPreviousOne(int x, int y) {
        if (previousPlacedCell == null) {
            return false;
        } else {
            return x == previousPlacedCell.getXLoc() && y == previousPlacedCell.getYLoc();
        }
    }

    private static int verticalScore(int x, int y) {
        int start = y - 1;
        int end = y + 1;
        while (start >=0 && !boardLetters[x][start].isEmpty()) {
            --start;
        }
        while (end < 20 && !boardLetters[x][end].isEmpty()) {
            ++end;
        }
        start += 1;
        end -= 1;
        if (end - start < 1) {
            return 0;
        }
        else {
            int score = 0;
            int multiTimes = 0;
            for (int i = start; i <= end; i++) {
                int type = ScrabbleGame.mainGameBoard.getCellType(x, i);
                if (type == 0) {
                    score += 2;
                } else if (type == 1) {
                    score += 3;
                } else if (type == 2) {
                    score += 1;
                    multiTimes += 2;
                } else if (type == 3) {
                    score += 1;
                    multiTimes += 3;
                } else {
                    score += 1;
                }
            }
            if (multiTimes != 0) {
                return score * multiTimes;
            } else {
                return score;
            }
        }
    }

    private static int horizontalScore(int x, int y) {
        int start = x - 1;
        int end = x + 1;
        while (start >= 0 && !boardLetters[start][y].isEmpty()) {
            --start;
        }
        while (end < 20 && !boardLetters[end][y].isEmpty()) {
            ++end;
        }
        start += 1;
        end -= 1;
        if (end - start < 1) {
            return 0;
        }
        else {
            int score = 0;
            int multiTimes = 0;
            for (int i = start; i <= end; i++) {
                int type = ScrabbleGame.mainGameBoard.getCellType(i, y);
                if (type == 0) {
                    score += 2;
                } else if (type == 1) {
                    score += 3;
                } else if (type == 2) {
                    score +=1;
                    multiTimes += 2;
                } else if (type == 3) {
                    score +=1;
                    multiTimes += 3;
                } else {
                    score += 1;
                }
            }
            if (multiTimes != 0) {
                return score * multiTimes;
            } else {
                return score;
            }
        }
    }
}
