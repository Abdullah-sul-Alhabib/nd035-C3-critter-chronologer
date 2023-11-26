package com.udacity.jdnd.course3.critter;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Launches the Spring application. Unmodified from starter code.
 */
@SpringBootApplication
public class CritterApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CritterApplication.class, args);
    }

    /**
     * Model mapper model mapper.
     *
     * @return the model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
