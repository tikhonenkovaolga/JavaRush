package com.game.service;

import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@RequestMapping("/players")
public class PlayerServiceImplement implements PlayerService {
    private static final int MAX_SIZE_OF_NAME = 12;
    private static final int MAX_SIZE_OF_TITLE = 30;
    private static final int MIN_LEVEL = 0;
    private static final int MAX_LEVEL = 10000000;
    private static final String FIRST = "01.01.2000";
    private static final String LAST = "31.12.3000";
    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static Date FIRST_DATE = null;
    private static Date LAST_DATE = null;

    static {
        try {
            FIRST_DATE = format.parse(FIRST);
            LAST_DATE = format.parse(LAST);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void save(Player player) {
        if (player.getName().isEmpty() || player.getName().length() > MAX_SIZE_OF_NAME) {
            player.setName(null);
        }
        if (player.getTitle().length() >= MAX_SIZE_OF_TITLE) {
            player.setTitle(null);
        }
        if (player.getLevel() <= MIN_LEVEL || player.getLevel() > MAX_LEVEL) {
            player.setLevel(null);
        }
        if (player.getBirthday().getTime() < 0 || player.getBirthday().getTime() <= FIRST_DATE.getTime()
                || player.getBirthday().getTime() >= LAST_DATE.getTime()){
            player.setBirthday(null);
        }

        playerRepository.save(player);
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player getById(long id) {
        return playerRepository.getOne(id);
    }


    @Override
    public void delete(long id) {
        playerRepository.deleteById(id);
    }


    @Override
    public boolean isIdOk(long id) {
        return id >= 0 && Character.isDigit((char) id) && id % 1 == 0;
    }
}
