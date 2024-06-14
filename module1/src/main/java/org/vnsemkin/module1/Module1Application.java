package org.vnsemkin.module1;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RequiredArgsConstructor
public class Module1Application {
    public static void main(String[] args) {
        SpringApplication.run(Module1Application.class, args);
    }

    @RestController()
    @RequestMapping("/module1")
    public static class Module1PingController {
        @GetMapping("/ping")
        public String ping() {
            return "pong from module1";
        }
    }
}
