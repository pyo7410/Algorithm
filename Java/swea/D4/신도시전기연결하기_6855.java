package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 신도시전기연결하기_6855 {
	public static int N, K, answer;
	public static int[] cityMap, cityLen;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cityMap = new int[N];
			
			// 각 구간의 길이를 저장하기에 N - 1의 크기만 필요
			cityLen = new int[N - 1];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				cityMap[i] = Integer.parseInt(st.nextToken());
			}
			
			int answer = 0;
			
			// K가 N보다 크거나 같다면 답은 무조건 0이된다.
			if (N <= K) {
				sb.append("#").append(tc).append(" ").append(answer).append("\n");
				continue;
			}
			
			// 각 구간의 길이를 구한다.
			for (int i = 0; i < N - 1; ++i) {
				cityLen[i] = cityMap[i + 1] - cityMap[i];
			}
			
			// 작은 순으로 꺼내기위해 오름차순 정렬
			// 즉, 가장 큰 구간 길이를 가진 곳에는 K개의 발전소를 설치한다는 의미
			Arrays.sort(cityLen);
			
			// 발전소를 제외한 N - K개의 집에는 전선을 설치하므로
			// 전선의 길이를 더해준다.
			for (int i = 0; i < N - K; ++i) {
				answer += cityLen[i];
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
