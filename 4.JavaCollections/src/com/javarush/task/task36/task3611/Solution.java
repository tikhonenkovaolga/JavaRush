package com.javarush.task.task36.task3611;

import java.util.*;

/* 
Сколько у человека потенциальных друзей?
*/

public class Solution {
    private boolean[][] humanRelationships;

    Set<Integer> allFriends = new TreeSet<>();


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.humanRelationships = generateRelationships();


        Set<Integer> allFriendsAndPotentialFriends = solution.getAllFriendsAndPotentialFriends(4, 3);
        System.out.println(allFriendsAndPotentialFriends);                              // Expected: [0, 1, 2, 3, 5, 7]
        Set<Integer> potentialFriends = solution.removeFriendsFromSet(allFriendsAndPotentialFriends, 4);
        System.out.println(potentialFriends);                                           // Expected: [2, 5, 7]

    }

    public Set<Integer> getAllFriendsAndPotentialFriends(int index, int deep) {

        allFriends.addAll(getFriends(index));

        Queue<Integer> qFriends = new ArrayDeque<>();
        qFriends.addAll(allFriends);
            while (deep > 1){
                int qSize = qFriends.size();
                for (int i = 0; i < qSize; i++) {
                    int friend = qFriends.poll();
                    Set <Integer> frendsOfFrend = getFriends(friend);
                    for (Integer integer : frendsOfFrend) {
                        allFriends.add(integer);
                        if (!qFriends.contains(integer)){
                            qFriends.add(integer);
                        }
                    }
                }
                deep--;
            }

            allFriends.remove(Integer.valueOf(index));

        return allFriends;

    }

    public Set<Integer> getFriends (int index){
        Set<Integer> friends = new HashSet<>();
        for (int i = 0; i < index; i++) {
            if (humanRelationships[index][i]) {
                friends.add(i);
            }
        }
        for (int j = index + 1; j < humanRelationships.length; j++) {
            if (humanRelationships[j][index]) {
                friends.add(j);
            }
        }
        return friends;
    }


    // Remove from the set the people with whom you already have a relationship
    public Set<Integer> removeFriendsFromSet(Set<Integer> set, int index) {
        for (int i = 0; i < humanRelationships.length; i++) {
            if ((i < index) && (index < humanRelationships.length) && humanRelationships[index][i]) {
                set.remove(i);
            } else if ((i > index) && humanRelationships[i][index]) {
                set.remove(i);
            }
        }
        return set;
    }

    // Return test data
    private static boolean[][] generateRelationships() {
        return new boolean[][]{
                {true},                                                                 //0
                {true, true},                                                           //1
                {false, true, true},                                                    //2
                {false, false, false, true},                                            //3
                {true, true, false, true, true},                                        //4
                {true, false, true, false, false, true},                                //5
                {false, false, false, false, false, true, true},                        //6
                {false, false, false, true, false, false, false, true}                  //7
        };
    }


}