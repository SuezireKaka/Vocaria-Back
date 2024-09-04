package www.voca.ria.party.model.role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"act"})
public class ActVO {
	private String act;
	private String area;
	private String type;
	private String info;
	
	public ActVO(String act) {
		this.act = act;
	}
}
