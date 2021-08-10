import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내려가기_2096 {
	public static int N;
	public static int[][] minMemo = new int[2][3];
	public static int[][] maxMemo = new int[2][3];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		int[] map = new int[3];
		int[][] max = new int[2][3];
		int[][] min = new int[2][3];
		
		// 첫 줄을 입력받고, max와 min에도 넣어준다.
		for (int i = 0; i < 3; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			max[0][i] = min[0][i] = map[i] = Integer.parseInt(st.nextToken());
		}
		
		// 두번째줄부터 계산해나간다.
		for (int i = 1; i < N; ++i) {
			// 현재 라인을 입력받아서
			for (int j = 0; j < 3; ++j) {
				map[j] = Integer.parseInt(st.nextToken());
			}
			
			// 윗 줄(0번줄)에서 큰 값, 작은 값 찾아서 아랫줄에 쓴다.
			max[1][0] = Math.max(max[0][0], max[0][1]);
			max[1][2] = Math.max(max[0][1], max[0][2]);
			// 아직 현재의 값을 더하지 않았기에 j가 0, 1에는 각각의 최대 값이 들어가 있으므로
			// [1][0], [1][2]를 비교해주면 된다.
			max[1][1] = Math.max(max[1][0], max[1][2]);
			
			min[1][0] = Math.min(min[0][0], min[0][1]);
			min[1][2] = Math.min(min[0][1], min[0][2]);
			min[1][1] = Math.min(min[1][0], min[1][2]);
			
			// 현재 줄의 값을 더해준다.
			for (int j = 0; j < 3; ++j) {
				max[1][j] += map[j];
				min[1][j] += map[j];
				
				// 이번에 구한 아랫줄(1번줄)이 다음번에 위엣줄이 되도록 0번줄로 이동
				max[0][j] = max[1][j];
				min[0][j] = min[1][j];
			}
		}
		int ans_max = 0;
		int ans_min = 987654321;
		
		for (int i = 0; i < 3; ++i) {
			ans_max = Math.max(max[0][i], ans_max);
			ans_min = Math.min(min[0][i], ans_min);
		}
		
		System.out.println(ans_max + " " + ans_min);
		
		
//		for (int i = 0; i < N; ++i) {
//			st = new StringTokenizer(br.readLine(), " ");
//			
//			for (int j = 0; j < 3; ++j) {
//				int num = Integer.parseInt(st.nextToken());
//				minMemo[1][j] = num;
//				maxMemo[1][j] = num;
//			}
//			
//			// 최소값 찾기
//			minMemo[1][0] = Math.min(minMemo[0][0], minMemo[0][1]) + minMemo[1][0];
//			minMemo[1][1] = Math.min(minMemo[0][0], 
//					Math.min(minMemo[0][1], minMemo[0][2])) + minMemo[1][1];
//			minMemo[1][2] = Math.min(minMemo[0][1], minMemo[0][2]) + minMemo[1][2];			
//			
//			// 최대값 찾기
//			maxMemo[1][0] = Math.max(maxMemo[0][0], maxMemo[0][1]) + maxMemo[1][0];
//			maxMemo[1][1] = Math.max(maxMemo[0][0], 
//					Math.max(maxMemo[0][1], maxMemo[0][2])) + maxMemo[1][1];
//			maxMemo[1][2] = Math.max(maxMemo[0][1], maxMemo[0][2]) + maxMemo[1][2];
//			
//			// 다음 입력을 위해 자리를 위로 올림
//			for (int j = 0; j < 3; ++j) {
//				minMemo[0][j] = minMemo[1][j];
//				maxMemo[0][j] = maxMemo[1][j];
//			}
//		}
//		
//		int max = Math.max(maxMemo[0][0], Math.max(maxMemo[0][1], maxMemo[0][2]));
//		int min = Math.min(minMemo[0][0], Math.min(minMemo[0][1], minMemo[0][2]));
//		
//		System.out.println(max + " " + min);
	}
}
