package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Serializable, Cloneable {

    Entry<String> root;   //корень

    private ArrayList<Entry> listChild = new ArrayList<>();

    public CustomTree() {
        this.root = new Entry<>("0");
        listChild.add(0, root);
    }


    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override


    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    int n = 0;

    @Override
    public boolean add(String element) {
        Entry curElem;
        Entry child = new Entry(element);
        for (int i = 0; i < listChild.size(); i++) {
            curElem = listChild.get(i);
            if (curElem.isAvailableToAddChildren()) {
                if (curElem.leftChild == null) {
                    curElem.leftChild = child;

                } else curElem.rightChild = child;
                child.parent = curElem;
                curElem.checkChildren();
                listChild.add(child);
                n++;
                return true;
            }

        }


        return false;
    }

    @Override
    public int size() {
        return n;

    }

    public String getParent(String s) {

        for (int i = 0; i < listChild.size(); i++) {
            Entry elementList = listChild.get(i);
            if (s.equals(elementList.elementName)) {
                return elementList.parent.elementName;
            }
            if (s.equals(root.elementName)) {
                return root.elementName;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {

            if (!(o instanceof String)){ //элемент не является строкой
                throw new UnsupportedOperationException();
            }
            Entry remElem;
            remElem = getElementByName(o.toString());
            Entry parentRemElem;
            parentRemElem = remElem.parent;

                    if (remElem.leftChild == null && remElem.rightChild == null) {
                        if (parentRemElem.leftChild == remElem) {
                            parentRemElem.leftChild = null;
                            parentRemElem.availableToAddLeftChildren = true;
                        } else {
                            parentRemElem.rightChild = null;
                            parentRemElem.availableToAddRightChildren = true;
                        }

                    } else {
                        if (remElem.leftChild != null) {
                            remove(remElem.leftChild.elementName);
                        }
                        if (remElem.rightChild != null) {
                            remove(remElem.rightChild.elementName);
                        }
                    }
                    listChild.remove(remElem);
                    n--;
        return true;
    }

    public Entry getElementByName(String elementName) {
        Entry element = null;
        for (Entry e : listChild) {
            if (e.elementName.equals(elementName))
                element = e;
        }
        return element;
    }

    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }


    static class Entry<T> implements Serializable {   //T в данном случае воспринимается как Object
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddRightChildren = true;
            availableToAddLeftChildren = true;
        }

        void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;// дизъюнкция полей
        }


    }


}
