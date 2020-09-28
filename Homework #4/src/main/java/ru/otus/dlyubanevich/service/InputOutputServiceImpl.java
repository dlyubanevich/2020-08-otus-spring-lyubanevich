package ru.otus.dlyubanevich.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {

   private final Scanner scanner;
   private final PrintStream printStream;

    public InputOutputServiceImpl(@Value("#{T(java.lang.System).in}") InputStream inputStream,
                                  @Value("#{T(java.lang.System).out}") PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    @Override
    public void print(String message) {
        printStream.print(message);
    }

    @Override
    public void println(String message) {
        printStream.println(message);
    }

    @Override
    public String scan() {
        return scanner.nextLine();
    }

}
