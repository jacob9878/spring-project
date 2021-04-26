package bit.com.a.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BbsDao;
import bit.com.a.dto.BbsDto;
import bit.com.a.dto.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao {

	@Autowired
	SqlSessionTemplate session;
	
	String ns = "Bbs.";
		
	@Override
	public List<BbsDto> getBbslist(BbsParam bbs) {		
		return session.selectList(ns + "bbslist", bbs);
	}

	@Override
	public int getBbsCount(BbsParam bbs) {		
		return session.selectOne(ns + "getBbsCount", bbs);
	}

	@Override
	public boolean writeBbs(BbsDto dto) {
		int n = session.insert(ns + "writeBbs", dto);
		return n>0?true:false;
	}
	
	@Override
	public BbsDto getBbs(int seq) {		
		return session.selectOne(ns + "getBbs", seq);
	}
	
	@Override
	public void readCount(int seq) {
		session.update(ns + "readCount", seq);
	}
	
	@Override
	public boolean replyBbsUpdate(BbsDto bbs) {		
		session.update(ns+"replyBbsUpdate", bbs);
		return true; 
	}

	@Override
	public boolean replyBbsInsert(BbsDto bbs) {
		session.insert(ns+"replyBbsInsert", bbs);
		return true;
	}
	
	@Override
	public void deleteBbs(int seq) {
		session.update(ns+"deleteBbs", seq);
	}
	
	@Override
	public void updateBbs(BbsDto bbs) {
		session.update(ns+"updateBbs", bbs);
	}
}








