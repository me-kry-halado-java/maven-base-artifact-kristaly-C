package hu.meiit.haladojava.calculator;

import java.util.Scanner;

public class App 
{

    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        String szoveg = scanner.nextLine();
        Calculalas Kalkulalo = new Calculalas(szoveg);
        Kalkulalo.EredmenyPrint();
    }
}
