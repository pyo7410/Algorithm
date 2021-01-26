import java.util.Scanner;

public class Simple369_1926 {
	
	static String[] answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		answer = new String[n];
		
		for (int i = 1; i <= n; ++i) {
			int clapCnt = 0;
			String num = Integer.toString(i);
			
			for (int j = 0; j < num.length(); ++j) {
				if (num.charAt(j) == '3') {
					clapCnt++;
				} 
				else if (num.charAt(j) == '6') {
					clapCnt++;
				}
				else if (num.charAt(j) == '9') {
					clapCnt++;
				}
			}
			
			if (clapCnt == 0) {
				answer[i - 1] = num;
			} else {
				String temp = "";
				for (int j = 0; j < clapCnt; ++j) {
					temp += '-';
				}
				answer[i - 1] = temp;
			}
		}
		
		for (int i = 0; i < n; ++i) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
		
		sc.close();
	}
}
