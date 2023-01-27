package org.adamnok.merchant;

import org.adamnok.merchant.model.Interpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {
    @Autowired
    private Interpreter interpreter;

    @Override
    public void run(String... args) throws Exception {
        interpreter.startInteractive();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(ConsoleApp.class)
            .bannerMode(Banner.Mode.OFF)
            .logStartupInfo(false)
            .run(args);
    }
}
