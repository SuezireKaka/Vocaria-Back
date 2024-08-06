package www.voca.ria.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.voca.ria.framework.mapper.CodeMapper;
import www.voca.ria.framework.model.form.FormVO;
import www.voca.ria.framework.model.remocon.RemoconVO;

@Service
public class CodeService {
	@Autowired
	private CodeMapper codeMapper;

	public FormVO getFormFor(String purpose) {
		FormVO result = codeMapper.getFormFor(purpose);
		result.adjust();
		
		return result;
	}

	public RemoconVO getRemoconByName(String name) {
		return codeMapper.getRemoconByName(name);
	}
}
