import Utils.Przedzial;

public class Main {

    // Ciekawy przypadek, gdzie przy stosowaniu funkcji wyswietl wersja bez watkow jest szybsza
    public static String wyswietl(double wynikProstokatow,double wynikTrapezow, double wynikSimpsona,long Czas){
        return "Metoda prostokątow wynik = " + wynikProstokatow +"\nMetoda trapezów wynik = "
                + wynikTrapezow +("\nMetoda Simpsona wynik = " + wynikSimpsona)+
                ("\nCzas wykonania: " + (System.nanoTime() - Czas)/10000);
    }


    public static void main(String[] args) {

        try {
            double PoczatekPrzedzialu = 0;
            double KoniecPrzedzialu = 1;
            int N = 1000;
            int Watki = 4;

            Funkcja funkcja = new Funkcja() {
                public double wartosc(double x) {
                    return x * x + 2 * x;
                }
            };

            MetodaProstokatow mp = new MetodaProstokatow(PoczatekPrzedzialu, KoniecPrzedzialu, N, funkcja);
            MetodaTrapezow mt = new MetodaTrapezow(PoczatekPrzedzialu, KoniecPrzedzialu, N, funkcja);
            MetodaSimpsona ms = new MetodaSimpsona(PoczatekPrzedzialu, KoniecPrzedzialu, N, funkcja);

            System.out.println("---- BEZ WĄTKÓW ----");
            long startTime = System.nanoTime();
//            System.out.println(wyswietl(mp.policz(),mt.policz(),ms.policz(),startTime));
            System.out.println("Metoda prostokątow wynik = " + mp.policz());
            System.out.println("Metoda trapezów wynik = " + mt.policz());
            System.out.println("Metoda Simpsona wynik = " + ms.policz());
            System.out.println("Czas wykonania: " + (System.nanoTime() - startTime)/10000);

            long startTime2 = System.nanoTime();
            System.out.println("---- Z WĄTKAMI ----");
            Przedzial przedzialCalki = new Przedzial(PoczatekPrzedzialu, KoniecPrzedzialu);
            Przedzial[] przedzialyWatkow = przedzialCalki.podzielPrzedzialy(Watki);
            MetodaBazowaThread[] mp_tablica_watkow = new MetodaBazowaThread[Watki];
            MetodaBazowaThread[] mt_tablica_watkow = new MetodaBazowaThread[Watki];
            MetodaBazowaThread[] ms_tablica_watkow = new MetodaBazowaThread[Watki];
            for (int j = 0; j < przedzialyWatkow.length; j++) {
                Przedzial p = przedzialyWatkow[j];
                MetodaProstokatow mp_2 = new MetodaProstokatow(p.getA(), p.getB(), N, funkcja);
                MetodaTrapezow mt_2 = new MetodaTrapezow(p.getA(), p.getB(), N, funkcja);
                MetodaSimpsona ms_2 = new MetodaSimpsona(p.getA(), p.getB(), N, funkcja);
                mp_tablica_watkow[j] = new MetodaBazowaThread(mp_2);
                mt_tablica_watkow[j] = new MetodaBazowaThread(mt_2);
                ms_tablica_watkow[j] = new MetodaBazowaThread(ms_2);
                mp_tablica_watkow[j].run();
                mt_tablica_watkow[j].run();
                ms_tablica_watkow[j].run();
                mp_tablica_watkow[j].join();
                mt_tablica_watkow[j].join();
                ms_tablica_watkow[j].join();
            }

            double mp_t_wynik = 0;
            double mt_t_wynik = 0;
            double ms_t_wynik = 0;
            for (int j = 0; j < przedzialyWatkow.length; j++) {
                mp_t_wynik += mp_tablica_watkow[j].wynik;
                mt_t_wynik += mt_tablica_watkow[j].wynik;
                ms_t_wynik += ms_tablica_watkow[j].wynik;
            }
//            System.out.println(wyswietl(mp_t_wynik,mt_t_wynik,ms_t_wynik,startTime2));

            System.out.println("Metoda prostokątow wynik = " + mp_t_wynik);
            System.out.println("Metoda trapezów wynik = " + mt_t_wynik);
            System.out.println("Metoda Simpsona wynik = " + ms_t_wynik);
            System.out.println("Czas wykonania: " + (System.nanoTime() - startTime2)/10000);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
