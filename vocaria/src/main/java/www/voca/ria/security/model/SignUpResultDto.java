package www.voca.ria.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/** 회원가입 결과 정보 */
@Data
@SuperBuilder
@NoArgsConstructor
public class SignUpResultDto {

	private boolean success;

	private int code;

	private String msg;

}