package D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상호의배틀필드_1873 {
	public static char[][] playMap;
	public static int H, W, cur_i, cur_j;
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static char[] dirChar = {'^', 'v', '<', '>'};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input, " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 초기 맵을 저장받아 플레이할 맵에 저장
			playMap = new char[H][W];			
			for (int i = 0; i < H; ++i) {
				String initMap = br.readLine();
				playMap[i] = initMap.toCharArray();
				for (int j = 0; j < W; ++j) {// 시작할때의 전차의 방향과 위치 저장
					if (playMap[i][j] == '^' || playMap[i][j] == 'v' ||
							playMap[i][j] == '<' || playMap[i][j] == '>') {
						cur_i = i;
						cur_j = j;
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());			
			String userInput = br.readLine();
			
			// 명령어 수행
			for (int i = 0; i < N; ++i) {
				switch (userInput.charAt(i)) {
				case 'U' :
					move(0);
					break;
				case 'D' :
					move(1);
					break;
				case 'L' :
					move(2);
					break;
				case 'R' :
					move(3);
					break;
				case 'S' :
					if (playMap[cur_i][cur_j] == '^') {
						shoot(0, cur_i, cur_j);
					}
					else if (playMap[cur_i][cur_j] == 'v') {
						shoot(1, cur_i, cur_j);
					}
					else if (playMap[cur_i][cur_j] == '<') {
						shoot(2, cur_i, cur_j);
					}
					else if (playMap[cur_i][cur_j] == '>') {
						shoot(3, cur_i, cur_j);
					}
					break;
				}
			}
			
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < H; ++i) {
				for (int j = 0; j < W; ++j) {
					sb.append(playMap[i][j]);
				}
				sb.append("\n");
			}
			
			System.out.print(sb);
			sb.setLength(0);
		}
	}
	
	public static void shoot(int dir, int i, int j) {
		while (i >= 0 && i < H && j >= 0 && j < W) {			
			if (playMap[i][j] == '*') {
				playMap[i][j] = '.';
				break;
			}
			else if (playMap[i][j] == '#') {
				break;
			}
			
			i += dy[dir];
			j += dx[dir];
		}
	}
	
	public static void move(int dir) {
		int ny = cur_i + dy[dir];
		int nx = cur_j + dx[dir];
		
		playMap[cur_i][cur_j] = dirChar[dir];
		
		if (ny < 0 || nx < 0 || ny >= H || nx >= W) {
			return;
		}
		
		if (playMap[ny][nx] == '.') {
			playMap[ny][nx] = playMap[cur_i][cur_j];
			playMap[cur_i][cur_j] = '.';
			cur_i = ny;
			cur_j = nx;
		}
	}
}
