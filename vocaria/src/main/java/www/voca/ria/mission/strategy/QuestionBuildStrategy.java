package www.voca.ria.mission.strategy;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
	@Type(value = AutomaticallyIteratingStrategy.class, name = "AUTO"),
	@Type(value = DirectlyChoosingStrategy.class, name = "DIRECT")
})
public interface QuestionBuildStrategy {
	public static final String SEPARATOR = "-";

	public StrategyType getType();

	public List<String> getParsedData();

	public static enum StrategyType {
		AUTO, DIRECT
	}
}
