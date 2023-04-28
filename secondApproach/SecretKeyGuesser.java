package secondApproach;

import secondApproach.SecretKey;

public class SecretKeyGuesser {


    // Sample implementation to get the order of the characters
    static int order(char c) {
        if (c == 'R') {
            return 0;
        } else if (c == 'M') {
            return 1;
        } else if (c == 'I') {
            return 2;
        }
        return 3;
    }

    // Sample implementation to get the characters from the orders
    static char charOf(int order) {
        if (order == 0) {
            return 'R';
        } else if (order == 1) {
            return 'M';
        } else if (order == 2) {
            return 'I';
        }
        return 'T';
    }

    // Group implementation to change the current character in the string
    public static String changeNext(String current, int idx) {
        char[] curr = current.toCharArray();

        if (order(curr[idx]) < 3) {
            curr[idx] = charOf(order(curr[idx]) + 1);
            return String.valueOf(curr);
        }
        curr[idx] = 'R';
        return String.valueOf(curr);
    }

    //Group implementation to revert the current character in the string
    public static String changeBack(String current, int idx) {
        char[] curr = current.toCharArray();
        if (order(curr[idx]) < 3 && order(curr[idx]) > 0) {
            curr[idx] = charOf(order(curr[idx]) - 1);
            return String.valueOf(curr);
        }
        curr[idx] = 'R';
        return String.valueOf(curr);
    }


    //Group implementation to check and process other steps (recursive)
    public static String check(String guessedString, SecretKey key, int idx, int newestMatched) {

        System.out.println("Guessing... " + guessedString);

        //Guess the number of matched characters
        int numOfMatched = key.guess(guessedString);

        //Base case to break the recursion
        if (numOfMatched == 16) {
            System.out.println("The correct key is: " + guessedString);
            return guessedString;
        }

        // Processing if number of matched less than the newestMatched
        if (numOfMatched < newestMatched) {
            // Change the next character in string to check
            guessedString = changeNext(guessedString, idx + 1);
            // Revert the current character in string
            guessedString = changeBack(guessedString, idx);
            // Call the method after changing
            return check(guessedString, key, idx + 1, newestMatched);
        }

        //Processing if number of matched is greater than the newestMatched
        if (numOfMatched > newestMatched) {

            newestMatched = numOfMatched;
            //Change the next char to check
            guessedString = changeNext(guessedString, idx + 1);
            // Call the method after changing
            return check(guessedString, key, idx + 1, newestMatched);
        }

        //Processing if number of matched is equal to the newestMatched
        //Change the current character
        guessedString = changeNext(guessedString, idx);
        // Call the method again with the same index
        return check(guessedString, key, idx, newestMatched);
    }

    public void start() {
        SecretKey key = new SecretKey();
        String guessedKey = "RRRRRRRRRRRRRRRR";
        int newestMatched = key.guess(guessedKey);
        check(guessedKey, key, 0, newestMatched);
    }
}
