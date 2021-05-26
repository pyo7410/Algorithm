import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Maaaaaaaaaze_16985 {
	public static int answer;
	public static int[] number = new int[5];
	public static boolean[] isSelected = new boolean[5];
	
	public static int[][] copyBoard;
	
	// 3차원 -> 판 번호, i, j
	public static boolean[][][] visited;
	public static int[][][] board = new int[5][5][5];
	public static int[][][] selectedBoard;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int k = 0; k < 5; ++k) {
			for (int i = 0; i < 5; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 5; ++j) {
					board[k][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		selectBoard(0);
		
		System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
	}
	
	public static void selectBoard(int cnt) {
		if (cnt == 5) {
			selectedBoard = new int[5][5][5];
			
			for (int k = 0; k < 5; ++k) {
				for (int i = 0; i < 5; ++i) {
					for (int j = 0; j < 5; ++j) {
						selectedBoard[k][i][j] = board[number[k]][i][j];
					}
				}
			}
			
			rotate(0);
			
			return;
		}
		
		for (int i = 0; i < 5; ++i) {
			if (isSelected[i]) {
				continue;
			}
			
			isSelected[i] = true;
			number[cnt] = i;
			selectBoard(cnt + 1);
			isSelected[i] = false;
		}
	}
	
	public static void rotate(int cnt) {
		if (cnt == 5) {
			bfs();
			return;
		}
		
		// 원본
		rotate(cnt + 1);
		
		// 오른쪽 회전 +1
		turnBoard(cnt);
		rotate(cnt + 1);
		
		// 오른쪽 회전 +2
		turnBoard(cnt);
		rotate(cnt + 1);
		
		// 오른쪽 회전 +3
		turnBoard(cnt);
		rotate(cnt + 1);
		
		// 다시 원래모양으로
		turnBoard(cnt);
	}
	
	// 오른쪽으로 회전
	public static void turnBoard(int idx) {
		copyBoard = new int[5][5];
		
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				copyBoard[i][j] = selectedBoard[idx][j][4 - i];
			}
		}
		
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				selectedBoard[idx][i][j] = copyBoard[i][j];
			}
		}
	}
	
	public static int[][] dir = {{0, -1, 0}, {0, 0, 1}, {0, 1, 0}, {0, 0, -1}, {1, 0, 0}, {-1, 0, 0}};
	
	public static void bfs() {
		visited = new boolean[5][5][5];
		
		Queue<int[]> q = new LinkedList<int[]>();

		if (selectedBoard[0][0][0] == 1) {
			// z, y, x, cnt
			q.offer(new int[] {0, 0, 0, 0});
			visited[0][0][0] = true;			
		}
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			
			int z = info[0];
			int y = info[1];
			int x = info[2];
			int cnt = info[3];
			
			if (cnt >= answer) {
				return;
			}
			
			if (z == 4 && y == 4 && x == 4) {
				answer = (answer > cnt) ? cnt : answer;
				return;
			}
			
			for (int i = 0; i < 6; ++i) {
				int nz = z + dir[i][0];
				int ny = y + dir[i][1];
				int nx = x + dir[i][2];
				
				if (nz < 0 || ny < 0 || nx < 0 || nz >= 5 || ny >= 5 || nx >= 5) {
					continue;
				}
				
				if (!visited[nz][ny][nx] && selectedBoard[nz][ny][nx] == 1) {
					q.offer(new int[] {nz, ny, nx, cnt + 1});
					visited[nz][ny][nx] = true;
				}
			}
		}
	}
}
