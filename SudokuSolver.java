/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.solver;

import java.util.*;

/**
 *
 * @author zxa
 */
public class SudokuSolver {

    /**
     * @param args the command line arguments
     */
    static boolean[][] rows;
    static boolean[][] cols;
    static boolean[][] boxes;

    public static void main(String[] args) {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = '.';
            }
        }
        board[0][0] = '1';
        solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
       
    }

    static int getBox(int x, int y) {
        int row = x / 3;
        int col = y / 3;

        return row * 3 + col;
    }

    public static void solveSudoku(char[][] board) {
        rows = new boolean[9][9];
        cols = new boolean[9][9];
        boxes = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    rows[i][(board[i][j] - '0') - 1] = true;
                    cols[j][(board[i][j] - '0') - 1] = true;
                    boxes[getBox(i, j)][(board[i][j] - '0') - 1] = true;
                }
            }
        }
        solveSudoku(board, 0, 0);
    }

    public static boolean solveSudoku(char[][] board, int x, int y) {
        if (x == 9) {
            return true;
        }
        if (board[x][y] != '.') {
            if (y + 1 == 9) {
                return solveSudoku(board, x + 1, 0);
            } else {
                return solveSudoku(board, x, y + 1);
            }
        }
        for (int c = 1; c <= 9; c++) {

            if (rows[x][c - 1] == false && cols[y][c - 1] == false && boxes[getBox(x, y)][c - 1] == false) {
                rows[x][c - 1] = true;
                cols[y][c - 1] = true;
                boxes[getBox(x, y)][c - 1] = true;
                boolean result = false;

                if (y + 1 == 9) {
                    result = solveSudoku(board, x + 1, 0);
                } else {
                    result = solveSudoku(board, x, y + 1);
                }
                if (result) {
                    board[x][y] = (char) (c + (char) '0');
                    return true;
                } else {
                    rows[x][c - 1] = false;
                    cols[y][c - 1] = false;
                    boxes[getBox(x, y)][c - 1] = false;
                }
            }
        }
        return false;
    }

   
}
