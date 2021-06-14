class Solution {
    private int[][] row=new int[9][9];
	private int[][] col=new int[9][9];
	private int[][] block=new int[9][9];
	private boolean success=false;
	private List<int[]> spaces=new ArrayList<int[]>();
	
	public void backtracking(int pos,char[][] board) {
		if(pos==spaces.size()) {
			success=true;
			return;
		}
		int[] space=spaces.get(pos);
		int i=space[0],j=space[1];
		for(int digit=0;digit<9 && !success;digit++) {
			if(row[i][digit]!=1 && col[j][digit]!=1 && block[cubeNum(i, j)][digit]!=1) {
				row[i][digit]=col[j][digit]=block[cubeNum(i, j)][digit]=1;
				board[i][j]=(char)(digit+'0'+1);
				backtracking(pos+1, board);
				row[i][digit]=col[j][digit]=block[cubeNum(i, j)][digit]=0;
			}
		}
	}
	
	public int cubeNum(int i,int j) {
		return i/3*3+j/3;
	}
	
	public void solveSudoku(char[][] board) {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(board[i][j]=='.') {
					spaces.add(new int[] {i,j});
				}else {
					int num=board[i][j]-'0'-1;
					row[i][num]=col[j][num]=block[cubeNum(i,j)][num]=1;
				}
			}
		}
		backtracking(0,board);
    }
}