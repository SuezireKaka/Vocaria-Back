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
import www.voca.ria.mission.model.ExamDTO;
import www.voca.ria.mission.model.MissionVO;
import www.voca.ria.mission.service.MissionService;
import www.voca.ria.mission.strategy.QuestionBuildStrategy;
import www.voca.ria.mission.strategy.QuestionBuildStrategy.StrategyType;
import www.voca.ria.party.model.AccountVO;

@RestController
@RequestMapping("/mission")
public class MissionController {
	@Autowired
	private MissionService missionService;
	
	// /mission/testSpel/account1-account2-account3
	@GetMapping("/testSpel/{accountIdChain}")
	@PreAuthorize("@groupScopeSpel.isTeachableChain(authentication, #accountIdChain)")
	public ResponseEntity<Boolean> buildMission(@PathVariable String accountIdChain) {
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	// /mission/listAllMissionFromMe/somewhatAccountId/2024-08-14/1
	@GetMapping("/listAllMissionFromMe/{studentId}/{dateString}/{pageNum}")
	@PreAuthorize("@groupScopeSpel.isTeachableChain(authentication, #studentId)")
	public ResponseEntity<Pair<List<MissionVO>, PageDTO>> listAllMissionFromMe(
			@AuthenticationPrincipal AccountVO teacher,
			@PathVariable String studentId, @PathVariable String dateString,
			@PathVariable int pageNum) {
		Pair<List<MissionVO>, PageDTO> result =
				missionService.listAllMissionFrom(
						teacher.getId(), studentId, dateString + "%", pageNum);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /mission/listAllMissionToMe/2024-08-14/1
	@GetMapping("/listAllMissionToMe/{dateString}/{pageNum}")
	@PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')")
	public ResponseEntity<Pair<List<MissionVO>, PageDTO>> listAllMissionToMe(
			@AuthenticationPrincipal AccountVO student,
			@PathVariable String dateString,
			@PathVariable int pageNum) {
		Pair<List<MissionVO>, PageDTO> result = missionService
				.listAllMissionTo(student.getId(), dateString + "%", pageNum);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /mission/listAllQuestionIn/0002
	@GetMapping("/listAllQuestionIn/{missionId}")
	@PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')")
	public ResponseEntity<ExamDTO> listAllQuestionIn(
			@AuthenticationPrincipal AccountVO student,
			@PathVariable String missionId) {
		ExamDTO result = missionService.listAllQuestionIn(missionId);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /mission/anonymous/listAllStrategyType
	@GetMapping("/anonymous/listAllStrategyType")
	public ResponseEntity<List<StrategyType>> listAllStrategyType() {
		List<StrategyType> result = Arrays.asList(StrategyType.values());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /mission/getMissionOfStudentById/somewhatStudentId/0000
	@GetMapping("/getMissionOfStudentById/{studentId}/{questionId}")
	@PreAuthorize("principal.getId().equals(#studentId)"
			+ "|| @groupScopeSpel.isTeachableChain(authentication, #studentId)")
	public ResponseEntity<MissionVO> getMissionOfStudentById(
			@AuthenticationPrincipal AccountVO account,
			@PathVariable String studentId,
			@PathVariable String questionId) {
		MissionVO result = missionService.getMissionOfStudentById(questionId);

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
		
		try {
			parsedData = strategy.getParsedData();
		}
		catch (Exception e) {
			throw new BusinessException("Invalid strategy found", ErrorCode.INVALID_STRATEGY);
		}
		
		boolean result = missionService.buildMission(accountIdChain,
				strategy, parsedData);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
