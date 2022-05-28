package hu.webuni.exam.logistics.service;

import hu.webuni.exam.logistics.model.Section;
import hu.webuni.exam.logistics.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Transactional
    public Section addNewSection(Section section) {
        return sectionRepository.save(section);
    }

    public boolean isSectionExistsById(long id) {
        return sectionRepository.existsById(id);
    }
}
