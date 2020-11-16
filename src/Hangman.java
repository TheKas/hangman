import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {
  // https://en.wikipedia.org/wiki/Hangman_(game) (e-t-a-o-i-n-s-h-r-d-l-u)
  // Lives: 0 0 0
  // Input x
  // word .ell.
  // Not Used: abcd.f
  // --------------------------

  public static void main(String[] args) throws FileNotFoundException {
    int lives = 7;
    String notUsed = "abcdefghijklmnopqrstuvwxyz";
    File dictionary = new File("./src/german.txt");
    Scanner scanner = new Scanner(System.in);
    Scanner textScanner = new Scanner(dictionary);
    ArrayList<String> words = new ArrayList<>();

    while (textScanner.hasNext()) {
      words.add(textScanner.nextLine());
    }

    String randomWord = words.get((int) (Math.random() * words.size()));
    char[] letters = new char[randomWord.length()];

    System.out.println("The word has " + randomWord.length() + " letters.");

    for (int i = 0; i < letters.length; i++) {
      letters[i] = '.';
    }

    while (lives > 0) {
      System.out.println("Lives: ");

      for (int i = 0; i < lives; i++) {
        System.out.print("O");
      }
      System.out.println();
      System.out.println("Input: ");

      String input = scanner.nextLine();

      char letter = input.charAt(0);

      boolean isGuessCorrect = false;

      for (int i = 0; i < randomWord.length(); i++) {
        char l = randomWord.charAt(i);

        if (l == letter) {
          letters[i] = l;
          isGuessCorrect = true;
          }
        if (l == Character.toUpperCase(letter)){
          letters[i] = Character.toUpperCase(l);
          isGuessCorrect = true;
          }
        }

      if (!isGuessCorrect) {
        lives = lives - 1;
      }

      boolean isGameFinished = true;

      System.out.print("Word: ");

      for (int i = 0; i < letters.length; i++) {
        if (letters[i] == '.') {
          isGameFinished = false;
        }
        System.out.print(letters[i]);
      }
      System.out.println();

      notUsed = notUsed.replace(letter, '.');

      System.out.println(notUsed);

      System.out.println("--------------------");
      if (isGameFinished) {
        System.out.println("~~~~~ YOU WON! ~~~~~");
        break;
      }
    }
    if (lives == 0) {
      System.out.println("You lost! The word was: " + randomWord);
      System.out.println("Exiting game");
    }
  }
}
