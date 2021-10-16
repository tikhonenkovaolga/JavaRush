package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en", Locale.ENGLISH);

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = "";
        try {
            s = bis.readLine();
            if (s.toLowerCase().contains("exit")) {
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        } catch (IOException e) {

        }
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String currencyCode = null;
        try {
            currencyCode = bis.readLine();
            if (currencyCode.toLowerCase().contains("exit")) {
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
            if (currencyCode.length() == 3) {
                return currencyCode.toUpperCase();
            } else {
                while (currencyCode.length() != 3) {
                    writeMessage(res.getString("invalid.data"));
                    currencyCode = bis.readLine();
                }
            }
        } catch (IOException e) {

        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] strings;
        int denomination;
        int count;
        writeMessage(res.getString("choose.denomination.and.count.format"));
        while (true) {
            String denominCount = readString();
            if (denominCount.toLowerCase().contains("exit")) {
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
            strings = denominCount.split(" ");
            try {
                denomination = Integer.parseInt(strings[0]);
                count = Integer.parseInt(strings[1]);
                if (denominCount == null || denomination < 0 || count < 0 || strings.length != 2) {
                    writeMessage(res.getString("invalid.data"));

                } else break;

            } catch (Exception e) {

            }
        }

        return strings;
    }

    public static Operation askOperation() throws InterruptOperationException {
        Operation operation = null;
        int i;

        while (true) {
            writeMessage(res.getString("choose.operation"));
            String codeOperation;
            try {
                codeOperation = bis.readLine();
                if (codeOperation.toLowerCase().contains("exit")) {
                    writeMessage(res.getString("the.end"));
                    throw new InterruptOperationException();
                }
                i = Integer.parseInt(codeOperation);
                operation = operation.getAllowableOperationByOrdinal(i);
                break;
            } catch (IOException a) {
                writeMessage(res.getString("invalid.data"));

            }
        }


        return operation;
    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
