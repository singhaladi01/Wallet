package snippet;

public class reverseStrWithPreservePos {

	public static void main(String[] args) {
		String str = "A$D$I$T$I$";
		char[] inputArr = str.toCharArray();

		int len = inputArr.length;

		char[] resultArr = new char[len];

		for (int i = 0; i < len; i++) {
			if (inputArr[i] == '$') {
				resultArr[i] = '$';
			}
		}
		System.out.println(resultArr);
		int j = len - 1;
		for (int i = 0; i < len; i++) {
			if (inputArr[i] != '$') {

				if (resultArr[j] == '$') {
					j--;
				}
				resultArr[j] = inputArr[i];
				j--;
			}
		}
		System.out.println(resultArr);
	}

}
