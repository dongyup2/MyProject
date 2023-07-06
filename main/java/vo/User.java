package vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
	private int mno;
	private String name;
	private String id;
	private String pw;
	private String email;
	
	public User toUser() {
		return User.builder()
				.mno(mno)
				.name(name)
				.id(id)
				.pw(pw)
				.email(email)
				.build();
			
	}
}
