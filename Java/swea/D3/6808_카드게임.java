package D3;

import java.util.Scanner;

public class 카드게임_6808 {
	public static boolean[] number;
	public static int[] kCard = new int[9];
	public static int winCnt, loseCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; ++tc) {			
			int input;
			number = new boolean[19];
			for (int i = 0; i < 9; ++i) {
				input = sc.nextInt();
				number[input] = true;
				kCard[i] = input;
			}
			
			winCnt = 0;
			loseCnt = 0;
			
			permutation(0, 0, 0, "");
			
			System.out.println("#" + tc + " " + winCnt + " " + loseCnt);
		}
		
		sc.close();
	}
	
	public static void permutation(int idx, int kScore, int iScore, String temp) {
		if (idx >= 9) {
			if (kScore > iScore) {
				winCnt++;
			}
			else if (kScore < iScore) {
				loseCnt++;
			}
			
			return;
		}
		
		for (int i = 1; i < 19; ++i) {
			if (!number[i]) {
				number[i] = true;
				
				int score = kCard[idx] + i;
				
				if (kCard[idx] >= i) {
					
					permutation(idx + 1, kScore + score, iScore, temp + " " + i);
				}
				else {
					permutation(idx + 1, kScore, iScore + score, temp + " " + i);
				}
				
				number[i] = false;
			}
		}
	}
}
