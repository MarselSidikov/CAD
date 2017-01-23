package ru.fsep.cad;

import ru.fsep.cad.algo.PathFindAlgorithm;
import ru.fsep.cad.algo.PathFindLeeAlgorithmImpl;
import ru.fsep.cad.models.Grid;
import ru.fsep.cad.models.GridTableImpl;
import ru.fsep.cad.models.Point;

import java.util.List;

/**
 * 22.01.17
 * Program
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Program {
    public static void main(String[] args) {
        Grid grid = GridTableImpl.buildGrid(1024, 1024);

        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 1024; j++) {
                grid.setBlank(new Point(j,i));
            }
        }

        PathFindAlgorithm algorithm = new PathFindLeeAlgorithmImpl();

        grid.setWall(new Point(2, 0));
        grid.setWall(new Point(2, 1));
        grid.setWall(new Point(1, 5));
        grid.setWall(new Point(2, 5));
        grid.setWall(new Point(3, 5));
        grid.setWall(new Point(4, 5));
        grid.setWall(new Point(8, 3));
        grid.setWall(new Point(9, 3));
        grid.setWall(new Point(4, 0));


        List<Point> points = algorithm.findPath(grid, new Point(3,6), new Point(3, 0));

        System.out.println("---------");
        for (int row = 0; row < grid.getGridAsArray().length; row++) {
            for (int col = 0; col < grid.getGridAsArray()[row].length; col++) {
                if (hasPoint(points,col, row)) {
                    System.out.printf("%1s","*");
                } else if (grid.getGridAsArray()[row][col] == -1) {
                    System.out.printf("%1s","@");
                } else System.out.printf("%1s", ".");
            }
            System.out.println();
        }

    }

    public static boolean hasPoint(List<Point> points, int i, int j) {
        for (Point point : points) {
            if (point.getX() == i && point.getY() == j) {
                return true;
            }
        }
        return false;
    }
}
