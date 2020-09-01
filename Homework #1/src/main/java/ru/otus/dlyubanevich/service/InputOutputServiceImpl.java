package ru.otus.dlyubanevich.service;

import java.io.InputStream;
import java.io.PrintStream;

public class InputOutputServiceImpl implements InputOutputService {

   private InputStream inputStream;
   private PrintStream printStream;

    public InputOutputServiceImpl() {
        this.inputStream = System.in;
        this.printStream = System.out;
    }

    @Override
    public void print(String textLine) {
        printStream.println(textLine);
    }

    @Override
    public void scan() {

    }

}
