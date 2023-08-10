package ru.fc2.figure.printer;

public class ConsolePrinter implements Printer {

    @Override
    public void print(String content) {
        System.out.println(content);
    }
}
