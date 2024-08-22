package www.voca.ria.vocabulary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.voca.ria.framework.mapper.GeneralMapper;
import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.vocabulary.model.VocaVO;

@Mapper
public interface VocaMapper extends GeneralMapper {
	public List<VocaVO> listAllVoca(@Param("page") PageDTO page);
	
	public VocaVO getVocaById(@Param("id") String id);
}
