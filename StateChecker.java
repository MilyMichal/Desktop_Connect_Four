package four;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StateChecker {

    public void checkFourInRow(JButton[][] gameBoard, String currPlayer) {
        ArrayList<JButton> winningCells = new ArrayList<>();
        for (JButton[] currentRow : gameBoard) {
            for (JButton button : currentRow) {
                if (winningCells.size() < 4) {
                    if (button.getText().equals(currPlayer)) {
                        winningCells.add(button);

                        if (winningCells.size() == 4) {
                            highlightWinner(winningCells,gameBoard);
                            break;
                        }

                    } else {

                        winningCells.clear();
                    }
                }
            }
        }
    }

    public void diagonalCheck(JButton[][] gameBoard, String currentPlayer) {
        ArrayList<JButton> winningCells = new ArrayList<>();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {

                if (i < gameBoard.length - 3 && j < gameBoard[i].length - 3) {
                    if (gameBoard[i][j].getText().equals(currentPlayer)
                            && gameBoard[i + 1][j + 1].getText().equals(currentPlayer)
                            && gameBoard[i + 2][j + 2].getText().equals(currentPlayer)
                            && gameBoard[i + 3][j + 3].getText().equals(currentPlayer)) {
                        winningCells.add(gameBoard[i][j]);
                        winningCells.add(gameBoard[i + 1][j + 1]);
                        winningCells.add(gameBoard[i + 2][j + 2]);
                        winningCells.add(gameBoard[i + 3][j + 3]);
                        highlightWinner(winningCells, gameBoard);
                        break;
                    }
                }
                if (i < gameBoard.length - 3 && j - 3 >= 0) {
                    if (gameBoard[i][j].getText().equals(currentPlayer)
                            && gameBoard[i + 1][j - 1].getText().equals(currentPlayer)
                            && gameBoard[i + 2][j - 2].getText().equals(currentPlayer)
                            && gameBoard[i + 3][j - 3].getText().equals(currentPlayer)) {
                        winningCells.add(gameBoard[i][j]);
                        winningCells.add(gameBoard[i + 1][j - 1]);
                        winningCells.add(gameBoard[i + 2][j - 2]);
                        winningCells.add(gameBoard[i + 3][j - 3]);
                        highlightWinner(winningCells, gameBoard);
                        break;

                    }
                }
            }
        }
    }

    public void checkFourInColum(int currentCol, String currPlayer, JButton[][] field) {
        ArrayList<JButton> winningCells = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
            if (winningCells.size() < 4) {
                if (field[i][currentCol].getText().equals(currPlayer)) {
                    winningCells.add(field[i][currentCol]);

                    if (winningCells.size() == 4) {
                        highlightWinner(winningCells, field);
                        break;
                    }

                } else {
                    winningCells.clear();

                }
            }
        }
    }

    private void highlightWinner(ArrayList<JButton> winningCells, JButton[][] field) {
        for (JButton button : winningCells) {
            button.setBackground(new Color(69, 215, 121));
        }
        for (JButton[] row : field) {
            for (JButton button : row) {
                button.setEnabled(false);
            }
        }
    }
}
