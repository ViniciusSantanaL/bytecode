package br.com.razes.bytecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.io.IOException;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableFeignClients
public class BytecodeApplication {

    public static void main(String[] args) throws IOException { SpringApplication.run(BytecodeApplication.class, args); }

}
