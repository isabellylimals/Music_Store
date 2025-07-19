package src.models;

import java.util.Scanner;
import src.utils.sons.Ambiente.SomAmbiente;
import views.TelaLoginView;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SomAmbiente.tocarSomAmbiente("src/utils/sons/Ambiente/som.wav");
        TelaLoginView.executarMenuLogin(scanner);

        scanner.close();
    }
}