package www.voca.ria.mission.strategy;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.exception.BusinessException;
import www.voca.ria.framework.exception.ErrorCode;

@Getter
@NoArgsConstructor
public class AutomaticallyIteratingStrategy implements QuestionBuildStrategy {
	private List<QuestionIteratingTag> tagList;
	
	@Getter
	@NoArgsConstructor
	public static class QuestionIteratingTag {
		private String vocaId;
		private int iterCount;
		
		@Override
		public String toString() {
			return vocaId + SEPARATOR + iterCount;
		}
		
		private QuestionIteratingTag(String vocaId, int iterCount) {
			this.vocaId = vocaId;
			this.iterCount = iterCount;
		}
		
		public static QuestionIteratingTag fromString(String expr) {
			String[] expArr = expr.split(SEPARATOR);
			if (expArr.length != 2) {
				throw new BusinessException(ErrorCode.INVALID_STRATEGY);
			}
			
			return new QuestionIteratingTag(expArr[0], Integer.valueOf(expArr[1]));
		}
	}

	@Override
	public StrategyType getType() {
		return StrategyType.AUTO;
	}

	@Override
	public List<String> getParsedData() {
		return tagList.stream()
				.map(QuestionIteratingTag::toString)
				.collect(Collectors.toList());
	}
}
