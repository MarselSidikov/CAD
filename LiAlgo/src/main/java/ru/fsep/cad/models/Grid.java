package ru.fsep.cad.models;

import ru.fsep.cad.models.Point;

/**
 * 22.01.17
 * Grid
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface Grid {
    int WALL = -1;
    int BLANK = -2;

    void setBlank(Point point);
    void setWall(Point point);
    int[][] getGridAsArray();
    int getHeight();
    int getWidth();
}
