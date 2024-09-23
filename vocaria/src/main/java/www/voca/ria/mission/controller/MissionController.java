package www.voca.ria.mission.controller;

import java.util.Arrays;
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
import www.voca.ria.mission.model.ChoiceDTO;
import www.voca.ria.mission.model.MissionVO;
import www.voca.ria.mission.service.MissionService;
import www.voca.ria.mission.strategy.QuestionBuildStrategy;
import www.voca.ria.mission.strategy.QuestionBuildStrategy.StrategyType;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.security.spel.GroupScopeSpel;

@RestController
@RequestMapping("/mission")
public class MissionController {
	@Autowired
	private MissionService missionService;
	@Autowired
	private GroupScopeSpel spel;
	
	// /mission/testSpel/account1-account2-account3
	@GetMapping("/testSpel/{accountIdChain}")
	@PreAuthorize("@groupScopeSpel.isTeachableChain(authentication, #accountIdChain)")
	public ResponseEntity<Boolean> buildMission(@PathVariable String accountIdChain) {
		spel.getImsiResult();
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	// /mission/getMission/somewhatAccountId/2024-08-14/1
	@GetMapping("/getMission/{accountId}/{dateString}/{pageNum}")
	@PreAuthorize("(@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')"
			+ " and "
			+ "principal.getId() == #accountId"
			+ ") or "
			+ "@groupScopeSpel.isTeachableChain(authentication, #accountId)")
	public ResponseEntity<Pair<List<MissionVO>, PageDTO>> listAllMission(@AuthenticationPrincipal AccountVO student,
			@PathVariable String accountId, @PathVariable String dateString,
			@PathVariable int pageNum) {
		Pair<List<MissionVO>, PageDTO> result = missionService.listAllMission(accountId, dateString, pageNum);
		spel.clearImsi();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /mission/anonymous/listAllStrategyType
	@GetMapping("/anonymous/listAllStrategyType")
	public ResponseEntity<List<StrategyType>> listAllVoca() {
		List<StrategyType> result = Arrays.asList(StrategyType.values());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /mission/evaluate
	@PostMapping("/evaluate")
	@PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')")
	public ResponseEntity<Integer> evaluate(@RequestBody ChoiceDTO choice,
			@AuthenticationPrincipal AccountVO student) throws BusinessException {
		if (! choice.isMatching()) {
			throw new BusinessException(ErrorCode.INVALID_BODY);
		}
		return new ResponseEntity<>(
				missionService.evaluate(student, choice)
				, HttpStatus.OK);
	}

	// /mission/buildMission/account1-account2-account3
	@PostMapping("/buildMission/{accountIdChain}")
	@PreAuthorize("@groupScopeSpel.isTeachableChain(authentication, #accountIdChain)")
	public ResponseEntity<Boolean> buildMission(
			@PathVariable String accountIdChain,
			@RequestBody QuestionBuildStrategy strategy) throws BusinessException {
		List<String> parsedData;
		
		List<AccountVO> studentsList = spel.getImsiResult();
		
		try {
			parsedData = strategy.getParsedData();
		}
		catch (Exception e) {
			throw new BusinessException("Invalid strategy found", ErrorCode.INVALID_STRATEGY);
		}
		
		boolean result = missionService.buildMission(studentsList, strategy, parsedData);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
