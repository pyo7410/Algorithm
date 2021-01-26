import java.util.Scanner;

public class Decoder_1928 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; ++tc) {
			String input = sc.next();
			
			System.out.print("#" + tc + " ");
			
			String binaryString = "";
			for (int i = 0; i < input.length(); ++i) {
				// decode한 int를 binary로 바꾸고 binary로 바뀐 수를 다시 int형으로 바꾼 후
				// String의 format에 맞는 String으로 다시 변환
				binaryString += String.format("%06d", Integer.parseInt(Integer.toBinaryString(decode(input.charAt(i)))));
			}
			
			//String decodeString = "";
			for (int i = 0; i < binaryString.length() / 8; ++i) {
				System.out.print((char)Integer.parseInt(binaryString.substring(8 * i, (i + 1) * 8), 2));
				//decodeString += (char)Integer.parseInt(binaryString.substring(8 * i, (i + 1) * 8), 2);
			}
			
			System.out.println();
		}
		
		sc.close();
	}
	
	public static int decode(char c) {
		int result;
		
		if(c >= 'A' & c <= 'Z') {
			result = c - 'A';
		} else if(c >= 'a' & c <= 'z') {
			result = c - 'a' + 26;
		} else if(c >= '0' & c <= '9') {
			result = c - '0' + 52;
		} else if(c == '+') {
			result = 62;
		} else {
			result = 63;    
		}
		 
		return result;
	}
}
