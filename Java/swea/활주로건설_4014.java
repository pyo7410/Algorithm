import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 활주로건설_4014 {
	public static int N, X;
	public static int[][] map;
	public static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			for (int i = 0; i < N; ++i) {
				// 경사로 설치 여부 검사
				isSelected = new boolean[N];
				
				// 가로 검사
				if (goX(i, 0)) {
					answer++;
				}
				
				// 경사로 설치 여부 검사
				isSelected = new boolean[N];
				
				// 세로 검사
				if (goY(0, i)) {
					answer++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 가로 검사
	public static boolean goX(int y, int x) {
		// 기준점 + 1을 검사하므로 N - 1까지 진행
		for (int i = 0; i < N - 1; ++i) {
			// 바로 앞의 숫자 검사
			int idx = x + i + 1;
			
			// 앞의 칸과의 높이차를 구함
			int height = map[y][idx] - map[y][x + i];
			
			if (height == 1) {
				// 높이의 차이가 1일 경우 내려가는 경사로를 뒤로 설치
				for (int j = i; j > i - X; j--) {
					// 경사로가 범위를 벗어나면 설치 불가
					if (j < 0) {
						return false;
					}
					
					// 경사로의 길이 X의 각 칸에서 높이의 차가 1이 아니면 설치 불가
					if (map[y][idx] - map[y][j] != 1) {
						return false;
					}
					
					// 이미 설치된 경사로는 설치 불가
					if (isSelected[j]) {
						return false;
					}
					
					// 경사로 설치 중복여부 체크
					isSelected[j] = true;
				}
			}
			else if (height == -1) {
				// 높이의 차이가 -1일 경우 내려가는 경사로를 앞으로 설치
				for (int j = idx; j < idx + X; ++j) {
					if (j >= N) {
						return false;
					}
					
					// 경사로의 길이 X의 각 칸에서 높이의 차가 -1이 아니면 설치 불가
					if (map[y][j] - map[y][i] != -1) {
						return false;
					}
					
					// 이미 설치된 경사로는 설치 불가
					if (isSelected[j]) {
						return false;
					}
					
					// 경사로 설치 중복여부 체크
					isSelected[j] = true;
				}
			}
			else if (height > 1 || height < -1) {
				// 만약 높이가 1보다 크고 -1보다 작다면 설치자체가 불가능
				
				return false;
			}
		}
		
		return true;
	}
	
	// 세로 검사
	public static boolean goY(int y, int x) {
		// 기준점 + 1을 검사하므로 N - 1까지 진행
		for (int i = 0; i < N - 1; ++i) {
			int idx = y + i + 1;
			int height = map[idx][x] - map[y + i][x];
			
			if (height == 1) {
				// 높이의 차이가 1일 경우 내려가는 경사로를 뒤로 설치
				for (int j = i; j > i - X; j--) {
					if (j < 0) {
						return false;
					}
					
					// 경사로의 길이 X의 각 칸에서 높이의 차가 1이 아니면 설치 불가
					if (map[idx][x] - map[j][x] != 1) {
						return false;
					}
					
					// 이미 설치된 경사로는 설치 불가
					if (isSelected[j]) {
						return false;
					}
					
					// 경사로 설치 중복여부 체크
					isSelected[j] = true;
				}
			}
			else if (height == -1) {
				// 높이의 차이가 -1일 경우 내려가는 경사로를 앞으로 설치
				for (int j = idx; j < idx + X; ++j) {
					if (j >= N) {
						return false;
					}
					
					// 경사로의 길이 X의 각 칸에서 높이의 차가 -1이 아니면 설치 불가
					if (map[j][x] - map[i][x] != -1) {
						return false;
					}
					
					// 이미 설치된 경사로는 설치 불가
					if (isSelected[j]) {
						return false;
					}
					
					// 경사로 설치 중복여부 체크
					isSelected[j] = true;
				}
			}
			else if (height > 1 || height < -1) {
				// 만약 높이가 1보다 크고 -1보다 작다면 설치자체가 불가능
				return false;
			}
		}
		
		return true;
	}
}
