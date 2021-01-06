package shop.beggar.common;

import java.security.SecureRandom;

/**
 * @PackageName		: shop.beggar.common
 * @FileName		: RandomGenerator.java
 * @Since			: 2020. 12. 11.
 * @Author			: HJLee
 * @Description		: 임시비밀번호 로직
 * 					  랜덤함수를 이용해서 10자의 랜덤함수 생성
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 11.		HJLee				최초 작성
 *
 */
public class RandomGenerator {
	private static SecureRandom random = new SecureRandom();

	/** 랜덤 문자열을 생성한다 **/
    public static String generate(String DATA, int length) {
        if (length < 1) throw new IllegalArgumentException("length must be a positive number."); // 1보다 작으면 메세지 뿌리고 튕겨낸다.
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append( DATA.charAt( //charAt 문자열을 1개씩 잘라낸다
            		random.nextInt(DATA.length()) //배열화 시킨다 nextInt 한개씩 순서대로 length 길이만큼 중에서 랜덤한 숫자에 순서에 1개를 뽑아낸다.
            		));
        }
        return sb.toString();
    }
    
    public static String randomPwd() {
    
    	String ENGLISH_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String ENGLISH_UPPER = ENGLISH_LOWER.toUpperCase();
        String NUMBER = "0123456789";
        
//        랜덤을 생성할 대상 문자열
        String DATA_FOR_RANDOM_STRING = ENGLISH_LOWER + ENGLISH_UPPER + NUMBER;
        
//        랜덤 문자열 길이
        int random_string_length=10;
//    	
//        System.out.println("DATA_FOR_RANDOM_STRING ==> " + DATA_FOR_RANDOM_STRING);
//        for (int i = 0; i < 10; i++) {
//            System.out.println("random " + i + " : " + generate(DATA_FOR_RANDOM_STRING, random_string_length));
//        }
        
        return generate(DATA_FOR_RANDOM_STRING, random_string_length);
    }
}
