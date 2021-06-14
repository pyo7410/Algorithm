import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 미세먼지안녕_17144 {
	public static class Info {
		int y, x;

		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	public static int R, C, T;
	public static int[][] A;
	public static List<Info> dustList;
	public static Info airCleaner;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		A = new int[R][C];
		
		for (int i = 0; i < R; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < C; ++j) {
				A[i][j] = Integer.parseInt(st.nextToken());
				
				if (airCleaner == null && A[i][j] == -1) {
					// 공기청정기의 윗부분 좌표 저장
					airCleaner = new Info(i, j);
				}
			}
		}
		
		for (int t = 0; t < T; ++t) {
			dustList = new ArrayList<Info>();
			
			// 미세먼지 확산
			for (int i = 0; i < R; ++i) {
				for (int j = 0; j < C; ++j) {
					if (A[i][j] > 0) {
						// 미세먼지의 위치를 먼저 저장한다.
						dustList.add(new Info(i, j));
					}
				}
			}
			diffusion();
			
			// 공기청정기 작동
			activateAirCleaner();
		}
		
		int answer = 0;
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (A[i][j] > 0) {
					answer += A[i][j];
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public static int[] dy = {-1, 0, 1, 0};
	public static int[] dx = {0, 1, 0, -1};
	
	public static void diffusion() {
		// 미세먼지가 퍼지는 것은 동시에 퍼져야하므로 이미 퍼진 미세먼지가
		// 아직 퍼지지 않은 미세먼지에 영향을 주어선 안된다.
		// 때문에 배열을 따로 만들어 퍼트린 후 기존 배열로 복사해주기위해 선언
		int[][] diffusionA = new int[R][C];
		
		for (Info dustInfo : dustList) {
			int y = dustInfo.y;
			int x = dustInfo.x;
			
			int cnt = 0;
			
			for (int i = 0; i < 4; ++i) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= R || nx >= C)
				{
					continue;
				}
				
				// 공기청정기 위치에는 퍼지면 안되므로
				if (A[ny][nx] >= 0) {
					diffusionA[ny][nx] += (A[y][x] / 5);
					cnt++;
				}
			}
			
			diffusionA[y][x] += (A[y][x] - ((A[y][x] / 5) * cnt));
		}
		
		// 퍼진 최종결과를 원래 배열에 복사
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				A[i][j] = diffusionA[i][j];
			}
		}
		
		// 공기청정기 다시 배치
		// 배열을 복사하면서 공기청정기의 위치는 생략됬으므로 다시 배치
		A[airCleaner.y][airCleaner.x] = -1;
		A[airCleaner.y + 1][airCleaner.x] = -1;
	}
	
	public static void activateAirCleaner() {
		// 윗 부분 순환
		top();
		
		// 아래 부분 순환
		bottom();
	}
	
	public static void top() {
		// 공기청정기는 무조건 1열에 설치됨...
		// 그리고 위아래로 두 칸 이상 떨어져 있으므로 무조건 회전하는 구조
		int y = airCleaner.y - 1;
		int x = airCleaner.x;
		
		for (int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (!check(ny, nx, 0)) {
				continue;
			}
			
			// 해당 방향에 있는 모든 미세먼지를 이동시킨다.
			while (check(ny, nx, 0)) {
				A[y][x] = A[ny][nx];
				
				y = ny;
				x = nx;
				
				ny += dy[i];
				nx += dx[i];
			}
		}
		
		// 공기청정기 -1인 부분을 그대로 가져온 부분은 0으로 바꾸어주어야 한다.
		A[airCleaner.y][airCleaner.x + 1] = 0;
	}
	
	public static void bottom() {
		// 공기청정기는 무조건 1열에 설치됨...
		// 그리고 위아래로 두 칸 이상 떨어져 있으므로 무조건 회전하는 구조
		int y = airCleaner.y + 2;
		int x = airCleaner.x;
		
		// 회전을 위해 아래방향부터 가야하므로 dir을 2로 세팅
		// 위에 선언한 dy, dx 배열 참고
		int idx = 2;
		for (int i = 0; i < 4; ++i) {
			int dir = idx - i;
			
			if (dir < 0) {
				dir = 3;
			}
			
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if (!check(ny, nx, 1)) {
				continue;
			}
			
			// 해당 방향에 있는 모든 미세먼지를 이동시킨다.
			while (check(ny, nx, 1)) {
				A[y][x] = A[ny][nx];
				
				y = ny;
				x = nx;
				
				ny += dy[dir];
				nx += dx[dir];
			}
		}
		
		// 공기청정기 -1인 부분을 그대로 가져온 부분은 0으로 바꾸어주어야 한다.
		A[airCleaner.y + 1][airCleaner.x + 1] = 0;
	}
	
	public static boolean check(int y, int x, int type) {
		if (y < 0 || x < 0 || y >= R || x >= C) {
			return false;
		}
		
		// 위 부분 공기 순환 체크
		if (type == 0 && y > airCleaner.y) {
			return false;
		}
		
		// 아래 부분 공기 순환 체크
		// 저장한 위치는 공기청정기의 윗부분이므로 윗부분까지 올라갔다면 false
		if (type == 1 && y <= airCleaner.y) {
			return false;
		}
		
		return true;
	}
}
