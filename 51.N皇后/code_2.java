// 45度对角线的横坐标和纵坐标的之和相同
// 135度对角线的横坐标和纵坐标的之差相同

class Solution {
    
	public static void backtracking(int row,List<List<String>> res,boolean[] colUsed,boolean[] diagonals45Used,boolean[] diagonals135Used,int n,char[][] board) {
		if (row == n) {
	        List<String> list = new ArrayList<>();
	        for (char[] chars : board) {
	            list.add(new String(chars));
	        }
	        res.add(list);
	        return;
	    }
		for (int col = 0; col < n; col++) {
	        int diagonals45Idx = row + col; // 45度角的对角线的横纵坐标之和相等
	        int diagonals135Idx = n - 1 + (row - col); // 135度角的对角线的横纵坐标之差相等
	        if (colUsed[col] || diagonals45Used[diagonals45Idx] || diagonals135Used[diagonals135Idx]) {
	            continue;
	        }
	        board[row][col] = 'Q';
	        colUsed[col] = diagonals45Used[diagonals45Idx] = diagonals135Used[diagonals135Idx] = true;
	        backtracking(row + 1,res,colUsed,diagonals45Used,diagonals135Used,n,board);
	        colUsed[col] = diagonals45Used[diagonals45Idx] = diagonals135Used[diagonals135Idx] = false;
	        board[row][col] = '.';
	    }
	}

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res=new ArrayList<List<String>>();
		char[][] board=new char[n][n];
	    for (int i = 0; i < n; i++) {
	        Arrays.fill(board[i], '.');
	    }
	    boolean[] colUsed = new boolean[n];
	    boolean[] diagonals45Used = new boolean[2 * n - 1];
	    boolean[] diagonals135Used = new boolean[2 * n - 1];
	    backtracking(0,res,colUsed,diagonals45Used,diagonals135Used,n,board);
		return res;
    }
}