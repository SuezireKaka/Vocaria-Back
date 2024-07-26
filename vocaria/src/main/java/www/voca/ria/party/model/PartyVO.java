package www.voca.ria.party.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.voca.ria.framework.model.entity.Entity;

@Getter
@SuperBuilder
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PartyVO extends Entity {	
	private String name;
	private Date birthDate; // 회사의 경우 설립일
	private List<ContactPointVO> contactPointList;
	
	public void addCP(ContactPointVO cp) {
		contactPointList.add(cp);
	}

	public void addAllCPs(List<ContactPointVO> contactsList) {
		contactPointList.addAll(contactsList);
	}
}
