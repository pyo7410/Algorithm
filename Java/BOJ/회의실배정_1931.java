import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 회의실배정_1931 {
	public static int N, answer;
	public static int[][] session;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		session = new int[N][2];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			session[i][0] = Integer.parseInt(st.nextToken());
			session[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 빨리 끝나는 회의들을 선택해야 많이 선택할 수 있다!
		
		// 끝나는 시간이 이른 순으로 정렬하되 끝나는 시간이 같다면 시작시간이 이른 순으로 정렬
		// 6 - 7, 7 - 7 인 경우 7 - 7은 바로 끝나므로 둘 다 선택이 가능해야하는데
		// 이때, 끝나는 시간이 같지만 시작이 이른 순으로 하지 않아 7 - 7이 먼저 선택 된다면
		// 6 - 7은 선택이 불가능 해지므로 반드시 끝나는 시간이 같다면 시작시간이 이른 순으로 정렬을 해야한다.
		Arrays.sort(session, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				int diff = o1[1] - o2[1];
				return diff != 0 ? diff : o1[0] - o2[0];
			}
			
		});
		
		int end = session[0][1];
		
		answer++;		
		for (int i = 1; i < N; ++i) {
			// 시작 시간이 이전에 회의의 끝나는 시간보다 이전이라면
			// 그 회의는 선택 불가
			if (session[i][0] < end) {
				continue;
			}
			
			end = session[i][1];
			answer++;
		}
		
		System.out.println(answer);
	}
}
