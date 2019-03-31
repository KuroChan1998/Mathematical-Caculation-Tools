package xxaqsxjc.method0;

import java.util.Scanner;

public class InputString {
	public static String inputString() {
		String str = "";
		Scanner scan = new Scanner(System.in);
		if (scan.hasNextLine()) {
			str = scan.nextLine();
		} else {
			System.out.println("·¢Éú´íÎó£¡");
		}
		scan.close();
		return str;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ss = InputString.inputString();
		System.out.println(ss);
	}

}
