import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 어른상어_19237 {
	public static class Shark {
		// sharkNum : 상어 번호
		// y, x: 상어의 위치
		// time : 상어의 시간
		// dir : 상어의 방향
		int sharkNum, y, x, time, dir;

		public Shark(int sharkNum, int y, int x, int time, int dir) {
			this.sharkNum = sharkNum;
			this.y = y;
			this.x = x;
			this.time = time;
			this.dir = dir;
		}
	}
	
	// visited 배열을 만들때 상어의 번호와 시간을 기록하기위한 클래스
	public static class Info {
		int sharkNum, time;

		public Info(int sharkNum, int time) {
			this.sharkNum = sharkNum;
			this.time = time;
		}
	}
	
	public static int N, M, k, sharkCnt;
	public static Queue<Shark> q;
	public static Shark[] sharks;
	public static int[][] map;
	public static Info[][] visited;
	public static int[][][] sharkPriority;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		sharks = new Shark[M + 1];
		map = new int[N][N];
		visited = new Info[N][N];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 상어라면
				if (map[i][j] > 0) {
					// 상어 정보 기록
					sharks[map[i][j]] = new Shark(map[i][j], i, j, 1, -1);
					// 상어 위치의 시간을 1로
					visited[i][j] = new Info(map[i][j], 1);
					// 상어의 수 증가
					sharkCnt++;
				}
				else {
					// 상어가 없는 위치는 0으로
					visited[i][j] = new Info(0, 0);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= sharkCnt; ++i) {
			sharks[i].dir = Integer.parseInt(st.nextToken());
		}
		
		// 문제에서 방향은 1부터 시작
		sharkPriority = new int[sharkCnt + 1][5][5];
		for (int i = 1; i <= sharkCnt; ++i) {

			// 1 : 위, 2 : 아래, 3 : 왼쪽, 4 : 오른쪽
			for (int j = 1; j < 5; ++j) {
				st = new StringTokenizer(br.readLine());
				
				for (int k = 1; k < 5; ++k) {
					sharkPriority[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		// 낮은 번호의 상어먼저 순서대로 처리하기 위해 큐에 담는다
		q = new LinkedList<>();
		for (int i = 1; i <= sharkCnt; ++i) {
			q.offer(sharks[i]);
		}
		
		int time = 0;
		// 문제의 조건에 따라 반복
		while (sharkCnt > 1 && time <= 1000) {
			time++;

			go();
			
			// 현재 큐에 있는 상어의 수를 저장한다.
			int cnt = q.size();
			// 현재 큐에 있는 상어의 수만큼 반복한다.
			// 여기서 sharkCnt나 q.size()를 쓸 경우 상어가 격자밖으로 쫒겨나면
			// 그만큼 수가 줄기때문에 정상적으로 반복이 안된다.
			for (int i = 0; i < cnt; ++i) {
				// 큐에 작은 숫자의 상어 순으로 들어있기에 작은 번호부터 처리한다.
				Shark shark = q.poll();
				
				// 만약 상어가 이동한 위치에 상어가 있고
				// 현재 상어의 시간과 같다면 이는 작은 숫자의 상어가 이미 이동했다는 의미이므로
				// 해당상어는 격자밖으로 뺀다.
				if (map[shark.y][shark.x] > 0 && shark.time == visited[shark.y][shark.x].time) {
					sharkCnt--;
					continue;
				}
				
				// 상어를 이동시킨다
				map[shark.y][shark.x] = shark.sharkNum;
				// 상어를 방문처리한다.
				visited[shark.y][shark.x] = new Info(shark.sharkNum, shark.time);
				// 다음 탐색을 위해 큐에 다시 넣는다.
				q.offer(new Shark(shark.sharkNum, shark.y, shark.x, shark.time, shark.dir));
			}
		}
		
		// 만약 시간이 1000이 넘지 않았다면 최후의 남는 상어는 무조건 가장 작은 번호인
		// 1번 상어이므로 시간만 출력하면 된다.
		System.out.println((time > 1000) ? -1 : time);
	}

	// 1 : 위, 2 : 아래, 3 : 왼쪽, 4 : 오른쪽
	public static int[] dy = {0, -1, 1, 0, 0};
	public static int[] dx = {0, 0, 0, -1, 1};
	public static void go() {
		for (int i = 0; i < sharkCnt; ++i) {
			// 낮은 번호의 상어부터
			Shark shark = q.poll();
			
			// 현재 위치의 상어를 옮길것이므로 우선 현재위치를 0으로
			map[shark.y][shark.x] = 0;
			
			// 상어를 전진해본다
			int num = isMove(shark);
			// isMove 함수의 반환값이 0이면 이동했다는 의미이므로 다음 상어를 전진
			if (num == 0) {
				continue;
			}
			
			// 문제의 조건에 따라 갈 수 있는 위치가 없으므로
			// 해당 상어의 냄새가 나는 방향을 찾는다
			for (int dir = 1; dir < 5; ++dir) {
				// 상어의 방향마다의 우선순위대로 방향을 가져옴
				int moveDir = sharkPriority[shark.sharkNum][shark.dir][dir];
				
				// 해당 방향으로 전진
				int ny = shark.y + dy[moveDir];
				int nx = shark.x + dx[moveDir];
				
				// 만약 갈수없는 점이라면 다음방향 조사
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
					continue;
				}
				
				// 방문하고자하는 위치의 상어번호가 현재 탐색하는 상어번호와 다르다면 갈 수 없으므로 다음방향 탐색
				if (visited[ny][nx].sharkNum != shark.sharkNum) {
					continue;
				}
				
				// 상어를 실제로 이동시킨다.
				moveShark(shark, ny, nx, moveDir);
				// 이동했으므로 더이상 조사할 필요가 없기때문에 탐색을 멈춘다
				break;
			}
		}
	}
	
	public static int isMove(Shark shark) {
		// 문제의 조건에 맞춰 냄새가 없는 방향을 찾는다.
		for (int dir = 1; dir < 5; ++dir) {
			// 상어의 방향마다의 우선순위대로 방향을 가져옴
			int moveDir = sharkPriority[shark.sharkNum][shark.dir][dir];
			
			// 해당 방향으로 전진
			int ny = shark.y + dy[moveDir];
			int nx = shark.x + dx[moveDir];
			
			// 만약 갈수없는 점이라면 다음방향 조사
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
				continue;
			}
			
			// 방문하고자 하는 점에 상어가 방문한적이있고 (현재 상어시간 - 냄새의 유효시간)이 방문하고자하는 위치의
			// 시간보다 작다면 아직 방문하고자하는 위치의 냄새의 유효시간이 남아있다는 의미이므로 다음 방향을 조사
			if (visited[ny][nx].time != 0 && shark.time - k < visited[ny][nx].time) {
				continue;
			}
			
			// 상어를 실제로 이동시킨다.
			moveShark(shark, ny, nx, moveDir);
			// 이동했으므로 더이상 조사할 필요가 없기때문에 0을 반환
			return 0;
		}
		
		// 모든 방향을 조사했지만 갈 방향이 없으므로 1을 반환
		return 1;
	}
	
	public static void moveShark(Shark shark, int ny, int nx, int moveDir) {
		// 큐에 우선 이동하고자하는 상어를 전부 넣는다.
		q.offer(new Shark(shark.sharkNum, ny, nx, shark.time + 1, moveDir));
	}
}
