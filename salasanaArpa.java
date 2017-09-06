
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class SalasananArpoja {

    private ArrayList<String> salasana;
    private Random random;
    private int pituus;
    private double merkkeja;
    private double kirjaimia;
    private double caps;
    private double numeroita;

    public SalasananArpoja() {
        this.random = new Random();
        this.pituus = 20;
        this.merkkeja = 100;
        this.kirjaimia = 25;
        this.numeroita = 65;
        this.caps = 50;
        this.salasana = new ArrayList<>();
    }

    public void setPituus(int pit) {
        this.pituus = pit;
    }
    
    public int getPituus(){
        return this.pituus;
    }
    
    public void setup() {
        Scanner lukija = new Scanner(System.in);

        System.out.println("[Default arvot: k:25 K:25 n:15 m:35]");
        System.out.println("------------------------------------");

        System.out.println("Anna pienten kirjainten osuus: (0-100%)");
        int newKirjaimia = Integer.parseInt(lukija.nextLine());

        System.out.println("Anna ISOJEN KIRJAINTEN osuus: (0-100%)");
        int newKIRJAIMIA = Integer.parseInt(lukija.nextLine());

        System.out.println("Anna numeroiden osuus: (0-100%)");
        int newNumeroja = Integer.parseInt(lukija.nextLine());

        System.out.println("Anna merkkien osuus: (0-100%)");
        int newMerkkeja = Integer.parseInt(lukija.nextLine());

        double total = newKirjaimia + newKIRJAIMIA + newNumeroja + newMerkkeja;

        if (total == 0.00) {
            System.out.println("Liikaa nollia, default arvot käytössä");
            this.merkkeja = 100;
            this.kirjaimia = 25;
            this.numeroita = 65;
            this.caps = 50;
        } else {
            double kPro = ((newKirjaimia / total));
            double KPro = ((newKIRJAIMIA / total));
            double nPro = ((newNumeroja / total));
            double mPro = ((newMerkkeja / total));

            System.out.println("----------------Prosenttiosuudet:----------------------------");
            System.out.println("k: " + kPro + ", K: " + KPro + ", n: " + nPro + ", m: " + mPro);
            System.out.println("-------------------------------------------------------------");
            this.kirjaimia = (kPro * 100);
            this.caps = (KPro * 100) + this.kirjaimia;
            this.numeroita = (nPro * 100) + this.caps;
            this.merkkeja = (mPro * 100) + this.numeroita;
        }
    }

    public String luoSalasana() {

        this.salasana.clear();

        ArrayList<Integer> kirjainNumeroLista = new ArrayList<>();
        ArrayList<Integer> numeroNumeroLista = new ArrayList<>();
        ArrayList<Integer> merkkiNumeroLista = new ArrayList<>();

        int y = 0;
        while (y != this.pituus) {
            kirjainNumeroLista.add(random.nextInt(26));
            numeroNumeroLista.add(random.nextInt(9));
            merkkiNumeroLista.add(random.nextInt(38));
            y++;
        }

        int pituusola = 0;

        while (pituusola != this.pituus) {

            double divider = (double) random.nextInt(101);

            if (divider >= 0 && divider <= this.kirjaimia) {
                String kirjain = String.valueOf(("abcdefghijklmnopqrstuvwxyz".charAt(kirjainNumeroLista.get(pituusola))));
                this.salasana.add(kirjain);
                pituusola++;
            }

            if (divider >= (this.kirjaimia + 1) && divider <= this.caps) {
                String KIRJAIN = String.valueOf(("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(kirjainNumeroLista.get(pituusola))));
                this.salasana.add(KIRJAIN);
                pituusola++;
            }

            if (divider >= (this.caps + 1) && divider <= this.numeroita) {
                String numero = String.valueOf("0123456789".charAt(numeroNumeroLista.get(pituusola)));
                this.salasana.add(numero);
                pituusola++;
            }

            if (divider >= (this.numeroita + 1) && divider <= this.merkkeja) {
                String merkki = String.valueOf("!\"@#£¤$%€&/{([)]=}?\\+´`¨~^'*-_.,:;<>|§½".charAt(merkkiNumeroLista.get(pituusola)));
                this.salasana.add(merkki);
                pituusola++;
            }

        }

        return kirjoita();

    }

    public void getInfo() {
        System.out.println("lower: " + this.kirjaimia);
        System.out.println("Upper: " + this.caps);
        System.out.println("!#¤%&: " + this.merkkeja);
        System.out.println("12345: " + this.numeroita);
    }

    public String kirjoita() {

        String sana = "";

        sana = String.join("", this.salasana);
        return sana;

    }

    
  /*  public String sanakirjasalasana() {
        String fileName = "words.txt";
        ArrayList<String> kaikkisanat = new ArrayList();
        
        String line = null;

        try {
            FileReader fileReader
                    = new FileReader(fileName);

            BufferedReader bufferedReader
                    = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                kaikkisanat.add(line);
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");
        } 
*/

            
        int numero = this.random.nextInt(kaikkisanat.size());
        String sana = kaikkisanat.get(numero);
        return sana;
    }

}




public class Tulostaja {

    private PrintWriter out;

    public Tulostaja(String nimi) throws FileNotFoundException {
        this.out = new PrintWriter(nimi + ".txt");
    }

    public void Tulosta(String teksti){
        this.out.println(teksti);
    }

    public void disco(){
        this.out.close();
    }
}

public class Kayttis {

    private Scanner scanner;
    private SalasananArpoja arpa;
    private int montako;
    private Tulostaja tulostaja;
    private ArrayList<String> awrotut;
    private String fielname;
    private boolean myosNimet;
    private int kirjasanoja;

    public Kayttis() {
        this.scanner = new Scanner(System.in);
        this.arpa = new SalasananArpoja();
        this.montako = 10;
        this.awrotut = new ArrayList();
        this.myosNimet = true;
        this.fielname = "";
        this.kirjasanoja = 0;
    }

    public void luo() {

        while (this.montako > 0) {
            Random rand = new Random();
            int randomchance = rand.nextInt(101);
            String sana = null;
            if (randomchance > this.kirjasanoja) {
                sana = this.arpa.luoSalasana();
            } else {
       //        sana = this.arpa.sanakirjasalasana();
            }
            if (this.myosNimet) {
       //         KayttajanimenArpoja ka = new KayttajanimenArpoja();
       //         String usern = ka.arvo();
       //         sana = usern + "\t" + " : " + "\t" + sana;
            }

            System.out.println(sana);
            this.awrotut.add(sana);
            this.montako--;
        }
    }

    public ArrayList<String> getArvotut() {
        return this.awrotut;
    }

    public int getKirjasanat() {
        return this.kirjasanoja;
    }

    public void run() throws FileNotFoundException {
        System.out.println("Setup? [y/n]");

        if (this.scanner.nextLine().equals("y")) {
            setup();
        }
        luo();
        tallenna();
    }

    public void setPituus(int pit) {
        this.arpa.setPituus(pit);
    }

    public int getPituus() {
        return this.arpa.getPituus();
    }

    public void setup() {
        System.out.println("Anna pituus: ");

        int pit = Integer.parseInt(this.scanner.nextLine());
        this.arpa.setPituus(pit);

        System.out.println("montako? ");

        int kertoja = Integer.parseInt(this.scanner.nextLine());;
        this.montako = kertoja;

      //  System.out.println("Ei käyttäjänimiä? [y/n]");
       // if (this.scanner.nextLine().equals("y")) {
            this.myosNimet = false;
        //}

    //    System.out.println("Sanakirja-salasanojen osuus? [0-100%]");

   //     int sanakirjoja = Integer.parseInt(this.scanner.nextLine());

     //   if (sanakirjoja < 0 || sanakirjoja > 100) {
     //       System.out.println("Väärin, luku väliltä 0-100, ääliö.");
            this.kirjasanoja = 0;
    //    } else {
      //      this.kirjasanoja = sanakirjoja;
        //}

        System.out.println("Tiedostonimi: ");
        this.fielname = this.scanner.nextLine();

        System.out.println("Full setup? (kirjainten, numeroiden ja merkkien osuudet) [y/n]");

        if (this.scanner.nextLine().equals("y")) {
            this.arpa.setup();
        }
    }

    public void tallenna() throws FileNotFoundException {

        if (this.fielname.isEmpty()) {
            this.fielname = arvoFilename(10);
        }

        String fn = this.fielname;
        String textola = "";

        this.tulostaja = new Tulostaja(fn);

        for (String gi : this.awrotut) {
            this.tulostaja.Tulosta(gi);
        }
        this.tulostaja.disco();
    }

    public String arvoFilename(int pit) {
        int pituusola = 0;
        int target = pit;
        ArrayList<String> filename = new ArrayList();
        ArrayList<Integer> kirjainNumeroLista = new ArrayList<>();
        int y = 0;
        Random random = new Random();
        while (y != target) {
            kirjainNumeroLista.add(random.nextInt(26));
            y++;
        }
        while (pituusola != target) {

            String kirjain = String.valueOf(("abcdefghijklmnopqrstuvwxyz".charAt(kirjainNumeroLista.get(pituusola))));
            filename.add(kirjain);
            pituusola++;
        }
        String fn = "";
        fn = String.join("", filename);
        return fn;
    }

}



public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        
        Kayttis k = new Kayttis();
        
        k.run();
    }
}

