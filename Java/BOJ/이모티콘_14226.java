import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 이모티콘_14226 {
	public static class Info {
		// screen : 현재 화면의 이모티콘 길이
		// clipboard : 클립보드에 저장된 이모티콘의 길이
		// time : 진행된 시간
		int screen, clipboard, time;
		
		public Info(int screen, int clipboard, int time) {
			super();
			this.screen = screen;
			this.clipboard = clipboard;
			this.time = time;
		}
	}
	
	public static int S, answer;
	public static boolean[][] visited = new boolean[1001][1001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = Integer.parseInt(br.readLine());
		
		bfs();
		
		System.out.println(answer);
	}
	
	// memoization을 사용
	// 현재 화면의 길이와 클립보드의 길이를 visited의 인덱스로 사용하여
	// 방문 상태를 계속해서 기록하여 중복을 제거한다.
	public static void bfs() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(1, 0, 0));
		visited[1][0] = true;
		
		while (!q.isEmpty()) {
			Info info = q.poll();
			
			// 현재 화면의 길이가 목표로하는 길이인 S와 같다면
			if (info.screen == S) {
				// 정답 갱신
				answer = info.time;
				
				// BFS 특성상 먼저 들어온 경우가 가장 빠르다!
				return;
			}
			
			// 복사
			if (!visited[info.screen][info.screen]) {
				q.offer(new Info(info.screen, info.screen, info.time + 1));
				visited[info.screen][info.screen] = true;
			}
			
			// 붙여넣기
			// 클립보드가 비어있다면 붙여넣기 불가
			// 일부만 복사 불가, 일부만 삭제 불가
			if (info.clipboard > 0 && info.screen + info.clipboard <= S && !visited[info.screen + info.clipboard][info.clipboard]) {
				q.offer(new Info(info.screen + info.clipboard, info.clipboard, info.time + 1));
				visited[info.screen + info.clipboard][info.clipboard] = true;
			}
			
			// 하나 삭제
			if (info.screen - 1 > 0 && !visited[info.screen - 1][info.clipboard]) {
				q.offer(new Info(info.screen - 1, info.clipboard, info.time + 1));
				visited[info.screen - 1][info.clipboard] = true;
			}
		}
	}
}
