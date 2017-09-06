import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AI extends Pelaaja {

    private ArrayList<Hattu> hatut;
    private HashMap<Hattu, Pallo> hp;
    private int voitot;

    public AI(String nimi) {
        super.setNimi(nimi);
        this.hatut = new ArrayList();
        this.voitot = 0;

        int i = 1;
        while (i < 101) {
            this.hatut.add(new Hattu(i));
            i++;
        }
    }

    public AI(String nimi, Boolean b) throws IOException {
        super.setNimi(nimi);
        this.hatut = new ArrayList();
        this.voitot = 0;

        int i = 1;
        while (i < 101) {
            this.hatut.add(new Hattu(i));
            i++;
        }

    }

    public void voitapisteita() {
        this.voitot++;
    }

    public int getPisteet() {
        return this.voitot;
    }

    public int pallota(int jalella) {

        int hattuvalinta = 0;

        if (jalella >= 1) {
            hattuvalinta = jalella - 1;
        }
        Hattu h = this.hatut.get(hattuvalinta);
        Pallo p = h.arvo(jalella);

        this.hp.put(h, p);

        if (this.hatut.get(hattuvalinta).pallojahatussa() > 1) {
            this.hatut.get(hattuvalinta).poista(p);
        }

        return p.getnro();

    }

    public ArrayList<Hattu> getHatut() {
        return this.hatut;
    }

    public void Hashh() {
        this.hp = new HashMap();
    }

    public void getMap() {
        System.out.println(this.hp);
    }

    public void voittajanPallomeri() {
        for (Hattu h : this.hp.keySet()) {
            for (Hattu k : this.hatut) {
                if (h.getnro() == k.getnro()) {
                    k.lisaa(this.hp.get(h));
                    k.lisaa(this.hp.get(h));
                }
            }
        }
    }

    public void lamePallomeri() {
        for (Hattu h : this.hp.keySet()) {
            for (Hattu k : this.hatut) {
                if (h.getnro() == k.getnro()) {
                    k.lisaa(this.hp.get(h));
                }
            }
        }
    }

    @Override
    public int pelaa(int jaljella) {
        int nosto = 0;
        while (true) {
            nosto = pallota(jaljella);
            if (nosto >= 1 && nosto <= 3 && nosto <= jaljella) {
                jaljella -= nosto;
                break;
            } else {
                nosto = jaljella;
                jaljella -= nosto;
                break;
//                System.out.println("nosta 1-3 tikkua!");
//                System.out.println("ei pitäisi voida tapahtua!");
            }
        }
        System.out.println(super.getNimi() + " nosti " + nosto);
        return nosto;
    }

    public int spelaa(int jaljella) {
        int nosto = 0;
        while (true) {
            nosto = pallota(jaljella);
            if (nosto >= 1 && nosto <= 3 && nosto <= jaljella) {
                jaljella -= nosto;
                break;
            } else {

                nosto = jaljella;
                jaljella -= nosto;
                break;

//                System.out.println("nosta 1-3 tikkua!");
//                System.out.println("ei pitäisi voida tapahtua!");
            }
        }
        return nosto;
    }

    @Override
    public String toString() {
        String ger = "";
        for (Hattu h : this.hatut) {
            ger = ger + " hat " + h.getnro() + ": ";

            ger = ger + h.kurkistaHattuun();

            ger = ger + ";";
            ger = ger + "\n";
        }
        return ger;
    }
}



import java.util.ArrayList;
import java.util.Random;

public class Hattu {

    private int hatnro;
    private ArrayList<Pallo> pallot;

    public Hattu(int nro) {

        this.hatnro = nro;
        this.pallot = new ArrayList();
        this.pallot.add(new Pallo(1));
        this.pallot.add(new Pallo(2));
        this.pallot.add(new Pallo(3));
    }

    public void lisaa(Pallo p) {
        this.pallot.add(p);
    }

    public void poista(Pallo p) {
        this.pallot.remove(p);
    }

    public Pallo arvo(int jaljella) {
        Random random = new Random();

        int index = 1;
        if (this.pallot.size() > 1 && jaljella > 0) {
            if (this.pallot.size() > jaljella) {
                index = random.nextInt(jaljella);
            } else {
                index = random.nextInt(this.pallot.size());
            }
        } else {
            index = 0;
        }
        Pallo p = this.pallot.get(index);

        return p;
    }

    public int pallojahatussa() {
        return this.pallot.size();
    }

    public int getnro() {
        return this.hatnro;
    }

    public String toString() {
        return String.valueOf(this.hatnro);
    }

