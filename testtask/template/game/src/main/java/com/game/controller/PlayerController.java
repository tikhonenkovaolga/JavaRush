package com.game.controller;

import com.game.entity.Player;
import com.game.service.PlayerService;
import com.game.service.PlayerServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/players") ///???

public class PlayerController {
    private PlayerServiceImplement playerServiceImplement;

    @Autowired
    private PlayerService playerService;

    @PostMapping(value = "")//????
    public ResponseEntity<Player> save(@RequestBody Player player) {

        if (player.getName() == null || player.getExperience() == null || player.getBirthday() == null || player.getTitle() == null
                || player.getProfession() == null || player.getRace() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if (player.getBanned() == null) player.setBanned(false);
        player.setLevel((int) ((Math.sqrt(2500 + 200 * player.getExperience()) - 50) / 100));
        player.setUntilNextLevel(50 * (player.getLevel() + 1) * (player.getLevel() + 2) - player.getExperience());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/players")
    public ResponseEntity<List<Player>> findAll() {
        final List<Player> players = playerService.findAll();
        return players != null && !players.isEmpty() ? new ResponseEntity<>(players, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/players/{id}")
    public ResponseEntity<Player> getById(@PathVariable(name = "id") long id) {
        if (!playerService.isIdOk(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player = this.playerService.getById(id);

        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PutMapping(value = "rest/players/{id}")///???
    public ResponseEntity<Player> update(@PathVariable(name = "id") long id, @RequestBody Player player) {

        if (!playerService.isIdOk(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (playerService.getById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Player oldPlayer = this.playerService.getById(id);
        if (oldPlayer.getLevel() != null) {
            player.setLevel((int) ((Math.sqrt(2500 + 200 * player.getExperience()) - 50) / 100));
        }
        if (oldPlayer.getUntilNextLevel() != null) {
            player.setUntilNextLevel(50 * (player.getLevel() + 1) * (player.getLevel() + 2) - player.getExperience());
        }
        if (oldPlayer.getBanned() == null) player.setBanned(null);
        if (oldPlayer.getName() == null) player.setName(null);
        if (oldPlayer.getTitle() == null) player.setTitle(null);
        if (oldPlayer.getRace() == null) player.setRace(null);
        if (oldPlayer.getProfession() == null) player.setProfession(null);
        if (oldPlayer.getBirthday() == null) player.setBirthday(null);
        if (oldPlayer.getExperience() == null) player.setExperience(null);

        playerService.save(player);

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @DeleteMapping(value = "rest/players/{id}")
    public ResponseEntity<Player> delete(@PathVariable(name = "id") long id) {
        if (!playerService.isIdOk(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (playerService.getById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "rest/players")
    public ResponseEntity<List<Player>> paramPlayers(@RequestBody Player player) {

        if (player.getOrder() == null){
            player.setOrder(PlayerOrder.ID);
        }
        if (player.getPageNumber() == null){
            player.setPageNumber(0);
        }
        if (player.getPageSize() == null){
            player.setPageSize(3);
        }
        final List<Player> filterPlayers = new ArrayList<>();

        List<Player> allPlayers = playerService.findAll();
        for (Player currentPlayer : allPlayers) {
            if (player.getName() != null) {
                if (!currentPlayer.getName().contains(player.getName())) {
                    break;
                }
            }
            if (player.getTitle() != null) {
                if (!currentPlayer.getTitle().contains(player.getTitle())) {
                    break;
                }
            }
            if (player.getRace() != null) {
                if (!currentPlayer.getRace().equals(player.getRace())) {
                    break;
                }
            }
            if (player.getProfession() != null) {
                if (!currentPlayer.getProfession().equals(player.getProfession())) {
                    break;
                }
            }
            if (player.getAfter() != null) {
                if (!currentPlayer.getAfter().equals(player.getAfter())) {
                    break;
                }
            }
            if (player.getBefore() != null) {
                if (!currentPlayer.getBefore().equals(player.getBefore())) {
                    break;
                }
            }
            if (player.getBanned() != null) {
                if (!currentPlayer.getBanned().equals(player.getBanned())) {
                    break;
                }
            }
            if (player.getMinExperience() != null) {
                if (!currentPlayer.getMinExperience().equals(player.getMinExperience())) {
                    break;
                }
            }
            if (player.getMaxExperience() != null) {
                if (!currentPlayer.getMaxExperience().equals(player.getMaxExperience())) {
                    break;
                }
            }
            if (player.getMinLevel() != null) {
                if (!currentPlayer.getMinLevel().equals(player.getMinLevel())) {
                    break;
                }
            }
            if (player.getMaxLevel() != null) {
                if (!currentPlayer.getMaxLevel().equals(player.getMaxLevel())) {
                    break;
                }
            }
            if (player.getOrder() != null) {
                if (!currentPlayer.getOrder().equals(player.getOrder())) {
                    break;
                }
            }
            if (player.getPageNumber() != null) {
                if (!currentPlayer.getPageNumber().equals(player.getPageNumber())) {
                    break;
                }
            }
            if (player.getPageSize() != null) {
                if (!currentPlayer.getPageSize().equals(player.getPageSize())) {
                    break;
                }
            }

            filterPlayers.add(currentPlayer);
        }

        return filterPlayers != null && !filterPlayers.isEmpty() ? new ResponseEntity<>(filterPlayers, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "rest/players/count")
    public ResponseEntity<Integer> countParamPlayers(@RequestBody Player player) {
        final Integer countOfFilter = paramPlayers(player).getBody().size();
        return new ResponseEntity<>(countOfFilter, HttpStatus.OK);
    }

}
