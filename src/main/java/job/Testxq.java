package job;

public class Testxq {

	public static void main(String[] args){
		int[][] A={{0,0,1,1},{1,0,1,0},{0,1,1,0},{0,0,1,0}};
		int[][] f={{2,2},{3,3},{4,4}};
		
	}
	public static int[][] flipChess(int[][] A, int[][] f) {

		// write code here

		for (int i = 0; i < f.length; i++) {

		int row = f[i][0] - 1;

		int col = f[i][1] - 1;

		if (row - 1 >= 0) {

		A[row - 1][col] = (A[row - 1][col] == 0) ? 1 : 0;

		}

		if (row + 1 <= 3) {

		A[row + 1][col] = (A[row + 1][col]) == 0 ? 1 : 0;

		}

		if (col - 1 >= 0) {

		A[row][col - 1] = (A[row][col - 1]) == 0 ? 1 : 0;

		}

		if (col + 1 <= 3) {

		A[row][col + 1] = (A[row][col + 1]) == 0 ? 1 : 0;

		}

		}

		return A;

		}
}
