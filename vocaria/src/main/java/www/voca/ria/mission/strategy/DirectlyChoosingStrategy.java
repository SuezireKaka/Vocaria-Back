package www.voca.ria.mission.strategy;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DirectlyChoosingStrategy implements QuestionBuildStrategy {
	private List<QuestionAddress> addressList;
	
	@Getter
	@NoArgsConstructor
	static class QuestionAddress {
		private String vocaId;
		private int chapterPos;
		private int questionPos;
		
		@Override
		public String toString() {
			return vocaId + SEPARATOR + chapterPos + SEPARATOR + questionPos;
		}
	}

	@Override
	public StrategyType getType() {
		return StrategyType.DIRECT;
	}

	@Override
	public List<String> getParsedData() {
		return addressList.stream()
				.map(QuestionAddress::toString)
				.collect(Collectors.toList());
	}
}
