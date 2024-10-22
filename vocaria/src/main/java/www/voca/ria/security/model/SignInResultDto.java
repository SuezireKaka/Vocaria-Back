package www.voca.ria.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

// 로그인 결과 정보 
@SuperBuilder
@Getter
@NoArgsConstructor
@ToString
public class SignInResultDto extends SignUpResultDto {
	private String token;	//JWT
	private String userId;
	private String userNick;
	private Collection<? extends GrantedAuthority> roleList;
}