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
import www.voca.ria.mission.model.MissionDTO;
import www.voca.ria.mission.service.MissionService;
import www.voca.ria.mission.strategy.QuestionBuildStrategy;
import www.voca.ria.mission.strategy.QuestionBuildStrategy.StrategyType;
import www.voca.ria.party.model.AccountVO;

@RestController
@RequestMapping("/mission")
public class MissionController {
	@Autowired
	private MissionService missionService;
	
	// /mission/anonymous/listAllStrategyType
	@GetMapping("/anonymous/listAllStrategyType")
	public ResponseEntity<List<StrategyType>> listAllVoca() {
		List<StrategyType> result = Arrays.asList(StrategyType.values());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /mission/getMission/somewhatAccountId/2024-08-14
	@GetMapping("/getMission/{accountId}/{dateString}")
	@PreAuthorize("(@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')"
			+ " and "
			+ "principal.getId() == #accountId"
			+ ") or "
			+ "@actScopeSpel.isAbleToRunAny(authentication, '0000', 'TM')")
	public ResponseEntity<MissionDTO> getMission(@AuthenticationPrincipal AccountVO student,
			@PathVariable String accountId, @PathVariable String dateString) {
		MissionDTO result = missionService.getMission(accountId, dateString);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// /mission/anonymous/buildMission/account1-account2-account3
	@PostMapping("/anonymous/buildMission/{accountIdChain}")
	// @PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, '0000', 'TM')")
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
		
		boolean result = missionService.buildMission(strategy, parsedData);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}