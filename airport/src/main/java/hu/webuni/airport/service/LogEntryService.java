package hu.webuni.airport.service;

import hu.webuni.airport.model.LogEntry;
import hu.webuni.airport.repository.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogEntryService {

    @Autowired
    LogEntryRepository logEntryRepository;

    public void createLog(String description) {
        logEntryRepository.save(new LogEntry(description, SecurityContextHolder.getContext().getAuthentication().getName()));
    }
}
