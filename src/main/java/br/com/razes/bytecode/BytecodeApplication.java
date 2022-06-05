package br.com.razes.bytecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableFeignClients
@EnableScheduling
@EnableAsync
public class BytecodeApplication {

    public static void main(String[] args) throws IOException { SpringApplication.run(BytecodeApplication.class, args); }

}
