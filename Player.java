package battleship;

public class Player {

    private final String name;
    private final String[][] ownField;
    private final String[][] checkField;

    public Player(String name, String[][] ownField, String[][] checkField) {
        this.name = name;
        this.ownField = ownField;
        this.checkField = checkField;
    }

    public String getName() {
        return name;
    }

    public String[][] getOwnField() {
        return ownField;
    }

    public String[][] getCheckField() {
        return checkField;
    }
}
