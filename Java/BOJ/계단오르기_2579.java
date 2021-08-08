import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 계단오르기 {
	public static int N;
	public static int[] stairs = new int[301];
	public static int[] memo = new int[301];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; ++i) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		memo[1] = stairs[1];
		memo[2] = Math.max(stairs[2], stairs[1] + stairs[2]);
		for (int i = 3; i <= N; ++i) {
			// 한칸 건너뛰어 올라온 경우와 이전계단에서 바로올라와 연속으로 세계단을 밟을 수 없어 3번째전 계단까지 포함한 경우
			memo[i] = Math.max(memo[i - 2], memo[i - 3] + stairs[i - 1]) + stairs[i];
		}
		
		System.out.println(memo[N]);
	}
}
