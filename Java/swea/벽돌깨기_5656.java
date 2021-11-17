import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벽돌깨기_5656 {
	public static int N, W, H, answer;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			
			for (int i = 0; i < H; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int j = 0; j < W; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			answer = Integer.MAX_VALUE;
			
			dfs(map, N);
			answer = (answer == Integer.MAX_VALUE) ? 0 : answer;
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int[][] map, int n) {
		if (n == 0) {
			int cnt = getBlockCnt(map);
			answer = (answer > cnt) ? cnt : answer;
			
			return;
		}
		
		// 모든 열에 구슬을 던져본다.
		for (int i = 0; i < W; ++i) {
			int[][] copyMap = new int[H][W];
			
			// 배열복사
			for (int j = 0; j < H; ++j) {
				for (int k = 0; k < W; ++k) {
					copyMap[j][k] = map[j][k];
				}
			}
			
			// 0이 아닌 블록에 닿을때만 터져야하므로 0이 아닌 블록을 찾는다.
			for (int j = 0; j < H; ++j) {
				if (copyMap[j][i] != 0) {
					// 블록을 제거한다.
					removeBlock(copyMap, j, i);
					// 제거된 블록을 채우기위해 남은 블록들을 이동시킨다.
					moveBlock(copyMap);
					
					dfs(copyMap, n - 1);
					break;
				}
			}
		}
	}
	
	public static void removeBlock(int[][] map, int h, int w) {
		if (h < 0 || w < 0 || h >= H || w >= W) {
			return;
		}
		
		int range = map[h][w];
		map[h][w] = 0;
		
		for (int i = 0; i < range; ++i) {
			// 범위만큼 터트릴 수 있는 블록을 전부 터트린다.
			removeBlock(map, h, w + i);
			removeBlock(map, h, w - i);
			removeBlock(map, h + i, w);
			removeBlock(map, h - i, w);
		}
	}
	
	public static void moveBlock(int[][] map) {
		for (int i = 0; i < H; ++i) {
			for (int j = 0; j < W; ++j) {
				if (map[i][j] == 0) {
					for (int k = i - 1; k >= 0; k--) {
						if (map[k][j] != 0) {
							map[k + 1][j] = map[k][j];
							map[k][j] = 0;							
						}
					}
				}
			}
		}
	}
	
	public static int getBlockCnt(int[][] map) {
		int cnt = 0;
		
		for (int i = 0; i < H; ++i) {
			for (int j = 0; j < W; ++j) {
				if (map[i][j] > 0) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}
