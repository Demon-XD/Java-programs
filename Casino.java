package Java_Projects;

/*
    A program inspired by the discord bot Dank Memer (Dank Memer#5192). This program is still in its alpha version.
    Programming logic by: Vedant Jadhav
    Current planned games include:
        something involving a card game (maybe blackjack idk)...
    current pending updates/ideas:
        make an achievement system to make the game more fun
*/

import java.util.Scanner;

public class Casino {
    private final Scanner sc = new Scanner(System.in);
    private double depositValue = 0d;
    
    void play() {
        String choice;
        System.out.println("Welcome to Vedant Casino! Please deposit your money:");
        this.depositValue = sc.nextDouble();
        if (depositValue < 5000) {
            System.out.println("Sorry, but you cannot play with less than Rs. 5000 in our casino!\n");
            play();
            
        }
        else {
            while (true) {
                System.out.println("Which game do you want to play? Enter a suitable number from the list below:");
                System.out.println("1. Guessing, a game where the bot chooses a random 1 digit number, and you need " +
                        "to guess what it might be!");
                System.out.println("2. Gambling, where it's all upon two dice to decide if you win or lose!");
                System.out.println("Enter 'exit' if you want to exit our casino!");
                choice = sc.next();
                if (choice.equalsIgnoreCase("exit"))
                    break;
                switch(Integer.parseInt(choice)) {
                    case 1:
                        guessing();
                        break;
                    case 2:
                            gamble();
                        break;
                    default:
                        System.out.println("Sorry but you entered an invalid choice!");
                }
            }
            System.out.println("Thank you for playing in our casino! You are leaving with a total of Rs. " + depositValue +
                    "! We hope you enjoyed your stay!");
        }
    }

    private void guessing() {
        String num = "";
        int randomNum = 0, chances = 3;
        double moneyGained = 0d, bet = 0d;
        boolean firstTime = true;
        while (true) {
            if (firstTime) {
                firstTime = false;
                moneyGained = 0d;
                randomNum = RandomNumberGenerator.generate(1, 9);
                chances = 3;
                System.out.println("What amount would you like to bet? Type 'all' if you would like to bet all your " +
                        "wallet money.");
                num = sc.next();
                if (num.equalsIgnoreCase("all"))
                    bet = depositValue;
                else {
                    if (!num.equalsIgnoreCase("all") && (Double.parseDouble(num) > depositValue || Double.parseDouble(num) < 500)) {
                        System.out.println("Sorry, but you cannot bet less than 500 or more than your wallet money!");
                        firstTime = true;
                        continue;
                    }
                    bet = Double.parseDouble(num);
                }
                System.out.println("I am thinking of a 1 digit number: type a number to guess what it is, or enter " +
                        "'end' to end the game. You only have three chances!");
                num = sc.next();
            }
            if (Integer.parseInt(num) > 9 || Integer.parseInt(num) < 0)
                System.out.println("The number you guessed is either not a one digit number, or is lesser than zero" +
                        ".\n");
            if (Integer.parseInt(num) == randomNum) {
                switch(chances) {
                    case 3:
                        moneyGained += bet;
                        break;
                    case 2:
                        moneyGained += 60.0/100.0 * bet;
                        break;
                    case 1:
                        moneyGained += 30.0/100.0 * bet;
                        break;
                }
                this.depositValue += moneyGained;
                System.out.println("Correct guess! You win Rs. " + moneyGained + "!");
                System.out.println("Want to play again? Enter any key apart from 'end' to continue.");
                num = sc.next();
                if(!num.equalsIgnoreCase("end")) {
                    firstTime = true;
                    continue;
                } else
                    break;
            } else if (Integer.parseInt(num) > randomNum && Integer.parseInt(num) <= 9) {
                chances--;
                System.out.println("You guessed a bit higher there! Try guessing lower. Chances remaining: " + chances);
            } else if (Integer.parseInt(num) < randomNum){
                chances--;
                System.out.println("You guessed a bit lower there! Try guessing higher. Chances remaining: " + chances);
            }
            if (chances == 0) {
                System.out.println("Sorry, but you have answered incorrectly three times! The correct number was: " + randomNum +
                        ".You have lost Rs. " + bet + " from your wallet.");
                this.depositValue -= bet;
                System.out.println("Want to play again? Enter any key apart from 'end' to continue.");
                num = sc.next();
                if (!num.equalsIgnoreCase("end")) {
                    firstTime = true;
                    continue;
                } else
                    break;
            }
            System.out.println("Enter a number again, or type 'end' to end the game:");
            num = sc.next();
        }
        System.out.println("Thank you for playing with us!\n");
    }

    private void gamble() {
        String num;
        int randomUserNum, randomBotNum;
        double moneyGained, bet, gain;
        while (true) {
            moneyGained = 0;
            randomUserNum = RandomNumberGenerator.generate(2, 12);
            randomBotNum = RandomNumberGenerator.generate(2, 12);
            System.out.println("What amount would you like to bet? Type 'all' if you would like to bet all your " +
                    "wallet money.");
            num = sc.next();
            if (num.equalsIgnoreCase("all"))
                bet = depositValue;
            else {
                if (Double.parseDouble(num) > depositValue || Double.parseDouble(num) < 500) {
                    System.out.println("Sorry, you cannot bet less than 500 or more than your wallet money!");
                    continue;
                }
                bet = Double.parseDouble(num);
            }
            if (randomUserNum == randomBotNum) {
                System.out.println("It's a tie! You and the bot both got " + randomBotNum + ".");
            } else if (randomUserNum < randomBotNum) {
                System.out.println("Sorry to say, but you lose! The bot got " + randomBotNum + ", whereas you got " +
                        randomUserNum + ". You have lost your bet of Rs. " + bet + ".");
                moneyGained -= bet;
            } else {
                gain = (((randomUserNum - randomBotNum) * 10.0)/100.0) * bet;
                System.out.println("You win! The bot got " + randomBotNum + ", whereas you got " + randomUserNum + "." +
                        " You have won Rs. " + gain);
                moneyGained += gain;
            }
            this.depositValue += moneyGained;
            System.out.println("Want to play again? Enter any key apart from 'end' to continue.");
            num = sc.next();
            if(num.equalsIgnoreCase("end"))
                break;
        }
    }
}
