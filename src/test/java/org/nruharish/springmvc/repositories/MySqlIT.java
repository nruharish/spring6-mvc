package org.nruharish.springmvc.repositories;

import org.junit.jupiter.api.Test;
import org.nruharish.springmvc.entities.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import static org.assertj.core.api.Assertions.*;


@Testcontainers
@SpringBootTest
@ActiveProfiles("localmysql")
public class MySqlIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:9");

    /*@DynamicPropertySource
    static void mySqlProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    }
    @Autowired
    DataSource dataSource;

     */

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testListBeer(){
        List<Beer> beers = beerRepository.findAll();
        assertThat(beers.size()).isGreaterThan(0);

    }
}
