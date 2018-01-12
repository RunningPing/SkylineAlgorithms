package ping;

/**
 * 二进制转换为浮点数
 * @author ping
 *
 */
public class binaryToDecimal {

	public static int ToDecimal(String binary) {
		int result = 0;
		int j = 0;
		for (int i = binary.length(); i > 0; i--) {
			// System.out.println("i="+i);
			// System.out.println("binary.substring(i, i-1)="+binary.substring(i-1, i));
			result += Integer.parseInt(binary.substring(i - 1, i)) * Math.pow(2, j);
			j++;
		}
		// System.out.println("result="+result);
		return result;
	} 
}
