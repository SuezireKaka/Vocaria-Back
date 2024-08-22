package www.voca.ria.vocabulary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.voca.ria.framework.exception.BusinessException;
import www.voca.ria.framework.exception.ErrorCode;
import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.framework.model.structure.Pair;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.model.ChapterVO;
import www.voca.ria.vocabulary.model.VocaVO;
import www.voca.ria.vocabulary.service.VocaService;
import www.voca.ria.vocabulary.strategy.QuestionBuildStrategy;
import www.voca.ria.vocabulary.strategy.QuestionBuildStrategy.StrategyType;

@RestController
@RequestMapping("/voca")
public class VocaController {
	@Autowired
	private VocaService vocaService;

	// /voca/anonymous/listAllVoca/1
	@GetMapping("/anonymous/listAllVoca/{pageNum}")
	public ResponseEntity<Pair<List<VocaVO>, PageDTO>> listAllVoca(@PathVariable int pageNum) {
		Pair<List<VocaVO>, PageDTO> result = vocaService.listAllVoca(pageNum);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /voca/anonymous/getVocaById/0000
	@GetMapping("/anonymous/getVocaById/{vocaId}")
	public ResponseEntity<VocaVO> getVocaById(@PathVariable String vocaId) {
		VocaVO result = vocaService.getVocaById(vocaId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /voca/getMission/somewhatAccountId/2024-08-14
	@GetMapping("/getMission/{accountId}/{dateString}")
	@PreAuthorize("(@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')"
			+ " and "
			+ "principal.getId() == #accountId"
			+ ") or "
			+ "@actScopeSpel.isAbleToRunAny(authentication, '0000', 'SM')")
	public ResponseEntity<ChapterVO> getMission(@AuthenticationPrincipal AccountVO student,
			@PathVariable String accountId, @PathVariable String dateString) {
		ChapterVO result = vocaService.getMission(accountId, dateString);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// /voca/buildMission/account1-account2-account3
	@PostMapping("/buildMission/{accountIdChain}")
	@PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, '0000', 'SM')")
	public ResponseEntity<Boolean> buildMission(@AuthenticationPrincipal AccountVO student,
			@PathVariable String accountIdChain,
			@RequestBody QuestionBuildStrategy strategy) throws BusinessException {
		List<String> parsedData;
		
		try {
			parsedData = strategy.getParsedData();
		}
		catch (Exception e) {
			throw new BusinessException("Invalid strategy found", ErrorCode.INVALID_STRATEGY);
		}
		
		boolean result = strategy.getType() == StrategyType.AUTO
				? true
				: false;
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}








