package www.voca.ria.attend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.voca.ria.attend.mapper.AttendMapper;

@Service
public class AttendService {
	@Autowired
	private AttendMapper attendMapper;

	public int attendAll(String userId) {
		return attendMapper.attendAll(userId);
	}

}
