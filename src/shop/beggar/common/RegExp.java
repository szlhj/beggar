package shop.beggar.common;

import java.util.regex.Pattern;

public class RegExp {
	public static final int REGEXP_ID = 1;
	public static final int REGEXP_PWD = 2;
	public static final int REGEXP_NAME = 3;
	public static final int REGEXP_NUMBER = 4;
	public static final int REGEXP_EMAIL = 5;
	
	public static boolean isValidExp(String data, int type) {
		boolean isValid = false;
		if (isEmpty(data)) {
			return isValid;
		}
		switch (type) {
		case REGEXP_ID:
			isValid = Pattern.matches("^[a-z0-9]{4,12}$", data);
			break;
		case REGEXP_PWD:
			isValid = Pattern.matches("^[a-zA-Z0-9!@#$%^&*]{4,12}$", data);
			break;
		case REGEXP_NAME:
			isValid = Pattern.matches("^[가-힣]{2,8}$", data);
			break;
		case REGEXP_NUMBER:
			isValid = Pattern.matches("^[0-9]*$", data);
			break;
		case REGEXP_EMAIL:
			isValid = Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", data);
//			var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			break;
		}
		
		return isValid;
	}
	
	public static boolean isEmpty(String data) {
		if (data == null || data.equals("")) {
			return true;
		}else {
			return false;
		}
	}
	
}
