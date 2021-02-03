package D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 항구에들어오는배_4371 {
	public static int N;
	public static boolean[] check;
	public static int[] harbor;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			check = new boolean[N];
			harbor = new int[N];
			
			for (int i = 0; i < N; ++i) {
				harbor[i] = Integer.parseInt(br.readLine());
			}
			
			int boatCnt = 0;
			for (int i = 1; i < N; ++i) {
				if (check[i]) {
					continue;
				}
				
				boatCnt++;
				
				int cycle = harbor[i] - 1;
				int happyDay = harbor[i];
				for (int j = i; j < N; ++j) {
					if (happyDay == harbor[j]) {
						check[j] = true;
						happyDay += cycle;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(boatCnt);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
