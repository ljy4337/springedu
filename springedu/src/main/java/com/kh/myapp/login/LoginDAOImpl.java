//package com.kh.myapp.login;
//
//import javax.inject.Inject;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.kh.myapp.member.dto.MemberDTO;
//
//@Repository(value="loginDAOImpl")
//public class LoginDAOImpl implements LoginDAO{
//	
//	private static final Logger logger = LoggerFactory.getLogger(LoginDAOImpl.class);
//	
//	@Inject
//	JdbcTemplate jdbcTemplate;
//
//
//	@Override
//	public boolean isMember(String id, String pw) {
//		
//		boolean result = false;
//		
//		StringBuffer sql = new StringBuffer();
//		sql.append("select count(id) from member ");
//		sql.append("where id=? and pw=? ");
//		
//		int cnt = jdbcTemplate.queryForObject(sql.toString(), new String[] {id,pw} , Integer.class);
//		
//		if(cnt>0) {
//			result = true;
//		}
//		
//		
//		
//		return result;
//	}
//
//	@Override
//	public MemberDTO login(String id, String pw) {
//		
//		MemberDTO mdto =  new MemberDTO();
//		
//		StringBuffer sql = new StringBuffer();
//		sql.append("select * from member ");
//		sql.append("where id=? and pw=? ");
//		
//		mdto = jdbcTemplate.queryForObject(sql.toString(), new String[] {id,pw}, 
//				new BeanPropertyRowMapper<>(MemberDTO.class));
//		return mdto;
//	}
//
//	@Override
//	public String findID(String nickname, String tel) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String findPW(String id, String nickname, String tel) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean isidMember(String nickname, String tel) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean ispwMember(String id, String nickname, String tel) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
