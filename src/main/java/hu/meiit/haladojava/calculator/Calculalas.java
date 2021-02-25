package hu.meiit.haladojava.calculator;

public class Calculalas {

    private final String eredmenyem;

    public Calculalas(String bemenet) {
        eredmenyem = consoleSzamitas(bemenet);
    }

    public Calculalas(int op1, int op2, String operator) {
        eredmenyem = szamitas(op1, op2, operator);
    }


    private static String[] parse(String bemenet) {
        String[] result = bemenet.split(" ");
        return result;
    }

    private static String consoleSzamitas(String bemenet) {
        String vissza;
        String[] parts = parse(bemenet);
        switch (parts.length) {
            case 1:
                vissza = tordel(bemenet);
                break;
            case 3:
                vissza = adatkonverzio(parts);
                break;
            default:
                throw new RuntimeException("Wrong input");
        }
        return vissza;
    }

    private static int toresPozKeresese(String bemenet){

        if (bemenet.contains("+")) {
            return bemenet.indexOf("+");
        }
        if (bemenet.contains("/")) {
            return bemenet.indexOf("/");
        }
        if (bemenet.contains("*")) {
            return bemenet.indexOf("*");
        }
        if (bemenet.contains("-")) {
            return bemenet.indexOf("-");
        }
        return -1;
    }


    private static String tordel(String bemenet) {
        String[] ertekVissza = new String[3];
        int tores = toresPozKeresese(bemenet);

        if(tores == -1){
            return "-";
        }

        if (tores != 0) {
            ertekVissza[0] = bemenet.substring(0, tores);
            ertekVissza[1] = String.valueOf(bemenet.charAt(tores));
            ertekVissza[2] = bemenet.substring(tores + 1);
            String vegertek = adatkonverzio(ertekVissza);
            return vegertek;
        } else {
            throw new RuntimeException("Wrong input(csak egy szam van)");
        }


    }

    private static String adatkonverzio(String[] parts) {
        int hibasadat = 0;
        int op1 = 0;
        int op2 = 0;
        try {
            op1 = Integer.parseInt(parts[0]);
            op2 = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            hibasadat++;
        }
        if (hibasadat == 0) {
            return szamitas(op1, op2, parts[1]);
        } else {
            return "-";
        }
    }

    private static String szamitas(int op1, int op2, String operator) {
        int szamitasiEredmeny = 0;
        switch (operator) {
            case "+":
                szamitasiEredmeny = op1 + op2;
                break;
            case "-":
                szamitasiEredmeny = op1 - op2;
                break;
            case "/":
                szamitasiEredmeny = op1 / op2;
                break;
            case "*":
                szamitasiEredmeny = op1 * op2;
                break;
            default:
                throw new RuntimeException("Hiba");
        }

        return String.valueOf(szamitasiEredmeny);
    }


    public void EredmenyPrint() {
        try {
            System.out.print(Double.valueOf(this.eredmenyem));
        } catch (NumberFormatException e) {
            System.out.print(this.eredmenyem);
        }


    }

}
