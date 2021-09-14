import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위윷놀이_17825 {
	// next : 다음 위치
	// nextBlue : 파란색일 경우 다음 위치
	// score : 현재 위치의 점수
	// isBlue : 파란색 여부
	// isLast : 마지막 지점 여부
	public static class Node {
		int next, nextBlue, score;
		boolean isBlue = false, isLast = false;

		public Node(int score, int next) {
			this.score = score;
			this.next = next;
		}
	}
	
	public static int answer;
	// 윷놀이칸에 말이 있는지 확인
	public static boolean[] visited;
	// dice : 주사위 번호 순서
	// pieceArr : 어떤 말이 해당 차례에 이동할지를 저장
	// curPositionArr : 말이 이동했을때의 마지막 위치를 저장
	public static int[] dice, pieceArr, curPositionArr;
	public static Node[] board;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		dice = new int[10];
		pieceArr = new int[10];
		// 도착점과 끝점까지 포함하여 총 33개의 점이 보드판에 있다
		board = new Node[33];
		
		for (int i = 0; i < 10; ++i) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		initBoard();
		permutation(0);
		
		System.out.println(answer);
	}
	
	// 순열을 통해 모든경우를 조사
	public static void permutation(int cnt) {
		if (cnt == 10) {
			// 10회에 맞게 말들을 이동시켜 보기위해 현재 위치를 시작점으로 초기화
			curPositionArr = new int[4];
			// 말이 있는 점의 위치를 나타내기위해 초기화
			visited = new boolean[33];
			
			int score = 0;
			
			// 총 10번의 주사위 숫자를 알고 있으므로 10번 반복
			for (int i = 0; i < 10; ++i) {
				// 현재 선택된 말의 위치에서 주사위 숫자만큼 움직인 위치를 저장
				int curPosition = go(pieceArr[i], dice[i]);
				
				// 만약 -1이라면 이동을 완료했을때, 도착지점이 아니면서
				// 이미 말이 있는 점으로 갔으므로 이동할 수 없음을 알려준다.
				// 때문에, 이러한 경우는 말을 움직일 수 없으므로 더이상 조사할 필요가 없다.
				if (curPosition == -1) {
					break;
				}
				
				// 현재 말이 주사위 숫자만큼 이동한 위치를 저장
				curPositionArr[pieceArr[i]] = curPosition;
				// 말이 이동하고 도착한 위치의 점수를 총점에 더한다
				score += board[curPosition].score;
			}
			
			answer = (answer < score) ? score : answer;
			return;
		}
		
		// 말은 총 4개
		// 10회의 기회 중 어떤 말이 해당 숫자를 이동할지를 결정
		for (int i = 0; i < 4; ++i) {
			pieceArr[cnt] = i;
			permutation(cnt + 1);
		}
	}
	
	// 현재 말과 주사위 횟수를 통해 말을 주사위 횟수만큼 이동시키고 이동한 위치를 반환
	public static int go(int curPiece, int num) {
		// 현재말의 위치를 저장
		int curPosition = curPositionArr[curPiece];
		
		// 주사위이므로 최소 숫자는 1 즉, 한번은 무조건 이동
		// 말을 다음 칸으로 이동하되 파란화살표면 파란화살표 위치로 이동
		curPosition = (board[curPosition].isBlue) ? 
				board[curPosition].nextBlue : board[curPosition].next;
		
		// 앞에서 미리 한번움직였으므로 1부터 시작하여
		// 움직일 수 있는 횟수만큼 한칸 씩 전진
		for (int i = 1; i < num; ++i) {
			// 만약 마지막 점이라면 전진을 안해도 되므로 정지
			if (board[curPosition].isLast) {
				break;
			}
			
			// 다음 위치로 이동
			curPosition = board[curPosition].next;
		}
		
		// 만약 맨 끝점이 아닌데 모든 이동과정이 끝나고
		// 이미 말이 있는곳에 방문했다면 이는 이동할 수 없으므로 -1을 반환
		if (!board[curPosition].isLast && visited[curPosition]) {
			return -1;
		}
		
		// 원래 말이있던 위치에서 이동했으므로 원래있던 위치를 false로 바꾸어주고
		visited[curPositionArr[curPiece]] = false;
		// 도착한 위치에 말이 존재하므로 도착한 위치를 true로 바꾸어준다.
		visited[curPosition] = true;		
		return curPosition;
	}
	
	// 문제에서 나온 보드판에 맞게 각 칸들을 연결
	public static void initBoard() {
		// 시작점
		board[0] = new Node(0, 1);
		
		for (int i = 1; i < 20; i++) {
			board[i] = new Node(i * 2, i + 1);
		}
		
		// 5점 위치의 파란화살표
		board[5].isBlue = true;
		board[5].nextBlue = 21;
		board[21] = new Node(13, 22);
		board[22] = new Node(16, 23);
		
		// 10점 위치의 파란화살표
		board[10].isBlue = true;
		board[10].nextBlue = 24;
		board[24] = new Node(22, 25);
		
		// 15점 위치의 파란 화살표
		board[15].isBlue = true;
		board[15].nextBlue = 26;
		board[26] = new Node(28, 27);
		board[27] = new Node(27, 28);
		
		// 중앙 값 29
		board[23] = new Node(19, 29);
		board[25] = new Node(24, 29);
		board[28] = new Node(26, 29);
		
		board[29] = new Node(25, 30);
		board[30] = new Node(30, 31);
		board[31] = new Node(35, 20);
		
		// 끝점으로 가는 점
		board[20] = new Node(40, 32);
		
		// 끝점처리
		board[32] = new Node(0, 32);
		board[32].isLast = true;
	}
}
