package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MatrixSolution {

    public static boolean isValidSudoku(char[][] board) {
        int[][] row = new int[9][9];
        int[][] colum = new int[9][9];
        int[][][] box = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '1';
                    row[i][index]++;
                    colum[j][index]++;
                    box[i / 3][j / 3][index]++;
                    if (row[i][index] > 1 || colum[j][index] > 1 || box[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int weight = matrix[0].length;
        int height = matrix.length;
        int u = 0;
        int l = 0;
        int r = weight;
        int d = height;
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (result.size() < weight * height) {
            if (j == l && i == u && !result.isEmpty()) {
                u++;
                l++;
                r--;
                d--;
                i++;
                j++;
            }
            result.add(matrix[i][j]);
            if (j < r - 1 && i == u) {
                j++;
            } else if (j == r - 1 && i < d - 1) {
                i++;
            } else if (j > l && i == d - 1) {
                j--;
            } else if (i > u && j == l) {
                i--;
            }
        }
        return result;
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = temp;
            }
        }
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> column = new HashSet<>();
        int weight = matrix[0].length;
        int height = matrix.length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    column.add(j);
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                if (row.contains(i) || column.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void gameOfLife(int[][] board) {
        int weight = board[0].length;
        int height = board.length;
        int[][] score = new int[height][weight];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                if (board[i][j] == 1) {
                    if (i + 1 < height) {
                        score[i + 1][j] += 1;
                        if (j + 1 < weight) {
                            score[i + 1][j + 1] += 1;
                        }
                        if (j - 1 >= 0) {
                            score[i + 1][j - 1] += 1;
                        }
                    }
                    if (j + 1 < weight) {
                        score[i][j + 1] += 1;
                    }
                    if (j - 1 >= 0) {
                        score[i][j - 1] += 1;
                    }
                    if (i - 1 >= 0) {
                        score[i - 1][j] += 1;
                        if (j + 1 < weight) {
                            score[i - 1][j + 1] += 1;
                        }
                        if (j - 1 >= 0) {
                            score[i - 1][j - 1] += 1;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                if (board[i][j] == 1) {
                    if (score[i][j] < 2 || score[i][j] > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (score[i][j] == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        gameOfLife(new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}});
    }

}