    public String kurkistaHattuun() {
        String gi = "";

        for (Pallo p : this.pallot) {
            gi = gi + p.toString();
        }
        return gi;
    }

    public String kurkistaHattuunPikaisesti() {

        String gi = "";

        int one = 0;
        int twi = 0;
        int thre = 0;

        for (Pallo p : this.pallot) {

            if (p.getnro() == 1) {
                one++;
            }

            if (p.getnro() == 2) {
                twi++;
            }
            if (p.getnro() == 3) {
                thre++;
            }

        }
        gi = this.hatnro + ":: " + "1:" + one + " // 2:" + twi + " // 3:" + thre;
        return gi;

    }

    public String getPallojenNumerot() {
        String gi = "";

        int one = 0;
        int twi = 0;
        int thre = 0;

        for (Pallo p : this.pallot) {

            if (p.getnro() == 1) {
                one++;
            }

            if (p.getnro() == 2) {
                twi++;
            }
            if (p.getnro() == 3) {
                thre++;
            }

        }
        gi = one + "," + twi + "," + thre;
        return gi;
    }

}


import java.util.Scanner;

public class Ihminen extends Pelaaja {

    private Scanner lukija;

    public Ihminen(String nimi, Scanner scan) {
        super.setNimi(nimi);
        this.lukija = scan;
    }

    @Override
    public int pelaa(int jaljella) {
        int nosto = 0;
        while (true) {
            System.out.println(super.getNimi() + ", nosta 1-3 tikkua!");
            nosto = Integer.parseInt(this.lukija.nextLine());
            if (nosto >= 1 && nosto <= 3 && nosto <= jaljella) {
                jaljella -= nosto;
                break;
            } else {
                System.out.println("nosta 1-3 tikkua!");
            }
        }
        System.out.println(super.getNimi() + " nosti " + nosto);
        return nosto;
    }

}



public class Pelaaja implements Pelattava {

    public String nimi;
    public boolean voittaja;
    public boolean haviaja;
    public int score;

    public Pelaaja() {
        this.voittaja = false;
        this.haviaja = false;
        this.score = 0;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return this.nimi;
    }

    public void setVoittaja() {
        this.voittaja = true;
        this.score = 1;
    }

    public void sethav() {
        this.haviaja = true;
    }

    public void nollaa() {
        this.voittaja = false;
        this.haviaja = false;
        this.score = 0;
    }

    public int getScore() {
        return this.score;
    }

