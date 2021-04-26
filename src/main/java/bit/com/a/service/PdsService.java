package bit.com.a.service;

import java.util.List;

import bit.com.a.dto.PdsDto;

public interface PdsService {

	List<PdsDto> getPdsList();
	
	boolean uploadPds(PdsDto dto);
	
	public PdsDto getPds(int seq);
	
	public void readCount(int seq);
	
	void updateBbs(PdsDto pds);
	
	void deletePds(int seq);
}
