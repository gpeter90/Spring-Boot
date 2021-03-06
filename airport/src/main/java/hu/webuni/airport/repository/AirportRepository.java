package hu.webuni.airport.repository;

import hu.webuni.airport.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    Long countByIata(String iata);

    Long countByIataAndIdNot(String iata, Long id);
}
