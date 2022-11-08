import game.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x;
        int s;
        Game g = new Game();
        while (true) {
            g.printBoard();
            System.out.print("play your move: ");
            x = sc.nextInt();
            s = g.play(x);
            System.out.println();
            if (s == -1)
                System.out.println("try again");
            if (s == 1) {
                System.out.println("first player won");
                break;
            }
            if (s == 2) {
                System.out.println("second player won");
                break;
            }
        }
    }
}
