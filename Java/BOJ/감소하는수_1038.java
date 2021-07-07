import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 감소하는수_1038 {
	public static int N;
	public static List<Long> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 감소하는 수의 가장 큰 수는 9876543210이다
		// why?
		// 9보다 큰 수는 10인데 이를 붙이면 109876543210이 되어버리고
		// 0 -> 9는 감소하는 수가 아니므로 즉, 9보다 큰 수는 앞에 올수가없기때문에
		// 10의 길이인 9876543210이 가장 긴 감소하는 수가된다.
		// 때문에 9876543210이 나오는 N인 1022보다 큰 수는 -1이 된다.
		
		// 1023을 넣게되면 IndexOutOfBoundsExeption 에러가 발생
		if (N > 1022) {
			System.out.println(-1);
			System.exit(0);
		}
		
		list = new ArrayList<Long>();
		for (int i = 0; i < 10; ++i) {
			// 0 ~ 9로 시작하는 감소하는 수를 구함
			calc(i, 1);
		}
		
		// list에는 감소하는 수가 순서에 상관없이 저장되어있다.
		// 이를 정렬을 시켜 순서대로 정리가 되게해준다.
		Collections.sort(list);
		System.out.println(list.get(N));
	}
	
	public static void calc(long num, int len) {
		// 감소하는 수의 가장 큰 수는 9876543210이다
		// why?
		// 9보다 큰 수는 10인데 이를 붙이면 109876543210이 되어버리고
		// 0 -> 9는 감소하는 수가 아니므로 즉, 9보다 큰 수는 앞에 올수가없기때문에
		// 10의 길이인 9876543210이 가장 긴 감소하는 수가된다.
		if (len > 10) {
			return;
		}
		
		// 리스트에 계속해서 생성된 감소하는 수인 num을 저장한다.
		list.add(num);
		
		for (int i = 0; i < 10; ++i) {
			// 숫자의 제일 마지막자리와 비교하여 작은 수를 맨 뒤에 넣는다.
			// ex) 98 -> 987, 986, 985, ... , 980 까지 재귀함수가 호출된다.
			if (num % 10 > i) {
				// 기존 감소하는 수인 num에 * 10을 하여 자리수를 높이고 작은 수를 맨뒤에 넣어줌
				// 길이를 +1
				calc((num * 10) + i, len + 1);
			}
		}
	}
}
