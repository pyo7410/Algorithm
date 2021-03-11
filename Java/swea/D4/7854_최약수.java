package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 최약수_7854 {
	public static int answer;
	public static int X;
	// 최약수를 저장
	public static int[][] num = {{1, 2, 5}, {10, 20, 25, 5}, {100, 125, 200, 250, 500}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			X = Integer.parseInt(br.readLine());
			
			// 최약수
			// 1, 2, 5
			// 10, 20, 25, 5
			// 100, 125, 200, 250, 500
			// 1000, 1250, 2000, 2500, 5000
			// ...xx100, ...xx125, ...xx200, ...xx250, ...xx500
			
			int len = Integer.toString(X).length();
			
			answer = 0;
			
			// 입력받은 숫자의 자리수보다 적은 자리수의 최약수는 모두 포함되므로
			// 다 더해주어야 한다.
			if (len > 1) {
				answer += 3;
			}
			
			if (len > 2) {
				answer += 4;
			}
			
			// 3자리 이상부터는 최약수가 5개씩만 있다
			for (int i = 3; i < len; ++i) {
				answer += 5;
			}
			
			if (len < 3) {
				for (int j = 0; j < num[len - 1].length; ++j) {
					if (num[len - 1][j] <= X) {
						answer++;
					}
					else {
						// 배열에 오름차순으로 최약수가 저장되있기에 뒤에 내용을 비교해봤자
						// 다 큰 수 이므로 할 필요가 없다.
						break;
					}
				}
			}
			else {
				int temp = Integer.parseInt(Integer.toString(X).substring(0, 3));
				
				for (int i = 0; i < 5; ++i) {
					if (num[2][i] <= temp) {
						answer++;
					}
					else {
						// 배열에 오름차순으로 최약수가 저장되있기에 뒤에 내용을 비교해봤자
						// 다 큰 수 이므로 할 필요가 없다.
						break;
					}
				}
			}
			
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
