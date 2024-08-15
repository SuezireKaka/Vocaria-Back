package www.voca.ria.vocabulary.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class MissionDTO {
	private List<MissionVO> missionList;
	
	private Date date;
	
	private int currentRead;
	private int currentComplete;
	
	private int totalCount;
	
	public MissionDTO(List<MissionVO> missionList, String dateString) throws ParseException {
		this.missionList = missionList;
		
		this.date = new SimpleDateFormat("yyyy-mm-dd").parse(dateString);
		
		this.totalCount = missionList.size();
		
		this.currentRead = (int) missionList.stream()
				.filter(MissionVO :: isRead)
				.count();
		
		this.currentComplete = (int) missionList.stream()
				.filter(MissionVO :: isComplete)
				.count();
	}
}
