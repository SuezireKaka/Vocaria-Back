package www.voca.ria.party.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.voca.ria.party.model.AccountVO;
import www.voca.ria.party.model.SignUpDto;
import www.voca.ria.party.service.PartyService;

@RestController
@CrossOrigin
@RequestMapping("/party")
public class PartyController {
	@Autowired
	private PartyService partyService;

	/*
	// /party/listAllAccount/0000/1
	@GetMapping("/listAllAccount/{ownerId}/{page}/{orderColumn}")
	@PreAuthorize("hasAnyAuthority('manager', 'admin')")
	public ResponseEntity<Pair<List<AccountVO>, PagingDTO>> listAllAccount(
			@AuthenticationPrincipal AccountVO manager, @PathVariable String ownerId, @PathVariable int page,
			@PathVariable String orderColumn) {
		Pair<List<AccountVO>, PagingDTO> result = partyService.listAllAccount(ownerId, page);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	*/
	
	// /party/tokentest
	@GetMapping("/tokentest")
	@PreAuthorize("hasAnyAuthority('0000-manager')")
	public ResponseEntity<String> tokentest(
			@AuthenticationPrincipal AccountVO tester) {
		return ResponseEntity.ok("token 성공적");
	}
	
	// /party/speltest
	@GetMapping("/speltest/{groupId}")
	@PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, #groupId, 'ST')")
	public ResponseEntity<String> speltest(
			@AuthenticationPrincipal AccountVO tester,
			@PathVariable String groupId) {
		return ResponseEntity.ok("SpEL 성공적");
	}

	// /party/anonymous/check/loginId/addr
	@GetMapping("/anonymous/check/loginId/{loginId}")
	public ResponseEntity<Boolean> checkLoginId(@PathVariable String loginId) {
		return ResponseEntity.ok(partyService.checkLoginId(loginId));
	}

	// /party/anonymous/check/nick/adfes
	@GetMapping("/anonymous/check/nick/{nick}")
	public ResponseEntity<Boolean> checkNick(@PathVariable String nick) {
		return ResponseEntity.ok(partyService.checkNick(nick));
	}

	
	// /party/anonymous/createMember
	@PostMapping("/anonymous/createMember")
	public ResponseEntity<Integer> createMember(@RequestBody SignUpDto signUpRequest) {
		return ResponseEntity.ok(partyService.manageMember(signUpRequest));
	}

	/*
	// /party/updateMember
	@PostMapping("/updateMember")
	@PreAuthorize("hasAnyAuthority('reader', 'writer','manager', 'admin')")
	public ResponseEntity<Integer> updateMember(@AuthenticationPrincipal AccountVO owner,
			@RequestBody SignUpDto signUpRequest) {
		return ResponseEntity.ok(partyService.mngMember(signUpRequest));
	}
	

	// /party/reRole
	@GetMapping("/reRole/{memberId}/{role}")
	@PreAuthorize("hasAnyAuthority('manager', 'admin')")
	public ResponseEntity<Integer> reRole(@AuthenticationPrincipal AccountVO owner, @PathVariable String memberId,
			@PathVariable String role) {
		return ResponseEntity.ok(partyService.reRole(memberId, role));
	}

	// /party/reRole
	@GetMapping("/updateStatus/{memberId}/{loginResultCode}")
	@PreAuthorize("hasAnyAuthority('manager', 'admin')")
	public ResponseEntity<Integer> updateStatus(@AuthenticationPrincipal AccountVO owner, @PathVariable String memberId,
			@PathVariable String loginResultCode) {
		return ResponseEntity.ok(partyService.updateStatus(memberId, loginResultCode));
	}

	// /party/deleteMember/닉
	@GetMapping("/deleteMember/{id}")
	@PreAuthorize("hasAnyAuthority('reader', 'writer','manager', 'admin')")
	public ResponseEntity<Integer> deleteMember(@AuthenticationPrincipal AccountVO owner, @PathVariable String id) {
		return ResponseEntity.ok(partyService.deleteMember(id));
	}
	
	*/

}
