import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도영이가만든맛있는음식_2961 {
	public static int N, answer;
	public static int[][] taste;
	public static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		taste = new int[N][2];
		isSelected = new boolean[N];
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			taste[i][0] = Integer.parseInt(st.nextToken());
			taste[i][1] = Integer.parseInt(st.nextToken());
		}
		
//		powerset(0);
		powerset2(0, 1, 0);
		
		System.out.println(answer);
	}
	
	public static void powerset(int cnt) {
		if (cnt == N) {
			int sour = 1, bitterness = 0;
			for (int i = 0; i < N; ++i) {
				if (isSelected[i]) {
					sour *= taste[i][0];
					bitterness += taste[i][1];
				}
			}
			
			int totalTaste = Math.abs(sour - bitterness);
			if (totalTaste < answer) {
				if (bitterness == 0) {
					return;
				}
				
				answer = totalTaste;
			}
			
			return;
		}
		
		isSelected[cnt] = true;
		powerset(cnt + 1);
		isSelected[cnt] = false;
		powerset(cnt + 1);
	}
	
	public static void powerset2(int cnt, int sour, int bitterness) {
		if (cnt == N) {
			// 한가지이상은 무조건 사용해야 하므로
			if (bitterness == 0) {
				return;
			}
			
			int totalTaste = Math.abs(sour - bitterness);
			if (totalTaste < answer) {
				answer = totalTaste;
			}
			
			return;
		}
		
		powerset2(cnt + 1, sour * taste[cnt][0], bitterness + taste[cnt][1]);
		powerset2(cnt + 1, sour, bitterness);
	}
}
