package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {

        private static AdvertisementStorage storage;
        private final List<Advertisement> videos = new ArrayList<>();


        private AdvertisementStorage(){
            Object someContent = new Object();
            videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
            videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
            videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60)); //10 min
            videos.add(new Advertisement(someContent, "Forth Video", 600, 4, 9 * 60));//
            videos.add(new Advertisement(someContent, "Fifth Video", 900, 5, 8 * 60));//
            videos.add(new Advertisement(someContent, "Шестое видео", 450, 2, 7 * 60));//
            videos.add(new Advertisement(someContent, "седьмое 7", 450, 2, 6 * 60));//
            videos.add(new Advertisement(someContent, "Eleven Video", 400, 1, 5 * 60));//
        }


        public static AdvertisementStorage getInstance() {
            if (storage == null){
                storage = new AdvertisementStorage();
            }
            return storage;
        }

        public List<Advertisement> list(){
            return videos;
        }

        public void add(Advertisement advertisement){
            videos.add(advertisement);
        }


    }


