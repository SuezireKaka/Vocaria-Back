package www.voca.ria.framework.mapper;

import org.apache.ibatis.annotations.Mapper;

import www.voca.ria.framework.model.form.FormVO;
import www.voca.ria.framework.model.remocon.RemoconVO;

@Mapper		//Container에 담기도록 지정
public interface CodeMapper {
	//LRCUD 순서로 함수들을 배치하여 빠르게 따라갈(추적성) 수 있도록 합니다. 
	public FormVO getFormFor(String purpose);

	public RemoconVO getRemoconByName(String name);
	
}
