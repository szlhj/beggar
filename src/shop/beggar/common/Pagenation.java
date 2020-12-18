package shop.beggar.common;

/**
 * @PackageName		: common
 * @FileName		: PageNation.java
 * @Since			: 2020. 12. 1.
 * @Author			: KB
 * @Description		: 페이지 네이션
 * --------------------------------------------------------------------------
 * 안에는 oracle DB만 사용하고 나머지는 마리아 DB에서 사용 가능
 * --------------------------------------------------------------------------
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 1.		KB				최초 작성
 *
 */
public class Pagenation {
	private final int ARTICLE_COUNT_PER_PAGE = 10; //현재 페이지 글 개수
	private final int PAGE_COUNT_PER_GROUP = 5; //페이지 번호 표시
	private int nowPageNumber; //현제페이지 번호
	private int totalArticleCount; //전체 글 개수
	private int startArticleNumber; //시작 글 번호
//--------------------------------------------------------------------------
	private int endArticleNumber; //마지막 글 번호
// -------------------------------------------------------------------------
	private int totalPageCount; //토탈페이지
	
	private int startPageNumber; //페이지 내의 시작번호
	private int endPageNumber; //페이지 내의 마지막번호
	
	
	public Pagenation(int nowPageNumber, int totalArticleCount) {
		this.nowPageNumber = nowPageNumber;
		this.totalArticleCount = totalArticleCount;
//		this.startArticleNumber = (nowPageNumber -1) * ARTICLE_COUNT_PER_PAGE; //페이지의 시작 글 번호
//-----------------------------------------------------------------------------------------
		this.startArticleNumber = (nowPageNumber * ARTICLE_COUNT_PER_PAGE) - (ARTICLE_COUNT_PER_PAGE ); //페이지의 시작 글 번호
		this.endArticleNumber = this.startArticleNumber + ARTICLE_COUNT_PER_PAGE - 1;
//-----------------------------------------------------------------------------------------		
		this.totalPageCount = (int) Math.ceil((double) totalArticleCount / ARTICLE_COUNT_PER_PAGE); //페이지 게수 (현재 전체 글을)
		if (this.totalPageCount < 1) {
			this.totalPageCount = 1;
		}
		
		this.startPageNumber = ((int) (((double) nowPageNumber/ PAGE_COUNT_PER_GROUP + 0.9)-1)) * PAGE_COUNT_PER_GROUP + 1;// 페이지 그룹 간의 시작 페이지 번호 나타냄
		this.endPageNumber = this.startPageNumber + (PAGE_COUNT_PER_GROUP - 1);
		if (this.endPageNumber > this.totalPageCount) {
			this.endPageNumber = this.totalPageCount;
		}
		
		
	}

	public int getEndArticleNumber() {
		return endArticleNumber;
	}


	public void setEndArticleNumber(int endArticleNumber) {
		this.endArticleNumber = endArticleNumber;
	}

	public int getNowPageNumber() {
		return nowPageNumber;
	}


	public void setNowPageNumber(int nowPageNumber) {
		this.nowPageNumber = nowPageNumber;
	}


	public int getTotalArticleCount() {
		return totalArticleCount;
	}


	public void setTotalArticleCount(int totalArticleCount) {
		this.totalArticleCount = totalArticleCount;
	}


	public int getStartArticleNumber() {
		return startArticleNumber;
	}


	public void setStartArticleNumber(int startArticleNumber) {
		this.startArticleNumber = startArticleNumber;
	}


	public int getTotalPageCount() {
		return totalPageCount;
	}


	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}


	public int getStartPageNumber() {
		return startPageNumber;
	}


	public void setStartPageNumber(int startPageNumber) {
		this.startPageNumber = startPageNumber;
	}


	public int getEndPageNumber() {
		return endPageNumber;
	}


	public void setEndPageNumber(int endPageNumber) {
		this.endPageNumber = endPageNumber;
	}


	public int getARTICLE_COUNT_PER_PAGE() {
		return ARTICLE_COUNT_PER_PAGE;
	}


	public int getPAGE_COUNT_PER_GROUP() {
		return PAGE_COUNT_PER_GROUP;
	}
}
