package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.CalendarDao;
import bit.com.a.dto.CalendarDto;

@Repository
public class CalendarDaoImpl implements CalendarDao {

	@Autowired
	SqlSession session;
	
	String ns = "Cal.";
	
	@Override
	public List<CalendarDto> getCalendarList(CalendarDto cal) {		
		return session.selectList(ns + "getCalendar", cal);		
	}
	
	@Override
	public boolean writeCalendar(CalendarDto cal) {
		int n = session.insert(ns + "writeCalendar", cal); 
		return n>0?true:false;
	}

	@Override
	public CalendarDto getDay(CalendarDto cal) {
		return session.selectOne(ns + "getDay", cal);		
	}

	@Override
	public void calupdate(CalendarDto cal) {
		session.update(ns + "calupdate", cal);
		
	}
}




