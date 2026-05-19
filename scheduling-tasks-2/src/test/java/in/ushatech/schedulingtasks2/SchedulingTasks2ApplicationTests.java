package in.ushatech.schedulingtasks2;

import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

@SpringBootTest
class SchedulingTasks2ApplicationTests {


    @MockitoSpyBean
    ScheduleTask scheduleTask;
    @Test
    void contextLoads() {
    }


    @Test
    void reportCurrentTime() {
        Awaitility.await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
            Mockito.verify(scheduleTask, Mockito.atLeast(2)).reportCurrentTime();
        });
    }
}
