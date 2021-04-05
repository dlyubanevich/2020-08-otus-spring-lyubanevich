package ru.otus.dlyubanevich.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dlyubanevich.domain.Summary;

public interface SummaryRepository extends JpaRepository<Summary, Long> {

}
