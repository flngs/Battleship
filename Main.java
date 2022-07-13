package battleship;

import java.io.IOException;
import java.util.Scanner;

import static battleship.FieldAndShips.*;
import static battleship.Shot.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Player 1, place your ships on the game field\r\n");
        Player player1 = new Player("Player 1", createFieldAndAddShips(sc), createField());

        promptEnterKey();

        System.out.println("Player 2, place your ships on the game field\r\n");
        Player player2 = new Player("Player 2", createFieldAndAddShips(sc), createField());

        promptEnterKey();

        while (checkLiveShips(player1.getOwnField()) && checkLiveShips(player2.getOwnField())) {
            printField(player1.getCheckField());
            System.out.println("---------------------");
            printField(player1.getOwnField());
            shooting(player2.getOwnField(), player1.getCheckField(), player1.getName(), sc);
            promptEnterKey();

            printField(player2.getCheckField());
            System.out.println("---------------------");
            printField(player2.getOwnField());
            shooting(player1.getOwnField(), player2.getCheckField(), player2.getName(), sc);
            promptEnterKey();

        }

    }

    public static void promptEnterKey() {
        System.out.println("\nPress Enter and pass the move to another player\r\n");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
