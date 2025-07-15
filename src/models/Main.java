package src.models;

import java.util.Scanner;
import views.TelaLoginView;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TelaLoginView.executarMenuLogin(scanner);
        scanner.close();
    }
}