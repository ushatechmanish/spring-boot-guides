package in.ushatech.schedulingtasks2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class SchedulingTasks2Application {

    public static void main(String[] args) {
        SpringApplication.run(SchedulingTasks2Application.class, args);
    }


}
