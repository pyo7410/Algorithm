import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class 드래곤커브_15685 {
	public static int N, answer;
	public static List<Integer> dirList;
	public static boolean[][] map = new boolean[101][101];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			getDir(d, g);
			makeDragonCurve(y, x);
		}
		
		answer = countSqure();
		
		System.out.println(answer);
	}
	
	// 0 : 우, 1 : 상, 2 :좌, 3: 하
	// 90도 돌렸을때의 순서
	// +2씩 하면 뒤집을 수도 있다.
	public static int[] dy = {0, -1, 0, 1};
	public static int[] dx = {1, 0, -1, 0};
	public static void getDir(int d, int g) {
		// 드래곤커브의 랭크별 방향이 들어간다.
		dirList = new ArrayList<Integer>();
		// 드래곤커브의 방향을 구할때 이전 방향의 역순부터 구해야하므로 스택을 사용
		Stack<Integer> stk = new Stack<Integer>();
		
		// 시작지점의 드래곤커브의 방향을 넣는다
		dirList.add(d);
		
		// 첫 시작 후 세대를 1 올려준다.
		int rank = 1;
		
		// 구하고자하는 세대까지 드래곤커브의 방향을 구한다
		while (rank <= g) {
			// 지금까지 구한 드래곤커브의 방향을 스택에 담아 역순으로 사용하기 위함
			for (int dir : dirList) {
				stk.push(dir);
			}
			
			// 다음 세대의 방향을 구한다.
			while (!stk.isEmpty()) {
				int dir = stk.pop();
				
				// 90도 회전시킨 후
				dir = ((dir - 1) < 0) ? 3 : (dir - 1);
				// 드래곤 커브를 그리기 위해 방향을 뒤집어 준다.
				dir = (dir + 2) % 4;
				
				// 구한 방향을 리스트에 추가
				dirList.add(dir);
			}
			
			// 세대를 1 올려준다.
			rank++;
		}
	}
	
	// 드래곤 커브를 배열에 그려준다.
	public static void makeDragonCurve(int y, int x) {
		map[y][x] = true;
		
		for (int dir : dirList) {
			y += dy[dir];
			x += dx[dir];
			
			map[y][x] = true;
		}
	}
	
	// 배열에서 문제에서 주어진 1x1 크기의 정사각형 개수를 구해준다.
	public static int countSqure() {
		int cnt = 0;
		
		// +1 을 해서 수를 구하므로 101 - 1 즉, 100번 인덱스 까지만 계산하면 된다.
		for (int i = 0; i < 100; ++i) {
			for (int j = 0; j < 100; ++j) {
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}
