
public class Main {

    static int liczbaPacjentow =100;
    static int liczbaLekarzy =10;
    static Lekarz lekarz;
    public Main(){


    }
    public static void main(String[] args) {
        lekarz =new Lekarz(liczbaLekarzy, liczbaPacjentow);
        for(int i = 0; i< liczbaPacjentow; i++)
            new Pacjent(i,500, lekarz).start();


    }

}
