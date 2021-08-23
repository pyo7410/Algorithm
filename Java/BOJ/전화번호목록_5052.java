import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class 전화번호목록_5052 {
	public static int t, n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		t = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < t; ++tc) {
			PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
				public int compare(String o1, String o2) {
					return Integer.compare(o1.length(), o2.length());
				}
			});
			Trie trie = new Trie();
			
			n = Integer.parseInt(br.readLine());
			
			boolean flag = true;
			for (int i = 0; i < n; ++i) {
				pq.offer(br.readLine());
			}
			
			trie.insert(pq.poll());
			
			while (!pq.isEmpty()) {
				String cur = pq.poll();
			
				if (trie.contains(cur)) {
					flag = false;
					break;
				}
				
				trie.insert(cur);
			}
			
			if (flag) {
				sb.append("YES\n");
			} 
			else {
				sb.append("NO\n");
			}
		}
		
		System.out.println(sb);
	}
	
	public static class TrieNode {
		// 자식 노드를 저장할 맵
		private Map<Character, TrieNode> childNodes = new HashMap<>();
		// 마지막 글자인지
		private boolean isLast;
		
		// 자식 노드맵을 리턴
		Map<Character, TrieNode> getChildNodes() {
			return this.childNodes;
		}
		
		// 마지막 원소 getter
		boolean getLast() {
			return this.isLast;
		}
		
		// 마지막 원소 setter
		void setLast(boolean isLast) {
			this.isLast = isLast;
		}
	}
	
	public static class Trie {
		 // 루트 노드
		private TrieNode rootNode;
		
		Trie() {
			rootNode = new TrieNode();
		}
		
		void insert(String word) {
			TrieNode thisNode = this.rootNode;
			
			for (int i = 0; i < word.length(); ++i) {
				// map의 computeIfAbsent 메소드는
				// 만약 자식노드에서 word.charAt(i)에 해당하는 위치에 노드가 생성이 되어있다면 그 값을 가져오고
				// 없다면 새로 객체를 만들어준다.
				thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
			}
			
			// 해당 단어의 마지막 위치를 true로 변경
			// computeIfAbsent의 결과로 word의 마지막 문자 
			// 위치가 for문을 통해 thisNode에 들어가있다
			thisNode.setLast(true);
		}
		
		boolean contains(String word) {
			TrieNode thisNode = this.rootNode;
			
			for (int i = 0; i < word.length(); ++i) {
				// 만약 마지막 글자라면
				// 즉, 현재 단어보다 숫자는 적지만
				// 현재 단어와 같은 문자로 이루어진 단어라면 멈추게한다.
				if (thisNode.getLast()) {
					break;
				}
				
				char ch = word.charAt(i);
				
				// 현재 글자에 해당하는 자식노드들을 얻어온다.
				TrieNode node = thisNode.getChildNodes().get(ch);
				
				if (node == null) {
					return false;
				}
				
				thisNode = node;
			}
			
			// 만약 현재 단어의 마지막 글자에 isLast값이 true라면
			// 해당 문자는 노드에 포함된 값이 된다.
			return thisNode.getLast();
		}
		
		// 사용자가 편하게 사용하게 하기 위함
		void delete(String word) {
			delete(this.rootNode, word, 0);
		}
		
		private void delete(TrieNode thisNode, String word, int index) {
			char ch = word.charAt(index);
			
			// 만약 단어를 탐색하다 단어에 포함된 문자가 없다면
			// 해당 단어는 노드에 포함되지 않은 단어가 되므로
			if (!thisNode.getChildNodes().containsKey(ch)) {
				throw new Error("There is no [" + word + "] in this Trie.");
			}
			
			TrieNode childNode = thisNode.getChildNodes().get(ch);
			index++;
			
			// 현재 위치가 맨 마지막 글자라면
			if (index == word.length()) {
				// 만약 단어를 탐색하다 단어에 포함된 문자가 없다면
				// 해당 단어는 노드에 포함되지 않은 단어가 되므로
				if (!childNode.getLast()) {
					throw new Error("There is no [" + word + "] in this Trie.");
				}
				
				// 마지막 단어임을 해제하여 해당 단어가 포함되지 않도록 만든다.
				childNode.setLast(false);
				
				// 마지막 단어에 해당하는 노드에 자식노드 전체가 존재하지 않는다면
				// 해당 단어보다 길면서 현재 인덱스까지 같은 문자를 가진 단어가 없다는 의미가 되므로
				// 현재 노드에서 지워버린다.
				if (childNode.getChildNodes().isEmpty()) {
					thisNode.getChildNodes().remove(ch);
				}
			} else {
				// 다음 글자를 지운다.
				delete(childNode, word, index);
				
				// 맨 마지막 문자가 아닌데 자식 노드 전체가 비어있다면
				// 즉, 재귀함수를 통해 맨 마지막 문자가 이미 삭제된 단어라면
				// 현재노드에서 지워준다.
				if (!childNode.getLast() && childNode.getChildNodes().isEmpty()) {
					thisNode.getChildNodes().remove(ch);
				}
			}
		}
	}
}
