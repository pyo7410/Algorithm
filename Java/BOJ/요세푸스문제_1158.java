import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 요세푸스문제_1158 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Integer> arr = new LinkedList<Integer>();
		for (int i = 1; i <= N; ++i) {
			arr.add(i);
		}
		
		sb.append("<");
		int idx = K - 1;
		while (arr.size() > 1) {
			if (idx >= arr.size()) {
				idx %= arr.size();
			}
			
			sb.append(arr.get(idx)).append(", ");
			arr.remove(idx);
			// 해당 위치를 삭제했으므로 뒤의 원소들은 앞으로 당겨지게된다
			// 때문에 현재위치에서부터 다시 인덱스를 계산해야지 옳바른 결과가 나온다.
			idx += (K - 1);
		}
		
		sb.append(arr.get(0)).append(">");
		System.out.println(sb);
	}
}
