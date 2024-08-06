package www.voca.ria.party.model;

import java.util.Date;

import lombok.Data;

@Data
public class SignUpDto {
	private String name;
	private Date birth;
	private String loginId;
	private String rawPassword;
	private String nick;
	private String introduce;
}