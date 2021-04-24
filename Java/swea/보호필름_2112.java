import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 보호필름_2112 {
	public static int D, W, K, answer;
	public static int[][] filmInfo;
	public static int[] type;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			filmInfo = new int[D][W];
			type = new int[D];
			
			for (int i = 0; i < D; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int j = 0; j < W; ++j) {
					filmInfo[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 만약 아무것도 주입하지 않고 끝날 수 있는지 확인하기 위해
			// -1로 초기화
			Arrays.fill(type, -1);
			
			if (K == 1 || isPass()) {
				// 아무것도 주입하지 않는다면 끝
				answer = 0;
			}
			else {
				// 주입을 해야하는 경우
				answer = Integer.MAX_VALUE;
				
				// 모든 주입을 다 시도해본다.
				powerset(0, 0);
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void powerset(int d, int insertCnt) {
		// 주입한 칸의 수가 정답보다 크다면
		if (insertCnt >= answer) {
			return;
		}
		
		// 모든 칸에대해 어떤 종류의 주입을 할지 정해졌으면
		if (d == D) {
			// 통과여부 조사
			if (isPass()) {
				answer = (answer > insertCnt) ? insertCnt : answer;
			}
			
			return;
		}
		
		// 유지하는 경우
		type[d] = -1;
		powerset(d + 1, insertCnt);
		
		// A로 바꾸는 경우
		type[d] = 0;
		powerset(d + 1, insertCnt + 1);
		
		// B로 바꾸는 경우
		type[d] = 1;
		powerset(d + 1, insertCnt + 1);
	}
	
	public static boolean isPass() {
		for (int j = 0; j < W; ++j) {
			// 우선 가장 맨 위의 값을 기준으로 한다.
			int cur = filmInfo[0][j];
			
			// 아직 조사를 안했으므로 A, B의 카운트는 0
			int cnt = 0;
			
			// 탈출 조건
			boolean flag = false;
			
			for (int i = 0; i < D; ++i) {
				// 조사할 값을 i = 0부터 한다.
				int target = filmInfo[i][j];
				
				// 만약 주입을한 칸이라면
				if (type[i] != -1) {
					// 주입한 타입에 맞게 값을 변경
					target = type[i];
				}
				
				// 현재 조사하고자 하는 값과 기준 값이 다르다면
				if (cur != target) {
					// 기준 값을 조사하는 값으로 변경
					cur = target;
					
					// 조사하는 값은 조사를 한 것 이므로 1로 초기화
					cnt = 1;
				}
				else {
					// 만약 같다면 K를 만족하는지 조사를 위해 +1
					cnt++;
				}
				
				// K를 만족하므로 탈출
				if (cnt >= K) {
					flag = true;
					break;
				}
			}
			
			if (!flag) {
				return false;
			}
		}
		
		return true;
	}
}
