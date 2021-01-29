package D3;

import java.util.Scanner;

public class 햄버거다이어트_5215 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		for (int idx = 1; idx <= tc; ++idx) {
			answer = 0;
			n = sc.nextInt();
			l = sc.nextInt();
			
			for (int i = 0; i < n; ++i)
			{
				score[i] = sc.nextInt();
				kcal[i] = sc.nextInt();
			}
			
			solve(0, 0, 0);
			System.out.println("#" + idx + " " + answer);
		}
		
		sc.close();
	}

	static int[] score = new int[21];
	static int[] kcal = new int[21];
	static int answer = 0;
	static int n, l;
    
    static void solve(int idx, int sumScore, int sumKcal) {
		if (idx == n) {
			if (answer < sumScore && sumKcal <= l) {
				answer = sumScore;
			}
			
			return;
		}
		
		solve(idx + 1, sumScore + score[idx], sumKcal + kcal[idx]);
		solve(idx + 1, sumScore, sumKcal);
	}
}
