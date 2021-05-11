import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기_1759 {
	public static int L, C;
	public static StringBuilder sb;
	public static char[] words;
	public static int[] number;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		words = new char[C];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; ++i) {
			words[i] = st.nextToken().charAt(0);
		}
		
		// 암호를 이루는 알파벳이 정렬되어 증가하는 순서로 배열되있을 것으로 추측되므로
		// 우선 정렬을 해준다.
		Arrays.sort(words);
		
		sb = new StringBuilder("");
		combination(0, 0, "");
		
		System.out.println(sb);
	}
	
	public static void combination(int cnt, int idx, String s) {
		// 주어진 길이만큼 암호가 만들어지면
		if (cnt == L) {
			// 모음은 1개 이상
			int vowelCnt = 0;
			// 자음을 2개 이상
			int consonantsCnt = 0;
			for (int i = 0; i < L; ++i) {
				if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u') {
					vowelCnt++;
				}
				else {
					consonantsCnt++;
				}
			}

			// 모음, 자음 조건 체크
			// 증가하는 순서는 이미 원본 알파벳들을 순서대로 정렬했으므로 상관 X
			if (vowelCnt >= 1 && consonantsCnt >= 2) {
				sb.append(s).append("\n");
			}
			return;
		}
		
		// 알파벳 암호 조합을 찾는다.
		for (int i = idx; i < C; ++i) {
			combination(cnt + 1, i + 1, s + Character.toString(words[i]));
		}
	}
}
