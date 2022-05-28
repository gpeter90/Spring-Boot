package hu.webuni.exam.logistics.service;

import hu.webuni.exam.logistics.model.MileStone;
import hu.webuni.exam.logistics.repository.MileStoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MileStoneService {

    @Autowired
    MileStoneRepository mileStoneRepository;

    public Optional<MileStone> findMilestoneById(long id) {
        return mileStoneRepository.findById(id);
    }

    @Transactional
    public MileStone updateMilestone(MileStone mileStone) {
        return mileStoneRepository.save(mileStone);
    }
}
