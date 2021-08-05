import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 규칙을 잘 살펴보면 N이 2의 제곱 수 라면 N이 남게 된다.
 
 */

public class 카드2_2164 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		Queue<Integer> q = new LinkedList<Integer>();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; ++i) {
			q.offer(i);
		}
		
		while (q.size() > 1) {
			q.poll();
			
			q.offer(q.poll());
		}
		
		sb.append(q.peek());
		System.out.println(sb);
	}
}

/*
 public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		//1. N과 가장 가까운 2의 제곱수를 찾는다.
		//2. N - (1의숫자) * 2 가 답.
		
		int n = 1;
		while( n < N ) {
			n *= 2;
		}
		n /= 2;
		if( N == 1 )
			System.out.println(1);
		else
			System.out.println( (N-n) * 2 );
		
	}
}
*/
