package www.voca.ria.mission.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChoiceDTO {
	private List<String> questionIdList;
	private List<String> chooseList;
	
	public boolean isMatching() {
		return questionIdList.size() == chooseList.size();
	}
}
