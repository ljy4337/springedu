package com.kh.myapp.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.myapp.bbs.dto.RbbsDTO;

@Repository(value="rbbsDAOImplXML")
public class RbbsDAOImplXML implements RbbsDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(RbbsDAOImplXML.class);
	@Inject
	SqlSession sqlsession;

	@Override
	public int write(RbbsDTO rbbsdto) {
		
		return sqlsession.insert("mappers.rbbs.write", rbbsdto);
	}

	@Override
	public List<RbbsDTO> list(String bnum) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("mappers.rbbs.listOld", bnum);
	}

	@Override
	public List<RbbsDTO> list(String bnum, int startRec, int endRec) throws Exception {

		Map<String,Object> map = new HashMap<>();
		map.put("bnum", bnum);
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sqlsession.selectList("mappers.rbbs.list", map);
	}

	@Override
	public int modify(RbbsDTO rbbsdto) {
		
		return sqlsession.update("mappers.rbbs.modify", rbbsdto);
	}

	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;
		
		if(isReply(rnum)) {
			cnt = sqlsession.update("mappers.rbbs.update_isdel", rnum);
		}else {
			cnt = sqlsession.delete("mappers.rbbs.delete", rnum);
		}
		return cnt;
	}

	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sqlsession.selectOne("mappers.rbbs.isReply", rnum);
		if(cnt>0) {
			isYN = true;
		}
		return isYN;
	}

	@Override
	public int goodOrbad(String rnum, String goodOrbad) {

		Map<String,String> map = new HashMap<>();
		map.put("rnum", rnum);
		logger.info(goodOrbad);
		map.put("goodOrbad", goodOrbad);

		return sqlsession.update("goodOrbad", map);
	}


	@Override
	public int totalRec(String bnum) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//대댓글 등록
	@Override
	public int reply(RbbsDTO rbbsDTO) throws Exception {
		int cnt1=0, cnt2=0;
		//댓글대상 정보 읽어오기
		logger.info("참조글:"+rbbsDTO.getRrnum());
		RbbsDTO originDTO = replyView(rbbsDTO.getRrnum());

		//이전 답글 step 업데이트(원글그룹에 대한 세로정렬 재정의)
		cnt1 = updateStep(originDTO.getRgroup(), originDTO.getRstep());
		
		Map<String,Object> map = new HashMap<>();
		map.put("originDTO", originDTO);
		map.put("rbbsDTO", rbbsDTO);
		
		cnt2 = sqlsession.insert("mappers.rbbs.reply", map);
		return cnt2;
	}
	


	// 댓글대상 읽어오기
	private RbbsDTO replyView(Integer rrnum) {
		RbbsDTO rdto = null;
		rdto = sqlsession.selectOne("mappers.rbbs.replyView", rrnum);
		return rdto;
	}

	@Override
	public int replyTotalRec(String bnum) {

		return sqlsession.selectOne("mappers.rbbs.replyTotalRec", bnum);
	}

	@Override
	public int updateStep(int rgroup, int rstep) {
		Map<String,Object> map = new HashMap<>();
		map.put("rgroup", rgroup);
		map.put("rstep", rstep);
		return sqlsession.update("mappers.rbbs.updateStep", map);
	}
	



}
