import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PuyoPuyo_11559 {
	public static int answer, totalCnt;
	public static boolean[][] visited;
	public static char[][] map = new char[12][6];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 12; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		
		do {
			totalCnt = 0;
			visited = new boolean[12][6];
			
			// BFS 탐색을 통해 뿌요를 탐색한다.
			for (int i = 0; i < 12; ++i) {
				for (int j = 0; j < 6; ++j) {
					if (!visited[i][j] && map[i][j] != '.') {
						search(i, j);
					}
				}
			}
			
			// 만약 터질 수 있는 뿌요가 없다면 정지
			if (totalCnt == 0) {
				break;
			}
			
			// 터진 뿌요가 존재하므로 +1
			answer++;
			
			// 뿌요가 터졌으므로 뿌요들을 내려준다.
			downPuyo();
		} while (totalCnt > 0);
		
		System.out.println(answer);
	}
	
	public static class Info {
		int y, x;
		
		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static int dy[] = {-1, 1, 0, 0};
	public static int dx[] = {0, 0, -1, 1};
	
	// 터트릴 뿌요뿌요를 찾는다
	public static void search(int y, int x) {
		// 탐색한 뿌요들의 좌표를 저장
		List<Info> puyoList = new ArrayList<Info>();
		Queue<Info> q = new LinkedList<Info>();
		puyoList.add(new Info(y, x));
		q.offer(new Info(y, x));
		visited[y][x] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int ny = info.y + dy[i];
				int nx = info.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= 12 || nx >= 6) {
					continue;
				}
				
				if (!visited[ny][nx] && map[ny][nx] == map[y][x]) {
					puyoList.add(new Info(ny, nx));
					q.offer(new Info(ny, nx));
					visited[ny][nx] = true;
				}
			}
		}
		
		// 탐색한 뿌요의 수가 4이상이면 터트린다.
		if (puyoList.size() >= 4) {
			for (Info puyo : puyoList) {
				map[puyo.y][puyo.x] = '.';
			}
			
			totalCnt++;
		}
	}
	
	// 뿌요들을 아래로 내림
	public static void downPuyo() {
		for (int j = 0; j < 6; ++j) {
			for (int i = 10; i >= 0; i--) {
				// 뿌요라면
				if (map[i][j] != '.') {
					// 바닥에 제일 가까운 '.'을 찾는다.
					int bottomIdx = findBottom(i, j);
					
					// 바닥에 제일 가까운 '.'이 없는경우
					// 즉, 아래에 전부 뿌요들이거나 맨 바닥인 경우를 제외한다
					if (bottomIdx != -1) {
						// 바닥에 제일 가까운 '.'을 찾았다면 서로 위치를 교환하여
						// 뿌요를 내려준다
						char temp = map[i][j];
						map[i][j] = map[bottomIdx][j];
						map[bottomIdx][j] = temp;
					}
				}
			}
		}
	}
	
	// 바닥에 제일 가까운 '.'의 인덱스를 구한다.
	public static int findBottom(int y, int x) {
		for (int i = 11; i > y; i--) {
			if (map[i][x] == '.') {
				return i;
			}
		}
		
		// 바닥에 제일 가까운 '.'이 없는경우
		// 즉, 아래에 전부 뿌요들이거나 맨 바닥인 경우 -1 리턴
		return -1;
	}
}
