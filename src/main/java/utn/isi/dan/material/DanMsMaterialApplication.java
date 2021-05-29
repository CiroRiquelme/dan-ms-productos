package utn.isi.dan.material;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DanMsMaterialApplication {

	public static void main(String[] args) {
		SpringApplication.run(DanMsMaterialApplication.class, args);
	}

}
