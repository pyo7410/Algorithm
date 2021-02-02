package D3;

import java.util.Scanner;

public class 조만들기_8104 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder answer = new StringBuilder("");				
				
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; ++tc) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			answer.append("#" + tc + " ");
			
			int group_1_score = 0;			
			for (int i = 1; i <= n; ++i) {				
				if (i % 2 == 0) {
					// 짝수면 팀원의 등수가 i * k씩 증가
					group_1_score += (i * k);
				}
				else {
					// 홀수면 팀원의 등수가 (k * (i - 1)) + 1씩 증가
					group_1_score += ((i - 1) * k) + 1;
				}
			}
			
			for (int i = 0; i < k; ++i) {
				if (n % 2 != 0) {
					// 팀원의 수가 홀수면 첫 번째 팀 부터 맨 마지막 팀까지 +1 씩 증가
					answer.append((group_1_score + i) + " ");
				}
				else {
					// 짝수라면 모든 조의 합이 동일
					answer.append(group_1_score + " ");
				}
			}
			
			System.out.println(answer);		
			answer.setLength(0);
		}
		
		sc.close();
	}
}
