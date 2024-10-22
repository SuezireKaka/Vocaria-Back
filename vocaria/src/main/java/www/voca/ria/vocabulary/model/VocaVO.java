package www.voca.ria.vocabulary.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.model.entity.TimeEntity;
import www.voca.ria.party.model.AccountVO;

@Getter
@NoArgsConstructor
public class VocaVO extends TimeEntity {
	private String name;
	private AccountVO maker;
	
	private boolean isForTeacher;
	
	private String introduce;
	
	private List<ChapterVO> chapterList;
}
