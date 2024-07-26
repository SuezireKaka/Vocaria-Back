package www.voca.ria.framework.model.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InputPieceVO {
	private String title;
	private String type;
	private String validRegex;
	private boolean isUnique;
}
