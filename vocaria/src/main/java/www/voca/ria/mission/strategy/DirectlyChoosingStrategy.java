package www.voca.ria.mission.strategy;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.voca.ria.framework.exception.BusinessException;
import www.voca.ria.framework.exception.ErrorCode;

@Getter
@NoArgsConstructor
public class DirectlyChoosingStrategy implements QuestionBuildStrategy {
	private List<QuestionAddress> addressList;
	
	@Getter
	@NoArgsConstructor
	public static class QuestionAddress {
		private String vocaId;
		private int chapterPos;
		private int questionPos;
		
		@Override
		public String toString() {
			return vocaId + SEPARATOR + chapterPos + SEPARATOR + questionPos;
		}
		
		private QuestionAddress(String vocaId, int chapterPos, int questionPos) {
			this.vocaId = vocaId;
			this.chapterPos = chapterPos;
			this.questionPos = questionPos;
		}
		
		public static QuestionAddress fromString(String expr) {
			String[] expArr = expr.split(SEPARATOR);
			if (expArr.length != 3) {
				throw new BusinessException(ErrorCode.INVALID_STRATEGY);
			}
			
			return new QuestionAddress(expArr[0],
					Integer.valueOf(expArr[1]),
					Integer.valueOf(expArr[2]));
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
