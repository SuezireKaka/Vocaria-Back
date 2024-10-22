package www.voca.ria.security.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import www.voca.ria.vocabulary.model.VocaVO;
import www.voca.ria.vocabulary.service.VocaService;

@Component
public class VocaScopeSpel extends CommonSpel {
	@Autowired
	private VocaService vocaService;
	
	@Autowired
	private ActScopeSpel actScopeSpel;
	
	public boolean isOpenFor(Authentication authentication,
			String vocaId) {
		
		VocaVO voca = vocaService.getVocaById(vocaId);
		
		// 선생용이 아니거나
		// 요청자가 선생이면 통과
		return ! voca.isForTeacher()
				|| actScopeSpel.isAbleToRunAny(authentication, "0000", "TM");
	}
}
