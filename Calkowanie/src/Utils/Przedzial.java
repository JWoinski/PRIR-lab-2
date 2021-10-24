package Utils;

public class Przedzial {
    private double a;
    private double b;

    public Przedzial(double a, double b) throws Exception {
        if (b < a) {
            throw new Exception("Koniec przedzialu musi byc wiekszy niz poczatek");
        }
        this.a = a;
        this.b = b;
    }

    public Przedzial[] podzielPrzedzialy(int n) throws Exception {
        Przedzial[] przedzialy = new Przedzial[n];

        double dx = this.policzDlugoscPrzedzialu() / n;

        for (int i = 0; i < n; i++) {
            przedzialy[i] = new Przedzial(this.a + (i * dx), this.a + (i * dx) + dx);
        }

        return przedzialy;
    }

    public double policzDlugoscPrzedzialu() {
        return this.b - this.a;
    }

    public double getA() {
        return a;
    }


    public double getB() {
        return b;
    }

}
