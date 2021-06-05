import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 참외밭_2477 {
	public static int K;
	
	// 동 서 남 북
	public static int[] NEWS = new int[6];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < 6; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			st.nextToken();
			NEWS[i] = Integer.parseInt(st.nextToken());
		}
		
		// 가장 긴 변을 찾음
		int max = NEWS[0];
		int maxIdx = 0;
		for (int i = 1; i < 6; ++i) {
			if (max < NEWS[i]) {
				max = NEWS[i];
				maxIdx = i;
			}
		}
		
		// 그 다음 긴 변과 작은 사각형의 변들을 찾는다.
		int idx = (maxIdx - 1) < 0 ? 5 : maxIdx - 1;
		int nextMax = NEWS[idx];
		int min, nextMin;
		if (NEWS[idx] < NEWS[(maxIdx + 1) % 6]) {
			idx = (maxIdx + 1) % 6;
			nextMax = NEWS[idx];
			min = max - NEWS[(idx + 1) % 6];
			nextMin = nextMax - NEWS[(maxIdx - 1) < 0 ? 5 : maxIdx - 1];
		}
		else {
			min = nextMax - NEWS[(maxIdx + 1) % 6];
			nextMin = max - NEWS[(idx - 1) < 0 ? 5 : idx - 1];
		}
		
		int area = (max * nextMax) - (min * nextMin);
		System.out.println(area * K);
	}
}
