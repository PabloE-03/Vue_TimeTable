package es.iesjandula.reaktor.timetable_server.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer
{
	@Value("${urlCors}")
	private String urlCors;
	
	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry.addMapping("/**").allowedOrigins(urlCors)
		.allowedMethods("GET","POST","PUT","DELETE")
		.allowedHeaders("*");
	}
}
