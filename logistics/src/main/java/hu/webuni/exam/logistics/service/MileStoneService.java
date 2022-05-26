package hu.webuni.exam.logistics.service;

import hu.webuni.exam.logistics.model.MileStone;
import hu.webuni.exam.logistics.repository.MileStoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MileStoneService {

    @Autowired
    MileStoneRepository mileStoneRepository;

    @Transactional
    public MileStone addNewMilestone(MileStone mileStone) {
        return mileStoneRepository.save(mileStone);
    }
}
