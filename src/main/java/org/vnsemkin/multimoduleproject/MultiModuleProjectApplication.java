package org.vnsemkin.multimoduleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import org.vnsemkin.module1.Module1Application;
import org.vnsemkin.module2.Module2Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class MultiModuleProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(new Class[]{Module1Application.class, Module2Application.class}, args);
        startPlayPingPong();
    }

    private static void startPlayPingPong() {
        Thread thread = new Thread(() -> {
            WebClient client = WebClient.builder().baseUrl("http://localhost:9091").build();
            String currentModule = "/module1/ping";
            while (true) {
                String responseBody = client
                    .get()
                    .uri(currentModule)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

                System.out.println(responseBody);
                assert responseBody != null;
                currentModule = responseBody.equals("pong from module1") ?
                    "/module2/ping" : "/module1/ping";
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        );

        try (ExecutorService es = Executors.newSingleThreadExecutor()) {
            es.execute(thread);
        }
    }
}
