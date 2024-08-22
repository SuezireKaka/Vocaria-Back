package www.voca.ria.vocabulary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.framework.model.structure.Pair;
import www.voca.ria.vocabulary.mapper.VocaMapper;
import www.voca.ria.vocabulary.model.VocaVO;

@Service
public class VocaService {
	@Autowired
	private VocaMapper vocaMapper;
	
	public Pair<List<VocaVO>, PageDTO> listAllVoca(int pageNum) {
		PageDTO page = new PageDTO(pageNum);
		
		List<VocaVO> vocaList = vocaMapper.listAllVoca(page);
		
		page.buildPagination(vocaMapper.getFoundRows());
		
		return new Pair<>(vocaList, page);
	}
	
	public VocaVO getVocaById(String id) {
		return vocaMapper.getVocaById(id);
	}
}
