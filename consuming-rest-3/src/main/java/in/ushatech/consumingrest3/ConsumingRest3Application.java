package in.ushatech.consumingrest3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class ConsumingRest3Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumingRest3Application.class);
    public static void main(String[] args) {
        SpringApplication.run(ConsumingRest3Application.class, args);
    }


    @Bean
    @Profile("!test")
    public ApplicationRunner run(RestClient.Builder clientBuilder) {
        RestClient restClient = clientBuilder.build();
        return args -> {
            Quote quote = restClient.get().uri("http://localhost:8080/api/random")
                    .retrieve()
                    .body(Quote.class);
            LOGGER.info(quote.toString());
        };
    }
}
