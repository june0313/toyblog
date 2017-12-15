package net.jun.toyblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EntityScan(
		basePackageClasses = {Jsr310JpaConverters.class},
		basePackages = {"net.jun.toyblog.domain"})
@EnableJpaAuditing
@SpringBootApplication
public class ToyBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToyBlogApplication.class, args);
	}
}
