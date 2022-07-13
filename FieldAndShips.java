package battleship;

import java.util.Arrays;
import java.util.Scanner;

class FieldAndShips {

    public static String[][] createFieldAndAddShips(Scanner scanner) {
        String shipName;
        int shipLength;
        String coordinates;
        int cr1_1;
        int cr1_2;
        int cr2_1;
        int cr2_2;

        String[][] field;
        field = createField();
        printField(field);

        for (int i = 6; i > 1; i--) {
            // ships length
            if (i > 3) {
                shipLength = i - 1;
            } else {
                shipLength = i;
            }
            // ships name
            switch (i) {
                case 6:
                    shipName = "Aircraft Carrier";
                    break;
                case 5:
                    shipName = "Battleship";
                    break;
                case 4:
                    shipName = "Submarine";
                    break;
                case 3:
                    shipName = "Cruiser";
                    break;
                case 2:
                    shipName = "Destroyer";
                    break;
                default:
                    shipName = "";
            }
            System.out.println("\nEnter the coordinates of the " + shipName +
                            " (" + shipLength + " cells):\r\n");

            while (scanner.hasNext()) {
                // parse coordinates
                coordinates = scanner.next();
                cr1_1 = coordinates.charAt(0);
                cr1_2 = Integer.parseInt(coordinates.substring(1));
                coordinates = scanner.next();
                cr2_1 = coordinates.charAt(0);
                cr2_2 = Integer.parseInt(coordinates.substring(1));
                // if reversed coordinates
                if (cr1_1 > cr2_1) {
                    int temp = cr1_1;
                    cr1_1 = cr2_1;
                    cr2_1 = temp;
                } else if (cr1_2 > cr2_2) {
                    int temp = cr1_2;
                    cr1_2 = cr2_2;
                    cr2_2 = temp;
                }
                // check coordinates, add ships
                if (checkCoordinates(cr1_1, cr1_2, cr2_1, cr2_2, shipLength) &&
                        checkAround(cr1_1, cr1_2, cr2_1, cr2_2, field)) {
                    addShip(cr1_1, cr1_2, cr2_1, cr2_2, field);
                } else {
                    printErr();
                    continue;
                }
                break;
            }
            System.out.println("\n");
            printField(field);
        }
        return field;
    }
    // methods
    static String[][] createField() {
        String[][] field = new String[11][11];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (i == 0 && j == 0) {
                    field[i][j] = " ";
                } else if (i == 0) {
                    field[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    field[i][j] = String.valueOf((char) (64 + i));
                } else {
                    field[i][j] = "~";
                }
            }
        }
        return field;
    }

    private static void addShip(int cr1_1, int cr1_2, int cr2_1, int cr2_2, String[][] field) {
        if (!(cr1_1 == cr2_1)) {
            for (int j = cr1_1 - 64; j <= cr2_1 - 64; j++) {
                field[j][cr1_2] = "O"; // using letters
            }
        } else if (!(cr1_2 == cr2_2)) {
            for (int k = cr1_2; k <= cr2_2; k++) {
                field[cr1_1 - 64][k] = "O"; // using numbers
            }
        }
    }

    private static boolean checkCoordinates(int cr1_1, int cr1_2, int cr2_1, int cr2_2, int shipLength) {
        return (cr1_1 > 64 && cr1_1 < 75) && (cr2_1 > 64 && cr2_1 < 75) &&
                (cr1_2 > 0 && cr1_2 < 11) && (cr2_2 > 0 && cr2_2 < 11) &&
                (cr1_1 == cr2_1 || cr1_2 == cr2_2) && ((cr2_1 - cr1_1 + 1 == shipLength) ||
                (cr2_2 - cr1_2 + 1 == shipLength));
    }

    private static boolean checkAround(int cr1_1, int cr1_2, int cr2_1, int cr2_2, String[][] field) {
        boolean st = true;
        cr1_1 = cr1_1 - 1 < 65 ? cr1_1 : cr1_1 - 1;
        cr1_2 = cr1_2 - 1 < 1 ? cr1_2 : cr1_2 - 1;
        cr2_1 = cr2_1 + 1 > 74 ? cr2_1 : cr2_1 + 1;
        cr2_2 = cr2_2 + 1 > 10 ? cr2_2 : cr2_2 + 1;
        for (int j = cr1_1 - 64; j <= cr2_1 - 64; j++) {
            for (int k = cr1_2; k <= cr2_2; k++) {
                if (field[j][k].equals("O")) {
                    st = false;
                    break;
                }
            }
        }
        return st;
    }

    static void printField(String[][] field) {
        System.out.println(
                Arrays.deepToString(field).replace("], ", "\n").replace(
                        "[", "").replace("]]", "").replace(",", ""));
    }

    private static void printErr() {
        System.out.println(
                "\nError! Wrong ship location! Try again:\r\n");
    }
}

