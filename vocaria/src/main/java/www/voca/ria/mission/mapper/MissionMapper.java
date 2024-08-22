package www.voca.ria.mission.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.voca.ria.framework.mapper.GeneralMapper;
import www.voca.ria.framework.model.structure.PageDTO;
import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.model.VocaVO;

@Mapper
public interface MissionMapper extends GeneralMapper {
	public List<VocaVO> listAllVoca(@Param("page") PageDTO page);
	
	public VocaVO getVocaById(@Param("id") String id);
	
	public int setupTodayMission(@Param("student") AccountVO student);

	

}
