package www.voca.ria.framework.model.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InputPieceVO {
	private String propName;
	private String title;
	private String type;
	private String validRegex;
	private String holder;
	private boolean isUnique;
	
	public void adjust() {
		holder = holder.formatted(title);
	}
}
