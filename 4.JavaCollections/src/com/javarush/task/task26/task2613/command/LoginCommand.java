package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.ConsoleHelper.readString;

 class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en", Locale.ENGLISH);


    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String cardNumber;
        String pinCode;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            cardNumber = readString();
            pinCode = readString();
            if (cardNumber != null && cardNumber.matches("\\d{12}") && pinCode != null && pinCode.matches("\\d{4}")) {
                try {
                    if (validCreditCards.containsKey(cardNumber) && validCreditCards.getString(cardNumber).equals(pinCode)) {
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));
                        break;

                    } else {
                        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
                    }

                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }

        }


    }
}


