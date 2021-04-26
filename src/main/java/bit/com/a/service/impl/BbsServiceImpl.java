package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BbsDao;
import bit.com.a.dto.BbsDto;
import bit.com.a.dto.BbsParam;
import bit.com.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDao dao;
	
	@Override
	public List<BbsDto> getBbslist(BbsParam bbs) {		
		return dao.getBbslist(bbs);
	}

	@Override
	public int getBbsCount(BbsParam bbs) {		
		return dao.getBbsCount(bbs);		
	}

	@Override
	public boolean writeBbs(BbsDto dto) {		
		return dao.writeBbs(dto);
	}

	@Override
	public BbsDto getBbs(int seq) {		
		return dao.getBbs(seq);
	}

	@Override
	public void readCount(int seq) {
		dao.readCount(seq);
	}

	@Override
	public boolean reply(BbsDto bbs) {
		boolean b1 = dao.replyBbsUpdate(bbs);
		boolean b2 = dao.replyBbsInsert(bbs);
		if(b1 == false || b2 == false) {
			return false;
		} 
		return b2;
	}

	@Override
	public void deleteBbs(int seq) {
		dao.deleteBbs(seq);
	}

	@Override
	public void updateBbs(BbsDto bbs) {
		// TODO Auto-generated method stub
		dao.updateBbs(bbs);
	}
	
	
	
}





