
package com.bg.plzSeatdown.mypage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bg.plzSeatdown.alarm.model.vo.Alarm;
import com.bg.plzSeatdown.common.vo.PageInfo;
import com.bg.plzSeatdown.community.model.vo.Community;
import com.bg.plzSeatdown.community.model.vo.Reply;
import com.bg.plzSeatdown.member.model.vo.Member;
import com.bg.plzSeatdown.mypage.model.vo.Like;
import com.bg.plzSeatdown.mypage.model.vo.Profile;
import com.bg.plzSeatdown.mypage.model.vo.QnAEH;
import com.bg.plzSeatdown.mypage.model.vo.ReviewEH;
import com.bg.plzSeatdown.mypage.model.vo.ReviewImageEH;
import com.bg.plzSeatdown.qna.model.vo.QnA;
import com.bg.plzSeatdown.review.model.vo.SeatReview;

@Repository
public class MypageDAO {

   @Autowired //rootcontext에 bean 설정 되있음
   private SqlSessionTemplate sqlSession;
   

   

   /** 마이페이지 조회용
    * @param memberNo
    * @return
    * @throws Exception
    */
   public Member selectMypage(int memberNo) throws Exception {
      return sqlSession.selectOne("mypageMapper.selectMypage", memberNo);
   }



   /** 마이페이지 그림 조회용
    * @param memberNo
    * @return
    * @throws Exception
    */
   public Profile selectMypageProf(int memberNo) throws Exception{
      return sqlSession.selectOne("mypageMapper.selectMypageProf", memberNo);
   }



   /** 비밀번호 조회용 DAO
    * @param memberNo
    * @return
    * @throws Exception
    */
   public String selectPwd(int memberNo) throws Exception{
      return sqlSession.selectOne("mypageMapper.selectPwd", memberNo);
   }



   /** 비밀번호 수정용 DAO
    * @param member
    * @return
    * @throws Exception
    */
   public int updatePwd(Member member) throws Exception{
      return sqlSession.update("mypageMapper.updatePwd", member);
   }



   /** 회원 탈퇴용 DAO
    * @param member
    * @return
    * @throws Exception
    */
   public int deleteMember(Member member) throws Exception {
      return sqlSession.update("mypageMapper.deleteMember", member);
   }


   /** 문의내역 전체 게시글수 조회용
    * @return
    * @throws Exception
    */
   public int getAskCount(int qnaWriter) throws Exception{
   return sqlSession.selectOne("mypageMapper.getAskCount", qnaWriter);
   }


   /** 문의내역 게시글 조회
    * @param map
    * @param pInf
 * @param qnaWriter 
    * @return
    * @throws Exception
    */
   public List<QnAEH> selectQlist(PageInfo pInf, int memberNo) throws Exception{
      int offset = (pInf.getCurrentPage() - 1) *  pInf.getLimit();
      RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
      return sqlSession.selectList("mypageMapper.selectQlist",  memberNo, rowBounds);
   }



   /** 문의내역 게시글 상세 조회
    * @param no
    * @return
    */
   public QnAEH selectAsk(Integer no) throws Exception {
      return sqlSession.selectOne("mypageMapper.selectAsk", no);
   }



   /** 마이페이지 수정용
    * @param member
    * @return
    */
   public int updateMypage(Member member)throws Exception  {
	   return sqlSession.update("mypageMapper.updateMypage", member);
   }



   /** 마이페이지 프로필  삽입용
    * @param insertFile
 	* @return
 	* @throws Exception
 	*/
   public int insertProfile(Profile insertFile)throws Exception  {
	   return sqlSession.insert("mypageMapper.insertProfile", insertFile);
   }



   /** 마이페이지 프로필 수정용
    * @param updateFile
    * @return
    */
   public int updateProfile(Profile updateFile)throws Exception {
	   return sqlSession.update("mypageMapper.updateProfile", updateFile);
   }



   	/** 마이페이지 프로필 삭제용
   	 * @param memberNo
   	 * @return
   	 * @throws Exception
   	 */
   public int deleteProfile(int memberNo)throws Exception{
	   return sqlSession.delete("mypageMapper.deleteProfile", memberNo);
   }



	/** 마이리뷰 전체 게시글 수 조회
	 * @return
	 * @throws Exception
	 */
	public int getReviewCount(int memberNo) throws Exception{
		return sqlSession.selectOne("mypageMapper.getReviewCount", memberNo);
	}



	/** 마이리뷰 목록 조회
	 * @param pInf
	 * @return
	 * @throws Exception
	 */
	public List<SeatReview> selectRlist(PageInfo pInf, int memberNo) throws Exception{
		int offset = (pInf.getCurrentPage() - 1) *  pInf.getLimit();
	    RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("mypageMapper.selectRlist", memberNo, rowBounds);
	}


	

	
	
	/** 마이티켓 게시글수 조회
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int getTicketCount(int memberNo) throws Exception{
		return sqlSession.selectOne("mypageMapper.getTicketCount", memberNo);
	}

	
	

	/** 마이티켓 이미지 조회
	 * @param pInf
	 * @param memberNo
	 * @return
	 */
	public List<ReviewImageEH> selectRimgList(PageInfo pInf, int memberNo)throws Exception {
		int offset = (pInf.getCurrentPage() - 1) *  pInf.getLimit();
	    RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("mypageMapper.selectRimgList",memberNo, rowBounds);
	}



	/** 마이글 게시물수 조회
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int getWriteCount(int memberNo)throws Exception {
		return sqlSession.selectOne("mypageMapper.getWriteCount", memberNo);
	}

	
	


	/** 마이글 전체 조회
	 * @param pInf
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Community> selectClist(PageInfo pInf, int memberNo) throws Exception{
		int offset = (pInf.getCurrentPage() - 1) *  pInf.getLimit();
	    RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("mypageMapper.selectClist",memberNo,rowBounds);
	}




	
	/** 마이리뷰 그림들 조회
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<ReviewImageEH> selectRimglist(int memberNo)throws Exception {
		return sqlSession.selectList("mypageMapper.selectRimglist", memberNo);
	}



	
	
	/**마이 커뮤 댓글조회
	 * @param pInf 
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Reply> selectReplist(PageInfo pInf, int memberNo)throws Exception  {
		int offset = (pInf.getCurrentPage() - 1) *  pInf.getLimit();
	    RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("mypageMapper.selectReplist",memberNo,rowBounds);
	}



	/** 프로필 사진 조회 DAO
	 * @param memberNo
	 * @return profile
	 * @throws Exception
	 */
	public Profile selectProfile(int memberNo) throws Exception {
		return sqlSession.selectOne("mypageMapper.selectProfile", memberNo);
	}



	/** 알람 목록 조회
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Alarm> selectAlarmlist(int memberNo)throws Exception {
		return sqlSession.selectList("mypageMapper.selectAlarmlist", memberNo);
	}
   
	
	
	/** 마이티켓 달력버전
	 * @param memberNo
	 * @return ticketList
	 * @throws Exception
	 */
	public List<SeatReview> selectTicketList(int memberNo) throws Exception{
		return sqlSession.selectList("mypageMapper.selectTicketList", memberNo);
	}


	/** 전체 댓글수  조회
	 * @param memberNo
	 * @throws Exception
	 */
	public int getWriteReply(int memberNo)throws Exception{
		return sqlSession.selectOne("mypageMapper.getWriteReply", memberNo);
	}


	
	/** 닉네임 유효성 검사
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int nicknameDupCheck(String memberNickname) throws Exception{
		return sqlSession.selectOne("mypageMapper.nicknameDupCheck", memberNickname);
	}



   
   
   

}