    public boolean getVoittaja() {
        if (this.voittaja == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int pelaa(int tilanne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}


public interface Pelattava {

    public int pelaa(int jaljella);
    
}


import java.util.Random;
import java.util.Scanner;

public class Tikkupeli {

    private int kaikkitikut;
    private int jaljella;
    private Scanner lukija;
    private AI aly1;
    private AI aly2;
    private Pelaaja p1;
    private Pelaaja p2;
    private int kierros;

    public Tikkupeli() {
        this.kierros = 1;
        this.lukija = new Scanner(System.in);
        this.aly1 = new AI("Abel");
        this.aly1.Hashh();
        this.aly2 = new AI("Babel");
        this.aly2.Hashh();
    }

    // metodi kahden tekoälyn väliselle kierrokselle koulutustarkoitukseen
    public void mastermind() {
        this.p1 = this.aly1;
        this.p2 = this.aly2;
        int tikkuja = 100;
        this.kaikkitikut = tikkuja;
        this.jaljella = this.kaikkitikut;
        silentpeli();
        masterVoittaja();
    }

    //metodi useammalle AI -kierrokselle koulutukseen.
    public void multiMind() {

        int i = 0;
        while (true) {
            i++;

            mastermind();

            if (i > 100000) {
                break;
            }
        }
    }

    public void run() {
        multiMind();

        int valinta = valitsetekoalytaiihminen();

        aloitus();
        if (valinta == 1) {
            System.out.println("Anna 1. nimi:");
            String aabel = this.lukija.nextLine();
            this.p1 = new Ihminen(aabel, this.lukija);
            System.out.println("Anna 2. nimi:");
            String babel = this.lukija.nextLine();

            this.p2 = new Ihminen(babel, this.lukija);

        } else if (valinta == 2) {

            System.out.println("Anna nimi:");
            String aabel = this.lukija.nextLine();

            this.p1 = new Ihminen(aabel, this.lukija);
            this.p2 = this.aly1;

        } else if (valinta == 3) {
            this.p1 = this.aly1;
            this.p2 = this.aly2;
        }
        peli();
        voittaja();

        System.out.println(this.aly1);
        System.out.println(this.aly2);
    }

    public int valitsetekoalytaiihminen() {
        System.out.println("Vaihtoehdot: ");
        System.out.println("  Pelaa kaveria vastaan(1)");
        System.out.println("  Pelaa tietokonetta vastaan(2)");
        System.out.println("  Kone v Kone(3)");
        System.out.println("Kuinka valitset(1-3)?");
        int valinta = 0;
        while (true) {
            valinta = Integer.parseInt(this.lukija.nextLine());
            if (valinta >= 1 && valinta <= 3) {
                return valinta;
            } else {
                System.out.println("valitse 1-3!");

            }
        }
    }

    public void aloitus() {
        System.out.println("Tervetuloa tikkupeliin!");

        while (true) {

            System.out.println("Kuinka monta tikkua pöydällä on aluksi (10-100)?");
            int haluttumaara = Integer.parseInt(this.lukija.nextLine());

            if (haluttumaara >= 10 && haluttumaara <= 100) {
                int tikkuja = haluttumaara;
                this.kaikkitikut = tikkuja;
                this.jaljella = this.kaikkitikut;
                break;
            } else {
                System.out.println("Valitse luku väliltä 10-100.");
            }
        }
    }

    public void peli() {
        this.p1.nollaa();
        this.p2.nollaa();

        while (!loppuu()) {
            p1();
            p2();
        }

    }

    //pelikierros ilman tulostuksia
    public void silentpeli() {
        this.p1.nollaa();
        this.p2.nollaa();

        Random random = new Random();
        int rando = random.nextInt(2);
        if (rando == 0) {
            while (!loppuu()) {

                p2silent();
                p1silent();
            }
        } else {

            while (!loppuu()) {
                p1silent();
                p2silent();
            }
        }
    }

    public void p1() {

        while (true) {
            if (this.jaljella == 0) {
                this.p1.setVoittaja();
                this.p2.sethav();
                break;
            } else {

                System.out.println("vuorossa: " + this.p1.getNimi());
                tilanne();
                this.jaljella = this.jaljella - this.p1.pelaa(this.jaljella);
                break;
            }
        }
    }

    //pelikierros ilman tulostuksia
    public void p1silent() {

        while (true) {
            if (this.jaljella == 0) {
                this.p1.setVoittaja();
                this.p2.sethav();
                break;
            } else {

                this.jaljella = this.jaljella - this.aly1.spelaa(this.jaljella);
                break;
            }
        }
    }

    public void p2() {
        while (true) {
            if (this.jaljella == 0) {
                this.p2.setVoittaja();
                this.p1.sethav();
                break;
            } else {

                System.out.println("vuorossa: " + this.p2.getNimi());
                tilanne();
                this.jaljella = this.jaljella - this.p2.pelaa(this.jaljella);
                break;
            }
        }
    }

    //pelikierros ilman tulostuksia
    public void p2silent() {
        while (true) {
            if (this.jaljella == 0) {
                this.p2.setVoittaja();
                this.p1.sethav();
                break;
            } else {
                this.jaljella = this.jaljella - this.aly2.spelaa(this.jaljella);
                break;
            }
        }
    }

    public void tilanne() {
        System.out.println("Pöydällä on " + this.jaljella + " tikkua.");
    }

    public boolean loppuu() {
        return (this.p1.getVoittaja() == true || this.p2.getVoittaja() == true);
    }

    public void voittaja() {
        if (p1.getVoittaja() == true) {
            System.out.println(p1.getNimi() + " Voitti!");
        } else if (p2.getVoittaja() == true) {
            System.out.println(p2.getNimi() + " Voitti!");
        } else {
            System.out.println("Tasapeli! (miten ihmeessä...?)");

        }
    }

    //asettaa tekoälylle voittostatuksen mukaisesti palloja hattuun
    public void masterVoittaja() {
        if (this.aly1.getVoittaja()) {
            this.aly1.voittajanPallomeri();
            this.aly1.voitapisteita();
        } else {
            this.aly2.lamePallomeri();
            this.aly2.voitapisteita();
        }

        if (this.kierros % 10000 == 0) {
            System.out.println("Ladataan tekoälyä... Eteneminen: " + this.kierros / 10000 + "/10");

        }
        this.kierros++;
    }
}


public class Pallo {

    private int palnro;

    public Pallo(int nro) {
        this.palnro = nro;
    }

    public int getnro(){
        return this.palnro;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.palnro);
    }
}


public class Main {

    public static void main(String[] args) {
        Tikkupeli tik = new Tikkupeli();

        tik.run();

        
    }
}


