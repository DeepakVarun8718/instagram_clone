package in.deepak.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto  {

	
	@Id
	private Integer id;
	private String username;
	private String email;
	private String name;
	private String userImage;
	
}
