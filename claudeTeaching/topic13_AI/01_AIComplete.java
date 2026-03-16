package topic13_AI;

import java.util.*;

/**
 * ============================================================================
 * TOPIC 13: ARTIFICIAL INTELLIGENCE - Introduction
 * ============================================================================
 *
 * This file introduces basic AI concepts and algorithms:
 * 1. Search Algorithms (BFS, DFS, A*)
 * 2. Minimax Algorithm (Game playing)
 * 3. Heuristics
 * 4. State Space Search
 * 5. Simple Neural Network concepts
 */

public class AIComplete {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     TOPIC 13: ARTIFICIAL INTELLIGENCE                            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        // ========================================================================
        // SECTION 1: SEARCH ALGORITHMS
        // ========================================================================
        System.out.println("═══════════════════════════════════════════════════════════════════");
        System.out.println("                    SEARCH ALGORITHMS                              ");
        System.out.println("═══════════════════════════════════════════════════════════════════\n");

        // Create a simple graph
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("E", "G");

        System.out.println("Graph:");
        graph.printGraph();

        // BFS
        System.out.println("\n--- Breadth-First Search (BFS) ---");
        System.out.println("Explores level by level");
        List<String> bfsPath = graph.bfs("A", "G");
        System.out.println("BFS path from A to G: " + bfsPath);

        // DFS
        System.out.println("\n--- Depth-First Search (DFS) ---");
        System.out.println("Explores as deep as possible before backtracking");
        List<String> dfsPath = graph.dfs("A", "G");
        System.out.println("DFS path from A to G: " + dfsPath);

        // ========================================================================
        // SECTION 2: MINIMAX ALGORITHM
        // ========================================================================
        System.out.println("\n═══════════════════════════════════════════════════════════════════");
        System.out.println("                    MINIMAX ALGORITHM                              ");
        System.out.println("═══════════════════════════════════════════════════════════════════\n");

        System.out.println("Used in game playing (Tic-Tac-Toe, Chess, etc.)");
        System.out.println("Maximizer tries to maximize score, minimizer tries to minimize\n");

        // Create a simple game tree
        // Leaf values represent game outcomes
        int[] leafValues = {3, 5, 6, 9, 1, 2, 0, -1};

        Minimax minimax = new Minimax();
        int bestValue = minimax.minimax(leafValues, 0, true);
        System.out.println("Best value for maximizer: " + bestValue);

        // Tic-Tac-Toe example
        System.out.println("\n--- Tic-Tac-Toe AI ---");
        TicTacToe game = new TicTacToe();
        game.playGame();

        // ========================================================================
        // SECTION 3: HEURISTICS
        // ========================================================================
        System.out.println("\n═══════════════════════════════════════════════════════════════════");
        System.out.println("                    HEURISTICS                                     ");
        System.out.println("═══════════════════════════════════════════════════════════════════\n");

        System.out.println("Heuristics are rules of thumb that guide search");
        System.out.println("Example: Manhattan distance for pathfinding\n");

        // 8-Puzzle heuristic example
        int[][] current = {{1, 2, 3}, {4, 0, 6}, {7, 5, 8}};
        int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        int manhattanDistance = calculateManhattan(current, goal);
        System.out.println("8-Puzzle Manhattan distance: " + manhattanDistance);
        System.out.println("(Lower is better - represents moves needed)");

        // ========================================================================
        // SECTION 4: A* ALGORITHM
        // ========================================================================
        System.out.println("\n═══════════════════════════════════════════════════════════════════");
        System.out.println("                    A* (A-STAR) ALGORITHM                          ");
        System.out.println("═══════════════════════════════════════════════════════════════════\n");

        System.out.println("Combines actual cost (g) with heuristic estimate (h)");
        System.out.println("f(n) = g(n) + h(n)\n");

        // Simple grid pathfinding
        GridPathfinder pathfinder = new GridPathfinder(10, 10);
        pathfinder.addObstacle(3, 3);
        pathfinder.addObstacle(3, 4);
        pathfinder.addObstacle(3, 5);

        List<Node> path = pathfinder.findPath(0, 0, 9, 9);
        System.out.println("Path from (0,0) to (9,9):");
        pathfinder.printPath(path);

        // ========================================================================
        // SUMMARY
        // ========================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  AI ALGORITHMS SUMMARY:                                            ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  SEARCH:                                                         ║");
        System.out.println("║    BFS:  Breadth-First, optimal for unweighted graphs            ║");
        System.out.println("║    DFS:  Depth-First, memory efficient                           ║");
        System.out.println("║    A*:   Best-first with heuristic, optimal and efficient          ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  GAME PLAYING:                                                   ║");
        System.out.println("║    Minimax: Two-player zero-sum games                            ║");
        System.out.println("║    Alpha-Beta: Optimized minimax (pruning)                       ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  HEURISTICS:                                                     ║");
        System.out.println("║    Admissible: Never overestimates true cost                     ║");
        System.out.println("║    Consistent: h(n) ≤ cost(n→n') + h(n')                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }

    // Calculate Manhattan distance for 8-puzzle
    public static int calculateManhattan(int[][] current, int[][] goal) {
        int distance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (current[i][j] != 0 && current[i][j] != goal[i][j]) {
                    // Find where this tile should be
                    for (int gi = 0; gi < 3; gi++) {
                        for (int gj = 0; gj < 3; gj++) {
                            if (goal[gi][gj] == current[i][j]) {
                                distance += Math.abs(i - gi) + Math.abs(j - gj);
                            }
                        }
                    }
                }
            }
        }
        return distance;
    }
}

