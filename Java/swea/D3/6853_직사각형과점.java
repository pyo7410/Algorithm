package D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 직사각형과점_6853 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X1 = Integer.parseInt(st.nextToken());
			int Y1 = Integer.parseInt(st.nextToken());
			int X2 = Integer.parseInt(st.nextToken());
			int Y2 = Integer.parseInt(st.nextToken());
			
			int N = Integer.parseInt(br.readLine());
			
			int inCnt = 0;
			int outCnt = 0;
			int lineCnt = 0;
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				if (x < X1 || x > X2 || y < Y1 || y > Y2) {
					outCnt++;
				}
				else if ((x > X1 && x < X2) && (y > Y1 && y < Y2)) {
					inCnt++;
				}
				else {
					// 줄위 입력은 조건이 생각보다 까다로우므로
					// 직접 쓸필요없이 마지막 else로 빼내면 된다!
					lineCnt++;
				}
			}
			
			sb.append("#").append(tc).append(" ");
			sb.append(inCnt).append(" ").append(lineCnt).append(" ").append(outCnt);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
