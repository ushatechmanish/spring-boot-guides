package in.ushatech.restservice1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final AtomicLong counter = new AtomicLong();
    private static final String template = "Hello, %s!";
    @GetMapping("/greeting")
    public Greeting greet(@RequestParam(defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), template.formatted(name));
    }
}
