import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기1_16926 {
	public static int N, M, R, cnt;
	public static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 배열에서 한번 회전할 때 필요한
		// 반시계 이동은 행과 열 중에서 가장 작은 값을 2로 나눈 수가 된다.
		// 작은 수가 홀수 일때는 남는 가운데 수가 어차피 한개뿐이라 굳이 할 필요가 없기에
		// min(N, M) / 2가 된다.
		cnt = Math.min(N, M) / 2;
		
		for (int i = 0; i < R; ++i) {
			rotate(0);
		}
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void rotate(int n) {
		if (n >= cnt) {
			return;
		}
		
		int left = n;
		int bottom = N - n - 1;
		int right = M - n - 1;
		int top = n;
		
		// 왼쪽 위 오른쪽 아래 순으로 배열을 회전시키면 결국 왼쪽의 맨 위 원소만 빼고
		// 전부 회전할 수 있으므로 temp변수에 왼쪽의 맨 위 원소를 저장하여 마지막에
		// 아래로 내려간 위치에 값을 넣어주면 된다.
		int temp = arr[top][left];		
		
		// 인덱스를 이동할때 마지막 인덱스는 이동시킬 필요가 없기에
		// 배열의 크기를 N, M까지만 해도 문제가 없고 마지막 원소 이전까지만 이동시키면 된다.
		// 마지막 인덱스는 어차피 다음 이동에서 덧씌우기 때문이다.
		// 왼쪽으로
		for (int i = left; i < right; ++i) {
			arr[top][i] = arr[top][i + 1];
		}
		
		// 위로
		for (int i = top; i < bottom; ++i) {
			arr[i][right] = arr[i + 1][right];
		}
		
		// 오른쪽으로
		for (int i = right; i > left; i--) {
			arr[bottom][i] = arr[bottom][i - 1];
		}
		
		// 아래로 
		for (int i = bottom; i > top; i--) {
			arr[i][left] = arr[i - 1][left];
		}
		
		// 왼쪽의 맨 위 원소는 덧씌워져 temp에 저장했으므로
        // 이동할 위치에 다시 temp에 저장한 값을 넣는다.
		arr[top + 1][left] = temp;
		
		rotate(n + 1);
	}
}
