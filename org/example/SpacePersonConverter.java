package org.example;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

public class SpacePersonConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ask user for English sentence to translate
        System.out.print("Enter an English secret message: ");
        String userInput = scanner.nextLine();

        // Now converting to space person alphabet
        String spacePerson = convertToSpacePerson(userInput);
        System.out.println("Space Person Message: " + spacePerson);

        // SHA-256 hashing
        String hashedString = getSHA256(spacePerson);
        System.out.println("SHA-256: " + hashedString);

        // 5 character Caesar Cipher shift
        String caesarCipher = doCaesarCipher(userInput, 5);
        System.out.println("Caesar Cipher: " + caesarCipher);

        // bruteForce CaesarCipher(userInput);
        bruteForceCaesarCipher(userInput);

        scanner.close();
    }

    public static String convertToSpacePerson(String text) {
        HashMap<Character, String> spacePersonMap = new HashMap<>();
        spacePersonMap.put('A', "~");
        spacePersonMap.put('B', "!");
        spacePersonMap.put('C', "@");
        spacePersonMap.put('D', "#");
        spacePersonMap.put('E', "$");
        spacePersonMap.put('F', "%");
        spacePersonMap.put('G', "^");
        spacePersonMap.put('H', "&");
        spacePersonMap.put('I', "*");
        spacePersonMap.put('J', "~~");
        spacePersonMap.put('K', "!!");
        spacePersonMap.put('L', "@@");
        spacePersonMap.put('M', "##");
        spacePersonMap.put('N', "$$");
        spacePersonMap.put('O', "%%");
        spacePersonMap.put('P', "^^");
        spacePersonMap.put('Q', "&&");
        spacePersonMap.put('R', "**");
        spacePersonMap.put('S', "~~~");
        spacePersonMap.put('T', "!!!");
        spacePersonMap.put('U', "@@@");
        spacePersonMap.put('V', "###");
        spacePersonMap.put('W', "$$$");
        spacePersonMap.put('X', "%%%");
        spacePersonMap.put('Y', "^^^");
        spacePersonMap.put('Z', "&&&");
        spacePersonMap.put('0', "***");
        spacePersonMap.put('1', "~!");
        spacePersonMap.put('2', "%^");
        spacePersonMap.put('3', "#!");
        spacePersonMap.put('4', "&*&");
        spacePersonMap.put('5', "@%");
        spacePersonMap.put('6', "*%^");
        spacePersonMap.put('7', "#$#");
        spacePersonMap.put('8', "^^&");
        spacePersonMap.put('9', "*!*");

        StringBuilder spacePersonBuilder = new StringBuilder();
        text = text.toUpperCase();

        for (char c : text.toCharArray()) {
            if (c == ' ') {
                spacePersonBuilder.append(" ");
            } else if (spacePersonMap.containsKey(c)) {
                spacePersonBuilder.append(spacePersonMap.get(c)).append(" ");
            }
        }
        return spacePersonBuilder.toString().trim();
    }

    public static String getSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String doCaesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            char shifted;

            if (Character.isLetter(ch)) {
                shifted = (char) (ch + shift);

                if ((Character.isLowerCase(ch) && shifted > 'z') || (Character.isUpperCase(ch) && shifted > 'Z')) {
                    shifted = (char) (ch - (26 - shift));
                }
            } else {
                shifted = ch;
            }

            result.append(shifted);
        }

        return result.toString();
    }
    // This is the brute force attack to decrypt the Ceasar cipher
    public static void bruteForceCaesarCipher(String text) {
        System.out.println("Brute Force Caesar Cipher:");

        for (int i = 0; i < 26; i++) {
            System.out.println("Shift " + i + ": " + doCaesarCipher(text, i));
        }
    }
}
