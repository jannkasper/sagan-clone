package sagan.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SiteProperties.class)
public class SaganSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaganSiteApplication.class, args);
	}

}
