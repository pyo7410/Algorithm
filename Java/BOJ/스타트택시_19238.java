import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트택시_19238 {
	// 택시를 이동할 때 사용할 클래스
	public static class Taxi {
		int y, x, fuel;

		public Taxi(int y, int x, int fuel) {
			this.y = y;
			this.x = x;
			this.fuel = fuel;
		}
	}
	
	// 손님의 위치정보와 현재 택시의 연료를 저장하는 클래스
	public static class Info implements Comparable<Info> {
		int st_y, st_x, en_y, en_x, curFeul;

		public Info(int st_y, int st_x, int en_y, int en_x, int curFeul) {
			this.st_y = st_y;
			this.st_x = st_x;
			this.en_y = en_y;
			this.en_x = en_x;
			this.curFeul = curFeul;
		}

		@Override
		public int compareTo(Info o) {
			int diff = Integer.compare(this.st_y, o.st_y);
			return (diff == 0) ? Integer.compare(this.st_x, o.st_x) : diff;
		}
	}
	public static int N, M, initFuel, answer;
	// 손님의 위치정보는 여러개 이므로 배열을 사용하여 저장
	public static Info[] infos;
	// 문제에서 주어진 우선순위에 맞는 손님을 태우기 위해 사용
	public static PriorityQueue<Info> pq;
	// 택시가 이동 시 방문한 위치에는 다시 방문하지 않도록 하기 위한 배열
	public static boolean[][] visited;
	// 맵의 정보를 저장
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		initFuel = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int taxi_y = Integer.parseInt(st.nextToken()) - 1;
		int taxi_x = Integer.parseInt(st.nextToken()) - 1;
		
		infos = new Info[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int st_y = Integer.parseInt(st.nextToken()) - 1;
			int st_x = Integer.parseInt(st.nextToken()) - 1;
			int en_y = Integer.parseInt(st.nextToken()) - 1;
			int en_x = Integer.parseInt(st.nextToken()) - 1;
			
			// 고유한 손님의 번호를 알기위해 map에서 쓰이지않는 2이상의 수를 만들기위해
			// +2를 해주어 맵에 표시
			map[st_y][st_x] = i + 2;
			
			infos[i] = new Info(st_y, st_x, en_y, en_x, -1);
		}
		
		// 택시에 태운 손님의 수를 카운트
		int cnt = 0;
		// 현재 택시의 연료를 저장
		int curFuel = initFuel;
		for (int i = 0; i < M; ++i) {
			pq = new PriorityQueue<>();
			// 현재 태울 수 있는 손님을 전부 구한다.
			findCustomer(taxi_y, taxi_x, curFuel);
			
			// 만약 우선순위큐가 비어있다면 이는 태울 손님이 없거나 연료가 다 떨어졌다는 의미이므로
			if (pq.isEmpty()) {
				// 정지
				break;
			}
			
			Info info = pq.poll();
			// 우선순위에 맞게 뽑힌 손님을 태우고 목적지까지 전진하고 남은 연료를 반환받는다
			curFuel = go(info);
			
			// 남은 연료가 0보다 작다면 더이상 운행이 불가하므로 정지 
			if (curFuel <= 0) {
				break;
			}
			
			// 택시가 목적지에 도착했으므로 택시 위치 갱신
			taxi_y = info.en_y;
			taxi_x = info.en_x;
			
			// 손님 수 카운트
			cnt++;
		}
		
		
		if (cnt < M) {	// 만약 M명의 손님을 전부 태우지 못했다면
			System.out.println(-1);
		}
		else { 			// M명의 손님을 전부 태웠다면
			System.out.println(curFuel);
		}
	}
	
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static void findCustomer(int y, int x, int curFuel) {
		// 연료가 가장 많이 남는 위치의 손님을 찾기위해 변수를 사용
		int maxFuel = 0;
		
		visited = new boolean[N][N];
		Queue<Taxi> q = new LinkedList<>();
		q.offer(new Taxi(y, x, curFuel));
		
		while (!q.isEmpty()) {
			Taxi taxi = q.poll();
			
			// 만약 연료가 0보다 작다면 이동이 불가능 하므로
			// 멈춘다.
			if (taxi.fuel < 0) {
				return;
			}
			
			// 손님의 위치이고 현재 남은 연료가 이전에 저장한 남은 연료보다 더 많이 남거나 같다면
			if (map[taxi.y][taxi.x] > 1 && maxFuel <= taxi.fuel) {
				// 배열에서 손님 정보를 빼오기위해 +2를 한 숫자에서 -2를 한다.
				int customer = map[taxi.y][taxi.x] - 2;
				// 남은 연료를 기록
				infos[customer].curFeul = taxi.fuel;
				// 문제에서 주어진 우선순위대로 뽑기위해 우선순위큐에 삽입
				pq.offer(new Info(infos[customer].st_y, infos[customer].st_x, 
						infos[customer].en_y, infos[customer].en_x, taxi.fuel));
				// 남은 연료를 갱신
				maxFuel = taxi.fuel;
				continue;
			}
			
			for (int i = 0; i < 4; ++i) {
				int ny = taxi.y + dy[i];
				int nx = taxi.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
					continue;
				}
				
				if (!visited[ny][nx] && map[ny][nx] != 1) {
					q.offer(new Taxi(ny, nx, taxi.fuel - 1));
					visited[ny][nx] = true;
				}
			}
		}
	}
	
	public static int go(Info info) {
		// 남은 연료를 반환하기 위함
		int fuel = -1;
		
		// 현재 손님을 태웠으므로 0으로 변경
		map[info.st_y][info.st_x] = 0;
		
		visited = new boolean[N][N];
		Queue<Taxi> q = new LinkedList<>();
		q.offer(new Taxi(info.st_y, info.st_x, info.curFeul));
		visited[info.st_y][info.st_x] = true;
		
		while (!q.isEmpty()) {
			Taxi taxi = q.poll();
			
			// 만약 연료가 0보다 더 작다면
			// 더이상 이동할 수 없으므로 정지
			if (taxi.fuel < 0) {
				break;
			}
			
			// 목적지에 도착했다면
			if (taxi.y == info.en_y && taxi.x == info.en_x) {
				// 문제의 조건에 맞게 연료를 갱신
				fuel = taxi.fuel + (info.curFeul - taxi.fuel) * 2; 
				break;
			}
			
			for (int i = 0; i < 4; ++i) {
				int ny = taxi.y + dy[i];
				int nx = taxi.x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
					continue;
				}
				
				if (!visited[ny][nx] && map[ny][nx] != 1) {
					q.offer(new Taxi(ny, nx, taxi.fuel - 1));
					visited[ny][nx] = true;
				}
			}
		}
		
		return fuel;
	}
}
