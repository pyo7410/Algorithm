package D3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 성공적인공연기획_4789 {
	public static int[] info;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			String input = br.readLine();
			int len = input.length();
			info = new int[len];
			
			for (int i = 0; i < len; ++i) {
				info[i] = input.charAt(i) - '0';
			}
			
			int curPeople = 0;
			int employerCnt = 0;
			
			for (int i = 0; i < len; ++i) {
				if (curPeople >= i) {
					curPeople += info[i];
					continue;
				}
				else {
					employerCnt += (i - curPeople);
					// 고용자를 두어야 뒤의 사람들도 조사가 가능하다!
					// i 전까지의 사람들이 부족하다는 의미이므로 최소인원의 고용자를 즉, 1명씩 0인 위치에 포함하면
					// i 명의 사람들이 있게되고  i명 이상일 때 박수치는 사람들도 포함해야하므로 info[i]를 더한다.
					curPeople = i + info[i];
				}
			}
			
			sb.append("#").append(tc).append(" ").append(employerCnt);
			bw.write(sb.toString());
			bw.newLine();
			sb.setLength(0);
		}
		
		bw.flush();
	}
}
