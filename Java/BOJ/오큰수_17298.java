import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 오큰수_17298 {
	public static int N;
	public static int[] A, answer;
	public static Stack<Integer> stk;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;		
		
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];		
		answer = new int[N];
		stk = new Stack<Integer>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		stk.add(0);
		for (int i = 1; i < N; ++i) {
			if (stk.isEmpty()) {
				stk.add(i);
			}
			
			while (!stk.isEmpty() && A[stk.peek()] < A[i]) {
				answer[stk.peek()] = A[i];
				stk.pop();
			}
			
			stk.add(i);
		}
		
		while (!stk.isEmpty()) {
			answer[stk.pop()] = -1;
		}
		
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < N; ++i) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}
}
