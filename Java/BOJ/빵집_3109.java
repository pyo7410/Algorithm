import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빵집_3109 {
	public static int R, C, answer;
	public static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < R; ++i) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		
		answer = 0;
		
		for (int i = 0; i < R; ++i) {
			connectPipe(i, 0);
		}
		
		System.out.println(answer);
	}
	
	// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
	// 파이프 연결 규칙에 의해 맨 위부터 최대한 오른쪽 위 대각선 오른쪽, 오른쪽 아래 대각선 순으로 설치한다면
	// 최대로 파이프를 설치가 가능하다.
	// 모르면 그림 그려봐
	public static int[] dy = {-1, 0, 1};
	public static boolean connectPipe(int r, int c) {
		if (c == C - 1) {
			answer++;
			return true;
		}
		
		for (int j = 0; j < 3; ++j) {
			int ny = r + dy[j];
			
			if (ny < 0 || ny >= R || map[ny][c + 1] != '.') {
				continue;
			}
			
			if (connectPipe(ny, c + 1)) {
				map[r][c] = 'p';
				return true;
			}
			
			// 해당 경로로는 어차피 접근을 못하므로 다음 검사 시에도 못가게 바꾸어 준다. 
			map[r][c] = 'x';
		}
		
		return false;
	}
}
