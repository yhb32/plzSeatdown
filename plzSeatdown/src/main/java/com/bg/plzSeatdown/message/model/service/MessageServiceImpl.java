package com.bg.plzSeatdown.message.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bg.plzSeatdown.common.vo.PageInfo;
import com.bg.plzSeatdown.message.model.dao.MessageDAO;
import com.bg.plzSeatdown.message.model.vo.Message;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;
	
	
	/** 쪽지 갯수 확인 서비스
	 * @return listCount
	 * @throws Exception
	 */
	  @Override
	    public int getListCount(String nickName) throws Exception {
	        return messageDAO.getListCount(nickName);
	    }

	/** 쪽지 리스트 조회 서비스
	 * @param pInf
	 * @return list
	 * @throws Exception
	 */
	  @Override
	    public List<Message> selectList(String nickName, PageInfo pInf) throws Exception {
	        return messageDAO.selectList(nickName, pInf);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertMessage(Message message) throws Exception {
		int result = 0;
		int messageNo = messageDAO.selectNextNo();
		if(messageNo > 0) {
			message.setMessageContent(message.getMessageContent().replace("\r\n", "<br>"));
			message.setMessageNo(messageNo);

			result = messageDAO.insertMessage(message);
			
			if(result > 0) result = messageNo;
			else		throw new Exception();
		}
		return result;
	}

	
	@Override
	public int getListCount2(String nickName) throws Exception {
		return messageDAO.getListCount2(nickName);
	}
	
	@Override
	public List<Message> selectList2(String nickName, PageInfo pInf) throws Exception {
		return messageDAO.selectList2(nickName, pInf);
	}

	@Override
	public Message selectMessage(Integer no) throws Exception {
		return messageDAO.selectMessage(no);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteMessage(Integer no) throws Exception {
		return messageDAO.deleteMessage(no);
	}

	@Override
	public int msgCount(Integer no) throws Exception {
		return messageDAO.msgCount(no);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateMessage(Integer no) throws Exception {
		return messageDAO.updateMessage(no);
	}

	@Override
	public Message selectSendMessage(Integer no) throws Exception {
		return messageDAO.selectSendMessage(no);
	}

	@Override
	public Message selectReplyForm(Integer no) throws Exception {
		return messageDAO.selectReplyForm(no);
	}

	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int sendReply(Message message) throws Exception {
		int result = 0;
		int messageNo = messageDAO.selectNextNo();
		if(messageNo > 0) {
			
			message.setMessageContent(message.getMessageContent().replace("\r\n", "<br>"));
			message.setMessageNo(messageNo);

			result = messageDAO.insertMessage2(message);
			
			if(result > 0) result = messageNo;
			else		throw new Exception();
		}
		return result;
		
	}
	
	/** 받은 쪽지함 삭제 
	 * @param check
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int checkDel(int check) throws Exception{
		return messageDAO.checkDel(check);
	}
	
	/** 받은 쪽지함 삭제 
	 * @param check
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int sendDel(int check) throws Exception {
		return messageDAO.sendDel(check);
	}

	@Override
	public int sendDelMsg(Integer no) throws Exception {
		return messageDAO.sendDelMsg(no);
	}

}
