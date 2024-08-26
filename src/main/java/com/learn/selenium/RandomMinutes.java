package com.learn.selenium;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomMinutes {
    public static void main(String[] args) {


        List<LocalTime> timeItems = IntStream.range(0, 24 * 6)
                .mapToObj(i -> LocalTime.of(i / 6, (i % 6) * 10))
                .collect(Collectors.toList());

        timeItems.add(LocalTime.of(23, 59));

        timeItems.forEach(System.out::println);

        System.out.println("------------------" +  timeItems.size());

        Integer a = 9;
        String b = "04:40";



        String timeFromDb = String.valueOf(String.format("%02d:00", a));

        String timeToDb = String.valueOf(b);
        LocalTime timeFrom = LocalTime.parse(timeFromDb);//03:00
        LocalTime timeTo = LocalTime.parse(timeToDb); //04:10


        List<LocalTime> timeItemNeedRemove = timeItems.stream()
//                .filter(time -> !time.isBefore(timeFrom) && !time.isAfter(timeTo))
                .filter(time -> time.compareTo(timeFrom) >= 0 && time.compareTo(timeTo) < 0)
                .collect(Collectors.toList());

        System.out.println("List Need Remove");
        timeItemNeedRemove.forEach(System.out::println);
        System.out.println("------------------");

        timeItems.removeAll(timeItemNeedRemove);

        System.out.println("List Response");
        timeItems.forEach(System.out::println);
    }
}
