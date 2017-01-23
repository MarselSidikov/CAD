package ru.fsep.cad.algo;

import ru.fsep.cad.models.Grid;
import ru.fsep.cad.models.Point;

import java.util.List;

/**
 * 22.01.17
 * PathFindAlgorithm
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface PathFindAlgorithm {
    List<Point> findPath(Grid grid, Point start, Point end);
}
