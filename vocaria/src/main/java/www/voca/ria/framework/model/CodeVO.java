package www.voca.ria.framework.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CodeVO {
	private String codeType;
	private String codeVal;
	private String validRegex;
}
