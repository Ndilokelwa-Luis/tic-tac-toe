package tictactoe;

import java.util.Scanner;

public class tictactoe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String state = "         ";
        printState(state);

        int space = countXO(state, ' ');
        char player = 'X';

        String cLine;
        String cCol;


        while (space != 0) {
            System.out.print("Enter the coordinates");
            cLine = scanner.next();
            cCol = scanner.next();
            if ( isNotNumeric(cLine) || isNotNumeric(cCol)) {
                System.out.println("You should enter numbers!");
            } else if (isNotInRange(cCol) || isNotInRange(cLine)) {
                System.out.println("Coordinates should be form 1 to 3!");
            } else if (isOccupied(state, cLine, cCol)){
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                state = play(state, cLine, cCol, player);
                printState(state);
                if (threeInARow(state, player)) {
                    System.out.println(player + " wins");
                    break;
                }
                space = countXO(state, ' ');
                if (space == 0) {
                    System.out.println("Draw");
                } else {
                    if (countXO(state, 'X') > countXO(state, 'O')) {
                        player = 'O';
                    } else {
                        player = 'X';
                    }
                }
            }
        }
    }

    public static boolean isNotNumeric(String c) {
        try {
            Integer.parseInt(c);
        } catch (NumberFormatException nfe){
            return true;
        }
        return false;
    }

    public static boolean isNotInRange(String c) {
        int i = Integer.parseInt(c);
        return i > 3 || i < 1;
    }

    public static boolean isOccupied(String state, String cL, String cC) {
        int cLine = Integer.parseInt(cL);
        int cCol = Integer.parseInt(cC);

        switch (cLine) {
            case 1:
                switch (cCol) {
                    case 1:
                        return verifyChar(state, 0);
                    case 2:
                        return verifyChar(state, 1);
                    case 3:
                        return verifyChar(state, 2);
                    default:
                        break;
                }
                break;
            case 2:
                switch (cCol) {
                    case 1:
                        return verifyChar(state, 3);
                    case 2:
                        return verifyChar(state, 4);
                    case 3:
                        return verifyChar(state, 5);
                    default:
                        break;
                }
                break;
            case 3:
                switch (cCol) {
                    case 1:
                        return verifyChar(state, 6);
                    case 2:
                        return verifyChar(state, 7);
                    case 3:
                        return verifyChar(state, 8);
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return true;
    }

    public static boolean verifyChar(String state, int pos) {
        return state.charAt(pos) != ' ';
    }

    public static String play(String state, String cL, String cC, char player) {
        int cLine = Integer.parseInt(cL);
        int cCol = Integer.parseInt(cC);

        switch (cLine) {
            case 1:
                switch (cCol) {
                    case 1:
                        return player + state.substring(1,9);
                    case 2:
                        return state.substring(0,1) + player + state.substring(2,9);
                    case 3:
                        return state.substring(0,2) + player + state.substring(3,9);
                    default:
                        break;
                }
                break;
            case 2:
                switch (cCol) {
                    case 1:
                        return state.substring(0,3) + player + state.substring(4,9);
                    case 2:
                        return state.substring(0,4) + player + state.substring(5,9);
                    case 3:
                        return state.substring(0,5) + player + state.substring(6,9);
                    default:
                        break;
                }
                break;
            case 3:
                switch (cCol) {
                    case 1:
                        return state.substring(0,6) + player + state.substring(7,9);
                    case 2:
                        return state.substring(0,7) + player + state.charAt(8);
                    case 3:
                        return state.substring(0,8) + player;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return state;
    }

    public static void printState(String newState) {
        System.out.println("---------");
        System.out.println("| " + newState.charAt(0) + " " + newState.charAt(1) + " " + newState.charAt(2) + " |");
        System.out.println("| " + newState.charAt(3) + " " + newState.charAt(4) + " " + newState.charAt(5) + " |");
        System.out.println("| " + newState.charAt(6) + " " + newState.charAt(7) + " " + newState.charAt(8) + " |");
        System.out.println("---------");
    }

    public static int countXO(String state, char posXO) {
        int count = 0;
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == posXO) {
                count++;
            }
        }
        return count;
    }

    public static boolean threeInARow(String state, char posXO) {
        boolean line1 = state.charAt(0) == posXO && state.charAt(1) == posXO && state.charAt(2) == posXO;
        boolean line2 = state.charAt(3) == posXO && state.charAt(4) == posXO && state.charAt(5) == posXO;
        boolean line3 = state.charAt(6) == posXO && state.charAt(7) == posXO && state.charAt(8) == posXO;

        boolean col1 = state.charAt(0) == posXO && state.charAt(3) == posXO && state.charAt(6) == posXO;
        boolean col2 = state.charAt(1) == posXO && state.charAt(4) == posXO && state.charAt(7) == posXO;
        boolean col3 = state.charAt(2) == posXO && state.charAt(5) == posXO && state.charAt(8) == posXO;

        boolean cross1 = state.charAt(0) == posXO && state.charAt(4) == posXO && state.charAt(8) == posXO;
        boolean cross2 = state.charAt(6) == posXO && state.charAt(4) == posXO && state.charAt(2) == posXO;

        return line1 || line2 || line3 || col1 || col2 || col3 || cross1 || cross2;
    }
}
