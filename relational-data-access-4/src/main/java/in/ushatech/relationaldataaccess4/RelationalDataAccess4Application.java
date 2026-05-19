package in.ushatech.relationaldataaccess4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class RelationalDataAccess4Application  implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(RelationalDataAccess4Application.class);
    private final JdbcTemplate jdbcTemplate;

    public RelationalDataAccess4Application(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(RelationalDataAccess4Application.class, args);
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello World";
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Relational Data Access Application started");
        jdbcTemplate.execute("DROP TABLE IF EXISTS customers");
        jdbcTemplate.execute(
                """
CREATE TABLE customers (id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))
"""
        );
        List<String[]> splitUpNames = Stream.of("manish chauhan", "vineet chauhan", "amit kakar")
                .map(name -> name.split(" "))
                .toList();



        splitUpNames.forEach(name -> log.info("Inserting customer record for {} {}", name[0], name[1]));


        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?, ?)",new ArrayList<>(splitUpNames));// it requires List<Object>

        log.info("Querying for customer records");

        jdbcTemplate.query("select id,first_name,last_name FROM customers where last_name=?"
                ,(rs,rowNum)-> new Customer(rs.getLong("id"),rs.getString("first_name"),rs.getString("last_name"))
        ,"chauhan").forEach( result -> log.info(result.toString()));


        log.info("Relational Data Access Application finished");
        /**
         * Internally this happens and maps naturally to placeHolders
         * PreparedStatement ps = connection.prepareStatement(sql);
         *
         * for(Object[] row : batchArgs) {
         *     ps.setObject(1, row[0]);
         *     ps.setObject(2, row[1]);
         *     ps.setObject(3, row[2]);
         *     ps.addBatch();
         * }
         * */
    }
}
