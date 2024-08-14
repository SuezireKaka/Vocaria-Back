package www.voca.ria.vocabulary.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WordVO {
	public static final String WORD_SEPERATOR = ", ";
	
	private String word;
	private String meaning;
}
