import java.util.*;

/**
 * The ScrabbleGame class is the main class of this Scrabble game. It starts the game and
 * keeps track of the information of 2-4 players. Determines the winner from the highest
 * score between the players.
 * @author Oluwatomisin Ajayi (101189490)
 * @version October 25, 2022
 */

public class ScrabbleGame {

    public static final int SIZE = 14;
    private ArrayList<Player> players;
    private ArrayList<String> letters;
    private ArrayList<Integer> score;
    private Player winner;
    private ScrabbleBoard scrabbleBoard;
    private Player currentPlayer;
    private TileRack tileRack;
    private boolean gameStarted;
    private boolean gameOver;

    //private char[][] grid;
    //private HashMap<String, Integer> letterSet;
    //private List<Tile> tileValueList;

    /**
     * Constructor for the class ScrabbleGame. Initializes the players, the tile letters and
     * the scores.
     */
    public ScrabbleGame() {
        players = new ArrayList<>();
        letters = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L","M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
        score = new ArrayList<>((Arrays.asList(1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8 ,4, 10)));
        //grid = new char[SIZE][SIZE];

    }

//    public int getScore(String word){
//        int totalScore = 0;
//        for (char l : word.toCharArray()){
//            totalScore += getLetterScore(l);
//        }
//
//        return totalScore;
//    }

    //    public int getLetterScore(char letter) {
//        for (Tile t : tileValueList) {
//            if (t.containsLetter(letter)) {
//                return t.getScore();
//            }
//        }
//        throw new IllegalArgumentException("'" + letter + "' is not valid");
//    }

    /**
     * Adds a player to the current arraylist of players.
     * @param player
     */
    private void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Checks if ArrayList, characters, contains a specified string in the array of strings.
     * @param array
     * @param characters
     * @return
     */
    private boolean contains(String[] array, ArrayList<String> characters){
        for (int i=0; i<characters.size(); i++){
            if (!characters.contains(array[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * Play routine. Loops until the game ends.
     */
    private void play() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game of Scrabble");
        System.out.println("How many players will play this round (2 - 4)? ");

        int numbersOfPlayers = Integer.parseInt(scanner.nextLine());
        while(numbersOfPlayers<2 || numbersOfPlayers>4){
            System.out.println("Players must be between 2 to 4. Enter a new number ");
            numbersOfPlayers = Integer.parseInt(scanner.nextLine());
        }

        for(int i=0; i<numbersOfPlayers; i++){
            System.out.println("Player " + (i+1) + " enter your name ");
            String playerName = scanner.nextLine();
            Player player = new Player(playerName);
            addPlayer(player);
        }
        Random random = new Random(25);
        int rand = random.nextInt();
        for(int i=0; i<numbersOfPlayers; i++){
            for(int j=0; j<7; j++){
                Tile tile= new Tile(letters.get(rand), score.get(rand));//Get a random character and it's associated value
                players.get(i).getRack().add(tile); //Add tile to the players rack
            }
        }
        int playerNumber = 0;
        currentPlayer = players.get(0);

        while(run){
            System.out.println(currentPlayer + "'s Turn");
            System.out.println("Enter the characters in UPPERCASE for the word from your rack, separate with ','");
            System.out.println("The letters from your rack are: ");
            ArrayList<Tile> currentPlayerRack = currentPlayer.getRack();
            ArrayList<String> tileCharacters = new ArrayList<>();
            ArrayList<Tile> words = new ArrayList<>();
            for(int i=0; i<currentPlayer.getRack().size(); i++){
                tileCharacters.add(currentPlayerRack.get(i).getCharacter());//prints out the letters in the players rack
                System.out.println(currentPlayerRack.get(i).getCharacter());//print out the characters
            }
            String chosenLetters = scanner.nextLine();
            String[] array = chosenLetters.split(",");

            while(!contains(array, tileCharacters)){
                System.out.println("Some letters are not in your rack. try again!");
                chosenLetters = scanner.nextLine();
                array = chosenLetters.split(",");
            }
            for (int i=0; i< array.length; i++){
                for (int j=0; j<currentPlayerRack.size(); j++){
                    if (currentPlayerRack.get(j).getCharacter() == array[i]){
                        words.add(currentPlayerRack.get(j));//if the tile character matches the character in the array list, add that tile to the words arraylist
                    }
                }
            }

            System.out.println("Choose a starting and ending square: ");
            System.out.println("Enter your first coordinates (Enter the x-value and press enter then type the y-value: ");
            int start = Integer.parseInt(scanner.nextLine());
            int end = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter your second coordinates (Enter the x-value and press enter then type the y-value: ");
            int start2 = Integer.parseInt(scanner.nextLine());
            int end2 = Integer.parseInt(scanner.nextLine());

            boolean validEntry = scrabbleBoard.placeWord(words, scrabbleBoard.getScrabbleBoard()[start][end], scrabbleBoard.getScrabbleBoard()[start2][end2], currentPlayer);//place the words on the board and if any error, choose an appropriate location

            while(!validEntry){
                System.out.println("Choose a starting and ending square: ");
                System.out.println("Enter your first coordinates (Enter the x-value and press enter then type the y-value: ");
                start = Integer.parseInt(scanner.nextLine());
                end = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter your second coordinates (Enter the x-value and press enter then type the y-value: ");
                start2 = Integer.parseInt(scanner.nextLine());
                end2 = Integer.parseInt(scanner.nextLine());
                validEntry = scrabbleBoard.placeWord(words, scrabbleBoard.getScrabbleBoard()[start][end], scrabbleBoard.getScrabbleBoard()[start2][end2], currentPlayer);//place the words on the board and if any error, choose an appropriate location
            }


            //Change to the next player and repeat the process again
            playerNumber = (playerNumber + 1) % numbersOfPlayers;
            currentPlayer = players.get(playerNumber);

            if(currentPlayer == players.get(players.size()-1)){
                System.out.println("Do you wish to continue to the next round? (y/n) ");
                String response = scanner.nextLine();
                if(response.toLowerCase()== "y"){//if they all agree to end the game, it will be after the last player. If they want to quit, the player with the control types in 'y' and then the winner is determined
                    int max = 0;
                    for(Player player : players){
                        if (player.getTotalScore() > max){
                            max = player.getTotalScore();
                            winner = player;
                        }
                        System.out.println(player.getPlayerName() + "'s score: " + player.getTotalScore());//Print each player's score
                    }
                    System.out.println("The winner of this round is " + winner.getPlayerName());//Print the winner's name
                    run = false;//exit the while loop and close
                }
            }
        }


    }

    /**
     * Starts the Scrabble game.
     * @param args
     */
    public static void main(String[] args) {
        ScrabbleGame scrabbleGame = new ScrabbleGame();
        scrabbleGame.play();
    }



}

