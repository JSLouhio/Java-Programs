import java.util.ArrayList;
import java.util.HashMap;



/*
    Luokka kuvastamaan yksittäistä shakkilaudan ruutua.
*/

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /*
        Kertoo ruudun x-koordinaatin
    */
    public int getX() {
        return this.x;
    }

    /*
        Kertoo ruudun y-koordinaatin
    */
    public int getY() {
        return this.y;
    }

    /*
        Kertoo, ovatko kaksi ruutua samat verraten niiden koordinaatteja.
    */
    public Boolean sama(Point p) {
        return (this.x == p.getX() && this.y == p.getY());

    }
    
    /*
        Antaa kirjallisen esityksen ruudusta.
    */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}


/*
    Luokka, joka kuvastaa shakkilaudan ritaria, eli hevosta.
    Sijainti = kohta laudalla, jossa hevonen kulloinkin sijaitsee.
    pointistaKielletyt = HashMap, jonka avaimena laudan ruutu, ja sisältönä liikkeet, jotka johtavat umpikujaan.
*/


public class Nihti {

    private Point sijainti;
    private ArrayList<Point> kuljetut;
    private final String name;
    private HashMap<Point, ArrayList<Point>> pointistaKielletyt;


    public Nihti() {
        this.name = "Sir Horse";
        this.kuljetut = new ArrayList();
        this.pointistaKielletyt = new HashMap();
    }

    /*
        asettaa hevosen p:n kuvastamaan ruutuun.
    */
    public void setSijainti(Point p) {

        if (this.kuljetut.contains(p)) {
            System.out.println("Annettu sijainti on jo käyty, ei kelpaa");
            this.sijainti = new Point(-999, -999);
        } else {
            this.sijainti = p;
            this.kuljetut.add(p);
        }
    }

    /*
        palauttaa sijainnin laudalla.
    */
    public Point getSijainti() {
        return this.sijainti;
    }

    /*
        palautaa listan kuljetuista ruuduista.
    */
    public ArrayList<Point> getKuljetut() {
        return this.kuljetut;
    }

    /*
       Palauttaa hevosen edelliseen ruutuun, ja postaa nykyisen ruudun kuljetuista, lisäten kiellettyihin liikkeisiin sellaiset, jotka johtaisivat umpikujaan.
    */
    public void edellinen() {
        if (this.kuljetut.isEmpty()) {
        } else if (this.kuljetut.size() > 1) {
            if (!this.pointistaKielletyt.containsKey(this.kuljetut.get(this.kuljetut.size() - 2))) {
                ArrayList<Point> uudet = new ArrayList();
                uudet.add(this.sijainti);
                this.pointistaKielletyt.put(this.kuljetut.get(this.kuljetut.size() - 2), uudet);
            } else {
                ArrayList<Point> uudet = this.pointistaKielletyt.get(this.kuljetut.get(this.kuljetut.size() - 2));
                uudet.add(this.sijainti);
                this.pointistaKielletyt.replace(this.kuljetut.get(this.kuljetut.size() - 2), uudet);
            }

            this.sijainti = this.kuljetut.get(this.kuljetut.size() - 2);
            this.kuljetut.remove(this.kuljetut.size() - 1);
        }
    }

    /*
        Kertoo, onko hevosella ruutuja, joista ei saa siirtyä toiseen ruutuun.
    */
    public boolean kieltoja() {
        return !this.pointistaKielletyt.isEmpty();
    }

    /*
        Poistaa kiellot ruudulta a.
    */
    public void poistakiellot(Point a) {
        this.pointistaKielletyt.remove(a);
    }

    /*
        Kertoo, onko ruudulle p kiellettyjä siirtoja.
    */
    public boolean kieltojapointille(Point p) {
        return this.pointistaKielletyt.keySet().contains(p);
    }

    /*
        Palauttaa listan kielletyistä siirroista ruudulle p.
    */
    public ArrayList getKielletyt(Point p) {
        return this.pointistaKielletyt.get(p);
    }
}


/*
    Luokka kuvastamaan shakkilautaa.
*/

public class Lauta {

    private ArrayList<Point> ruudut;

    /*
        Luo laudaksi Listan ruutuja [(1,1)..(5,5)]
    */
    
    public Lauta() {
        this.ruudut = new ArrayList<>();

        int a = 1;
        int b = 1;

        while (a <= 5) {

            while (b <= 5) {
                ruudut.add(new Point(a, b));
                b++;
            }
            a++;
            b = 1;
        }
    }

    /*
        Palauttaa laudan ruudut.
    */
    public ArrayList<Point> getRuudukko() {
        return this.ruudut;
    }

    /*
        Poistaa ruudun p laudalta (kuvastaa hevosen käyntiä ruudussa).
    */
    
    public void poista(Point p) {
        ArrayList<Point> uusi = new ArrayList<>();

        int x = p.getX();
        int y = p.getY();

        for (Point pp : this.ruudut) {
            if (pp.getX() == x && pp.getY() == y) {

            } else {
                uusi.add(pp);
            }
        }
        this.ruudut = uusi;
    }

    /*
        Lisää ruudun p takaisin laudalle.
    */
    public void lisaa(Point p) {
        this.ruudut.add(p);
    }

