package org.nruharish.springmvc.services;

import org.junit.jupiter.api.Test;
import org.nruharish.springmvc.model.BeerCSVRecord;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class BeerCsvServiceImplTest {

    BeerCsvService beerCsvService = new BeerCsvServiceImpl();
    @Test
    void convertCSV() throws FileNotFoundException {
        File csvFile = ResourceUtils.getFile("classpath:csvdata/beers.csv");

        List<BeerCSVRecord> recs = beerCsvService.convertCSV(csvFile);
        assertThat(recs.size()).isGreaterThan(0);

    }
}