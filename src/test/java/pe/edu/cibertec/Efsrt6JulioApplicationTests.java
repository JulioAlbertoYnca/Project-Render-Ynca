package pe.edu.cibertec;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class Efsrt6JulioApplicationTests {

	@Test
	void contextLoads() {
		String rawPassword = "1234";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Contraseña encriptada: " + encryptedPassword);
	}
	

}