    /*
        Tarjoaa kirjallisen esityksen shakkilaudasta.
    */
    public String toString() {
        String s = "";
        for (Point p : this.ruudut) {
            s = s + " " + p.toString();
        }
        return s;
    }

    /*
        Palauttaa ruudussa p seisovalle hevoselle mahdolliset vaihtoehdot seuraavaksi ruuduksi.
    */
    public ArrayList<Point> getVaihtoehdot(Point p) {
        ArrayList<Point> ve = new ArrayList();

        for (Point x : this.ruudut) {
            if (horsemove(x, p)) {
                    ve.add(x);
            }
        }
        return ve;
    }

    /*
        Kertoo, ovatko ruudut a ja b hevosenloikkaaman päässä toisistaan.
    */
    public Boolean horsemove(Point a, Point b) {

        int ax = a.getX();
        int ay = a.getY();
        int bx = b.getX();
        int by = b.getY();

        return (ax == bx + 2 && ay == by + 1
                || ax == bx + 1 && ay == by + 2
                || ax == bx - 1 && ay == by - 2
                || ax == bx - 2 && ay == by - 1
                || ax == bx - 1 && ay == by + 2
                || ax == bx - 2 && ay == by + 1
                || ax == bx + 2 && ay == by - 1
                || ax == bx + 1 && ay == by - 2);
    }
}



public class KnightTour {

    /*
        Palauttaa listan ruuduista, jotka ovat mahdollisia siirtoja laudan l ruudussa p seisovalle hevoselle.
     */
     
    public static ArrayList<Point> pointinvaihtoehdot(Point p, Lauta l) {
        return l.getVaihtoehdot(p);
    }

    /*
        päämetodi, joka käy läpi kaikki mahdolliset vaihtoehdot, aloittaen ruudusta (1,1) (vasen yläkulma)
     */
    public static int tour() {

        /*
            Luo laudan ja hevosen.
         */
        Nihti hevonen = new Nihti();
        Lauta shakki = new Lauta();

        /*
            Muuttuja i laskee montako onnistunutta kierrosta on suoritettu. Kaikkien vaihtoehtoejen käytyä läpi, metodi palauttaa i:hin kertyneen arvon.
         */
        int i = 0;

        /*
            Sijoittaa pelinappulan laudan ensimmäiseen ruutuun. (Vasen yläkulma, tai 'Luoteiskulma')
         */
        hevonen.setSijainti(shakki.getRuudukko().get(0));
        shakki.poista(shakki.getRuudukko().get(0));

        /*
            Kyllä/ei -arvo onko kaikki vaihtoehtoiset reitit käyty läpi.
            Loop jatkaa pyörimistä, kunnes on.
         */
        boolean kaikkikayty = false;

        while (kaikkikayty == false) {

            ArrayList<Point> hevonvaihtoehdot = pointinvaihtoehdot(hevonen.getSijainti(), shakki);

            Point next = new Point(0, 0);

            /*
                Jos kaikki hevosen vaihtoehdot ovat sille kielletyjä (johtavat umpikujaan), siirtää hevosen edelliseen sen käymään ruutuun.
                Edelliseen ruutuun siirrytään myös, jos vaihtoehtoja ei muutenkaan ole.
             */
            boolean kaikkikiellettyja = false;

            while (true) {

                if (hevonvaihtoehdot.isEmpty()) {
                    shakki.lisaa(hevonen.getSijainti());
                    hevonen.edellinen();
                } else {
                    break;
                }

                hevonvaihtoehdot = pointinvaihtoehdot(hevonen.getSijainti(), shakki);
            }

            if (hevonen.kieltojapointille(hevonen.getSijainti())) {

                boolean kaikkikielletty = true;
                for (Point p : hevonvaihtoehdot) {
                    if (!hevonen.getKielletyt(hevonen.getSijainti()).contains(p)) {
                        kaikkikielletty = false;
                        next = p;
                    }
                }

                if (kaikkikielletty) {
                    if (hevonen.getKuljetut().size() == 1) {
                        kaikkikayty = true;
                    }
                    shakki.lisaa(hevonen.getSijainti());
                    hevonen.poistakiellot(hevonen.getSijainti());
                    hevonen.edellinen();
                    kaikkikiellettyja = true;
                }
            } else {
                /*
                    eli tähän asti on päästy, jos vaihtoehdot eivät ole loppuneet kesken, eikä kiellettyjä ruutuja
                    asettaa hevosen seuraavaksi liikkeeksi ensimmäisen ruudun mahdollisista vaihtoehdoista.
                 */
                next = hevonvaihtoehdot.get(0);
            }

            /*
                Jos kaikki vaihtoehdot eivät ole kiellettyjä, poistaa next -ruudun laudalta ja asettaa hevon sinne.
                Jos Kaikki vaihtoehdot ovat kiellettyjä, palataan tässä vaiheessa loopin alkuun.
             */
            if (!kaikkikiellettyja) {
                shakki.poista(next);
                hevonen.setSijainti(next);
            }
            /*
                Jos hevonen on kulkenut 25 ruudun läpi, on se toteuttanut kierroksen. Muuttuja i laskee tämän pisteeksi.
             */
            if (hevonen.getKuljetut().size() == 25) {
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {

        int toureja = tour();

        System.out.println("Yhtensä mahdollisia reittejä, joilla Hevonen käy läpi kaikki 5x5 shakkilaudan ruudut: " + toureja);

    }

}
