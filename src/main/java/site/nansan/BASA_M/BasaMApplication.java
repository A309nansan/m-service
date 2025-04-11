package site.nansan.BASA_M;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BasaMApplication {
	public static void main(String[] args) {
		SpringApplication.run(BasaMApplication.class, args);
	}
}
