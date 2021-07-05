import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두용액_2470 {
	public static int N;
	public static int[] answer = new int[2];
	public static int[] liquid;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		liquid = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; ++i) {
			liquid[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(liquid);
		
		int l = 0, r = liquid.length - 1;
		int min = Integer.MAX_VALUE;
		while (l < r) {
			int sum = liquid[l] + liquid[r];
			
			if (Math.abs(sum) < Math.abs(min)) {
				min = sum;
				
				answer[0] = liquid[l];
				answer[1] = liquid[r];
			}
			
			if (sum < 0) {
				l++;
			}
			else if (sum > 0) {
				r--;
			}
			else {
				answer[0] = liquid[l];
				answer[1] = liquid[r];
				break;
			}
		}
		
		System.out.println(answer[0] + " " + answer[1]);
	}
}
