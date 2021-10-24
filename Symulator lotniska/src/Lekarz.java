
public class Lekarz {
    static int PACJENT =1;
    static int START=2;
    static int BADANIE =3;
    static int KONIEC_BADANIA =4;
    static int Smierc_Pacjenta =5;

    int iloscLekarzy;
    int iloscZajetychLekarzy;
    int ilosc_Pacjentow;
    Lekarz(int ilosc_Lekarzy, int ilosc_Pacjentow){
        this.iloscLekarzy = ilosc_Lekarzy;
        this.ilosc_Pacjentow =ilosc_Pacjentow;
        this.iloscZajetychLekarzy =0;
    }
    synchronized int RozpoczecieBadania(int numer){

        iloscZajetychLekarzy--;
        System.out.println("Zaczynam badanie numer: "+numer);
        return START;

    }
    synchronized int konczenieBadania(){

        try{

            Thread.currentThread().sleep(1000);

        }
        catch(Exception ie){
        }
        if(iloscZajetychLekarzy < iloscLekarzy){
            iloscZajetychLekarzy++;
            System.out.println("Badanie zakończyło się. Aktualna liczba zajętych lekarzy: "+ iloscZajetychLekarzy);
            return PACJENT;
        }
        else
        {return KONIEC_BADANIA;}

    }
    synchronized void Zgon(){

        ilosc_Pacjentow--;
        System.out.println("ZABILEM");
        if(ilosc_Pacjentow == iloscLekarzy) System.out.println("Ilosc Pacjentow jest taka sama jak Lekarzy ");


    }

}
