import java.util.Random;


    public class Pacjent extends Thread {
        public Pacjent(int id, int zmeczenie, Lekarz lekarz){
            this.id =id;
            this.zmeczenie =zmeczenie;
            this.stan = SalaPrzyjecPacjenta;
            this.lekarz =lekarz;
            random =new Random();
        }

        static int SalaPrzyjecPacjenta =1;
        static int RozpoczecieBadania =2;
        static int Badanie =3;
        static int KoniecBadania =4;
        static int Zgon =5;
        static int Odpocznij =1000;
        static int ReszkiSilLekarza =500;


        int id;
        int zmeczenie;
        int stan;
        Lekarz lekarz;
        Random random;


        public void run(){
            while(true){
                if(stan == SalaPrzyjecPacjenta){
                    if(random.nextInt(2)==1){
                        stan = RozpoczecieBadania;
                        zmeczenie = Odpocznij;
                        System.out.println("Zapraszam do sali pacjenta z numerem: "+ id);
                        try {
                            Thread.currentThread().sleep(2000);//sleep for 1000 ms
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        stan = lekarz.RozpoczecieBadania(id);

                    }
                    else{
                        System.out.println("Prosze poczekac 5 minut na wejscie do sali");
                    }
                }
                else if(stan == RozpoczecieBadania){
                    System.out.println("Zaczynam badanie o nummerze "+ id);
                    stan = Badanie;
                }
                else if(stan == Badanie){
                    zmeczenie -=20;
				System.out.println("Jestem w trakcie badania numer "+ id);
                    if(zmeczenie <= ReszkiSilLekarza){
                        stan = KoniecBadania;

                    }
                    else try{
                        sleep(random.nextInt(1000));
                    }
                    catch (Exception e){}
                }
                else if(stan == KoniecBadania){
                    System.out.println("Koncze badanie pacjenta o numerze: "+ id +" ilosc mozliwych do przyjecia pacjentow przed przerwa to:  "+ zmeczenie);
                    stan = lekarz.konczenieBadania();
                    if(stan == KoniecBadania){
                        zmeczenie -=20;
                        System.out.println("Lekarz jest bardzo zmeczony! ");
                        if(zmeczenie <=0) stan = Zgon;
                    }
                }
                else if(stan == Zgon){
                    System.out.println("Pacjent o numerze"+ id +"zmarł");
                    lekarz.Zgon();

                }
            }
        }

    }

