//package com.kh.myapp.student;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementSetter;
//import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import lombok.Data;
//
///* 
// * 1. 쿼리 결과가 1행의 레코드를 리턴할때
// * queryForMap : 리턴값이 Map<String,Object>
// * 				  -	키는 테이블의 컬럼명
// * 				  -	값은 컬럼값
// * queryForObject : 
// * 	RowMapper 인터페이스
// * 	BeanPropertyRowMapper : 행의 테이블 레코드와 객체를 매핑해줄때 사용(필드명 같아야함)
// * 							필드명이 다를 경우는 수동으로 RowMapper 인터페이스 구현해야
// * 
// * 2. 쿼리 결과가 2행 이상의 레코드를 리턴할때
// * query
// * queryForList List<Map<String,Object>> 타입의 결과를 반환받고 싶을 경우 사용
// * 
// * 3. insert, update, delete 의 경우
// * update 메소드 사
// * */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
//class JdbcTemplateTest {
//
//	private static final Logger logger = LoggerFactory.getLogger(JdbcTemplateTest.class);
//
//	@Inject
//	JdbcTemplate jt;
//
//	StringBuffer sql;
//	int cnt;
//
//	@BeforeEach
//	void before() {
//		sql = new StringBuffer();
//	}
//
//	@Test
//	@Disabled
//	void insert() {
//		logger.info("insert test");
//		sql.append("insert into student (id, name, kor, eng, math) ");
//		sql.append("values(?,?,?,?,?) ");
//
//		cnt = jt.update(new PreparedStatementCreator() {
//
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//
//				PreparedStatement pstmt = con.prepareStatement(sql.toString());
//
//				pstmt.setString(1, "id2");
//				pstmt.setString(2, "name2");
//				pstmt.setInt(3, 10);
//				pstmt.setInt(4, 80);
//				pstmt.setInt(5, 70);
//
//				return pstmt;
//
//			}
//		});
//
//		logger.info("insert : " + cnt);
//	}
//
//	@Test
//	@Disabled
//	void insert2() {
//		logger.info("insert2 test");
//		sql.append("insert into student (id, name, kor, eng, math) ");
//		sql.append("values('id3','name3',20,20,20) ");
//
//		cnt = jt.update(sql.toString());
//		logger.info("생성건수 : " + cnt);
//	}
//
//	@Test
//	@Disabled
//	void insert3() {
//		logger.info("insert3 test");
//		sql.append("insert into student (id, name, kor, eng, math) ");
//		sql.append("values(?,?,?,?,?) ");
//
//		cnt = jt.update(con -> {
//			PreparedStatement pstmt = con.prepareStatement(sql.toString());
//
//			pstmt.setString(1, "id4");
//			pstmt.setString(2, "name4");
//			pstmt.setInt(3, 10);
//			pstmt.setInt(4, 80);
//			pstmt.setInt(5, 70);
//
//			return pstmt;
//		});
//
//		logger.info("insert : " + cnt);
//	}
//
//	@Test
//	@Disabled
//	void insert4() {
//		logger.info("insert4 test");
//		sql.append("insert into student (id, name, kor, eng, math) ");
//		sql.append("values(?,?,?,?,?) ");
//		cnt = jt.update(sql.toString(), "id5", "name5", 10, 10, 10);
//
//		logger.info("insert : " + cnt);
//	}
//
//	@Test
//	@Disabled
//	void insert5() {
//		logger.info("insert5 test");
//		sql.append("insert into student (id, name, kor, eng, math) ");
//		sql.append("values(?,?,?,?,?) ");
//
//		cnt = jt.update(sql.toString(), new PreparedStatementSetter() {
//
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//
//				ps.setString(1, "id6");
//				ps.setString(2, "name6");
//				ps.setInt(3, 10);
//				ps.setInt(4, 80);
//				ps.setInt(5, 70);
//			}
//
//		});
//		logger.info("insert : " + cnt);
//	}
//
//	@Test
//	@Disabled
//	void insert6() {
//		logger.info("insert6 test");
//		sql.append("insert into student (id, name, kor, eng, math) ");
//		sql.append("values(?,?,?,?,?) ");
//
//		cnt = jt.update(sql.toString(), ps -> {
//			ps.setString(1, "id7");
//			ps.setString(2, "name7");
//			ps.setInt(3, 10);
//			ps.setInt(4, 80);
//			ps.setInt(5, 70);
//
//		});
//		logger.info("insert : " + cnt);
//	}
//
//	@Test
//	@Disabled
//	void insert7() {
//		logger.info("insert7 test");
//		sql.append("insert into student (id, name, kor, eng, math) ");
//		sql.append("values(?,?,?,?,?) ");
//
//		int[] ty = { Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER };
//		cnt = jt.update(sql.toString(), new Object[] { "id8", "name8", 10, 10, 10 }, ty);
//
//		logger.info("insert : " + cnt);
//	}
//
////	
////	@Test
////	
////	void insert8() {
////		logger.info("insert8 test");
////		sql.append("insert into student (id, name, kor, eng, math) ");
////		sql.append("values(?,?,?,?,?) ");
////		
////		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
////		jt.update(new PreparedStatementCreator() {
////			
////			@Override
////			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
////				
////				PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[] {"id"});
////				
////				pstmt.setString(1, "id9");
////				pstmt.setString(2, "name9");
////				pstmt.setInt(3, 10);
////				pstmt.setInt(4, 80);
////				pstmt.setInt(5, 70);
////	
////				return pstmt;
////			}
////		}, generatedKeyHolder);
////		
////		logger.info("키값 : " + generatedKeyHolder.getKey().toString());
////		logger.info("insert : " + cnt);
////	}
//	@Test
//	@Disabled
//	void list() {
//		sql.append("select id,name,kor,eng,math from student "); // where kor > ?");
//		List<Map<String, Object>> list = null;
//
////		하나의 레코드를 컬럼은 key, 값은 value로 매핑하여 맵객체를 만들고 List에 담아올때 사용
//		list = jt.queryForList(sql.toString(), 50);
//
//		for (Map<String, Object> map : list) {
//			logger.info(map.toString());
//		}
//	}
//
//	@Test
//	@Disabled
//	void list2() { // 하나만 불러올수있음
//		sql.append("select math from student ");
//
//		@Data
//		class Student {
//			private String id;
//			private String name;
//			private int kor, eng, math;
//		}
//
////		1개의 레코드와 1개 컬럼만을 기본타입으로 List로 담아올때 사용
//		List<Integer> list = null;
//		list = jt.queryForList(sql.toString(), Integer.class);
//
//		for (Integer s : list) {
//			logger.info(s.toString());
//		}
//	}
//
//	@Test
//	@Disabled
//	void list3() {
//		sql.append("select id,name,kor,eng,math from student where kor > ? and math > ? ");
//		List<Map<String, Object>> list = null;
//
////		하나의 레코드를 컬럼은 key, 값은 value로 매핑하여 맵객체를 만들고 List에 담아올때 사용
//		list = jt.queryForList(sql.toString(), 50, 50);
////		
////		for(Map<String, Object> map : list) {
////			logger.info(map.toString());
////		}
//		list.stream().forEach(map -> {
//			logger.info(map.toString());
//		});
//	}
//
//	@Test
//	@Disabled
//	void list4() {// 하나만 불러올수있음
//		sql.append("select name from student where eng > ? and math > ? ");
//
//		@Data
//		class Student {
//			private String id;
//			private String name;
//			private int kor, eng, math;
//		}
//
////		1개의 레코드와 1개 컬럼만을 기본타입으로 List로 담아올때 사용
//		List<String> list = null;
//		list = jt.queryForList(sql.toString(), String.class, 50, 50);
//
//		for (String s : list) {
//			logger.info(s.toString());
//		}
//	}
//
//	@Test
//	@Disabled
//	void list5() {
//		sql.append("select name from student where eng > ? and math > ? ");
//
////		1개의 레코드와 1개 컬럼만을 기본타입으로 List로 담아올때 사용
//		List<String> list = null;
//		list = jt.queryForList(sql.toString(), new Object[] { 50, 50 }, String.class);
//
//		for (String s : list) {
//			logger.info(s.toString());
//		}
//	}
//
//	@Test
//	@Disabled
//	void list6() {
//		sql.append("select id,name,kor,eng,math from student where eng > ? and math > ? ");
////		2번째 매개값 치환변수에 대해 3번째 매개값으로 타입을 변환해주고자 할때 사용
//		List<Map<String, Object>> list = null;
//		list = jt.queryForList(sql.toString(), new Object[] { 50, 50 }, new int[] { Types.INTEGER, Types.INTEGER });
//
//		for (Map<String, Object> map : list) {
//			logger.info(map.toString());
//		}
//	}
//
//	@Test
//	@Disabled
//	void list7() {
//		sql.append("select name from student where eng > ? and math > ? ");
//
//		List<String> list = null;
//		list = jt.queryForList(sql.toString(), new Object[] { "50", "50" }, new int[] { Types.INTEGER, Types.INTEGER },
//				String.class);
//
//		list.stream().forEach(s -> logger.info(s.toString()));
//	}
//
//	@Test
//	@Disabled
//	void list8() {
//		sql.append("select * from student where name='name1' ");
//		Map<String, Object> map = null;
//		map = jt.queryForMap(sql.toString());
////		logger.info(map.toString());
//		logger.info("key : " + map.keySet().toString());
//		logger.info("ID : " + map.get("ID"));
//
//	}
//
//	@Test
//	@Disabled
//	void list9() {
//
//		sql.append("select count(*) from student where kor = 10 ");
//		Integer cnt = null;
//		cnt = jt.queryForObject(sql.toString(), Integer.class);
//		logger.info("cnt : " + cnt);
//	}
//
//	@Test
//	@Disabled
//	void list10() {
//		sql.append("select * from student where name = 'name1' ");
//
//		// 한행의 테이블 레코드와 객체를 매핑해줄때 사용 ( 전제조건 : 테이블 컬럼명과 객체의 setter 메소드명이 같아야 한다.
//		Student s = null;
//		s = jt.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(Student.class));
//		logger.info(s.toString());
//	}
//
//	@Test
//	@Disabled
//	void list11() {
//		sql.append("select count(*) from student where kor >= ? ");
//
//		Integer cnt = 0;
//
//		cnt = jt.queryForObject(sql.toString(), Integer.class, 50);
//		logger.info("국어 50 이상 : " + cnt);
//	}
//
//	@Test
//	@Disabled
//	void list12() {
//		sql.append("select count(*) from student where kor >= ? ");
//
//		Integer cnt = 0;
//
//		cnt = jt.queryForObject(sql.toString(), new Object[] { 50 }, Integer.class);
//		logger.info("국어 50 이상 : " + cnt);
//	}
//
//	@Test
//	@Disabled
//	void list13() {
//		sql.append("select * from student where name = 'name1' ");
//		Student2 s = null;
//		// 한행의 테이블 레코드와 객체를 수동으로 매핑해줄때 사용 ( 전제조건 : 테이블 컬럼명과 객체의 setter 메소드명이 같아야 한다.
//
//		s = jt.queryForObject(sql.toString(), new RowMapper<Student2>() {
//
//			@Override
//			public Student2 mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//				Student2 s = new Student2();
//				s.setSid(rs.getString("id"));
//				s.setSname(rs.getString("name"));
//				s.setSkor(rs.getInt("kor"));
//				s.setSeng(rs.getInt("eng"));
//				s.setSmath(rs.getInt("math"));
//
//				return s;
//			}
//
//		});
//		logger.info(s.toString());
//
//	}
//
//	@Test
//	@Disabled
//	void list14() {
//		sql.append("select id,name,kor,eng,math from student where math >= ? ");
//		Student s = null;
////		jt.query(new PreparedStatementCreator() {
////			
////			@Override
////			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
////				PreparedStatement pstmt = con.prepareStatement(sql.toString());
////				pstmt.setInt(1, 50);
////				return pstmt;
////			}
////		}, new ResultSetExtractor<Student>() {
////
////			@Override
////			public Student extractData(ResultSet rs) throws SQLException, DataAccessException {
////				Student student = new Student();
////				student.setId(rs.getString("id"));
////				student.setName(rs.getString("name"));
////				student.setKor(rs.getInt("kor"));
////				student.setEng(rs.getInt("eng"));
////				student.setMath(rs.getInt("math"));
////				return student;
////			}
////		});
//		s = jt.query(con -> {
//			PreparedStatement pstmt = con.prepareStatement(sql.toString());
//			pstmt.setInt(1, 50);
//			return pstmt;
//		}, rs -> {
//			Student student = new Student();
//			while (rs.next()) {
//
//				student.setId(rs.getString("id"));
//				student.setName(rs.getString("name"));
//				student.setKor(rs.getInt("kor"));
//				student.setEng(rs.getInt("eng"));
//				student.setMath(rs.getInt("math"));
//			}
//			return student;
//		});
//		logger.info("student : " + s.toString());
//
//	}
//
//	@Test
//	@Disabled
//	void list15() {
//		sql.append("select id, name, kor, eng, math from student where eng >= ? ");
//
//		List<Student> list = null;
//		list = jt.query(new PreparedStatementCreator() {
//
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				PreparedStatement pstmt = con.prepareStatement(sql.toString());
//				pstmt.setInt(1, 70);
//				return pstmt;
//			}
//		}, new RowMapper<Student>() {
//
//			@Override
//			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Student s = new Student();
//				s.setId(rs.getString("id"));
//				s.setName(rs.getString("name"));
//				s.setKor(rs.getInt("kor"));
//				s.setEng(rs.getInt("eng"));
//				s.setMath(rs.getInt("math"));
//				return s;
//			}
//		});
//
//		logger.info(list.toString());
//	}
//
//	@Test
//	@Disabled
//	void list18() {
//		sql.append("select id, name, kor, eng, math from student where eng >= ? and math > ? ");
//
//		List<Student> result = null;
//		result = jt.query(new PreparedStatementCreator() {
//
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				PreparedStatement pstmt = con.prepareStatement(sql.toString());
//				return pstmt;
//			}
//		}, new PreparedStatementSetter() {
//
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setInt(1, 70);
//				ps.setInt(2, 50);
//
//			}
//		}, new ResultSetExtractor<List<Student>>() {
//
//			@Override
//			public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
//				List<Student> list = new ArrayList<Student>();
//				while (rs.next()) {
//					Student s = new Student();
//					s.setId(rs.getString("id"));
//					s.setName(rs.getString("name"));
//					s.setKor(rs.getInt("kor"));
//					s.setEng(rs.getInt("eng"));
//					s.setMath(rs.getInt("math"));
//					list.add(s);
//				}
//				return list;
//			}
//		});
//		for (Student s : result) {
//			logger.info(s.toString());
//		}
//
//	}
//
//	@Test
//	@Disabled
//	void list19() {
//		sql.append("select id, name, kor, eng, math from student where eng >= ? and math > ? ");
//
//		List<Student> result = null;
//		result = jt.query(con -> {
//			PreparedStatement pstmt = con.prepareStatement(sql.toString());
//			return pstmt;
//		}, ps -> {
//			ps.setInt(1, 70);
//			ps.setInt(2, 50);
//
//		}, rs -> {
//			List<Student> list = new ArrayList<Student>();
//			while (rs.next()) {
//				Student s = new Student();
//				s.setId(rs.getString("id"));
//				s.setName(rs.getString("name"));
//				s.setKor(rs.getInt("kor"));
//				s.setEng(rs.getInt("eng"));
//				s.setMath(rs.getInt("math"));
//				list.add(s);
//			}
//			return list;
//		});
////		 for(Student s : result) {
////			 logger.info(s.toString());
////		 }
////				
//		result.stream().forEach(student -> logger.info(student.toString()));
//	}
//
//	@Test @Disabled
//	void update() {
//		sql.append("update student set kor=?, eng=?, math=? where name=? ");
//
//		int cnt = 0;
//		cnt = jt.update(sql.toString(), new Object[] { "10", "10", "10", "name1" },
//				new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR });
//
//		logger.info("갱신 : " + cnt);
//	}
//
//	@Test
//	@Disabled
//	void update2() {
//		sql.append("update student set kor=?, eng=?, math=? where name=? ");
//
//		int cnt = 0;
//		cnt = jt.update(sql.toString(), new Object[] {Integer.parseInt("100"),Integer.parseInt("100"),Integer.parseInt("100"),"name1"});
//
//		logger.info("갱신 : " + cnt);
//	}
//
//}
//
//@Data
//class Student {
//	private String id;
//	private String name;
//	private int kor;
//	private int eng;
//	private int math;
//}
//
//@Data
//class Student2 {
//	private String sid;
//	private String sname;
//	private int skor;
//	private int seng;
//	private int smath;
//}
