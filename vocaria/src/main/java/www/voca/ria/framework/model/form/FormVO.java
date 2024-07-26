package www.voca.ria.framework.model.form;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormVO {
	private String purpose;
	private List<InputPieceVO> pieceList;
	
	
}
