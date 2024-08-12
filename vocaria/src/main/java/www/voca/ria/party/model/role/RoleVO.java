package www.voca.ria.party.model.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.voca.ria.framework.model.entity.Entity;
import www.voca.ria.party.model.GroupVO;

@Getter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(of = {"provider", "name"}, callSuper = false)
public class RoleVO extends Entity implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	public static final String AUTHORITY_SEPERATOR = "-";
	
	private GroupVO provider;
	private String name;
	private String info;
	private boolean isDefault;
	
	@Builder.Default
	private List<ActVO> allowedActList = new ArrayList<>();

	public String getAuthority() {
		return this.toString();
	}
	
	@Override
	public String toString() {
		return provider.getId() + AUTHORITY_SEPERATOR + name;
	}
}
