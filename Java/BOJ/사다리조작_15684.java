import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리조작_15684 {
	public static int N, M, H;
	public static boolean[][] ladder;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ladder = new boolean[H][N];
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// 문제에서는 인덱스가 1부터 시작함을 주의
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			// 왼쪽이 true면 오른쪽과 연결되있음을 알려준다.
			ladder[a][b] = true;
		}
		
		// 최대 3개까지의 가로선을 추가할 수 있으므로
		// 아예 설치를 하지않는 0부터 3까지 가로선을 
		// 순열을 통해 추가하도록 한다.
		for (int i = 0; i < 4; ++i) {
			permutation(0, i);
		}
		
		System.out.println(-1);
	}
	
	// i번에서 시작하는 세로선이 i번에 도착하는지 검사
	public static boolean check() {
		for (int j = 0; j < N; ++j) {
			// 현재 세로선의 번호를 기록
			int cur = j;
			
			for (int i = 0; i < H; ++i) {
				if (cur - 1 >= 0 && ladder[i][cur - 1]) {
					// 만약 왼쪽이 true라면 왼쪽과 연결
					cur -= 1;
				}
				else if (cur + 1 < N && ladder[i][cur]) {
					// 만역 오른쪽이 true라면 오른쪽과 연결
					cur += 1;
				}
			}
			
			// 출발한 번호와 도착 번호가 다르다면 false
			if (cur != j) {
				return false;
			}
		}
		
		// 모든 조건을 봐서 정상적이면 true
		return true;
	}
	
	public static void permutation(int cnt, int maxCnt) {
		// 최대로 추가할 수 있는 가로선 개수만큼 추가했다면
		if (cnt == maxCnt) {
			// 문제에서 요구한 조건을 충족하는지 검사
			if (check()) {
				// main문에서 추가할 수 있는 가로선 개수가 작은 수부터 조사하므로
				// 먼저 조건에 충족한다면 더이상 검사할 필요가 없다.
				System.out.println(cnt);
				System.exit(0);
			}
			
			// 만약 요구한 조건을 충족하지 못했다면 다른 조건을 볼 수 있도록
			// 반환
			return;
		}
		
		for (int i = 0; i < H; ++i) {
			// 왼쪽이 true라면 오른쪽과 연결되 있으므로 N-1까지만 반복
			for (int j = 0; j < N - 1; ++j) {
				// 만약 이미 오른쪽과 연결되 있을 경우 가로선 추가 X
				if (ladder[i][j]) {
					continue;
				}
				
				// 이미 왼쪽 점과 연결되 있을 경우 가로선 추가 X
				if (j - 1 >= 0 && ladder[i][j - 1]) {
					continue;
				}
				
				// 이미 오른쪽의 점이 그 오른쪽의 점과 연결되 있을 경우 가로선 추가 X
				if (j + 1 < N && ladder[i][j + 1]) {
					continue;
				}
				
				// 가로선을 추가할 수 있으므로 오른쪽 점과 연결
				ladder[i][j] = true;
				// 모든 경우를 조사
				permutation(cnt + 1, maxCnt);
				// 다른 경우를 조사하기 위해 다시 초기화
				ladder[i][j] = false;
			}
		}
	}
}	

