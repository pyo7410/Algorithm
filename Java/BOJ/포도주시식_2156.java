import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 포도주시식_2156 {
	public static int N;
	// 주의!!
	// 만약 N이 1일 경우 DP를 할때에 memo배열이 1만 생기기때문에 memo[2] 부분에서 오류가 생긴다...
	public static int[] arr = new int[10001];
	public static int[] memo = new int[10001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		memo[1] = arr[1];
		// 음이 아닌 정수가 입력으로 들어오므로
		// 2잔째에서 최대는 첫번째를 마시고 두번째도 마시는 경우가 된다.
		memo[2] = arr[1] + arr[2];
		for (int i = 3; i <= N; ++i) {
			// 현재 잔을 안먹고 건너뛸 수 도 있으므로 현재잔을 안먹는 경우와
			// 한 잔을 건너뛰고 마시는 경우와
			// 두잔연속 먹고 한잔을 건너뛰는 경우중 최대값
			memo[i] = Math.max(memo[i - 1], Math.max(memo[i - 2] + arr[i], memo[i - 3] + arr[i - 1] + arr[i]));
		}
		
		System.out.println(memo[N]);
	}
}
