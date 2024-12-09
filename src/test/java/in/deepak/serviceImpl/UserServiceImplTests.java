package in.deepak.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.deepak.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTests {

	@Autowired
	private UserRepository userRepo;
	
	@Disabled
	@Test
	public void testAdd() {
		assertEquals(4, 2+2);
//	assertNotNull(userRepo.findByEmail("luffy@gmail.com"));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"1,1,2",
		"2,10,12",
		"3,3,9"
	})
	public void parameterized1(int a , int b , int excepted) {
		assertEquals(excepted, a+b);
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = {
		"luffy@gmail.com",
		"zoro@gmail.com"
		
	})
	public void parameterized2(String name) {
		assertNotNull(userRepo.findByEmail(name));
	}
	
	
	
	
}
