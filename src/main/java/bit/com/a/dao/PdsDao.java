package bit.com.a.dao;

import java.util.List;
import bit.com.a.dto.PdsDto;

public interface PdsDao {

	List<PdsDto> getPdsList();
	
	boolean uploadPds(PdsDto dto);
	
	PdsDto getPds(int seq);
	
	void readCount(int seq);
	
	void updateBbs(PdsDto pds);
	
	void deletePds(int seq);
}
