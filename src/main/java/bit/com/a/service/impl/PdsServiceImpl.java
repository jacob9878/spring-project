package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.PdsDao;
import bit.com.a.dto.PdsDto;
import bit.com.a.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService {

	@Autowired
	PdsDao dao;

	@Override
	public List<PdsDto> getPdsList() {		
		return dao.getPdsList();
	}

	@Override
	public boolean uploadPds(PdsDto dto) {		
		return dao.uploadPds(dto);		
	}

	@Override
	public PdsDto getPds(int seq) {
		return dao.getPds(seq);
	}

	@Override
	public void readCount(int seq) {
		dao.readCount(seq);
		
	}

	@Override
	public void updateBbs(PdsDto pds) {
		dao.updateBbs(pds);
		
	}

	@Override
	public void deletePds(int seq) {
		dao.deletePds(seq);
		
	}
	
	
	
}
