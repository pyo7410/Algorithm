import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합_1806 {
	public static int N, S, answer;
	public static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; ++i) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		answer = Integer.MAX_VALUE;
		int l = 0, r = 0;
		int sum = num[0];
		while (r < N && l <= r) {
			if (sum >= S) {
				int len = r - l + 1;
				answer = (answer > len) ? len : answer;
			}
			
			if (sum < S) {
				// sum이 S보다 작은데 r이 끝에 도달했다는 의미는
				// 더이상 더할 숫자가 없으므로 이보다 큰 수를 만들 수 없다
				// 때문에 더이상 조사할 필요가 없다.
				if (r + 1 >= N) {
					break;
				}
				
				// r을 이동하고 이동한 다음 위치를 더해준다.
				sum += num[++r];
			}
			else {
				// 현재 위치를 빼고 l을 이동
				sum -= num[l++];
			}
		}
		
		System.out.println((answer == Integer.MAX_VALUE) ? 0 : answer);
	}
}
