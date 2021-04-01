package ru.otus.dlyubanevich.batch.writer;

import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import ru.otus.dlyubanevich.domain.Summary;
import ru.otus.dlyubanevich.service.SummaryService;

import java.util.List;

@Component
@AllArgsConstructor
public class SummaryItemWriter implements ItemWriter<Summary> {

    private final SummaryService summaryService;

    @Override
    public void write(List<? extends Summary> items) {
        items.forEach(this::save);
    }

    public void save(Summary summary){
        summaryService.save(summary);
    }
}
