import java.util.*;

public class ScrabbleGame {

    public static final int SIZE = 15;
    private ArrayList<Player> players;
    private ArrayList<String> letters;
    private ArrayList<Integer> score;
    private Player winner;
    private ScrabbleBoard scrabbleBoard;
    private Player currentPlayer;
    private TileRack tileRack;
    private boolean gameStarted;
    private boolean gameOver;

    private char[][] grid;
    //private HashMap<String, Integer> letterSet;
    private List<Tile> tileValueList;

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

    private void addPlayer(Player player) {
        players.add(player);
    }

    private boolean contains(String[] array, ArrayList<String> characters){
        for (int i=0; i<characters.size(); i++){
            if (!characters.contains(array[i])){
                return false;
            }
        }
        return true;
    }

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
            for(int i=0; i<currentPlayer.getRack().size(); i++){
                tileCharacters.add(currentPlayerRack.get(i).getCharacter());
                System.out.println(currentPlayerRack.get(i).getCharacter());
            }
            String chosenLetters = scanner.nextLine();
            String[] array = chosenLetters.split(",");

            while(!contains(array, tileCharacters)){
                System.out.println("Some letters are not in your rack. try again!");
                chosenLetters = scanner.nextLine();
                array = chosenLetters.split(",");
            }


            playerNumber = (playerNumber + 1) % numbersOfPlayers;
            currentPlayer = players.get(playerNumber);
        }


    }

    public static void main(String[] args) {
        ScrabbleGame scrabbleGame = new ScrabbleGame();
        scrabbleGame.play();
    }



}

