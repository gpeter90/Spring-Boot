package hu.webuni.exam.logistics.repository;

import hu.webuni.exam.logistics.model.MileStone;
import hu.webuni.exam.logistics.model.TransportPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MileStoneRepository extends JpaRepository<MileStone, Long>, JpaSpecificationExecutor<MileStone> {
}
