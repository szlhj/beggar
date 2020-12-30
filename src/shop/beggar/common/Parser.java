package shop.beggar.common;

/**
 * @PackageName		: common
 * @FileName		: Parser.java
 * @Since			: 2020. 11. 30.
 * @Author			: KB
 * @Description		: 
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 11. 30.		KB				최초 작성
 *
 */
public class Parser {
	public static String chgToStr(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("'", "&#039;");
		str = str.replaceAll("\"", "&quot;");
		
		return str;
	}
	public static String chgToHtml(String str) {
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&quot;", "\"");
		
		return str;
	}
	
	
	public static String categoryParser(String category) {
		String str = "";
		switch(category) {
		case "1":
			str = "New In";
			break;
		case "2":
			str = "Earrings";
			break;
		case "3":
			str = "Necklaces";
			break;
		case "4":
			str = "Bracelets";
			break;
		case "5":
			str = "Rings";
			break;
		case "6":
			str = "Anklets";
			break;
		case "7":
			str = "Best";
			break;
		default:
			str = "etc";
			break;
		}
		
		return str;
	}
	
	public static int disPrice(int price, int discount) {
		int disp = 0;
		disp = price - (price * discount /100);
		
		return disp;
	}
	
}
