package com.okayjam.code.leetcode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/08/02 20:05
 **/
public class Grid {
    public static void main(String[] args) {
        System.out.println("Default main method!");
    }

    private final static int[][] DIRECTIONS4 = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private final int[][] direction = new int[][] {{-1,-1},{-1,0},{-1,1}, {0,-1},{0,1}, {1,-1},{1,0},{1,1}};


    /**
     * 576. Out of Boundary Paths
     * 定义 dp[i][j][k] 表示球移动 i 次之后位于坐标 (j,k) 的路径数量。当 i=0 时，球一定位于起始坐标 (startRow,startColumn)
     * 因此动态规划的边界情况是：dp[0][startRow][startColumn]=1，当 (j,k) != (startRow,startColumn) 时有 dp[0][j][k]=0
     * 当 0≤j′<m 且 0≤k′<n 时，球在移动 i+1 次之后没有出界，将 dp[i][j][k] 的值加到 dp[i+1][j′][k′]；
     * 否则，球在第 i+1 次移动之后出界，将 dp[i][j][k] 的值加到出界的路径数。
     * 注意到 dp[i][][] 只在计算 dp[i+1][][] 时会用到，因此可以将 dp 中的移动次数的维度省略，将空间复杂度优化到 O(m×n)
     * @param m m
     * @param n n
     * @param maxMove maxMove
     * @param startRow startRow
     * @param startColumn startColumn
     * @return ans
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        final int MOD = 1000000007;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int outCounts = 0;
        int[][] dp = new int[m][n];
        dp[startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            int[][] dpNew = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[j][k];
                    if (count <= 0) continue;
                    for (int[] direction : directions) {
                        int j1 = j + direction[0], k1 = k + direction[1];
                        if (j1 >= 0 && j1 < m && k1 >= 0 && k1 < n) {
                            dpNew[j1][k1] = (dpNew[j1][k1] + count) % MOD;
                        } else {
                            outCounts = (outCounts + count) % MOD;
                        }
                    }
                }
            }
            dp = dpNew;
        }
        return outCounts;
    }

    /**
     * 566. Reshape the Matrix
     * @param mat mat
     * @param r r
     * @param c c
     * @return ans
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length;
        int total = m * n;
        if (total  != r * c) return mat;
        int[][] ans = new int[r][c];
        for (int i = 0; i < total; i++) {
            ans[i/c][i % c] = mat[i/n][i %n];
        }
        return ans;
    }

    /**
     * 542. 01 矩阵
     * @param mat mat
     * @return ans
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    ans[i][j] = -1;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + DIRECTIONS4[k][0];
                int ny = y + DIRECTIONS4[k][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && ans[nx][ny] == -1) {
                    ans[nx][ny] = ans[x][y] + 1;
                    // 新增之后，继续遍历
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return ans;
    }

    public int[][] updateMatrixDP(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];
        int INF = m + n; // 安全上限，避免 Integer.MAX_VALUE + 1 溢出

        // 初始化：0 保持 0，1 先置为 INF
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                ans[i][j] = mat[i][j] == 0 ? 0 : INF;

        // 第一遍：上、左
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    if (i > 0)     ans[i][j] = Math.min(ans[i][j], ans[i - 1][j] + 1);
                    if (j > 0)     ans[i][j] = Math.min(ans[i][j], ans[i][j - 1] + 1);
                }
            }
        }

        // 第二遍：下、右
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j] == 1) {
                    if (i < m - 1) ans[i][j] = Math.min(ans[i][j], ans[i + 1][j] + 1);
                    if (j < n - 1) ans[i][j] = Math.min(ans[i][j], ans[i][j + 1] + 1);
                }
            }
        }
        return ans;
    }



    /**
         * 529. 扫雷游戏 529. Minesweeper
         * @param board board
         * @param click click
         * @return ans
         */
    public char[][] updateBoard(char[][] board, int[] click) {
       if(board[click[0]][click[1]] == 'M') {
           board[click[0]][click[1]] = 'X';
           return board;
       }
        updateBoard(board, click[0],click[1]);
       return board;
    }

