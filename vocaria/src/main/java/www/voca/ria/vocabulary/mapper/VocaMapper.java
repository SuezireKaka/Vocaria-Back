package www.voca.ria.vocabulary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.voca.ria.framework.mapper.GeneralMapper;
import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.model.ChapterVO;
import www.voca.ria.vocabulary.model.VocaVO;

@Mapper
public interface VocaMapper extends GeneralMapper {
	
	public List<VocaVO> listAllVoca(@Param("page") PageDTO page);
	
	public List<VocaVO> listAllSubscribes(@Param("student") AccountVO student,
			@Param("page") PageDTO page);
	
	
	public VocaVO getVocaById(@Param("id") String id);
	
	public ChapterVO getChapter(@Param("vocaId") String vocaId,
			@Param("chapterNum") int chapterNum);
	
	public boolean isSubscribing(@Param("student") AccountVO student,
			@Param("vocaId") String vocaId);
	
	public boolean isFirstSubscribe(@Param("student") AccountVO student,
			@Param("vocaId") String vocaId);
	

	public int firstSubscribe(@Param("student") AccountVO student,
			@Param("vocaId") String vocaId);
	

	public int toggleSubscribe(@Param("student") AccountVO student,
			@Param("vocaId") String vocaId);
}
