package hu.meiit.haladojava.calculator;

public class Calculalas {

    private final String eredmenyem;
    private static final String ErrorSymbol = "-";

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

    private static int toresPozKeresese(String bemenet) {

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

        if (tores == -1) {
            return ErrorSymbol;
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
        double op1 = 0;
        double op2 = 0;
        try {
            op1 = Double.parseDouble(parts[0]);
            op2 = Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            hibasadat++;
        }

        if (hibasadat == 0) {
            return szamitas(op1, op2, parts[1]);
        } else {
            return ErrorSymbol;
        }
    }

    private static int secondNumberIsNull(double secondNumber) {
        if (secondNumber == 0)
            return 1;
        return 0;
    }

    private static String szamitas(double op1, double op2, String operator) {
        double szamitasiEredmeny = 0;
        switch (operator) {
            case "+":
                szamitasiEredmeny = op1 + op2;
                break;
            case "-":
                szamitasiEredmeny = op1 - op2;
                break;
            case "/":
                try {
                    szamitasiEredmeny = op1 / op2;
                    if (szamitasiEredmeny == Double.POSITIVE_INFINITY || szamitasiEredmeny == Double.NEGATIVE_INFINITY) {
                        throw new ArithmeticException();
                    }
                } catch (ArithmeticException ae) {
                    return ErrorSymbol;
                }
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
