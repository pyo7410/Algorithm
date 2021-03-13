import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 프로세서연결하기_1767 {
	public static int N, maxCoreCnt, answer;
	public static int[][] maxinos;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			maxinos = new int[N][N];
			maxCoreCnt = 0;
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; ++j) {
					maxinos[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			go(0, 0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static void go(int y, int x, int coreCnt, int wireLen) {
		if (y == N) {
			if (coreCnt > maxCoreCnt) {
				// 연결된 코어의 갯수가 더 많다면
				// 갯수가 더 많은 코어의 길이로 갱신
				maxCoreCnt = coreCnt;
				answer = wireLen;
			}
			else if (coreCnt == maxCoreCnt) {
				// 연결된 코어의 최대 갯수가 같다면
				// 전선의 길이만 비교해서 교체
				answer = (answer > wireLen) ? wireLen : answer;
			}
			
			return;
		}
		
		if (x >= N) {
			go(y + 1, 0, coreCnt, wireLen);
			return;
		}
		
		if (maxinos[y][x] == 1) {
			// 가장자리는 이미 연결됨
			if (y == N - 1 || x == N - 1 || y == 0 || x == 0) {
				go(y, x + 1, coreCnt + 1, wireLen);
				return;
			}
			
			boolean flag = true;
			for (int i = 0; i < 4; ++i) {
				if (isConnected(y, x, i)) {
					flag = false;
					int ny = y + dy[i];
					int nx = x + dx[i];
					
					// 전선의 길이
					int len = 0;
					
					// 전선 연결
					while (ny < N && nx < N && ny >= 0 && nx >= 0) {
						maxinos[ny][nx] = 2;
						ny += dy[i];
						nx += dx[i];
						len++;
					}
					
					go(y, x + 1, coreCnt + 1, wireLen + len);
					
					// 전선 해제
					ny = y + dy[i];
					nx = x + dx[i];
					while (ny < N && nx < N && ny >= 0 && nx >= 0) {
						maxinos[ny][nx] = 0;
						ny += dy[i];
						nx += dx[i];
					}
				}
			}
			
			if (flag) {
				go(y, x + 1, coreCnt, wireLen);
				return;
			}
		}
		else {
			// 아무것도 연결안하고 넘어가는 경우도 체크
			go(y, x + 1, coreCnt, wireLen);			
		}
	}
	
	public static boolean isConnected(int y, int x, int dir) {
		// dir -> 0 : 상, 1 : 하, 2 : 좌, 3 : 우
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
			return false;
		}
		
		while (ny < N && nx < N && ny >= 0 && nx >= 0) {
			if (maxinos[ny][nx] != 0) {
				return false;
			}
			
			ny += dy[dir];
			nx += dx[dir];
		}
		
		return true;
	}
}
