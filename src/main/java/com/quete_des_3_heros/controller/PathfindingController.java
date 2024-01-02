package main.java.com.quete_des_3_heros.controller;

import main.java.com.quete_des_3_heros.element.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathfindingController {
    public static class Point{
        public int x;
        public int y;
        public Point previous;

        public Point(int x, int y, Point previous){
            this.x = x;
            this.y = y;
            this.previous = previous;
        }

        @Override
        public String toString() { return String.format("(%d, %d)", x, y); }

        @Override
        public boolean equals(Object o) {
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() { return Objects.hash(x, y); }

        public Point offset(int ox, int oy) {
            return new Point(x + ox, y + oy, this);
        }
    }

    public static boolean IsWalkable(Entity[][] grid, Point point) {
        if (point.x < 0 || point.x > grid.length - 1) return false;
        if (point.y < 0 || point.y > grid[0].length - 1) return false;
        return grid[point.x][point.y] == null;
    }

    public static List<Point> FindNeighbors(Entity[][] grid, Point point) {
        List<Point> neighbors = new ArrayList<>();
        Point up = point.offset(0,  1);
        Point down = point.offset(0,  -1);
        Point left = point.offset(-1, 0);
        Point right = point.offset(1, 0);
        if (IsWalkable(grid, up)) neighbors.add(up);
        if (IsWalkable(grid, down)) neighbors.add(down);
        if (IsWalkable(grid, left)) neighbors.add(left);
        if (IsWalkable(grid, right)) neighbors.add(right);
        return neighbors;
    }

    public static List<Point> FindPath(Entity[][] grid, Point start, Point end) {
        boolean finished = false;
        List<Point> used = new ArrayList<>();
        used.add(start);
        while (!finished) {
            List<Point> newOpen = new ArrayList<>();
            for(int i = 0; i < used.size(); ++i){
                Point point = used.get(i);
                for (Point neighbor : FindNeighbors(grid, point)) {
                    if (!used.contains(neighbor) && !newOpen.contains(neighbor)) {
                        newOpen.add(neighbor);
                    }
                }
            }

            for(Point point : newOpen) {
                used.add(point);
                if (end.equals(point)) {
                    finished = true;
                    break;
                }
            }

            if (!finished && newOpen.isEmpty())
                return null;
        }

        List<Point> path = new ArrayList<>();
        Point point = used.get(used.size() - 1);
        while(point.previous != null) {
            path.add(0, point);
            point = point.previous;
        }
        return path;
    }
}
