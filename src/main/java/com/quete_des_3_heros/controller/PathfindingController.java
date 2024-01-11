package main.java.com.quete_des_3_heros.controller;

import main.java.com.quete_des_3_heros.element.Element;
import main.java.com.quete_des_3_heros.element.Entity;

import java.util.*;

import static java.awt.geom.Point2D.distance;

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

    public static boolean IsWalkable(Element[][] grid, Point point) {
        if (point.x < 0 || point.x > grid.length - 1) return false;
        if (point.y < 0 || point.y > grid[0].length - 1) return false;
        return grid[point.x][point.y] == null;
    }

    public static List<Point> FindNeighbors(Element[][] grid, Point point) {
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

    public static List<Point> FindPath(Element[][] grid, Point start, Point end) {
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

    public static int[][] FindAllPaths(Element[][] grid, Point start, int maxDistance) {
        List<int[]> allPointsList = new ArrayList<>();
        List<Point> currentPath = new ArrayList<>();

        FindPathsRecursively(grid, start, maxDistance, currentPath, allPointsList);

        // Convertir la liste de points en tableau d'entiers
        int[][] allPathsArray = new int[allPointsList.size()][2];
        for (int i = 0; i < allPointsList.size(); i++) {
            allPathsArray[i][0] = allPointsList.get(i)[0];
            allPathsArray[i][1] = allPointsList.get(i)[1];
        }

        return removeRedundantElements(allPathsArray);
    }

    private static void FindPathsRecursively(Element[][] grid, Point current, int maxDistance,
                                             List<Point> currentPath, List<int[]> allPointsList) {
        if (maxDistance == 0) {
            // Ajouter tous les points du chemin à la liste
            for (Point point : currentPath) {
                allPointsList.add(new int[]{point.x, point.y});
            }
            return;
        }

        for (Point neighbor : FindNeighbors(grid, current)) {
            if (!currentPath.contains(neighbor)) {
                currentPath.add(neighbor);
                FindPathsRecursively(grid, neighbor, maxDistance - 1, currentPath, allPointsList);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    public static int[][] removeRedundantElements(int[][] inputArray) {
        Set<String> uniquePairs = new HashSet<>();
        List<int[]> result = new ArrayList<>();

        for (int[] pair : inputArray) {
            String pairString = pair[0] + "," + pair[1];

            if (uniquePairs.add(pairString)) {
                // Si la paire n'est pas déjà présente, on l'ajoute au résultat
                result.add(pair);
            }
        }

        // Convertir la liste résultante en un tableau
        int[][] resultArray = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }


    public static List<Point> FindPathMonster(Element[][] grid, Point start, Point end, int proximityThreshold) {
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

                int distanceToTarget = (int) distance(point.x, point.y, end.x, end.y);
                if (distanceToTarget <= proximityThreshold) {
                    finished = true;
                    break;
                }

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
