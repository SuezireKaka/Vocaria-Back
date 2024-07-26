package www.voca.ria.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.voca.ria.framework.model.form.FormVO;
import www.voca.ria.framework.model.remocon.RemoconVO;
import www.voca.ria.framework.service.CodeService;

@RestController		//Container에 담기도록 지정
@RequestMapping("/framework")
public class CodeController {
	@Autowired
	private CodeService codeService;
	
	// /framework/anonymous/getFormFor/register
	@GetMapping("/anonymous/getFormFor/{purpose}")
	public ResponseEntity<FormVO> getFormFor(String purpose) {
		FormVO form = codeService.getFormFor(purpose);
		return new ResponseEntity<>(form, HttpStatus.OK);
	}
	
	// /framework/anonymous/getRemoconByName/remocon
	@GetMapping("/anonymous/getRemoconByName/{name}")
	public ResponseEntity<RemoconVO> getRemoconByName(@PathVariable String name) {
		RemoconVO remocon = codeService.getRemoconByName(name);
		return new ResponseEntity<>(remocon, HttpStatus.OK);
	}
}
