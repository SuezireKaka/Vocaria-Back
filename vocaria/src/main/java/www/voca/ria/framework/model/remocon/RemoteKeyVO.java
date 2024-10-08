package www.voca.ria.framework.model.remocon;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RemoteKeyVO {
	private String name;
	private String info;
	private String auth;
	private boolean isImmediate;
}
