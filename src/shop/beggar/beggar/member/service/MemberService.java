package shop.beggar.beggar.member.service;

import java.sql.Connection;

import shop.beggar.beggar.member.dao.MemberDao;
import shop.beggar.beggar.vo.MemberVo;

import static shop.beggar.common.JdbcUtil.*;

/**
 * @PackageName		: shop.beggar.beggar.member.service
 * @FileName		: MemberService.java
 * @Since			: 2020. 12. 6.
 * @Author			: HJLee
 * @Description		: MemberService 설정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 6.		HJLee				최초 작성
 *
 */
public class MemberService {
	public boolean registerMember(MemberVo vo) {
		boolean isSuccess = true;
		
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.registerMember(vo);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public MemberVo getMemberLoginInfo(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MemberVo voInfo = dao.getMemberLoginInfo(vo);
		
		close(con);
		
		return voInfo;
	}
	
	public String searchId(String query) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String searchId = dao.searchId(query);
		
		close(con);
		
		return searchId;
	}
	
	public boolean changeRandomPwd(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		
		boolean isSuccess = true;
		
		dao.setConnection(con);
		int count = dao.changeRandomPwd(vo);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public boolean changeNewPwd(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		
		boolean isSuccess = true;
		
		dao.setConnection(con);
		int count = dao.changeNewPwd(vo);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	public boolean loginHisRegister(MemberVo vo, String ipAddr) {
		boolean isSuccess = true;
		
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.loginHisRegister(vo, ipAddr);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public MemberVo modifyMemberInfo(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MemberVo voInfo = dao.modifyMemberInfo(vo);
		
		close(con);
		
		return voInfo;
	}
	
	public boolean modifyMember(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modifyMember(memberVo);
		boolean isSuccess = true;
		if(count > 0 ) {
			commit(con); //0보다 크면 커밋
		} else {
			rollback(con);  //작으면 롤백함
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}
}
