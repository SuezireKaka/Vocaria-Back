package www.voca.ria.mission.strategy;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AutomaticallyIteratingStrategy implements QuestionBuildStrategy {
	private List<QuestionIteratingTag> tagList;
	
	@Getter
	@NoArgsConstructor
	static class QuestionIteratingTag {
		private String vocaId;
		private int iterCount;
		
		@Override
		public String toString() {
			return vocaId + SEPARATOR + iterCount;
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
