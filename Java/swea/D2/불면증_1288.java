import java.util.Scanner;

public class 불면증_1288 {
	/*
	 * 해시맵으로 더 간단하게 풀 수 있다
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			
			int num = n, cnt = 0;
			boolean[] check = new boolean[10];
			while (true) {

				int temp = num;
				while (temp > 0) {
					if (!check[temp % 10]) {
						cnt++;
					}
					check[temp % 10] = true;
					temp /= 10;
				}
				
				if (cnt == 10) {
					break;
				}
				
				num += n;
			}
			
			System.out.println("#" + tc + " " + num);
		}
		
		sc.close();
	}

}
