package shop.beggar.common;

import java.text.DecimalFormat;

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
	
	public static String shipping(int shipping) {
		String str = "";
		switch(shipping) {
		case 0:
			str = "결제대기 중";
		case 1:
			str = "물건준비 중";
			break;
		case 2:
			str = "배송준비 중";
			break;
		case 3:
			str = "집하장 도착";
			break;
		case 4:
			str = "집하장에서 출발";
			break;
		case 5:
			str = "배송완료";
			break;
		case 6:
			str = "주문 삭제";
			break;
		case 7:
			str = "주문 삭제 완료";
			break;
		default:
			str = "etc";
			break;
		}
		
		return str;
	}
	
	public static String comma(int price) {
		DecimalFormat format = new DecimalFormat("###,###");
		
		return format.format(price) + " 원";
	}
	
	public static String stok(int stok) {
		DecimalFormat format = new DecimalFormat("###,###");
		
		return format.format(stok) + " 개";
	}
	
	public static String itemSumPrice(int stok, int price) {
		DecimalFormat format = new DecimalFormat("###,###");
		
		int sumPrice = price * stok;
		
		return format.format(sumPrice) + " 원";
	}	
	
	public static String payment(int payment) {
		String str = "";
		switch(payment) {
		case 1:
			str = "카드 결제";
		case 2:
			str = "무통장 입금";
			break;
		default:
			str = "etc";
			break;
		}
		
		return str;
	}	
	
	public static String Board_numberName(int Board_number) {
		String str = "";
		switch(Board_number) {
		case 1:
			str = "공지사항";
			break;
		case 2:
			str = "1:1문의";
			break;
		case 3:
			str = "제품관련문의";
			break;
		default:
			str = "etc";
			break;
		}
		
		return str;
	}
	
}
