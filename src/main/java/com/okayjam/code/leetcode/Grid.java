package com.okayjam.code.leetcode;

import org.bouncycastle.util.Arrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/08/02 20:05
 **/
public class Grid {
	public static void main(String[] args) {
		System.out.println("Default main method!");
	}


	/**
	 * 240. 搜索二维矩阵 II
	 * https://leetcode.cn/problems/search-a-2d-matrix-ii/description/
	 * @param matrix  matrix
	 * @param target target
	 * @return ans
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length, n = matrix[0].length;
		int i = m-1, j = 0;
		while (i >= 0 && j< n) {
			if (matrix[i][j] == target) {return true;}
			if (matrix[i][j] < target) {
				j++;
			} else  if (matrix[i][j] > target) {
				i--;
			}
		}
		return false;
	}

	/**
	 * https://leetcode.cn/problems/maximal-square/description/
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
					dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) +1;
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
		return !valid? new int[0]:  findOrderAns.stream().mapToInt(v->v).toArray();
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
	List<List<Integer>> edges  = new ArrayList<>();
	boolean valid = true;
	/**
	 * 207. 课程表
	 * https://leetcode.cn/problems/course-schedule/description/
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
	 * @param node
	 * @return
	 */
	private Map<Node, Node> visited = new HashMap<>();
	public Node cloneGraph(Node node) {
		if(node == null) return null;
		if(visited.containsKey(node)) return visited.get(node);
		Node cloneNode = new Node(node.val, new ArrayList<>());
		visited.put(node, cloneNode);
		for(Node neighbor : node.neighbors) {
			cloneNode.neighbors.add(cloneGraph(neighbor));
		}
		return cloneNode;
	}

	/**
	 * https://leetcode.cn/problems/valid-sudoku/
	 * 36. 有效的数独
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
	 * @param board
	 */
	public void solve(char[][] board) {
		int m = board.length;
		if (m == 0) return;
		int n = board[0].length;
		for (int i = 0; i < m; i++) {
			solveDfs(board, i , 0);
			solveDfs(board, i , n-1);
		}
		for (int i = 1; i < n-1; i++) {
			solveDfs(board, 0, i);
			solveDfs(board, m -1, i);
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
		if (i<0 || i>= board.length || j<0 || j >= board[0].length || board[i][j] != 'O') {
			return;
		}
		board[i][j] = 'A';
		solveDfs(board, i , j + 1);
		solveDfs(board, i , j - 1);
		solveDfs(board, i - 1 , j);
		solveDfs(board, i + 1 , j);
	}


	/**
	 * 695. 岛屿的最大面积
	 * https://leetcode.cn/problems/max-area-of-island/
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
					if (t  > re) {
						re = t;
					}
				}
			}
		}
		return re;
	}

	public int dfs(int[][] grid, int r, int c) {
		if (r <0 || r >= grid.length || c < 0 || c>= grid[0].length) {
			return 0;
		}
		if (grid[r][c] != 1) {
			return 0 ;
		}
		grid[r][c] = '2';
		int re = 1;
		re += dfs(grid, r+1, c);
		re += dfs(grid, r-1, c);
		re += dfs(grid, r, c-1);
		re += dfs(grid, r, c+1);
		return re;
	}


	/**
	 * 463. 岛屿的周长
	 * https://leetcode.cn/problems/island-perimeter/
	 * @param grid
	 * @return
	 */
	public int islandPerimeter(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int re = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					re += isWater(grid, i-1, j);
					re += isWater(grid, i+1, j);
					re += isWater(grid, i, j-1);
					re += isWater(grid, i, j+1);
				}
			}
		}
		return re;
	}
	public int isWater(int[][] grid, int r, int c){
		if (r <0 || r >= grid.length || c < 0 || c>= grid[0].length) {
			return 1;
		}
		if (grid[r][c] != 1) {
			return 1;
		}
		return 0;
	}

	/**
	 * 200. 岛屿数量
	 * https://leetcode.cn/problems/number-of-islands/
	 * @param grid
	 * @return
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
		if (r <0 || r >= grid.length || c < 0 || c>= grid[0].length) {
			return;
		}
		if (grid[r][c] != '1') {
			return;
		}
		grid[r][c] = '2';
		dfs(grid, r+1, c);
		dfs(grid, r-1, c);
		dfs(grid, r, c-1);
		dfs(grid, r, c+1);
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
