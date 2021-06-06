import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이_10163 {
	public static int N, cnt;
	public static int[][] arr = new int[101][101];
	public static int[] answer = new int[101];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			putPaper(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
		}
		
		for (int i = 0; i < 101; ++i) {
			for (int j = 0; j < 101; ++j) {
				if (arr[i][j] > 0) {
					answer[arr[i][j]]++;
				}
			}
		}
		
		for (int i = 1; i <= N; ++i) {
			sb.append(answer[i]).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void putPaper(int y, int x, int w, int h, int val) {
		// 높이와 너비를 바꾸어 주어야 문제에서 요구하는 위치에
		// 종이를 둘 수 있다!!
		for (int i = y; i < y + w; ++i) {
			for (int j = x; j < x + h; ++j) {
				arr[i][j] = val;
			}
		}
	}
}