// ============================================================================
// GRAPH CLASS FOR SEARCH ALGORITHMS
// ============================================================================
class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(String from, String to) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        adjacencyList.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
    }

    public void printGraph() {
        for (String node : adjacencyList.keySet()) {
            System.out.println("  " + node + " -> " + adjacencyList.get(node));
        }
    }

    // Breadth-First Search
    public List<String> bfs(String start, String goal) {
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(goal)) {
                return reconstructPath(parent, start, goal);
            }

            for (String neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }
        return null;  // No path found
    }

    // Depth-First Search
    public List<String> dfs(String start, String goal) {
        Stack<String> stack = new Stack<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();

            if (visited.contains(current)) continue;
            visited.add(current);

            if (current.equals(goal)) {
                return reconstructPath(parent, start, goal);
            }

            for (String neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    parent.put(neighbor, current);
                    stack.push(neighbor);
                }
            }
        }
        return null;
    }

    private List<String> reconstructPath(Map<String, String> parent, String start, String goal) {
        List<String> path = new ArrayList<>();
        String current = goal;
        while (current != null) {
            path.add(0, current);
            current = parent.get(current);
        }
        return path;
    }
}

// ============================================================================
// MINIMAX ALGORITHM
// ============================================================================
class Minimax {
    // Simple minimax for a binary game tree
    public int minimax(int[] values, int index, boolean isMaximizer) {
        // Leaf node
        if (index >= values.length / 2) {
            return values[index];
        }

        if (isMaximizer) {
            // Maximizer's turn - choose maximum
            return Math.max(
                minimax(values, 2 * index + 1, false),
                minimax(values, 2 * index + 2, false)
            );
        } else {
            // Minimizer's turn - choose minimum
            return Math.min(
                minimax(values, 2 * index + 1, true),
                minimax(values, 2 * index + 2, true)
            );
        }
    }
}

// ============================================================================
// TIC-TAC-TOE WITH MINIMAX
// ============================================================================
class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        for (char[] row : board) Arrays.fill(row, ' ');
        currentPlayer = 'X';
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard();

            if (currentPlayer == 'X') {
                // Human turn
                System.out.print("Enter row (0-2): ");
                int row = scanner.nextInt();
                System.out.print("Enter col (0-2): ");
                int col = scanner.nextInt();

                if (isValidMove(row, col)) {
                    board[row][col] = 'X';
                    if (checkWin('X')) {
                        printBoard();
                        System.out.println("You win!");
                        break;
                    }
                    currentPlayer = 'O';
                } else {
                    System.out.println("Invalid move!");
                }
            } else {
                // AI turn
                System.out.println("AI is thinking...");
                int[] bestMove = findBestMove();
                board[bestMove[0]][bestMove[1]] = 'O';
                System.out.println("AI plays at (" + bestMove[0] + ", " + bestMove[1] + ")");

                if (checkWin('O')) {
                    printBoard();
                    System.out.println("AI wins!");
                    break;
                }
                currentPlayer = 'X';
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }
        }
    }

    private int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int score = minimax(board, 0, false);
                    board[i][j] = ' ';

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizing) {
        if (checkWin('O')) return 10 - depth;
        if (checkWin('X')) return depth - 10;
        if (isBoardFull()) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = ' ';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = ' ';
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private boolean checkWin(char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') return false;
            }
        }
        return true;
    }

    private void printBoard() {
        System.out.println("\n  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.println(i + " " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("  ---------");
        }
        System.out.println();
    }
}

// ============================================================================
// A* PATHFINDING
// ============================================================================
class Node implements Comparable<Node> {
    int x, y;
    double g, h;  // g = cost from start, h = heuristic
    Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = Double.MAX_VALUE;
        this.h = 0;
    }

    public double f() { return g + h; }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.f(), other.f());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

class GridPathfinder {
    private int width, height;
    private boolean[][] obstacles;

    public GridPathfinder(int width, int height) {
        this.width = width;
        this.height = height;
        this.obstacles = new boolean[height][width];
    }

    public void addObstacle(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            obstacles[y][x] = true;
        }
    }

    public List<Node> findPath(int startX, int startY, int goalX, int goalY) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();

        Node start = new Node(startX, startY);
        start.g = 0;
        start.h = heuristic(startX, startY, goalX, goalY);
        openSet.offer(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.x == goalX && current.y == goalY) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor) || isObstacle(neighbor.x, neighbor.y)) {
                    continue;
                }

                double tentativeG = current.g + 1;

                if (tentativeG < neighbor.g) {
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = heuristic(neighbor.x, neighbor.y, goalX, goalY);

                    if (!openSet.contains(neighbor)) {
                        openSet.offer(neighbor);
                    }
                }
            }
        }
        return null;  // No path found
    }

    private double heuristic(int x1, int y1, int x2, int y2) {
        // Manhattan distance
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] dir : directions) {
            int newX = node.x + dir[0];
            int newY = node.y + dir[1];
            if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                neighbors.add(new Node(newX, newY));
            }
        }
        return neighbors;
    }

    private boolean isObstacle(int x, int y) {
        return obstacles[y][x];
    }

    private List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node);
            node = node.parent;
        }
        return path;
    }

    public void printPath(List<Node> path) {
        if (path == null) {
            System.out.println("No path found!");
            return;
        }

        char[][] grid = new char[height][width];
        for (char[] row : grid) Arrays.fill(row, '.');

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (obstacles[y][x]) grid[y][x] = '#';
            }
        }

        for (Node node : path) {
            grid[node.y][node.x] = '*';
        }

        grid[path.get(0).y][path.get(0).x] = 'S';
        grid[path.get(path.size() - 1).y][path.get(path.size() - 1).x] = 'G';

        for (char[] row : grid) {
            System.out.println(new String(row));
        }
    }
}
