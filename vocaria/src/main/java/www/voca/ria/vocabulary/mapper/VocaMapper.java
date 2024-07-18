package www.voca.ria.vocabulary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import www.voca.ria.vocabulary.model.WordVO;

@Mapper
public interface VocaMapper {
	public List<WordVO> listWordsOfNumber(int choiceNum);

}
