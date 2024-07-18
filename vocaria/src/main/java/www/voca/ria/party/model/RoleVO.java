package www.voca.ria.party.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RoleVO {
	private String role;
	private String manual;
	
	public SimpleGrantedAuthority getAuthority() {
		return new SimpleGrantedAuthority(role);
	}
}
