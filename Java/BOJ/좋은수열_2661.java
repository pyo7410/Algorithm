import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 좋은수열_2661 {
	public static boolean isFound;
	public static int N;
	public static String answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		permutation(0, "");
	}
	
	public static void permutation(int cnt, String s) {
		if (cnt == N) {
			System.out.println(s);
			System.exit(0);
			return;
		}
		
		for (int i = 1; i <= 3; ++i) {
			if (isCorrect(s + i)) {
				permutation(cnt + 1, s + i);
			}
		}
	}
	
	public static boolean isCorrect(String s) {
		int len = s.length();
		
		// 부분 문자열이 좋은 수열인지 판단을 할 때
		// 문자열에 새로 들어온 부분부터 조사를 해야
		// 좋은 수열인지 나쁜 수열인지 판별이 가능
		
		// 최대로 비교해 볼 수 있는 부분 문자열의 길이는 현재 문자열의
		// 절반까지 이므로 한 글자 부터 반으로 나눌 수 있는 문자열의 절반 길이 까지 탐색
		for (int i = 1; i <= len / 2; ++i) {
			// 비교할 문자열을 만든다
			// 이때, 새로운 숫자가 나쁜 수열에 해당하는지 봐야하기 때문에
			// 뒤에서 한글자, 두글자, ... , 문자열의 절반 길이 까지 분리한다.
			String frontStr = s.substring(len - (i * 2), len - i);
			String backStr = s.substring(len - i, len);
			
			if (frontStr.equals(backStr)) {
				return false;
			}
		}
		
		return true;
	}
}
