package ping;

import java.math.BigInteger;
import java.util.Random;

public class FSPP_CSP_Second_Step {

	public static BigInteger[] NewStepTwo(int[] _VB, int[] _VC, BigInteger alpha, BigInteger[] CC) {
//		BigInteger D = BigInteger.ZERO;
//		BigInteger B = BigInteger.ZERO;
		BigInteger KKK = BigInteger.ZERO;
		BigInteger[] PP = new BigInteger[_VC.length];
		BigInteger[] TT = new BigInteger[_VC.length];
		BigInteger[] VDD = new BigInteger[_VC.length];
		BigInteger[] VTT = new BigInteger[_VC.length];
		BigInteger RRR = new BigInteger(20, 64, new Random());
		BigInteger kkkp = BigInteger.ZERO;
		BigInteger kkkt = BigInteger.ZERO;
		
		int[] VB = _VB;
		int[] VC = _VC;

//		int[] VD = VB.clone();
		for (int i = 0; i < VC.length; i++) {
			KKK = alpha.multiply(new BigInteger(Integer.toString(VB[i])));
			PP[i] = CC[i + VC.length].add(KKK).add(RRR);
			TT[i] = CC[i].add(RRR);
		}

		for (int i = 0; i < VC.length; i++) {
			kkkp = PP[VC[i]];
			VDD[i] = kkkp;
			kkkt = TT[VC[i]];
			VTT[i] = kkkt;
			VDD[i] = VDD[i].add(VTT[i]);
		}
		
		return VDD;

	}

}
