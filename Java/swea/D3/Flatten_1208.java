package D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Flatten_1208 {
	public static int[] box = new int[100];
	public static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; ++tc) {
			int dump_cnt = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 100; ++i) {
				box[i] = Integer.parseInt(st.nextToken());
			}
			
			//int answer = 0;
			while (dump_cnt >= 0) {
				int max_idx = 0;
				int max_num = box[0];
				int min_idx = 0;
				int min_num = box[0];
				
				for (int i = 1; i < 100; ++i) {
					if (max_num < box[i]) {
						max_num = box[i];
						max_idx = i;
					}
					
					if (min_num > box[i]) {
						min_num = box[i];
						min_idx = i;
					}
				}
				
				if (dump_cnt == 0) {
					answer = max_num - min_num;
				}
				
				box[max_idx]--;
				box[min_idx]++;
				dump_cnt--;
			}
			
			System.out.println("#" + tc + " " + answer);
			
			// 방법 2
//			Arrays.sort(box);
//			
//			for (int i = 0; i < dump_cnt; ++i) {
//				box[99]--;
//				box[0]++;
//				
//				Arrays.sort(box);
//			}
//			
//			answer = box[99] - box[0];
//			System.out.println("#" + tc + " " + answer);
		}
	}
}
