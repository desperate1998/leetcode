class Solution {

    public static boolean isCol(int j,int[][] mark,int n) {
		for(int p=0;p<n;p++) {
			if(mark[p][j]==1) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isAngle(int i,int j,int[][] mark,int n) {
		// 左上角
		int p=i,q=j;
		while((i-1)>=0 && (j-1)>=0) {
			if(mark[i-1][j-1]==1) {
				return false;
			}
			i-=1;
			j-=1;
		}
		
		// 右上角
		i=p;
		j=q;
		while((i-1)>=0 && (j+1)<n) {
			if(mark[i-1][j+1]==1) {
				return false;
			}
			i-=1;
			j+=1;
		}
		
		// 左下角
		i=p;
		j=q;
		while((i+1)<n && (j-1)>=0) {
			if(mark[i+1][j-1]==1) {
				return false;
			}
			i+=1;
			j-=1;
		}
		
		// 右下角
		i=p;
		j=q;
		while((i+1)<n && (j+1)<n) {
			if(mark[i+1][j+1]==1) {
				return false;
			}
			i+=1;
			j+=1;
		}
		
		return true;
	}
	
	public static void backtracking(int i,List<List<String>> res,char[][] board,int[][] mark,int amount,int n) {
		if(amount==n) {
			List<String> perm=new ArrayList<String>();
			for(char[] temp : board) {
				perm.add(new String(temp));
			}
			res.add(perm);
			return;
		}
		int digit=0;
		for(;digit<n;digit++) {
			if(isCol(digit,mark,n) && isAngle(i,digit,mark,n)) {
				board[i][digit]='Q';
				mark[i][digit]=1;
				amount+=1;
				backtracking(i+1,res, board, mark, amount,n);
				board[i][digit]='.';
				mark[i][digit]=0;
				amount-=1;
			}
		}
	}
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res=new ArrayList<List<String>>();
		char[][] board=new char[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				board[i][j]='.';
			}
		}
		int[][] mark=new int[n][n];
		backtracking(0,res,board,mark,0,n);
		return res;
    }
}