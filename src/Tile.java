public class Tile {
    public int score;
    private String character;

    public Tile(String character, int score){
        this.score = score;
        this.character = character;

//        for (int i = 0; i < tileLetters.length(); i++){
//            this.tileLetters.add(tileLetters.charAt(i));
//        }
    }

    public int getScore() {
        return score;
    }

    public String getCharacter() {
        return character;
    }
}
