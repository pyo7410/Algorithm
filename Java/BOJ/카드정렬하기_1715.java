import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 카드정렬하기_1715 {
	public static int N, answer;
	public static PriorityQueue<Integer> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; ++i) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		while (pq.size() > 1) {
			int num1 = pq.poll();
			int num2 = pq.poll();
			
			answer += (num1 + num2);
			
			pq.add(num1 + num2);
		}
		
		System.out.println(answer);
	}
}
