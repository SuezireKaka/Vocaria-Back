package www.voca.ria.party.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class PersonVO extends PartyVO {
	// 다중계정으로의 확장가능성을 잃지 않은 채로 단일계정만 허용
	public static final int MAX_ACCOUNT_NUMBER = 1;

}
