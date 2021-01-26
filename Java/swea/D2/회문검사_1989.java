import java.util.Scanner;

public class 회문검사_1989 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			String s = sc.next();
			int mid = s.length() / 2;
			int left = 0, right = s.length() - 1;
			int answer = 1;
			
			while (left < mid) {
				if (s.charAt(left) != s.charAt(right)) {
					answer = 0;
				}
				
				left++;
				right--;
			}
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}
}
