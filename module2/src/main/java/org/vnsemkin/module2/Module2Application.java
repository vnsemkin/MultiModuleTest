package org.vnsemkin.module2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Module2Application {
    public static void main(String[] args) {
        SpringApplication.run(Module2Application.class, args);
    }

    @RestController
    @RequestMapping("/module2")
    public static class Module2PingController {
        @GetMapping("/ping")
        public String ping() {
            return "pong from module2";
        }
    }
}
