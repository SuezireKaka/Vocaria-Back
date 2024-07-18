package www.voca.ria.vocabulary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.voca.ria.vocabulary.model.QuestionDTO;
import www.voca.ria.vocabulary.service.VocaService;

@RestController
@RequestMapping("/voca")
public class VocaController {
	@Autowired
	private VocaService vocaService;

	// /bible/anonymous/getRandomQuestion/5
	@GetMapping("/anonymous/getRandomQuestion/{choiceNum}")
	public ResponseEntity<QuestionDTO> getRandomQuestion(@PathVariable int choiceNum) {
		QuestionDTO result = vocaService.getRandomQuestion(choiceNum);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
