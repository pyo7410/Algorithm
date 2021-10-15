import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기4_17406 {
	public static class Info {
		int r, c, s;
		int stY, stX, enY, enX;

		public Info(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.stY = r - s;
			this.stX = c - s;
			this.enY = r + s;
			this.enX = c + s;
		}
	}
	public static int N, M, K, r, c, s, answer;
	public static boolean[] isUsed;
	public static int[] sequence;
	public static Info[] infoArr;
	public static int[][] arr, copyArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		isUsed = new boolean[K];
		sequence = new int[K];
		infoArr = new Info[K];
		arr = new int[N][M];
		
		// 최소값을 찾기위해 정답값을 큰 수로 초기화
		answer = 987654321;
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			
			infoArr[i] = new Info(Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()));
		}
		
		// 회전 순서에 따라 정답이 달라지게 되므로 순열을 사용
		permutation(0);
		
		System.out.println(answer);
	}
	
	public static void permutation(int cnt) {
		if (cnt == K) {
			// 회전 순서에 따른 최소값을 구해야하므로 원본 배열은 유지가 되야한다.
			copyArr = new int[N][M];
			copy(arr, copyArr);
			
			for (int i = 0; i < K; ++i) {
				// infoArr에서 꺼낼때, sequence 배열의 인덱스에 해당하는
				// 숫자위치의 info값을 꺼내야함을 주의
				int stY = infoArr[sequence[i]].stY;
				int stX = infoArr[sequence[i]].stX;
				int enY = infoArr[sequence[i]].enY;
				int enX = infoArr[sequence[i]].enX;
				
				// 만약 시작지점과 끝지점이 같다면 이는 회전이 불가능 하므로
				// 시작지점과 끝지점이 다를때까지 반복
				while (stY < enY && stX < enX) {
					turn(stY++, stX++, enY--, enX--);
				}
			}
			
			// 최소값을 구한다
			// 이때, 매번 회전 후 최소값을 구하는 것이 아닌 회전이 다 끝난 후
			// 최소값을 구해야함을 주의
			getMin();
			return;
		}
		
		for (int i = 0; i < K; ++i) {
			if (isUsed[i]) {
				continue;
			}
			
			isUsed[i] = true;
			sequence[cnt] = i;
			permutation(cnt + 1);
			isUsed[i] = false;
		}
	}
	
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};
	
	public static void turn(int stY, int stX, int enY, int enX) {
		int y = stY;
		int x = stX;
		int dir = 0;
		
		// 시작지점 값을 미리 저장
		int temp = copyArr[y][x];
		
		// 회전 순서는 하, 좌, 상, 우로 돌리면 된다.
		// 때문에 방향이 4보다 커지면 더이상 회전을 할 필요가 없다.
		while (dir < 4) {
			// 전진
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			// 만약 전진한 칸이 시작 지점이라면 미리 저장해둔 시작지점 값을
			// 해당 칸에 넣어야 한다.
			if (ny == stY && nx == stX) {
				copyArr[y][x] = temp;
				return;
			}
			
			// 만약 갈 수 없는 칸이라면 방향을 바꾸고 다시 전진
			if (ny < stY || nx < stX || ny > enY || nx > enX) {
				dir++;
				continue;
			}
			
			// 숫자의 위치를 바꾼다.
			copyArr[y][x] = copyArr[ny][nx];
			
			// 현재 위치를 전진
			y = ny;
			x = nx;
		}
	}
	
	// 배열을 복사
	public static void copy(int[][] origin, int[][] target) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				target[i][j] = origin[i][j];
			}
		}
	}
	
	// 최소값을 찾는다.
	public static void getMin() {
		for (int i = 0; i < N; ++i) {
			int sum = 0;
			
			for (int j = 0; j < M; ++j) {
				sum += copyArr[i][j];
			}
			
			answer = (answer > sum) ? sum : answer;
		}
	}
}
