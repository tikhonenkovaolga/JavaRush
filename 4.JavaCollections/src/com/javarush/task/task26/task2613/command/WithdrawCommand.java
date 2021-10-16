package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int expectedAmount;
        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                String s = ConsoleHelper.readString();
                if (s == null) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                } else {
                    try {
                        expectedAmount = Integer.parseInt(s);
                        if ( expectedAmount > 0 && currencyManipulator.isAmountAvailable(expectedAmount)) {
                            Map<Integer, Integer> result = currencyManipulator.withdrawAmount(expectedAmount);
                            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),entry.getKey(), entry.getValue()));
                            }
                            break;
                        }
                        else ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    } catch (NotEnoughMoneyException a) {
                        ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                    }
                }
            } catch (NullPointerException | NumberFormatException |ConcurrentModificationException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }


        }
    }
}





