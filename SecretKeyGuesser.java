public class SecretKeyGuesser {
    public void start() {
        // brute force key guessing
        SecretKey key = new SecretKey();
        // int numGuesses = 0;

        // initialize guess to the first possible key
        StringBuilder guess = new StringBuilder("RRRRRRRRRRRRRRRR");

        // initialize maxMatched to the number of characters in the correct key
        int maxMatched = key.guess(guess.toString());

        System.out.println(maxMatched);

        // keep guessing until we find the correct key
        // while (maxMatched < 16) {
            StringBuilder newGuess = null;
            if(newGuess == null){
                System.out.println("Guessing..... " + guess);
            }
            // iterate over all positions in the guess
            for (int i = 0; i < guess.toString().length(); i++) {
                // iterate over all possible characters at this position
                for (char c : "RMIT".toCharArray()) {
                    // don't change the guess if the character is already correct
                    if (c == guess.charAt(i)) {
                        continue;
                    }

                    // create a new guess with the character changed at this position
                    newGuess = new StringBuilder(guess);
                    newGuess.setCharAt(i, c);

                    // check how many characters match the correct key
                    int matched = key.guess(newGuess.toString());

                    // if we found a better match, update the guess and maxMatched
                    if (matched > maxMatched) {
                        guess = newGuess;
                        maxMatched = matched;    
                        break;
                    }

                    System.out.println("Guessing..... " + newGuess);

                }
            }
        // }

        // print the final guess and number of guesses
        System.out.println("I found the secret key. It is " + guess.toString());
    }


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

    // return the next value in 'RMIT' order, that is
    // R < M < I < T
    public String next(String current) {
        char[] curr = current.toCharArray();
        for (int i = curr.length - 1; i >=0; i--) {
            if (order(curr[i]) < 3) {
                // increase this one and stop
                curr[i] = charOf(order(curr[i]) + 1);
                break;
            }
            curr[i] = 'R';
        }
        return String.valueOf(curr);
    }
}