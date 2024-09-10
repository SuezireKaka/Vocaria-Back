package www.voca.ria.vocabulary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.framework.model.structure.Pair;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.mapper.VocaMapper;
import www.voca.ria.vocabulary.model.ChapterVO;
import www.voca.ria.vocabulary.model.VocaVO;

@Service
public class VocaService {
	@Autowired
	private VocaMapper vocaMapper;
	
	public Pair<List<VocaVO>, PageDTO> listAllVoca(int pageNum) {
		PageDTO page = new PageDTO(pageNum);
		
		List<VocaVO> vocaList = vocaMapper.listAllVoca(page);
		
		page.buildPagination(vocaMapper.countAllVoca());
		
		return new Pair<>(vocaList, page);
	}
	
	public Pair<List<VocaVO>, PageDTO> listAllSubscribes(AccountVO student, int pageNum) {
		PageDTO page = new PageDTO(pageNum);
		
		List<VocaVO> vocaList = vocaMapper.listAllSubscribes(student, page);
		
		page.buildPagination(vocaMapper.countAllSubscribes(student));
		
		return new Pair<>(vocaList, page);
	}
	
	public VocaVO getVocaById(String id) {
		return vocaMapper.getVocaById(id);
	}
	
	public ChapterVO getChapter(String vocaId, int chapterNum) {
		ChapterVO result = vocaMapper.getChapter(vocaId, chapterNum);
		return result;
	}
	
	public boolean isSubscribing(AccountVO student, String vocaId) {
		return vocaMapper.isSubscribing(student, vocaId);
	}

	public int toggleSubscribe(AccountVO student, String vocaId) {
		if (vocaMapper.isFirstSubscribe(student, vocaId)) {
			return vocaMapper.firstSubscribe(student, vocaId);
		}
		return vocaMapper.toggleSubscribe(student, vocaId);
	}
}
