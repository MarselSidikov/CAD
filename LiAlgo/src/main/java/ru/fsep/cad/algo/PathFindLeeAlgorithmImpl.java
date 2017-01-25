package ru.fsep.cad.algo;

import ru.fsep.cad.models.Grid;
import ru.fsep.cad.models.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 22.01.17
 * PathFindLeeAlgorithmImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class PathFindLeeAlgorithmImpl implements PathFindAlgorithm {

    private final static int START_VALUE = 0;

    private final static int NEIGHBORS_COUNT = 4;

    private final static int stepsX[] = {1, 0, -1, 0};
    private final static int stepsY[] = {0, 1, 0, -1};

    private int currentValue;
    private boolean allVisited;

    private int[][] grid;
    private int height;
    private int width;

    public List<Point> findPath(Grid grid, Point start, Point end) {
        this.grid = grid.getGridAsArray();
        this.height = grid.getHeight();
        this.width = grid.getWidth();

        if (waveExpansion(start, end)) {
            return backtrace(start, end);
        } else return new ArrayList<Point>();
    }

    private boolean waveExpansion(Point start, Point end) {
        if (isNotCorrectConditions(start, end)) return false;

        currentValue = 0;

        setStart(start);

        do {
            allVisited = true;
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    Point currentPoint = new Point(x, y);
                    if (isVisited(currentPoint)) {
                        visitNeighbors(currentPoint);
                    }
                }
            }
            currentValue++;
        } while (!allVisited && endIsBlank(end));
        return !endIsBlank(end);
    }

    private boolean isNotCorrectConditions(Point start, Point end) {
        return grid[start.getY()][start.getX()] == Grid.WALL || grid[end.getY()][end.getX()] == Grid.WALL;
    }

    private boolean isVisited(Point currentPoint) {
        return grid[currentPoint.getY()][currentPoint.getX()] == currentValue;
    }

    private void visitNeighbors(Point currentPoint) {
        for (int k = 0; k < NEIGHBORS_COUNT; ++k ) {               // проходим по всем непомеченным соседям
            Point neighbor = new Point(currentPoint.getX() + stepsX[k], currentPoint.getY() + stepsY[k]);
            if (neighborInGrid(neighbor) && neighborIsBlank(neighbor)) {
                allVisited = false;              // найдены непомеченные клетки
                grid[neighbor.getY()][neighbor.getX()] = currentValue + 1;      // распространяем волну
            }
        }
    }

    private boolean neighborInGrid(Point neighbor) {
        return neighbor.getY() >= 0
                && neighbor.getY() < height
                && neighbor.getX() >= 0
                && neighbor.getX() < width;
    }

    private boolean neighborIsBlank(Point neighbor) {
        return grid[neighbor.getY()][neighbor.getX()] == Grid.BLANK;
    }

    private List<Point> backtrace(Point start, Point end) {

        List<Point> path = new ArrayList<Point>();

        int length = grid[end.getY()][end.getX()];

        Point currentPoint = end;

        currentValue = length;

        while (currentValue > 0) {
            path.add(currentPoint);

            currentValue--;

            for (int k = 0; k < NEIGHBORS_COUNT; ++k) {
                Point neighbor = new Point(currentPoint.getX() + stepsX[k], currentPoint.getY() + stepsY[k]);
                if (isMinValueNeighbor(neighbor)) {
                    currentPoint = neighbor;
                    break;
                }
            }
        }
        path.add(start);

        return path;
    }

    private boolean endIsBlank(Point end) {
        return grid[end.getY()][end.getX()] == Grid.BLANK;
    }

    private boolean isMinValueNeighbor(Point neighbor) {
        return neighbor.getY() >= 0
                && neighbor.getY() < height
                && neighbor.getX() >= 0
                && neighbor.getX() < width
                && grid[neighbor.getY()][neighbor.getX()] == currentValue;
    }

    private void setStart(Point start) {
        this.grid[start.getY()][start.getX()] = START_VALUE;
    }
}
