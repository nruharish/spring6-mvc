package org.nruharish.springmvc.mappers;

import org.mapstruct.Mapper;
import org.nruharish.springmvc.entities.Beer;
import org.nruharish.springmvc.model.BeerDTO;

@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDTO (Beer beer);

}
