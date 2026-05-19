package in.ushatech.schedulingtasks2;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTask {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private  static  final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        LOGGER.info("The current time is {}",sdf.format(new Date()));
    }
}
