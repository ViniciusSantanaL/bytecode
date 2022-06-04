package br.com.razes.bytecode.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileHandlerUtils {

    public static List<String> getAllSymbolsAvailable() throws IOException {

        File resource = new ClassPathResource("active-rates-coin.txt").getFile();
        String symbols = new String(Files.readAllBytes(resource.toPath()));
        symbols = symbols.replaceAll("\\s","");

        return List.of(symbols.split(","));
    }

}
