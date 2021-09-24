package D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 8 Cycle 당 15씩 숫자가 빼지고 원래자리로 돌아오게 된다.
 이를 이용해서 처음부터 큰 숫자로 큐에 넣고 빼고 하는게 아닌
 제일 작은 원래의 수 % 15를 해서 나머지로 남은 수를 큐에 넣어 작업하는게 더 빠르다.
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int[] pw = new int[8];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();
            st = new StringTokenizer(br.readLine(), " ");
            int sub, min = Integer.MAX_VALUE;
            for (int i = 0; i < 8; i++) {
                pw[i] = Integer.parseInt(st.nextToken());
                if (min > pw[i])
                    min = pw[i];
            }
            // 모든 값에서 가까운 15의 배수만큼 뺀다 (같은 수를 빼야해서 %안씀)
            // 0이 나오면 안되니까 divisible하면 +15
            if (min % 15 == 0)
                sub = min / 15 * 15 - 15;
            else
                sub = min / 15 * 15;
            for (int i = 0; i < 8; i++) 
                pw[i] -= sub;
            // 배열을 순회하면서 0이 나올 때까지 값을 빼준다
            int val = 0, i = 0;
            while (true) {
                pw[i] -= val + 1;
                if (pw[i] <= 0)
                    break;
                i = (i + 1) % 8;
                val = (val + 1) % 5;
            }
            pw[i] = 0;
            System.out.print("#" + tc + " ");
            for (int j = i; j < i+8; j++) {
                System.out.print(pw[(j + 1) % 8] + " ");
            }
            System.out.println();
        }
        System.exit(0);
    }
}
 */

public class 암호생성기_1225 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		for (int tc = 1; tc <= 10; ++tc) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < 8; ++i) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			
			int cnt = 1;
			while (true) {
				if (cnt > 5) {
					cnt = 1;
				}
				
				int num = q.poll() - cnt;
				cnt++;
				
				if (num <= 0) {
					q.offer(0);
					break;
				}
				
				q.offer(num);
			}
			
			sb.append("#").append(tc).append(" ");
			while (!q.isEmpty()) {
				sb.append(q.poll()).append(" ");
			}			
			
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
