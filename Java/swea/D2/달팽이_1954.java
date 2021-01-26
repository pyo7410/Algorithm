import java.util.Scanner;

public class 달팽이_1954 {
	
	static int[][] answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			
			answer = new int[n][n];
			// dir -> 0 : 우, 1 : 하, 2 : 좌, 3 : 상
			int x = 0, y = 0, dir = 0;
			
			for (int i = 1; i <= n * n; ++i) {
				answer[y][x] = i;
				
				int dx = x, dy = y;
				
				// 방향에 맞게 계산
				if (dir == 0) {
					dx += 1;
				} else if (dir == 1) {
					dy += 1;
				} else if (dir == 2) {
					dx -= 1;
				} else {
					dy -= 1;
				}
				
				if (dx < 0 || dy < 0 || dx >= n || dy >= n || answer[dy][dx] > 0) {
					// 방향을 바꾼 후 다시 계산
					dir = (dir + 1) % 4;
					
					if (dir == 0) {
						x += 1;
					} else if (dir == 1) {
						y += 1;
					} else if (dir == 2) {
						x -= 1;
					} else {
						y -= 1;
					}
					
					continue;
				}
				
				x = dx;
				y = dy;
			}
			
			System.out.println("#" + tc);
			
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					System.out.print(answer[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		sc.close();
	}

}
