package com;


import com.game.entity.Player;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class PlayerDemoApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("game/config/AppConfig.java");
        Player player = (Player) applicationContext.getBean("player");
        System.out.println(player);

    }
}
