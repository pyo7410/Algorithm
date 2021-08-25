import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 소수경로_1963 {
	public static class Info {
		int num, cnt;

		public Info(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

	public static int T;
	public static int originPwd, targetPwd;
	public static int[] numIntArr;
	public static boolean[] visited, isPrime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		isPrime = new boolean[10001];

		// 4자리 까지의 소수를 미리 구한다.
		// 이때, true는 소수가 아닌 수, false는 소수임을 주의
		for (int i = 2; i * i <= 10000; i++) {
			if (!isPrime[i]) {
				for (int j = i * i; j <= 10000; j += i) {
					isPrime[j] = true;
				}
			}
		}

		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");

			originPwd = Integer.parseInt(st.nextToken());
			targetPwd = Integer.parseInt(st.nextToken());

			visited = new boolean[10000];

			sb.append(bfs()).append("\n");
		}

		System.out.println(sb);
	}

	// 매번 소수임을 구하는 함수
	/*
	public static boolean getPrime(int num) {
		if (num < 2) {
			return false;
		}
		
		for (int i = 2; (i * i) <= num; ++i) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	*/

	public static int bfs() {
		int cnt = 0;
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(originPwd, 0));
		visited[originPwd] = true;

		while (!q.isEmpty()) {
			Info curInfo = q.poll();

			if (curInfo.num == targetPwd) {
				cnt = curInfo.cnt;
				break;
			}

			numIntArr = new int[4];

			for (int i = 0; i < 4; ++i) {
				intToArr(curInfo.num, numIntArr);

				for (int j = 0; j < 9; ++j) {
					numIntArr[i] = (numIntArr[i] + 1 > 9) ? 0 : numIntArr[i] + 1;

					int curNum = arrToInt(numIntArr);

					if (curNum < 1000) {
						continue;
					}

					// 이때, isPrime에서 true는 소수가 아닌 수, false는 소수임을 주의
					if (!visited[curNum] && !isPrime[curNum]) {
						q.offer(new Info(curNum, curInfo.cnt + 1));
						visited[curNum] = true;
					}
				}
			}
		}

		return cnt;
	}

	public static void intToArr(int num, int[] arr) {
		int idx = 3;
		while (num > 0) {
			numIntArr[idx--] = num % 10;
			num /= 10;
		}
	}

	public static int arrToInt(int[] arr) {
		int result = 0;
		int cur = 1000;

		for (int i = 0; i < 4; ++i) {
			result += cur * arr[i];
			cur /= 10;
		}

		return result;
	}
}
