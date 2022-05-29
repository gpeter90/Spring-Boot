package hu.webuni.exam.logistics.web;

import static org.assertj.core.api.Assertions.assertThat;

import hu.webuni.exam.logistics.model.Address;
import hu.webuni.exam.logistics.model.MileStone;
import hu.webuni.exam.logistics.model.Section;
import hu.webuni.exam.logistics.model.TransportPlan;
import hu.webuni.exam.logistics.repository.TransportPlanRepository;
import hu.webuni.exam.logistics.service.TransportPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@SpringBootTest
@AutoConfigureTestDatabase
public class TransportPlanControllerIT {

    private static final String BASE_URI = "/api/transportPlans";

    @Autowired
    TransportPlanService transportPlanService;

    @Autowired
    TransportPlanRepository transportPlanRepository;

    @Autowired
    WebTestClient webTestClient;

    String username = "TransportManager";
    String pass = "pass";

    @BeforeEach
    public void init() {
        String fromStr = "2022-06-29 08:30:00";
        String toStr = "2022-06-29 09:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, formatter);
        LocalDateTime toDateTime = LocalDateTime.parse(toStr, formatter);

        if(!transportPlanRepository.existsById(1L)) {
            TransportPlan transportPlan =
                    new TransportPlan(1, 15000,
                            new Section(1, 1,
                                    new MileStone(1, new Address(1, "HU", "Budapest", "Maria utca", "1104", 21, "N52", "W42")
                                            , fromDateTime),
                                    new MileStone(2, new Address(2, "HU", "Kecskemet", "Fo utca", "2345", 21, "N56", "W72")
                                    , toDateTime)));
            transportPlanRepository.save(transportPlan);
        }
    }

    @Test
    void testAddingDelayToExistingTranportPlan() throws Exception {

        String fromStr = "2022-06-29 08:30:00";
        String toStr = "2022-06-29 09:50:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, formatter);
        LocalDateTime toDateTime = LocalDateTime.parse(toStr, formatter);

        TransportPlan newTransportPlan =
                new TransportPlan(1, 15000,
                        new Section(1, 1,
                                new MileStone(1, new Address(1, "HU", "Budapest", "Maria utca", "1104", 21, "N52", "W42")
                                        , fromDateTime),
                                new MileStone(2, new Address(2, "HU", "Kecskemet", "Fo utca", "2345", 21, "N56", "W72")
                                        , toDateTime)));

        List<TransportPlan> transportPlansBefore = transportPlanRepository.findAll();

        modifyTransportPlan(newTransportPlan).expectStatus().isOk();

        List<TransportPlan> transportPlansAfter = transportPlanRepository.findAll();

        assertThat(transportPlansAfter).hasSameSizeAs(transportPlansBefore);
        assertThat(transportPlansAfter.get(transportPlansAfter.size() - 1))
                .usingRecursiveComparison()
                .isEqualTo(newTransportPlan);

    }

    private ResponseSpec modifyTransportPlan(TransportPlan newTransportPlan) {
        String path = BASE_URI + "/" + newTransportPlan.getId() + "/delay";
        return webTestClient
                .post()
                .uri(path)
                .headers(headers -> headers.setBasicAuth(username, pass))
                .bodyValue(newTransportPlan)
                .exchange();
    }



}
