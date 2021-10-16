package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private List<RoadObject> items = new ArrayList<>();
    private static final int PLAYER_CAR_DISTANCE = 12;
    private int passedCarsCount = 0;

    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject roadObject = createRoadObject(type, x, y);
        if (roadObject != null & isRoadSpaceFree(roadObject)) {
            items.add(roadObject);
        }
    }


    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        if (type == RoadObjectType.THORN) {
            return new Thorn(x, y);
        }
        if (type == RoadObjectType.DRUNK_CAR) {
            return new MovingCar(x, y);
        } else return new Car(type, x, y);
    }

    public void draw(Game game) {
        for (RoadObject r : items) {
            r.draw(game);
        }
    }

    public void move(int boost) {
        for (RoadObject r : items) {
            r.move(boost + r.speed, items);
        }
        deletePassedItems();
    }

    private boolean isThornExists() {
        boolean isThorn = false;
        for (RoadObject r : items) {
            if (r.type.equals(RoadObjectType.THORN)) {

                isThorn = true;
            }
        }
        return isThorn;
    }

    private void generateThorn(Game game) {
        int countOfThorn = game.getRandomNumber(100);
        if (countOfThorn < 10 & !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void deletePassedItems() {
        Iterator iterator = items.iterator();
        while (iterator.hasNext()) {
            RoadObject item = (RoadObject) iterator.next();
            if (item.y >= RacerGame.HEIGHT) {
                if (!(item instanceof Thorn)) passedCarsCount++;
                iterator.remove();
            }
        }
    }


    public boolean checkCrush(PlayerCar playerCar) {
        boolean result = false;
        for (RoadObject r : items) {
            if (r.isCollision(playerCar)) {
                result = true;
            }
        }
        return result;
    }

    private void generateRegularCar(Game game) {
        int carTypeNumber = game.getRandomNumber(4);
        if (game.getRandomNumber(100) < 30) {
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        boolean result = true;
        for (RoadObject g : items) {
            if (g.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
                result = false;
            }
        }
        return result;
    }

    private boolean isMovingCarExists() {
        boolean result = false;
        for (RoadObject o : items) {
            if (o.type == RoadObjectType.DRUNK_CAR) {
                result = true;
            }
        }

        return result;
    }

    private void generateMovingCar(Game game) {
        if (game.getRandomNumber(100) < 10 & !isMovingCarExists()) {
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }
}
