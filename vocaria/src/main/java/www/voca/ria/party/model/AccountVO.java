package www.voca.ria.party.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import www.voca.ria.framework.model.TimeEntity;

@Getter
@SuperBuilder
@NoArgsConstructor
@JsonIgnoreProperties({"passWord"})
public class AccountVO extends TimeEntity implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String passWord;
	
	private PersonVO owner;
	private OrganizationVO provider;
	
	private String nick;
	private String introduction;
	
	private List<RoleVO> roleList;

	public String getKSuspectType() {
		return "사용자";
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roleList.stream()
				.map(RoleVO::getAuthority)
				.collect(Collectors.toList());
	}

	public String getPassword() {
		return "";
	}

	public String getUsername() {
		return this.getId();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public void encodePswd(PasswordEncoder pswdEnc) {
		this.passWord = pswdEnc.encode(passWord);	
	}

}
