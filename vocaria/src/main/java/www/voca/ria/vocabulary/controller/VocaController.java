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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.framework.model.structure.Pair;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.model.ChapterVO;
import www.voca.ria.vocabulary.model.VocaVO;
import www.voca.ria.vocabulary.service.VocaService;

@RestController
@RequestMapping("/voca")
public class VocaController {
	@Autowired
	private VocaService vocaService;

	// /voca/anonymous/listAllVoca/1
	@GetMapping("/anonymous/listAllVoca/{pageNum}")
	public ResponseEntity<Pair<List<VocaVO>, PageDTO>> listAllVoca(
			@PathVariable int pageNum) {
		Pair<List<VocaVO>, PageDTO> result = vocaService.listAllVoca(pageNum);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /voca/listAllSubscribes/1
	@GetMapping("/listAllSubscribes/{pageNum}")
	@PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')")
	public ResponseEntity<Pair<List<VocaVO>, PageDTO>> listAllSubscribes(
			@PathVariable int pageNum,
			@AuthenticationPrincipal AccountVO student) {
		Pair<List<VocaVO>, PageDTO> result = 
				vocaService.listAllSubscribes(student, pageNum);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /voca/getVocaById/0000
	@GetMapping("/getVocaById/{vocaId}")
	@PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')"
			+ "&& @vocaScopeSpel.isOpenFor(authentication, #vocaId)")
	public ResponseEntity<VocaVO> getVocaById(@PathVariable String vocaId) {
		VocaVO result = vocaService.getVocaById(vocaId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /voca/getChapter/0000/1
	@GetMapping("/getChapter/{vocaId}/{chapterNum}")
	@PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')"
			+ "&& @vocaScopeSpel.isOpenFor(authentication, #vocaId)")
	public ResponseEntity<ChapterVO> getChapter(@PathVariable String vocaId,
			@PathVariable int chapterNum) {
		ChapterVO result = vocaService.getChapter(vocaId, chapterNum);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /voca/isSubscribing/0000
	@GetMapping("/isSubscribing/{vocaId}")
	@PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')")
	public ResponseEntity<Boolean> isSubscribing(@PathVariable String vocaId,
			@AuthenticationPrincipal AccountVO student) {
		boolean result = vocaService.isSubscribing(student, vocaId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /voca/toggleSubscribe/0000
	@PostMapping("/toggleSubscribe/{vocaId}")
	@PreAuthorize("@actScopeSpel.isAbleToRunAny(authentication, '0000', 'PS')")
	public ResponseEntity<Integer> toggleSubscribe(@PathVariable String vocaId,
			@AuthenticationPrincipal AccountVO student) {
		int result = vocaService.toggleSubscribe(student, vocaId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}