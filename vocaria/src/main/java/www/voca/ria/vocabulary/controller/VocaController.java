package www.voca.ria.vocabulary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.model.MissionDTO;
import www.voca.ria.vocabulary.service.VocaService;

@RestController
@RequestMapping("/voca")
public class VocaController {
	@Autowired
	private VocaService vocaService;

	// /voca/getMission/somewhatAccountId/2024-08-14
	@GetMapping("/getMission/{accountId}/{dateString}")
	@PreAuthorize("(@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')"
			+ " and "
			+ "principal.getId() == #accountId"
			+ ") or "
			+ "@actScopeSpel.isAbleToRunAny(authentication, '0000', 'SM')")
	public ResponseEntity<MissionDTO> getMission(
			@AuthenticationPrincipal AccountVO student,
			@PathVariable String accountId,
			@PathVariable String dateString) {
		MissionDTO result = vocaService.getMission(accountId, dateString);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
