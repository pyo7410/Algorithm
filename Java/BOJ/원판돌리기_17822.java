import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 원판돌리기_17822 {
	public static int N, M, T, X, D, K, answer;
	public static boolean[][] removePosition;
	public static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 1; j <= M; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			X = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			solve();
		}
		
		System.out.println(answer);
	}
	
	public static void solve() {
		// K번 만큼 회전 시킨다.
		for (int i = 0; i < K; ++i) {
			if (D == 0) {
				rotateRight();					
			}
			else if (D == 1) {
				rotateLeft();
			}
		}
		
		// 지워질 숫자의 개수를 카운트
		int removeCnt = 0;

		// 평균을 구할 숫자의 개수를 카운트 
		int numCnt = 0;
		// 평균을 구할 숫자의 합을 저장
		int result = 0;
		// 지우고자하는 위치를 저장
		// 미리 지우게 된다면 다음 탐색에서 현재 위치의 값과 같은 숫자가 나오더라도
		// 이미 지웠으므로 다른 숫자로 처리하게 되므로 위치만 기억하도록 함
		removePosition = new boolean[N + 1][M + 1];
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= M; ++j) {
				// 0인 위치는 숫자가 지워진 위치이므로 구할필요가 없다
				if (arr[i][j] > 0) {
					removeCnt += checkDuplicateNum(i, j);		
					
					// 지워지는 숫자가 아니라면 평균을 구하기 위해 값을 갱신
					if (!removePosition[i][j]) {
						numCnt++;
						result += arr[i][j];							
					}
				}
			}
		}
		
		// 만약 지워지는 숫자가 하나도 없다면
		if (removeCnt == 0) {
			// 평균을 구한다.
			// 이때, 실수형으로 한 이유는 3.1 은 3보다는 크고 4보다는 작은 수이므로
			// 이러한 경우를 처리하기 위함
			double avg = (double) result / (double) numCnt;
			// 정답을 갱신
			answer = getCompareAvgResult(avg);
		}
		else {
			// 정답을 갱신
			answer = getRemoveDuplicateNumResult();
		}
	}
	
	// 왼쪽으로 원판을 회전
	public static void rotateLeft() {
		for (int i = X; i <= N; i += X) {
			int temp = arr[i][1];
			
			for (int j = 2; j <= M; ++j) {
				arr[i][j - 1] = arr[i][j];
			}
			
			arr[i][M] = temp;
		}
	}
	
	// 오른쪽으로 원판을 회전
	public static void rotateRight() {
		for (int i = X; i <= N; i += X) {
			int temp = arr[i][M];
			
			for (int j = M - 1; j > 0; j--) {
				arr[i][j + 1] = arr[i][j];
			}
			
			arr[i][1] = temp;
		}
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	
	// 인접한 상하 좌우로 중복된 숫자가 있는지 검사
	public static int checkDuplicateNum(int y, int x) {
		int cnt = 0;
		
		for (int i = 0; i < 4; ++i) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 행은 범위가 벗어나면 검사할 필요가 없다
			// 원판에서 각 원
			if (ny < 1 || ny > N) {
				continue;
			}
			
			// 열은 원형으로 이루어져 있기때문에 범위가 벗어나면 처음이나 끝으로 세팅해주어야한다.
			// 원판안의 숫자
			nx = (nx > M) ? 0 : (nx < 1) ? M : nx;
			
			// 숫자가 같다면
			if (arr[ny][nx] == arr[y][x]) {
				// 지울 수 있는 숫자의 개수를 +1
				cnt++;
				// 지울 위치를 true로
				removePosition[ny][nx] = true;
			}
		}
		
		// 만약 지울 수 있는 숫자가 있다는 의미는
		// 자기 자신도 지울 수 있다는 의미이므로 현재위치도 true로
		if (cnt > 0) {
			removePosition[y][x] = true;
		}
		
		return cnt;
	}
	
	// 지울 위치의 숫자를 지우고 현재 원판의 숫자들의 합을 반환
	public static int getRemoveDuplicateNumResult() {
		int result = 0;
		
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= M; ++j) {
				// 0인 숫자는 지운 숫자이므로 탐색할 필요가 없다.
				if (arr[i][j] == 0) {
					continue;
				}
				
				// 만약 지워지지 않는 숫자라면 원판의 숫자들의 합을 구해야하므로 합해준다.
				if (!removePosition[i][j]) {
					result += arr[i][j];
				}
				else {
					// 지워지는 숫자가 아니라면 숫자를 제거한다.
					arr[i][j] = 0;
				}
			}
		}
		
		return result;
	}
	
	// 평균값보다 큰 수는 -1, 작은 수는 +1을 해주고 원판의 숫자들의 합을 반환
	public static int getCompareAvgResult(double avg) {
		int result = 0;
		
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= M; ++j) {
				// 0인 위치는 이미 지워진 위치이므로 조사할 필요가 없다.
				if (arr[i][j] == 0) {
					continue;
				}
				
				// 3.1은 3보다는 크고 4보다는 작은 수 이므로 이를 처리하기위해 실수형으로 처리
				if ((double)arr[i][j] > avg) {
					arr[i][j]--;
				}
				else if ((double)arr[i][j] < avg) {
					arr[i][j]++;
				}
				
				result += arr[i][j];
			}
		}
		
		return result;
	}
}
