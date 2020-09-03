package ru.otus.dlyubanevich.domain;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Task {

    @CsvBindByPosition(position = 0)
    private String question;

    @CsvBindAndSplitByPosition(position = 1, elementType = String.class, collectionType = ArrayList.class, splitOn = "\\|")
    private List<String> options;

    @CsvBindByPosition(position = 2)
    private int answer;

}
