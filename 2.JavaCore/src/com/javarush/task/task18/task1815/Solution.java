package com.javarush.task.task18.task1815;

import java.util.List;
import java.util.Locale;

/* 
Таблица
*/

public class Solution {
    public class TableInterfaceWrapper implements TableInterface{
        private TableInterface tableInter;
        public TableInterfaceWrapper(TableInterface tableInterface){
            this.tableInter = tableInterface;

        }

        @Override
        public void setModel(List rows) {
            System.out.println(rows.size());
            tableInter.setModel(rows);
        }

        @Override
        public String getHeaderText() {
            return tableInter.getHeaderText().toUpperCase();
        }

        @Override
        public void setHeaderText(String newHeaderText) {
           tableInter.setHeaderText(newHeaderText);
        }
    }

    public interface TableInterface {
        void setModel(List rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);
    }

    public static void main(String[] args) {
    }
}