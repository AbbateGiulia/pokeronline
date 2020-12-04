package it.solving.pokeronline.util;

public class Util {
	
	public static boolean isNumber(String numString) {
		try {
			Integer.parseInt(numString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isEmptyOrNull(String value) {
		return value == null || value == "";
	}

	

}
