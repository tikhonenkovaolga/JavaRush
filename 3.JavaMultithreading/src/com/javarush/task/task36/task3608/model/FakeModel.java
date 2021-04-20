package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model {
    private ModelData modelData = new ModelData();
    @Override
    public ModelData getModelData() {
        return modelData;
    }

    public void loadUsers(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("Petr", 1, 2));
        userList.add(new User("Anna", 2, 1));
        modelData.setUsers(userList);
    }

    @Override
    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long id) {
        throw new UnsupportedOperationException();
    }
    public  void changeUserData(String name, long id, int level){
        throw new UnsupportedOperationException();
    }

}
