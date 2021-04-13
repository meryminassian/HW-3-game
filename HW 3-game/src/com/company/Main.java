package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        Player archer = new Player("archer");
        Player swordsman = new Player("swordsman");


        Scanner scanner = new Scanner(System.in);

        File file = new File("game.txt");


        try (OutputStream outputStream = new FileOutputStream(file)) {
            while (!scanner.nextLine().equals("-1")) {

                if(swordsman.getHealth() == 0 || archer.getHealth() ==0){
                    System.out.print("game is over ");
                    if(swordsman.getHealth() == 0){
                        System.out.println("archer won");
                    } else System.out.println("swordsman won");
                    break;
                }
                //archer hitting swordsman
                swordsman.getHit(archer);
                byte[] bytes1 = swordsman.toString().getBytes();
                outputStream.write(bytes1);
                //swordsman hitting archer
                archer.getHit(swordsman);
                byte[] bytes2 = archer.toString().getBytes();
                outputStream.write(bytes2);

                if(scanner.nextLine().equals("stop")){

                    List<Player> p = new ArrayList<>();
                    //last two lines contain latest info about the players
                    try {
                        List<String> players = Files.lines(Path.of("game.txt"))
                                .skip(Files.lines(Path.of("game.txt")).count() - 2)
                                .map(line -> line.split(":"))
                                .map(each -> each[1].split(",")[0])
                                .collect(Collectors.toList());
                        //players.forEach(each -> System.out.println(each));

                        List<Double> lifeCounts = Files.lines(Path.of("game.txt"))
                                .skip(Files.lines(Path.of("game.txt")).count() - 2)
                                .map(line -> line.split(":"))
                                .map(each -> Double.parseDouble(each[2]))
                                .collect(Collectors.toList());
                        //lifeCounts.forEach(each -> System.out.println(each));

                        for (int i = 0; i < players.size(); i++) {
                            Player player = new Player(players.get(i));
                            player.setHealth(lifeCounts.get(i));
                            p.add(player);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    p.forEach(each -> System.out.println(each));
                    swordsman = p.get(0);
                    archer = p.get(1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//
//        List<Player> p = new ArrayList<>();
//        //last two lines contain latest info about the players
//        try {
//            List<String> players = Files.lines(Path.of("game.txt"))
//                    .skip(Files.lines(Path.of("game.txt")).count() - 2)
//                    .map(line -> line.split(":"))
//                    .map(each -> each[1].split(",")[0])
//                    .collect(Collectors.toList());
//            //players.forEach(each -> System.out.println(each));
//
//            List<Double> lifeCounts = Files.lines(Path.of("game.txt"))
//                    .skip(Files.lines(Path.of("game.txt")).count() - 2)
//                    .map(line -> line.split(":"))
//                    .map(each -> Double.parseDouble(each[2]))
//                    .collect(Collectors.toList());
//            //lifeCounts.forEach(each -> System.out.println(each));
//
//            for (int i = 0; i < players.size(); i++) {
//                Player player = new Player(players.get(i));
//                player.setHealth(lifeCounts.get(i));
//                p.add(player);
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        p.forEach(each -> System.out.println(each));
//        swordsman = p.get(0);
//        archer = p.get(1);


    }
}