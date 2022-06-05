package br.com.razes.bytecode.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;

public class FileHandlerUtils {

    public static Set<String> getAllSymbolsAvailable() throws IOException {

        File resource = new ClassPathResource("active-rates-coin.txt").getFile();
        String symbols = new String(Files.readAllBytes(resource.toPath()));
        symbols = symbols.replaceAll("\\s","");

        return Set.of(symbols.split(","));
    }

}
