import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 무선충전_5644 {
	public static class BC implements Comparable<BC> {
		int x, y;
		int c;
		int p;
		
		public BC(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public int compareTo(BC o) {
			// 가장 큰 P를 찾기 위해 내림차순 정렬
			return o.p - this.p;
		}
	}
	
	// 입력 값
	public static int M, A;
	
	// A, B의 좌표 위치
	public static int ax, ay, bx, by;
	
	// 이동정보
	// 이동X, 상, 우, 하, 좌
	public static int[] dx = {0, 0, 1, 0, -1};
	public static int[] dy = {0, -1, 0, 1, 0};
	
	// A, B의 이동정보
	public static int[] personA, personB;
	
	// 전체 맵
	public static int[][] map = new int[11][11];
	
	// BC정보들을 저장할 배열
	public static BC[] bc;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			// 0부터 시작하므로 주의
			personA = new int[M + 1];
			personB = new int[M + 1];
			
			bc = new BC[A];
			
			// A 입력
			// 0부터 시작하므로 주의
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i < M + 1; ++i) {
				personA[i] = Integer.parseInt(st.nextToken());
			}
			
			// B 입력
			// 0부터 시작하므로 주의
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i < M + 1; ++i) {
				personB[i] = Integer.parseInt(st.nextToken());
			}
			
			// BC 정보 입력
			for (int i = 0; i < A; ++i) {
				st = new StringTokenizer(br.readLine(), " ");				
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				bc[i] = new BC(x, y, c, p);
			}
			
			ax = 1;
			ay = 1;
			bx = 10;
			by = 10;
			
			int answer = 0;
			
			// 0부터 시작하므로 주의
			for (int i = 0; i < M + 1; ++i) {
				// A 이동
				ay += dy[personA[i]];
				ax += dx[personA[i]];
				
				// B 이동
				by += dy[personB[i]];
				bx += dx[personB[i]];
				
				// 현재 위치에서 붙어있는 BC를 전부 찾아 P가 높은 순으로 정렬
				PriorityQueue<BC> aPQ = new PriorityQueue<BC>();
				PriorityQueue<BC> bPQ = new PriorityQueue<BC>();
				for (int j = 0; j < A; ++j) {
					int aDist = Math.abs(ay - bc[j].y) + Math.abs(ax - bc[j].x);
					int bDist = Math.abs(by - bc[j].y) + Math.abs(bx - bc[j].x);
					
					if (aDist <= bc[j].c) {
						aPQ.add(bc[j]);
					}
					
					if (bDist <= bc[j].c) {
						bPQ.add(bc[j]);
					}
				}
				
				int sum = 0;
				
				if (!aPQ.isEmpty() && !bPQ.isEmpty()) {
					// 둘다 비어있지 않다면 중복되는 점이 있을 수 있으므로
					// 모든 경우에서의 P의 합을 다 구해준다.
					for (BC aBC : aPQ) {
						for (BC bBC : bPQ) {
							if (aBC.x == bBC.x && aBC.y == bBC.y) {
								sum = (sum < aBC.p) ? aBC.p : sum;
								continue;
							}
							
							sum = (sum < aBC.p + bBC.p) ? aBC.p + bBC.p : sum;
						}
					}
				}
				else if (!aPQ.isEmpty() && bPQ.isEmpty()) {
					// B가 비어있다면 A만 접근이 가능하므로 이때의 최대 P는 A의 우선순위큐 맨 앞의 값이 된다.
					sum = aPQ.poll().p;
				}
				else if (aPQ.isEmpty() && !bPQ.isEmpty()) {
					// A가 비어있다면 B만 접근이 가능하므로 이때의 최대 P는 B의 우선순위큐 맨 앞의 값이 된다.
					sum = bPQ.poll().p;
				}
				
				answer += sum;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
