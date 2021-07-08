import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 검문_2981 {
	public static int N;
	public static int[] num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];
		
		for (int i = 0; i < N; ++i) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(num);
		
		int gcd = num[1] - num[0];
		for (int i = 2; i < N; ++i) {
			gcd = getGcd(gcd, num[i] - num[i - 1]);
		}
		
		StringBuilder sb = new StringBuilder("");		
		for (int i = 2; i <= gcd; ++i) {
			if (gcd % i == 0) {
				sb.append(i).append(" ");
			}
		}
		
		System.out.println(sb);
	}
	
	// 유클리드 호제법
	public static int getGcd(int n1, int n2) {
		while (n2 != 0) {
			int r = n1 % n2;
			n1 = n2;
			n2 = r;
		}
		
		return n1;
	}
}
