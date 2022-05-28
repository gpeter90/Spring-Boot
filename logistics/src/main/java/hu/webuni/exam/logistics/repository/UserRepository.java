package hu.webuni.exam.logistics.repository;

import hu.webuni.exam.logistics.model.LogisticsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<LogisticsUser, String> {
}
