import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class 로프_2217 {
	public static int N;
	public static Integer[] rope;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		N = Integer.parseInt(br.readLine());
		
		rope = new Integer[N];
		
		for (int i = 0; i < N; ++i) {
			rope[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(rope, Collections.reverseOrder());
		
		for (int i = 0; i < N; ++i) {
			pq.offer(rope[i] * (i + 1));
		}
		
		System.out.println(pq.peek());
	}
}