    public void updateBoard(char[][] board, int i, int j) {
        if (board[i][j] != 'E' ) return;
        // 设置为 空格 避免被重复查找
        board[i][j] = ' ';
        // 8个方向雷的数量
        int cnt = 0;
        for (int[] ints : direction) {
          int i1 = i + ints[0];
          int j1 = j + ints[1];
          if (i1 < 0 || i1 == board.length || j1 < 0 || j1 == board[0].length ) continue;
          if ( board[i1][j1] =='M') cnt++;
        }
        // 如果周边没有雷，需要递归查找E的空格
        if (cnt == 0) {
            for (int[] ints : direction) {
                int i1 = i + ints[0];
                int j1 = j + ints[1];
                if (i1 < 0 || i1 == board.length || j1 < 0 || j1 == board[0].length ) continue;
                updateBoard(board, i1, j1);
            }
        }
        // 如果8 个方向都没有雷，可以设置为B，否则显示数量
        if (cnt == 0) {
            board[i][j] = 'B';
        } else {
            board[i][j] = (char)(cnt + '0');
        }
    }


    /**
     * 310. 最小高度树
     * <a href="https://leetcode.cn/problems/minimum-height-trees/description/">310. 最小高度树</a>
     * @param n n
     * @param edges edges
     * @return ans
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<Integer>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int[] parents = new int[n];
        Arrays.fill(parents, -1);
        //   找到与节点 0 最远的节点 x
        int x = findLongestNode(0, parents, adj);
        // 找到与节点 x 最远的节点 y
        int y = findLongestNode(x, parents, adj);
        // 求出节点 x 到节点 y 的路径
        List<Integer> path = new ArrayList<>();
        parents[x] = -1;
        while(y != -1) {
            path.add(y);
            y = parents[y];
        }
        int m = path.size();
        if ((m & 1) == 0 ) {
            ans.add(path.get(m/2 - 1));
        }
        ans.add(path.get(m/2));
        return ans;
    }

    /**
     *  找到最远的节点
     *  广度搜索，最后访问到就是最远路径的节点
     * @param u u
     * @param parent parent
     * @param adj edges
     * @return ans
     */
    public int findLongestNode(int u, int[] parent, List<Integer>[] adj) {
        int n  = adj.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n];
        queue.offer(u);
        visit[u] = true;
        int node = -1;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            node = cur;
            for (int v : adj[cur]) {
                if (!visit[v]) {
                    visit[v] = true;
                    parent[v] = cur;
                    queue.offer(v);

                }
            }
        }
        return node;
    }


        public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind unionFind = new UnionFind(m * n);
        boolean[] visited = new boolean[m * n];

        List<Integer> res = new ArrayList<>();
        for (int[] position : positions) {
            int x = position[0];
            int y = position[1];
            int index = x * n + y;
            if (visited[index]) {res.add(unionFind.getCount());continue;}
            visited[index] = true;
            // 把水变成陆地，连通分量个数加 1
            unionFind.addCount();
            for (int[] direction : DIRECTIONS4) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                int newIndex = newX * n + newY;
                if (inArea(newX, newY, m, n ) && visited[newIndex] && !unionFind.isConnected(index, newIndex))  {
                    unionFind.union(index, newIndex);
                }
            }
            res.add(unionFind.getCount());
        }
        return res;
    }

    public boolean inArea(int x, int y, int m, int n) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }


    /**
     * 289. 生命游戏
     * <a href="https://leetcode.cn/problems/game-of-life/">289. 生命游戏</a>
     * @param board input
     */
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count1 = 0;
                for (int[] ints : direction) {
                    int i1 = ints[0] + i;
                    int j1 = ints[1] + j;
                    if (i1 <0 || i1 >= board.length || j1 <0 || j1 >= board[0].length
                            || (board[i1][j1] == 0)  || board[i1][j1] == 3) {
                        continue;
                    }
                    count1++;
                    if (count1 > 3) {
                        break;
                    }
                }
                if (board[i][j] == 0) {
                    if (count1 ==3) board[i][j] =3;
                } else if (count1 <2  || count1 >3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if(board[i][j] == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }


    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    wallsAndGatesDfs(rooms, i, j, 0);
                }
            }
        }
    }
    public void wallsAndGatesDfs(int[][] rooms, int i, int j, int distance ) {
        if (i <0 || i>= rooms.length || j <0 || j >= rooms[0].length || rooms[i][j]  == -1 || (rooms[i][j] <= distance && rooms[i][j] !=0)) {
            return;
        }
        // 遍历到其他门的
        if (rooms[i][j] == 0 && distance != 0) return;
        rooms[i][j] = distance++;
        wallsAndGatesDfs(rooms, i-1, j, distance );
        wallsAndGatesDfs(rooms, i+1, j, distance);
        wallsAndGatesDfs(rooms, i, j-1, distance);
        wallsAndGatesDfs(rooms, i, j+1, distance);
    }

    /**
     * 277. 搜寻名人
     * <a href="https://leetcode.cn/problems/find-the-celebrity">277. 搜寻名人</a>
     * @param n n
     * @return ans
     */
    public int findCelebrity(int n) {
        int candidate = 0;
        // 同通过这一步可以定位出
        for (int i = 0; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        if (isCelebrity(candidate, n)) {
            return candidate;
        }
        return -1;
    }

    private boolean isCelebrity(int i, int n) {
        for (int j = 0; j < n; j++) {
            if (i == j) continue;
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }
    boolean knows(int i, int j) {return true;}


    /**
     * 253. 会议室 II
     * <a href="https://leetcode.cn/problems/meeting-rooms-ii/">253. 会议室 II</a>
     * @param intervals 会议时间
     * @return ans
     */
    public int minMeetingRooms(int[][] intervals) {
        int ans = 0;
        int [] start = new int[intervals.length];
        int [] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int endPoint = 0;
        for (int startPoint = 0; startPoint < intervals.length; startPoint++) {
            // 如果进入点大于当前的结束点，可以使用之前的房间，房子数量不变（-1因为下面会+1）
            if (start[startPoint] >= end[endPoint]) { ans--;endPoint++;}
            ans++;
        }
        return ans;
    }


    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < intervals.length - 1; i++) {
            int j = i + 1;
            if (intervals[i][1] > intervals[j][0]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 240. 搜索二维矩阵 II
     * https://leetcode.cn/problems/search-a-2d-matrix-ii/description/
     *
     * @param matrix matrix
     * @param target target
     * @return ans
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {
                j++;
            } else if (matrix[i][j] > target) {
                i--;
            }
        }
        return false;
    }

    /**
     * https://leetcode.cn/problems/maximal-square/description/
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int ans = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = matrix[i][j] - '0';
                else if (matrix[i][j] == '1')
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans * ans;
    }


    List<Integer> findOrderAns = new ArrayList<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        visited2 = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited2[i] == 0) {
                findOrderDfs(i);
            }
        }
        Collections.reverse(findOrderAns);
        return !valid ? new int[0] : findOrderAns.stream().mapToInt(v -> v).toArray();
    }

    public void findOrderDfs(int u) {
        visited2[u] = 1;
        for (int v : edges.get(u)) {
            if (visited2[v] == 0) {
                findOrderDfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited2[v] == 1) {
                valid = false;
                return;
            }
        }
        findOrderAns.add(u);
        visited2[u] = 2;
    }


    int[] visited2;
    List<List<Integer>> edges = new ArrayList<>();
    boolean valid = true;

    /**
     * 207. 课程表
     * https://leetcode.cn/problems/course-schedule/description/
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited2 = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited2[i] == 0) {
                canFinishDfs(i);
            }
        }
        return valid;
    }

    public void canFinishDfs(int u) {
        visited2[u] = 1;
        for (int v : edges.get(u)) {
            if (visited2[v] == 0) {
                canFinishDfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited2[v] == 1) {
                valid = false;
                return;
            }
        }
        visited2[u] = 2;
    }


    /**
     * 133. 克隆图
     * https://leetcode.cn/problems/clone-graph/
     *
     * @param node
     * @return
     */
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (visited.containsKey(node)) return visited.get(node);
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }

    /**
     * https://leetcode.cn/problems/valid-sudoku/
     * 36. 有效的数独
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            int[] temp = new int[9];
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (temp[board[i][j] - '1'] != 0) {
                    return false;
                }
                temp[board[i][j] - '1']++;
            }
        }
        for (int i = 0; i < n; i++) {
            int[] temp = new int[9];
            for (int j = 0; j < m; j++) {
                if (board[j][i] == '.') {
                    continue;
                }
                if (temp[board[j][i] - '1'] != 0) {
                    return false;
                }
                temp[board[j][i] - '1']++;
            }
        }
        for (int i = 0; i < m; i = i + 3) {
            for (int j = 0; j < n; j = j + 3) {
                int[] temp = new int[10];
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        if (board[k][l] == '.') {
                            continue;
                        }
                        if (temp[board[k][l] - '1'] != 0) {
                            return false;
                        }
                        temp[board[k][l] - '1']++;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 79. 单词搜索
     * https://leetcode.cn/problems/word-search/
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean f = exist(board, word, i, j, 0);
                    if (f) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, String word, int row, int col, int idex) {
        if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1 || board[row][col] != word.charAt(idex)) {
            return false;
        }
        if (idex == word.length() - 1) {
            return true;
        }
        idex++;
        return exist(board, word, row - 1, col, idex) || exist(board, word, row + 1, col, idex)
                || exist(board, word, row, col - 1, idex) || exist(board, word, row, col + 1, idex);
    }


    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int[] prev = new int[m];
        int[] curv = new int[m];
        prev[0] = curv[0] = triangle.get(0).get(0);
        int min = curv[0];
        for (int i = 1; i < m; i++) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (j == 0) {
                    curv[j] = prev[j] + row.get(j);
                    min = curv[j];
                } else if (j == row.size() - 1) {
                    curv[j] = prev[j - 1] + row.get(j);
                } else {
                    curv[j] = Math.min(prev[j - 1], prev[j]) + row.get(j);
                }
                min = Math.min(min, curv[j]);
            }
            int[] temp = prev;
            prev = curv;
            curv = temp;
        }
        return min;
    }

    /**
     * 130. 被围绕的区域
     * https://leetcode.cn/problems/surrounded-regions/
     *
     * @param board
     */
    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) return;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            solveDfs(board, i, 0);
            solveDfs(board, i, n - 1);
        }
        for (int i = 1; i < n - 1; i++) {
            solveDfs(board, 0, i);
            solveDfs(board, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void solveDfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'A';
        solveDfs(board, i, j + 1);
        solveDfs(board, i, j - 1);
        solveDfs(board, i - 1, j);
        solveDfs(board, i + 1, j);
    }


    /**
     * 695. 岛屿的最大面积
     * https://leetcode.cn/problems/max-area-of-island/
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int re = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int t = dfs(grid, i, j);
                    if (t > re) {
                        re = t;
                    }
                }
            }
        }
        return re;
    }

    public int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return 0;
        }
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = '2';
        int re = 1;
        re += dfs(grid, r + 1, c);
        re += dfs(grid, r - 1, c);
        re += dfs(grid, r, c - 1);
        re += dfs(grid, r, c + 1);
        return re;
    }


    /**
     * 463. 岛屿的周长
     * <a href="https://leetcode.cn/problems/island-perimeter/">463. 岛屿的周长</a>
     * 第一种检查四个方向的情况
     * 后面有一种更好的写法，检查有相连的领边，减少了两个方向
     * @param grid grid
     * @return ans
     */
    public int islandPerimeter1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int re = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    re += isWater(grid, i - 1, j);
                    re += isWater(grid, i + 1, j);
                    re += isWater(grid, i, j - 1);
                    re += isWater(grid, i, j + 1);
                }
            }
        }
        return re;
    }

    public int isWater(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return 1;
        }
        if (grid[r][c] != 1) {
            return 1;
        }
        return 0;
    }

    public int islandPerimeter(int[][] grid) {
        // 举例推导出公式 res = 4 * 岛屿格子数量 - 2 * 岛屿格子之间的相邻边
        // 每个格子4条边，只要有相邻的格子，那么旧会少2条边
        int m = 0, n= 0;
        if(grid == null || (m = grid.length) == 0 || (n = grid[0].length) == 0) return 0;

        int count = 0; // 岛屿格子数量
        int edge = 0; // 岛屿格子之间的相邻边
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0) continue;
                count++;
                // 判断右边是不是 陆地格子
                if(j+1 < n && grid[i][j+1] == 1)    edge++;
                // 判断下面是不是 陆地格子
                if(i+1 < m && grid[i+1][j] == 1)    edge++;
            }
        }
        return 4 * count - 2 * edge;
    }


    /**
     * 200. 岛屿数量
     * <a href="https://leetcode.cn/problems/number-of-islands/">200. 岛屿数量</a>
     *
     * @param grid grid
     * @return ans
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int re = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    re++;
                    dfs(grid, i, j);
                }
            }
        }
        return re;
    }

    public void dfs(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return;
        }
        if (grid[r][c] != '1') {
            return;
        }
        grid[r][c] = '2';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }


    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}
