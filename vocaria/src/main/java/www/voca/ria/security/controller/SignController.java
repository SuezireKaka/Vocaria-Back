package www.voca.ria.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.voca.ria.framework.exception.BusinessException;
import www.voca.ria.security.model.SignInDTO;
import www.voca.ria.security.model.SignInResultDto;
import www.voca.ria.security.service.SignService;

// 예제 13.28
@RestController
@CrossOrigin
@RequestMapping("/party")
public class SignController {

	private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
	
	@Autowired
	private SignService signService;
	
	// /party/anonymous/signin
	@PostMapping("/anonymous/signin")
	public SignInResultDto signIn(@RequestBody SignInDTO signInDTO) throws BusinessException {
		LOGGER.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", signInDTO.getLoginId());
		SignInResultDto signInResultDto = signService.signIn(signInDTO);

		if (signInResultDto.getCode() == 0) {
			LOGGER.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", signInDTO.getLoginId(), signInResultDto.getToken());
		}
		return signInResultDto;
	}

	/** CustomAccessDeniedHandler */
	@GetMapping(value = "/anonymous/exception")
	public void exceptionTest() throws RuntimeException {
		throw new RuntimeException("접근이 금지되었습니다.");
	}

}