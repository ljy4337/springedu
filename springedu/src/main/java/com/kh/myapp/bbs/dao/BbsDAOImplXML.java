package com.kh.myapp.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.myapp.bbs.dto.BbsDTO;

@Repository(value="bbsDAOImplXML")
public class BbsDAOImplXML implements BbsDAO{

	private static final Logger logger = LoggerFactory.getLogger(BbsDAOImplXML.class);

	@Inject
	SqlSession sqlsession;
	@Override
	public int write(BbsDTO bbsDTO) throws Exception {
		return sqlsession.insert("mappers.bbs.write",bbsDTO );
	}

	@Override
	public List<BbsDTO> list() throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList("listOld");
	}

	@Override
	public List<BbsDTO> list(int starRec, int endRec) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("starRec", starRec);
		map.put("endRec", endRec);
		
		return sqlsession.selectList("mappers.bbs.list", map);
	}

	@Override
	public BbsDTO view(String bnum) throws Exception {
		BbsDTO bbsDTO = null;
		bbsDTO = sqlsession.selectOne("mappers.bbs.view", bnum);
		// 조회수 증가
		updateHit(bbsDTO.getBnum());		
		return bbsDTO;
	}

	private void updateHit(int bnum) {
		// TODO Auto-generated method stub
		sqlsession.update("mappers.bbs.updateHit", bnum);
	}

	@Override
	public int modify(BbsDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.update("mappers.bbs.modify", boardDTO);
	}

	@Override
	public int delete(String bnum) throws Exception {
		int cnt = 0;
		logger.info("답글 : "+ isReply(bnum));
		//답글 있는 경우
		if(isReply(bnum)) {
			cnt = sqlsession.update("mappers.bbs.update_isdel", bnum);
		}
		//답글 없는 경우
		else {
			cnt = sqlsession.update("mappers.bbs.delete", bnum);
		}
		
		return cnt;
	}

	private boolean isReply(String bnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sqlsession.selectOne("mappers.bbs.isReply", bnum);
		logger.info("cntisYN : " + cnt);
		if(cnt > 0) {
			isYN = true;
			logger.info("isYS : " +isYN);
		}
		return isYN;
	}

	@Override
	public BbsDTO replyView(String bnum) throws Exception {
		
		return null;
	}

	@Override
	public int reply(BbsDTO bbsDTO) throws Exception {
		int cnt1 = 0, cnt2 = 0;
		
		// 1) 이전 답글 stepa 업데이트 (원글그룹에 대한 세로정렬 재정의)
		cnt1 = updateStep(bbsDTO.getBgroup(), bbsDTO.getBstep());
		// 2) 답글 등록
		cnt2 = sqlsession.insert("mappers.bbs.reply", bbsDTO);
		return cnt2;
	}

	private int updateStep(int bgroup, int bstep) {
		Map<String,Object> map = new HashMap<>();
		map.put("bgroup", bgroup);
		map.put("bstep", bstep);
		return sqlsession.update("mappers.bbs.updateStep", map);
	}

	@Override
	public int totalRec() throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("mappers.bbs.totalRec");
	}

	@Override
	public List<BbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		return sqlsession.selectList("mappers.bbs.flist", map);
	}

	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		logger.info("implxml - SearchTotalRec");
		Map<String,Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sqlsession.selectOne("mappers.bbs.searchTotalRec", map);
	}

}
