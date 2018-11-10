package testautomation;

import FrameworkConfig.GeneralConfig.payNetworks;

public class testautomation {

	public static void main(String[] args) {
		payNetworks p = payNetworks.MASTERCARD;
		String s = "MASTERCARD";
		if(s.equals(p.toString())) {
			System.out.println("Both values are equal!!!");
		}
	}
}
