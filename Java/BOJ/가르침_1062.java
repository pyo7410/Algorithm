import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가르침_1062 {
	public static int N, K, answer;
	public static String[] words;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if (K < 5) {
			// 5개 이하는 a, n, t, i, c중 무언가를 안배운게 되고
			// 저 5개의 알파벳중 하나만 모르더라도
			// 모든 단어를 말할 수 없으므로 0을 출력한다.
			System.out.println(0);
			return;
		}
		else if (K == 26) {
			// 알파벳은 26개이고 26개의 단어를 전부 배웠으므로
			// 단어 개수를 바로 출력하면 된다.
			System.out.println(N);
			return;
		}

		words = new String[N];
		
		for (int i = 0; i < N; ++i) {
			String word = br.readLine();
			
			// 앞의 anta와 뒤에있는 tica를 자른다
			words[i] = word.substring(4, word.length() - 4);
		}
		
		// 비트마스킹
		int bitNum = 0;
		
		// a, n, t, i, c는 이미 배웠으므로 알파벳의 인덱스에 해당하는
		// 비트를 1로 바꾸어준다.
		bitNum |= 1 << ('a' - 'a');
		bitNum |= 1 << ('n' - 'a');
		bitNum |= 1 << ('t' - 'a');
		bitNum |= 1 << ('i' - 'a');
		bitNum |= 1 << ('c' - 'a');
		
		combination(0, 0, bitNum);
		System.out.println(answer);
	}
	
	// 한번 배운 알파벳은 다시 배울필요가 없고 알파벳의 순서는 상관없으므로 조합을 사용
	public static void combination(int idx, int cnt, int bitNum) {
		// a, n, t, i, c는 이미 배웠으므로 해당 단어는 포함하면 X
		if (cnt == K - 5) {
			int maxWordsCnt = 0;
			
			for (int i = 0; i < N; ++i) {
				boolean flag = true;
				for (int j = 0; j < words[i].length(); ++j) {
					// 만약 알파벳에 해당하는 위치의 비트가 1이 아니라면
					// 배우지 않은 알파벳이 되므로 해당 단어는 읽을 수 없다.
					if ((bitNum & 1 << (words[i].charAt(j) - 'a')) == 0) {
						flag = false;
						break;
					}
				}
				
				if (flag) {
					maxWordsCnt++;
				}
			}
			
			answer = (answer < maxWordsCnt) ? maxWordsCnt : answer;
			return;
		}
		
		// 알파벳은 26개이고 인덱스는 0부터 시작한다. 0 ~ 25는 26개
		for (int i = idx; i < 26; ++i) {
			// a, n, t, i, c는 이미 알고 있는 단어이기에
			// 해당 비트는 이미 1로 세팅되있어서 넘겨야한다.
			if ((bitNum & 1 << i) == 1) {
				continue;
			}
			
			combination(i + 1, cnt + 1, bitNum | 1 << i);
		}
	}
}
