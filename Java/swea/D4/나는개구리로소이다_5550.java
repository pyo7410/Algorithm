package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나는개구리로소이다_5550 {
	public static int answer;
	public static char[] recordSound;
	public static int[] flogCnt;
	public static char[] flogSound = {'c', 'r', 'o', 'a', 'k'};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			String s = br.readLine();
			int len = s.length();
			
			flogCnt = new int[5];
			recordSound = new char[len];
			recordSound = s.toCharArray();
			
			answer = -1;
			boolean isPossible = true;
			for (int i = 0; i < len; ++i) {
				// 정상적으로 k까지 울음소리가 나오고 다음에 나온 c부터 시작하는 정상적인 울음소리는
				// 이전에 k까지 울었던 개구리이므로 카운팅하면 안되고 계속 갱신해야하므로
				// 매번 c의 위치에있는 현재 울고있는 개구리의 최대값을 비교하여 정답에 갱신한다.
				// 이상한 울음소리는 아래서 판단!
				answer = (answer < flogCnt[0]) ? flogCnt[0] : answer;
				
				for (int j = 0; j < 5; ++j) {
					if (recordSound[i] == flogSound[j]) {
						flogCnt[j]++;
						
						// 정상적으로 k까지 울음소리가 나왔다면 해당 개구리는 빠져도된다.
						if (flogSound[j] == 'k') {
							for (int k = 0; k < 5; ++k) {
								flogCnt[k]--;
							}
						}
					}
					
					// c, r, o, a, k의 순으로 나와야하는데
					// 뒤에있는 문자의 수가 크다는 것은 
					// c, r, o, a, k의 순으로 녹음이 된게 아니라는 의미이므로 
					// 개구리의 울음소리로 불가능하다
					if (j > 0 && flogCnt[j - 1] < flogCnt[j]) {
						isPossible = false;
						break;
					}
				}
				
				// 개구리의 울음소리로 불가능 하므로 더이상 돌 필요가 없다.
				if (!isPossible) {
					break;
				}
			}
			
			// k가 나올때마다 한마리의 울음소리를 뺏으므로
			// 만약 0이 아니라면 c, r, o, a, k 중 더 많이 나온 문자가 있다는 의미이므로
			// 올바른 개구리 울음소리가 될 수 없다.
			// ex) croakk
			if (isPossible) {
				for (int i = 0; i < 5; ++i) {
					if (flogCnt[i] != 0) {
						isPossible = false;
						break;
					}
				}				
			}
			
			answer = (isPossible) ? answer : -1;
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}	
