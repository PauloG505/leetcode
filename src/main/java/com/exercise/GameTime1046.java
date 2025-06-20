package com.exercise;

import java.util.Scanner;

public class GameTime1046 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String startEnd = scanner.nextLine();

        /* Substring eh mais performaico que split, ou usar scanner.useDelimiter */
        int index = startEnd.indexOf(' ');
        int startHour = Integer.parseInt(startEnd.substring(0, index));
        int endHour = Integer.parseInt(startEnd.substring(index + 1));

        int gameDuration = calculateDuration(startHour, endHour);

        System.out.println("O JOGO DUROU " + gameDuration + " HORA(S)");
    }

    private static int calculateDuration(int startHour, int endHour) {
        if (startHour == endHour) return 24;
        if (endHour < startHour) return (24 - startHour) + endHour;

        return endHour - startHour;
    }
}