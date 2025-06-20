package com.exercise;

import java.util.*;

public class CaesarCipher1253 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());

        String codifiedMsg;
        int shifts;

        for (int i = 0; i < n; i++) {
            codifiedMsg = scan.nextLine();
            shifts = Integer.parseInt(scan.nextLine());

            showOriginalMsg(codifiedMsg, shifts);
        }
    }

    private static void showOriginalMsg(String codifiedMsg, Integer shifts) {
        StringBuilder originalMsg = new StringBuilder(codifiedMsg.length());

        for (int i = 0; i < codifiedMsg.length(); i++) {
            int charId = codifiedMsg.charAt(i);
            int newCharId = charId - shifts;

            if (newCharId < 65) {
                newCharId = 91 - (65 - newCharId);
            }

            originalMsg.append((char) newCharId);
        }

        System.out.println(originalMsg);
    }
}