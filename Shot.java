package battleship;

import java.util.Scanner;

public class Shot {

    public static void shooting(String[][] enemyField, String[][] checkField, String name, Scanner scanner) {
        String coordinates;
        int cr1_ch;
        int cr2;
        System.out.printf("\n%s, it's your turn:\r\n", name);
        while (scanner.hasNext()) {
            coordinates = scanner.next();
            // parse coordinates
            cr1_ch = coordinates.charAt(0);
            cr2 = Integer.parseInt(coordinates.substring(1));
            // check for legal coordinates
            if ((cr1_ch < 65 || cr1_ch > 74) || (cr2 < 0 || cr2 > 10)) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\r\n");
                continue;
            // shoot
            } else {
                if (enemyField[cr1_ch - 64][cr2].equals("~")  ||
                        enemyField[cr1_ch - 64][cr2].equals("M")) {
                    checkField[cr1_ch - 64][cr2] = "M";
                    enemyField[cr1_ch - 64][cr2] = "M";
                    System.out.println("\nYou missed!\r\n");
                } else if (enemyField[cr1_ch - 64][cr2].equals("O") ||
                        enemyField[cr1_ch - 64][cr2].equals("X")) {
                    checkField[cr1_ch - 64][cr2] = "X";
                    enemyField[cr1_ch - 64][cr2] = "X";
                    if (checkNear(cr1_ch, cr2, enemyField)) {
                        System.out.println("\nYou hit a ship!\r\n");
                    } else if (checkLiveShips(enemyField)) {
                        System.out.println("\nYou sank a ship! Specify a new target:\r\n");
                    } else {
                        System.out.println("\nYou sank the last ship. You won. Congratulations!\r\n");
                    }
                }
            }
            break;
        }
    }


    private static boolean checkNear(int cr1_ch, int cr2, String[][] field) {
        boolean st = false;
        int cr1_1 = cr1_ch - 1 < 65 ? cr1_ch : cr1_ch - 1;
        int cr1_2 = cr2 - 1 < 1 ? cr2 : cr2 - 1;
        int cr2_1 = cr1_ch + 1 > 74 ? cr1_ch : cr1_ch + 1;
        int cr2_2 = cr2 + 1 > 10 ? cr2 : cr2 + 1;
        for (int j = cr1_1 - 64; j <= cr2_1 - 64; j++) {
            for (int k = cr1_2; k <= cr2_2; k++) {
                if (field[j][k].equals("O")) {
                    st = true;
                    break;
                }
            }
        }
        return st;
    }

    static boolean checkLiveShips(String[][] field) {
        for (String[] i : field) {
            for (String j : i) {
                if (j.equals("O")) {
                    return true;
                }
            }
        }
        return false;
    }
}