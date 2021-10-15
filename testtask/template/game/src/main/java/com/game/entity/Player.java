package com.game.entity;

import com.game.controller.PlayerOrder;


import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "player")
public class Player implements Serializable {
    public Player() {
    }

    private static final long serialVersionUID = 1L;

    private static final int MAX_LENGTH_OF_NAME = 12;
    private static final int MAX_LENGTH_OF_TITLE = 30;
    private final static int MIN_EXPERIENCE = 0;
    private final static int MAX_EXPERIENCE = 10000;
    private final static String START = "01.01.2000";
    private final static String FINISH = "31.12.3000";

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private Date startDate;
    private Date finishDate;

    {
        try {
            startDate = dateFormat.parse(START);
            finishDate = dateFormat.parse(FINISH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "race")
    private Race race;

    @Column(name = "profession")
    private Profession profession;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "level")
    private Integer level;

    @Column(name = "untilNextLevel")
    private Integer untilNextLevel;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "banned")
    private Boolean banned;


    private Long after;
    private Long before;
    private Integer minLevel;
    private Integer maxLevel;
    private PlayerOrder order;
    private Integer pageNumber;

    public Long getAfter() {
        return after;
    }

    public Long getBefore() {
        return before;
    }

    public Integer getMinExperience() {
        return MIN_EXPERIENCE;
    }

    public Integer getMaxExperience() {
        return MAX_EXPERIENCE;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public PlayerOrder getOrder() {
        return order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setOrder(PlayerOrder order) {
        this.order = order;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private Integer pageSize;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name.length() >= MAX_LENGTH_OF_NAME || name.isEmpty()) {
            this.name = null;
        } else this.name = name;
    }

    public void setTitle(String title) {
        if (title.length() >= MAX_LENGTH_OF_TITLE) {
            this.title = null;
        } else this.title = title;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setExperience(Integer experience) {
        if (experience < MIN_EXPERIENCE || experience > MAX_EXPERIENCE) {
            this.experience = null;
        } else this.experience = experience;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public void setBirthday(Date birthday) {
        if (birthday.getTime() < 0 || birthday.getTime() < startDate.getTime() || birthday.getTime() > finishDate.getTime()) {
            this.birthday = null;
        } else this.birthday = birthday;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }


}
