package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.*;
import ru.otus.dlyubanevich.dto.SummaryDto;

import java.util.List;

public interface SummaryService {

    Summary transform(Statistic statistic);
    void save(Summary summary);
    List<SummaryDto> getAllSummary();
}
