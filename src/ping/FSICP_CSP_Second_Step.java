package ping;

import java.math.BigInteger;
import java.util.Random;

public class FSICP_CSP_Second_Step {

	public static BigInteger[] NewStepTwo(int _VB, int length, BigInteger[][] KK ) {
		int VB = _VB;
		String lt = new String();
		String st = new String();
		String et = new String();

		et = Integer.toBinaryString(VB);
		BigInteger[] KKB = new BigInteger[length];
		String[] TKTT = new String[length];
		int[] IDEX = new int[length];

//		BigInteger[] FORKKD = new BigInteger[length];
//		BigInteger[] FORREM = new BigInteger[length];
//		BigInteger[] FORFINAL = new BigInteger[length];

		BigInteger BBQ = BigInteger.ZERO;
//		BigInteger EQQ = BigInteger.ZERO;
//		BigInteger BBT = BigInteger.ZERO;
//		BigInteger FEQ = BigInteger.ZERO;
//		BigInteger EQQK = BigInteger.ZERO;
//		BigInteger TTTTT = BigInteger.ZERO;
//		BigInteger KKKK = BigInteger.ZERO;
		BigInteger rppp;

		int kkkk = length - et.length() - 2;

		if (kkkk > 0) {
			for (int i = 0; i < kkkk; i++) {
				et = '0' + et;

			}
		}

		lt = "11" + et;
		String[] VK = new String[length];
		for (int i = 0; i < lt.length(); i++) {
			if (Integer.parseInt(lt.substring(i, i + 1)) == 0) {
				VK[i] = lt.substring(0, i) + '1';
				TKTT[i] = lt.substring(0, i) + '1';
				IDEX[i] = lt.length() - i - 1;

			}
		}

		for (int i = 0; i < length; i++) {
			if (VK[i] == null)
				continue;
			else {
				st = VK[i];
				BBQ = BigInteger.ZERO;
//				BBT = BigInteger.ZERO;
//				TTTTT = BigInteger.ZERO;
//				KKKK = BigInteger.ZERO;

				for (int ii = 0; ii < st.length(); ii++) {
					int meid = 0;
					meid = Integer.parseInt(st.substring(ii, ii + 1));
					rppp = new BigInteger(160, new Random());
					BBQ = BBQ.add(rppp.multiply(KK[meid][lt.length() - ii - 1]));

				}

				KKB[i] = BBQ;

			}
		}
		return KKB;
	}
}
