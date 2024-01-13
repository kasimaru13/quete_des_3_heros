package main.java.com.quete_des_3_heros.controller;

import main.java.com.quete_des_3_heros.element.Element;

import java.util.*;

import static java.awt.geom.Point2D.distance;

public class PathfindingController {
    /**
     * Represents a point with integer coordinates in a 2D space.
     * Each point may have a reference to a previous point, forming a linked structure.
     */
    public static class Point {
        public int x;           // X-coordinate of the point
        public int y;           // Y-coordinate of the point
        public Point previous;  // Reference to the previous point in a linked structure

        /**
         * Constructs a Point with specified coordinates and a reference to a previous point.
         *
         * @param x        The X-coordinate of the point.
         * @param y        The Y-coordinate of the point.
         * @param previous A reference to the previous point in a linked structure.
         */
        public Point(int x, int y, Point previous) {
            this.x = x;
            this.y = y;
            this.previous = previous;
        }

        /**
         * Returns a string representation of the point in the format "(x, y)".
         *
         * @return A string representation of the point.
         */
        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }

        /**
         * Checks if the current point is equal to another object.
         *
         * @param o The object to compare with.
         * @return True if the objects are equal; false otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        /**
         * Computes a hash code for the point based on its coordinates.
         *
         * @return The hash code for the point.
         */
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        /**
         * Creates a new Point by offsetting the current point with specified values.
         *
         * @param ox The offset in the X-coordinate.
         * @param oy The offset in the Y-coordinate.
         * @return A new Point resulting from the offset.
         */
        public Point offset(int ox, int oy) {
            return new Point(x + ox, y + oy, this);
        }
    }


    /**
     * Checks if a specified point on the grid is walkable.
     *
     * @param grid  The 2D grid representing the environment.
     * @param point The point to be checked for walkability.
     * @return      True if the point is within the grid boundaries and is not occupied; false otherwise.
     */
    public static boolean IsWalkable(Element[][] grid, Point point) {
        // Check if the point is within the grid boundaries
        if (point.x < 0 || point.x > grid.length - 1) return false;
        if (point.y < 0 || point.y > grid[0].length - 1) return false;

        // Check if the point is not occupied
        return grid[point.x][point.y] == null;
    }


    /**
     * Finds neighboring points around a given point on a 2D grid, considering walkability.
     *
     * @param grid  The 2D grid representing the environment.
     * @param point The central point for which neighbors are to be found.
     * @return      A list of walkable neighboring points around the central point.
     */
    public static List<Point> FindNeighbors(Element[][] grid, Point point) {
        List<Point> neighbors = new ArrayList<>();
        Point up = point.offset(0, 1);
        Point down = point.offset(0, -1);
        Point left = point.offset(-1, 0);
        Point right = point.offset(1, 0);

        // Add walkable neighboring points to the list
        if (IsWalkable(grid, up)) neighbors.add(up);
        if (IsWalkable(grid, down)) neighbors.add(down);
        if (IsWalkable(grid, left)) neighbors.add(left);
        if (IsWalkable(grid, right)) neighbors.add(right);

        return neighbors;
    }


    /**
     * Finds all unique paths on a 2D grid starting from a given point within a maximum distance.
     *
     * @param grid         The 2D grid representing the environment.
     * @param start        The starting point for path exploration.
     * @param maxDistance  The maximum distance allowed from the starting point.
     * @return             A 2D array containing all unique points found during the path exploration.
     */
    public static int[][] FindAllPaths(Element[][] grid, Point start, int maxDistance) {
        List<int[]> allPointsList = new ArrayList<>();  // List to store all unique points found during exploration
        List<Point> currentPath = new ArrayList<>();    // Current path being explored

        // Recursively find paths from the start point
        FindPathsRecursively(grid, start, maxDistance, currentPath, allPointsList);

        // Convert the list of points to a 2D array
        int[][] allPathsArray = new int[allPointsList.size()][2];
        for (int i = 0; i < allPointsList.size(); i++) {
            allPathsArray[i][0] = allPointsList.get(i)[0];
            allPathsArray[i][1] = allPointsList.get(i)[1];
        }

        // Remove redundant elements from the 2D array and return the result
        return removeRedundantElements(allPathsArray);
    }


    /**
     * Recursively finds paths on a 2D grid starting from a given point within a maximum distance.
     *
     * @param grid           The 2D grid representing the environment.
     * @param current        The current point in the search.
     * @param maxDistance    The maximum distance allowed from the starting point.
     * @param currentPath    The current path being explored.
     * @param allPointsList  A list to store all unique points found during the recursive search.
     */
    private static void FindPathsRecursively(Element[][] grid, Point current, int maxDistance, List<Point> currentPath, List<int[]> allPointsList) {
        // Base case: if the maximum distance is reached
        if (maxDistance == 0) {
            // Add each point in the current path to the list of all points
            for (Point point : currentPath) {
                allPointsList.add(new int[]{point.x, point.y});
            }
            return;
        }

        // Explore neighbors of the current point
        for (Point neighbor : FindNeighbors(grid, current)) {
            // If the neighbor is not already in the current path
            if (!currentPath.contains(neighbor)) {
                currentPath.add(neighbor);  // Add the neighbor to the current path
                // Recursively explore paths from the neighbor with reduced maxDistance
                FindPathsRecursively(grid, neighbor, maxDistance - 1, currentPath, allPointsList);
                currentPath.remove(currentPath.size() - 1);  // Backtrack: remove the last point from the current path
            }
        }
    }


    /**
     * Removes redundant elements from a 2D array of integer pairs.
     *
     * @param inputArray The 2D array containing integer pairs.
     * @return A new 2D array with redundant elements removed, preserving the order of the first occurrence.
     */
    public static int[][] removeRedundantElements(int[][] inputArray) {
        Set<String> uniquePairs = new HashSet<>();  // Set to track unique pairs as strings
        List<int[]> result = new ArrayList<>();  // List to store unique pairs

        // Iterate through the input array to identify and retain unique pairs
        for (int[] pair : inputArray) {
            String pairString = pair[0] + "," + pair[1];

            // Add the string representation of the pair to the set
            // If it's a new pair, add it to the result list
            if (uniquePairs.add(pairString)) {
                result.add(pair);
            }
        }

        // Convert the result list to a 2D array
        int[][] resultArray = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;  // Return the 2D array with redundant elements removed
    }


    /**
     * Finds a path from the start point to the end point on a 2D grid.
     *
     * @param grid              The 2D grid representing the environment.
     * @param start             The starting point of the path.
     * @param end               The end point to reach.
     * @param proximityThreshold The maximum distance allowed from the end point for path completion.
     * @return                  A list of points representing the path from start to end, or null if no path is found.
     */
    public static List<Point> FindPathMonster(Element[][] grid, Point start, Point end, int proximityThreshold) {
        // Initialization of variables
        boolean finished = false;  // Indicates whether the pathfinding is finished
        List<Point> used = new ArrayList<>();  // List of points used during the search
        used.add(start);  // Add the starting point to the used list

        // Main pathfinding loop
        while (!finished) {
            List<Point> newOpen = new ArrayList<>();  // List of new points open for exploration
            // Iterate through the already used points
            for (int i = 0; i < used.size(); ++i) {
                Point point = used.get(i);
                // Iterate through the neighbors of the current point
                for (Point neighbor : FindNeighbors(grid, point)) {
                    // Add neighbors that are not used and not in the newOpen list
                    if (!used.contains(neighbor) && !newOpen.contains(neighbor)) {
                        newOpen.add(neighbor);
                    }
                }
            }

            // Iterate through the new open points
            for (Point point : newOpen) {
                used.add(point);  // Add the new point to the used list

                // Calculate the distance to the target
                int distanceToTarget = (int) distance(point.x, point.y, end.x, end.y);
                // Check if the distance is less than or equal to the proximity threshold
                if (distanceToTarget <= proximityThreshold) {
                    finished = true;  // Stop the search if the proximity threshold is reached
                    break;
                }

                // Check if the current point is equal to the end point
                if (end.equals(point)) {
                    finished = true;  // Stop the search if the end point is reached
                    break;
                }
            }

            // Check for completion of the search and if the newOpen list is empty
            if (!finished && newOpen.isEmpty())
                return null;  // Return null if the search is finished and no path is found
        }

        // Reconstruct the path from the used points
        List<Point> path = new ArrayList<>();
        Point point = used.get(used.size() - 1);
        // Read the path from the last point using the "previous" references
        while (point.previous != null) {
            path.add(0, point);  // Add the point to the beginning of the list (reverse order)
            point = point.previous;
        }
        return path;  // Return the found path
    }
}
