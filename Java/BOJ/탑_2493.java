import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 탑_2493 {
	public static class Info {
		int idx, height;

		public Info(int idx, int height) {
			super();
			this.idx = idx;
			this.height = height;
		}
	}
	
	public static int N;
	public static int[] tower;
	public static int[] answer;
	public static Stack<Info> stk;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		tower = new int[N + 1];
		answer = new int[N + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 1; i <= N; ++i) {
			tower[i] = Integer.parseInt(st.nextToken());
		}
		
		int cur = N;
		stk = new Stack<Info>();
		do {
			while (!stk.isEmpty() && stk.peek().height <= tower[cur]) {
				Info info = stk.pop();
				answer[info.idx] = cur;
			}
			
			stk.add(new Info(cur, tower[cur--]));
		} while (cur > 0);
		
		for (int i = 1; i <= N; ++i) {
			sb.append(answer[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}
