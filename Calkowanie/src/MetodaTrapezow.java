public class MetodaTrapezow extends MetodaBazowa{

    public MetodaTrapezow(double a, double b, int n, Funkcja funkcja) throws Exception {
        super(a, b, n, funkcja);
    }

    @Override
    public double policz() {
        double dystans = this.przedzial.policzDlugoscPrzedzialu();
        double dx = dystans / this.n;
        double wynik = 0;
        for (int i = 1; i <= this.n; i++) {
            wynik += this.f.wartosc(this.przedzial.getA() + (dx * i));
        }

        return (wynik + (this.f.wartosc(this.przedzial.getA() + this.przedzial.getB()))/2) * dx;
    }
}
