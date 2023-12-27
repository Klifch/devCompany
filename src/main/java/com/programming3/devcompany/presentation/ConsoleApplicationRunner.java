package com.programming3.devcompany.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ConsoleApplicationRunner implements CommandLineRunner {

    private final ConsolePresenter consolePresenter;

    @Autowired
    public ConsoleApplicationRunner(ConsolePresenter consolePresenter) {
        this.consolePresenter = consolePresenter;
    }

    @Override
    public void run(String... args) throws Exception {
        consolePresenter.run();
    }
}
