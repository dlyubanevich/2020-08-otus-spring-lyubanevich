package ru.otus.dlyubanevich.service;

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {

   private final Scanner scanner;
   private final PrintStream printStream;

    public InputOutputServiceImpl() {
        this.scanner = new Scanner(System.in);
        this.printStream = System.out;
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
