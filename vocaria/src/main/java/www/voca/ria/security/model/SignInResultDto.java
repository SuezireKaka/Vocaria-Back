package www.voca.ria.security.model;

import java.util.List;

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
	private List<String> roles;
}