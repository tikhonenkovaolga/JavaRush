package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new TreeMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }



    public int getTotalAmount() {
        int totalAmount = 0;
        for (Integer denomination : denominations.keySet()) {
            totalAmount += denomination * denominations.get(denomination);
        }
        return totalAmount;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }


    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException, ConcurrentModificationException {
        int totalAmount = getTotalAmount();
        if (expectedAmount == 0) return null;
        if (expectedAmount > totalAmount) {
            throw new NotEnoughMoneyException();
        }

        TreeMap<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());
        ArrayList<Integer> denominationList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            denominationList.add(entry.getKey());
        }
        denominationList.sort(Collections.reverseOrder());

        int diff = expectedAmount;

        for (int i = 0; i < denominationList.size(); i++) {
            int denom = denominationList.get(i);
            if (denom == diff) {
                diff -= denom;
                containsOrNot(denom, result);
                emptyOrNot(denom, denominations.get(denom));
                break;
            }
            if (diff > denom) {
                containsOrNot(denom, result);
                boolean isEmpty = emptyOrNot(denom, denominations.get(denom));
                totalAmount -= denom;
                diff -= denom;
                if (diff > totalAmount) {
                    throw new NotEnoughMoneyException();
                }
                if (diff == 0) break;
                if (!isEmpty & denom <= diff)i--;

            }


        }
        if (diff > 0){
            throw new NotEnoughMoneyException();
        }


        return result;
    }

    public void containsOrNot (int key, Map<Integer, Integer> map){
        if (!map.containsKey(key)) {
            map.put(key, 1);

        } else {
            int mapValue = map.get(key);
            map.put(key, mapValue + 1);
        }
    }

    public boolean emptyOrNot(int key, int value){
        if (value == 1){
            denominations.remove(key, value);
            return true;
        }
        else denominations.put(key, value - 1);
        return false;
    }



}

