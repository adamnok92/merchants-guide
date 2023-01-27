package org.adamnok.merchant;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world");
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(ConsoleApp.class)
            .bannerMode(Banner.Mode.OFF)
            .logStartupInfo(false)
            .run(args);
    }
}
