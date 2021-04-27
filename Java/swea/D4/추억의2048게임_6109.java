package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 추억의2048게임_6109 {
	public static int N;
	public static String S;
	public static int[][] map, answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			S = st.nextToken();
			
			map = new int[N][N];
			answer = new int[N][N];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if (S.equals("up")) {
				up();
			}
			else if (S.equals("down")) {
				down();
			}
			else if (S.equals("left")) {
				left();
			}
			else if (S.equals("right")) {
				right();
			}
			
			sb.append("#").append(tc).append("\n");
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					sb.append(answer[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	public static void up() {
		// 현재 점에서 아래 점을 보면서 탐색
		for (int j = 0; j < N; ++j) {
			// 맨 마지막점은 볼 필요가 없다.
			for (int i = 0; i < N - 1; ++i) {
				if (map[i][j] == 0) {
					continue;
				}
				
				int idx = i + 1;
				
				// 0이 아닌 점을 찾는다
				while (map[idx][j] == 0) {
					if (idx == N - 1) {
						break;
					}
					idx++;
				}
				
				// 맨 끝까지 갔는데도 그 점이 0이라면
				if (map[idx][j] == 0) {
					continue;
				}
				
				if (map[i][j] == map[idx][j]) {
					map[i][j] += map[idx][j];
					map[idx][j] = 0;
					i = idx;
				}
			}
		
			// 정답 배열에 cur를 사용해서 0이 아닌 수만 복사하여 빈 공간을 채움
			int cur = 0;
			for (int i = 0; i < N; ++i) {
				if (map[i][j] != 0) {
					answer[cur++][j] = map[i][j];
				}
			}
		}
	}
	
	public static void down() {
		// 현재 점에서 위에있는 점을 보면서 탐색
		for (int j = 0; j < N; ++j) {
			// 맨 마지막점은 볼 필요가 없다.
			for (int i = N - 1; i > 0; i--) {
				if (map[i][j] == 0) {
					continue;
				}
				
				int idx = i - 1;
				
				// 0이 아닌 점을 찾는다
				while (map[idx][j] == 0) {
					if (idx == 0) {
						break;
					}
					
					idx--;
				}
				
				// 맨 끝까지 갔는데도 그 점이 0이라면
				if (map[idx][j] == 0) {
					continue;
				}
				
				if (map[i][j] == map[idx][j]) {
					map[i][j] += map[idx][j];
					map[idx][j] = 0;
					i = idx;
				}
			}
		
			// 정답 배열에 cur를 사용해서 0이 아닌 수만 복사하여 빈 공간을 채움
			int cur = N - 1;
			for (int i = N - 1; i >= 0; i--) {
				if (map[i][j] != 0) {
					answer[cur--][j] = map[i][j];
				}
			}
		}
	}
	
	public static void left() {
		// 현재 점에서 오른쪽에있는 점을 보면서 탐색
		for (int i = 0; i < N; ++i) {
			// 맨 마지막점은 볼 필요가 없다.
			for (int j = 0; j < N - 1; ++j) {
				if (map[i][j] == 0) {
					continue;
				}
				
				int idx = j + 1;
				
				// 0이 아닌 점을 찾는다
				while (map[i][idx] == 0) {
					if (idx == N - 1) {
						break;
					}
					
					idx++;
				}
				
				// 맨 끝까지 갔는데도 그 점이 0이라면
				if (map[i][idx] == 0) {
					continue;
				}
				
				if (map[i][j] == map[i][idx]) {
					map[i][j] += map[i][idx];
					map[i][idx] = 0;
					j = idx;
				}
			}
		
			// 정답 배열에 cur를 사용해서 0이 아닌 수만 복사하여 빈 공간을 채움
			int cur = 0;
			for (int j = 0; j < N; ++j) {
				if (map[i][j] != 0) {
					answer[i][cur++] = map[i][j];
				}
			}
		}
	}
	
	public static void right() {
		// 현재 점에서 오른쪽에있는 점을 보면서 탐색
		for (int i = 0; i < N; ++i) {
			// 맨 마지막점은 볼 필요가 없다.
			for (int j = N - 1; j > 0; j--) {
				if (map[i][j] == 0) {
					continue;
				}
				
				int idx = j - 1;
				
				// 0이 아닌 점을 찾는다
				while (map[i][idx] == 0) {
					if (idx == 0) {
						break;
					}
					
					idx--;
				}
				
				// 맨 끝까지 갔는데도 그 점이 0이라면
				if (map[i][idx] == 0) {
					continue;
				}
				
				if (map[i][j] == map[i][idx]) {
					map[i][j] += map[i][idx];
					map[i][idx] = 0;
					j = idx;
				}
			}
		
			// 정답 배열에 cur를 사용해서 0이 아닌 수만 복사하여 빈 공간을 채움
			int cur = N - 1;
			for (int j = N - 1; j >= 0; j--) {
				if (map[i][j] != 0) {
					answer[i][cur--] = map[i][j];
				}
			}
		}
	}
}
