import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 리모컨_1107 {
	public static int N, M;
	// 고장난 버튼은 true
	public static boolean[] buttons;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		buttons = new boolean[11];
		
		// M이 0인 경우 고장난 버튼들의 입력은 아예 주어지지않아 NullPointer 예외가 발생함을 주의
		if (M > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; ++i) {
				buttons[Integer.parseInt(st.nextToken())] = true;
			}			
		}
		
		// 시작 번호는 100이므로 가고 싶은 채널에서 100을 빼면 +, - 버튼만으로
		// N번 채널까지 가는 최대 횟수가 된다.
		int answer = Math.abs(N - 100);
		
		// 0 ~ 500000 ~ 0으로 +, -를 누르는 모든 경우의수를 탐색한다.
		for (int i = 0; i <= 1000000; ++i) {
			// 이동하고자 하는 채널
			int channel = i;
			
			int moveCnt = moveChannel(channel);
			
			// 채널의 모든 숫자에 해당하는 버튼을 누를 수 있어서 해당 채널로 이동했다면
			if (moveCnt > 0) {
				// 이동한 채널인 channel에서 이동하고자하는 채널인 N까지
				// +, - 버튼만으로 이동하는 횟수
				int pressCnt = Math.abs(channel - N);
				
				int totalCnt = moveCnt + pressCnt;
				
				// 임의의 채널로 옮기고 +, - 버튼만으로 움직인 횟수 중 작은 횟수로 갱신
				answer = (answer > totalCnt) ? totalCnt : answer;
			}
		}
		
		System.out.println(answer);
	}
	
	// 채널에 해당하는 버튼을 누를 수 있는지 확인하고 누를 수 있다면 해당 채널로 이동하면서
	// 누른 버튼의 횟수를 반환
	public static int moveChannel(int channel) {
		// 채널이 0번이고
		if (channel == 0) {
			// 고장난 버튼이라면
			if (buttons[0]) {
				// 0은 누를 수 없으므로 0반환
				return 0;
			}
			else { // 고장난 버튼이 아니라면
				return 1;
			}
		}
		
		int cnt = 0;

		// 채널이 0보다 클 때만,
		while (channel > 0) {
			// 채널의 맨 마지막 수에 해당하는 버튼이 고장났다면
			if (buttons[channel % 10]) {
				// 해당 채널은 누를 수 없으므로 0 반환
				return 0;
			}
			
			// 고장나지 않았다면 버튼을 누를 수 있으므로 +1
			cnt += 1;
			
			// 누른 맨 마지막 버튼은 제거
			channel /= 10;
		}
		
		// 누를 수 있는 채널이므로 해당 채널을 누른 횟수를 반환
		return cnt;
	}
}
