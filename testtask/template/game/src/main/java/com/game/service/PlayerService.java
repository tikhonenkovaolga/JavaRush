package com.game.service;


import com.game.entity.Player;

import java.util.List;

public interface PlayerService {
    void save(Player player);
    List<Player> findAll();
    Player getById (long id);
    void delete(long id);
 //   List<Player> paramPlayers(Player player);
  //  int countParamPlayers(Player player);
    boolean isIdOk (long id);
}
