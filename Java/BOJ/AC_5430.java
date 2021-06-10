import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class AC_5430 {
	// dir => 0 = 정방향, 1 = 역방향
	public static int T, N, dir;
	public static String p;
	public static Deque<Integer> deque;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; ++tc) {
			deque = new ArrayDeque<Integer>();
			
			p = br.readLine();
			N = Integer.parseInt(br.readLine());
			
			String input = br.readLine();
			// 정규표현식에서 특수문자를 사용하기위해 \\을 붙여준다.
			//input = input.replaceAll("\\[|\\]", "");
			
			// substring이 좀더 빠르다.
			input = input.substring(1, input.length() - 1);
			st = new StringTokenizer(input, ",");
			for (int i = 0; i < N; ++i) {
				deque.offer(Integer.parseInt(st.nextToken()));
			}
			
			// 방향을 정방향으로 초기화
			dir = 0;
			boolean isError = false;
			for (int i = 0; i < p.length(); ++i) {
				char cur = p.charAt(i);
				
				if (cur == 'R') {
					// 현재 명령어가 R인 경우 방향을 바꿈
					// 0 = 정방향, 1 = 역방향
					dir = (dir + 1) % 2;
				}
				else if (cur == 'D') {
					// 만약 배열이 비어있다면 에러발생
					if (deque.isEmpty()) {
						isError = true;
						break;
					}
					
					// 해당 방향의 맨 앞 숫자를 지운다.
					if (dir == 0) {
						deque.pollFirst();
					}
					else {
						deque.pollLast();
					}
				}
			}
			
			if (isError) {
				sb.append("error\n");
			}
			else {
				sb.append("[");
				
				// 마지막 한개의 숫자만 남기고 방향에 맞게 저장
				while (deque.size() > 1) {
					if (dir == 0) {
						sb.append(deque.pollFirst());
					}
					else {
						sb.append(deque.pollLast());
					}
					
					sb.append(",");
				}
				
				// D 명령이 성공적으로 수행되고 빈 배열이 될 수 있으므로 이를 처리해야한다.
				if (deque.size() == 0) {
					sb.append("]\n");
				}
				else {
					// 마지막 한개의 숫자도 저장, 이때, 숫자는 무조건 하나만 남기때문에
					// pollFirst(), pollLast() 어떤걸 쓰든 상관없다.
					sb.append(deque.pollFirst()).append("]\n");
				}
			}
		}
		
		// 총 결과를 출력
		System.out.println(sb);
	}
}
