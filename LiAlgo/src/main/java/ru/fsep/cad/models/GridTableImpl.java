package ru.fsep.cad.models;

/**
 * 22.01.17
 * GridTableImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class GridTableImpl implements Grid{

    private int height;
    private int width;

    private int grid[][];

    public static GridTableImpl buildGrid(int height, int width) {
        return new GridTableImpl(height, width);
    }

    private GridTableImpl(int height, int width) {
        this.height = height;
        this.width = width;

        this.grid = new int[height][];

        for (int i = 0; i < height; i++) {
            grid[i] = new int[width];
        }
    }

    public void setWall(Point point) {
        this.grid[point.getY()][point.getX()] = WALL;
    }

    public void setBlank(Point point) {
        this.grid[point.getY()][point.getX()] = BLANK;
    }

    public int[][] getGridAsArray() {
        return this.grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
