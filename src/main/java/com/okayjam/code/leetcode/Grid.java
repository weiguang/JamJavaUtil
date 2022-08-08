package com.okayjam.code.leetcode;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/08/02 20:05
 **/
public class Grid {

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
}
