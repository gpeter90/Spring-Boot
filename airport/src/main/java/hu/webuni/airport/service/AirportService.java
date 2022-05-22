package hu.webuni.airport.service;

import hu.webuni.airport.model.Airport;
import hu.webuni.airport.model.Flight;
import hu.webuni.airport.repository.AirportRepository;
import hu.webuni.airport.repository.FlightRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AirportService {

    AirportRepository airportRepository;
    FlightRepository flightRepository;

    public AirportService(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    //    @PersistenceContext
//    EntityManager entityManager;

    @Transactional
    public Airport save(Airport airport) {
        checkUniqueIata(airport.getIata(), null);
//        entityManager.persist(airport);
        return airportRepository.save(airport);
    }

    @Transactional
    public Airport update(Airport airport) throws NoSuchElementException {
        checkUniqueIata(airport.getIata(), airport.getId());
        if (airportRepository.existsById(airport.getId())) {
            return airportRepository.save(airport);
        } else {
            throw new NoSuchElementException();
        }
    }

    private void checkUniqueIata(String iata, Long id) {
        boolean forUpdate = id != null;
//        TypedQuery<Long> query = entityManager.createNamedQuery( forUpdate ? "Airport.countByIataAndIdNotIn" : "Airport.countByIata", Long.class)
//                .setParameter("iata", iata);
//        if (forUpdate) {
//            query.setParameter("id", id);
//        }
//        Long count = query
//                .getSingleResult();

        Long count = forUpdate ? airportRepository.countByIataAndIdNot(iata, id) : airportRepository.countByIata(iata);

        if (count > 0) {
            throw new NonUniqueIataException(iata);
        }
    }

    public List<Airport> findAll() {
//        return entityManager.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
        return airportRepository.findAll();
    }

    public Optional<Airport> findById(long id) {
//        return entityManager.find(Airport.class, id);
        return airportRepository.findById(id);
    }

    @Transactional
    public void delete(long id) {
//        entityManager.remove(findById(id));
        airportRepository.deleteById(id);
    }

    @Transactional
    public Flight createFlight(String flightNumber, long takeoffAirportId, long landingAirportId, LocalDateTime takeoffDateTime) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setTakeoff(airportRepository.findById(takeoffAirportId).get());
        flight.setLanding(airportRepository.findById(landingAirportId).get());
        flight.setTakeoffTime(takeoffDateTime);
        return flightRepository.save(flight);
    }

    public List<Flight> findFlightsByExample(Flight example) {

        long id = example.getId();
        String flightNumber = example.getFlightNumber();
        Airport takeoff = example.getTakeoff();
        String takeoffIata = null;

        if (takeoff != null) {
            takeoffIata = takeoff.getIata();
        }

        LocalDateTime takeoffTime = example.getTakeoffTime();

        Specification<Flight> spec = Specification.where(null);

        if (id > 0) {
            spec = spec.and(FlightSpecifications.hasId(id));
        }

        if(StringUtils.hasText(flightNumber)) {
            spec = spec.and(FlightSpecifications.hasFlightNumber(flightNumber));
        }

        if(StringUtils.hasText(takeoffIata)) {
            spec = spec.and(FlightSpecifications.hasTakeoffIata(takeoffIata));
        }

        if (takeoffTime != null) {
            spec = spec.and(FlightSpecifications.hasTakeoffTime(takeoffTime));
        }

        return flightRepository.findAll(spec, Sort.by("id"));
    }


}
