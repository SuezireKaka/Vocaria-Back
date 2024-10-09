package www.voca.ria.mission.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExamDTO {
	private List<QuestionVO> questionList;
}
