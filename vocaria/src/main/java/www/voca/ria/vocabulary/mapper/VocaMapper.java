package www.voca.ria.vocabulary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import www.voca.ria.party.model.AccountVO;
import www.voca.ria.vocabulary.model.MissionVO;

@Mapper
public interface VocaMapper {
	public List<MissionVO> listAllMission(@Param("studentId") String accountId,
			@Param("dateString") String dateString);
	
	public int setupTodayWords(@Param("student") AccountVO student,
			@Param("choiseNum") int choiseNum);

	public int buildSecretQuestion(@Param("student") AccountVO student,
			@Param("choiseNum") int choiseNum);

}
