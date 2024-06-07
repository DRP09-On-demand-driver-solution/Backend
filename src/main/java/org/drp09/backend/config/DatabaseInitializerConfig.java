package org.drp09.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Configuration
public class DatabaseInitializerConfig {

  @Autowired
  private ResourceLoader resourceLoader;

  @Bean
  public CommandLineRunner initializeDatabase(JdbcTemplate jdbcTemplate) {
    System.out.println("Initializing database...");
    return args -> {
      Resource resource = resourceLoader.getResource("classpath:init.sql");
      String script = new String(Files.readAllBytes(Paths.get(resource.getURI())));
      Stream.of(script.split(";"))
              .filter(line -> !line.trim().isEmpty())
              .forEach(jdbcTemplate::execute);
    };
  }
}