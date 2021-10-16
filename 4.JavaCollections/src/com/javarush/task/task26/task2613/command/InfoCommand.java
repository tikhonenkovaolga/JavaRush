package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

class InfoCommand implements Command {


    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en", Locale.ENGLISH);

    @Override
    public void execute()throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> set = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        boolean money = false;
        for (CurrencyManipulator c : set) {
            if (set.isEmpty()) {
                ConsoleHelper.writeMessage(res.getString("no.money"));
                break;
            } else {
                if (c.hasMoney()) {
                    ConsoleHelper.writeMessage(c.getCurrencyCode().toUpperCase() + " - " + c.getTotalAmount());
                    money = true;
                }

            }

        }
        if (!money)ConsoleHelper.writeMessage(res.getString("no.money"));

    }
}
