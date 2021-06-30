/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Ordinaite;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.Locale;
import studijosKTU.*;



/**
 *
 * @author rordi
 */
public class KnygaBandymai {
    
    ListKTUx<Knyga> knygos = new ListKTUx<>(new Knyga());
    ListKTUx<Knyga> knygos2 = new ListKTUx<>(new Knyga());
   
    
    void AtskirosKnygos()
    {
        Knyga k1 = new Knyga("Literatūra", "Tomas-Tomaitis", "Romanas", 
                1999, 200, 20);
        Knyga k2 = new Knyga("Eilėraščiai", "Jonas-Jonaitis", "Poezija",
                + 2000, 202, 15);
        Knyga k3 = new Knyga();
        Knyga k4 =new Knyga();
        
        k3.parse("Poezija Pranas Autobiografija 2005 100 15");
        k4.parse("Biologija Tomas-Jonaitis Mokomoji 2015 150 18");
        
        int vidurkis1; int vidurkis2;
        vidurkis1 = (k1.ImtiPuslapius()+k2.ImtiPuslapius())/2;
        vidurkis2 = (k3.ImtiPuslapius()+k4.ImtiPuslapius())/2;
        Ks.oun(k1);
        Ks.oun(k2);
        Ks.oun(k3);
        Ks.oun(k4);
        System.out.println("Pirmų dviejų knygų puslapių skaičiaus vidurkis: " 
                + vidurkis1);
        System.out.println("Paskutinių dviejų knygų puslapių skaičiaus vidurkis: "
                + vidurkis2);

    }
    
    void PridetiNari()
    {
        for (int i=0; i<3; i++)
        {
            knygos.add(new Knyga("Literatūra", "Vincas-Krėvė", "Romanas", 1999+i, 
             800-i*10, 12+i*2));
        }
        
    Knyga k2 = new Knyga("AKnyga Adomas Literatūra 2010 600 10,5");
    Knyga k3 = new Knyga("Kūriniai Petras Biografija 2010 600 10,5");
    knygos.add("Skaitiniai Petras Literatūra 2000 600 10,5");
    knygos.add("Pasakos Salomėja Poezija 2010 800 35,6");
   
    knygos.add(k3);
    knygos.add(k2);
    knygos.add(k2);
    knygos.println("Naujas knygų sąrašas");
    //Tikrinami set, remove, indexOf, contains, minmMax metodai
    knygos.remove(3);
    knygos.println("Naujas knygų sąrašas be pašalinto nario");
    knygos.set(2, k2);
    knygos.println("Set motodas");
    knygos.save("Rezultatai.txt");
    
    if(knygos.contains(k3))
    {
        System.out.println("Saraše yra autoriaus " + k3.ImtiAutoriu()+
                " knyga " + k3.ImtiPavadinima());
        System.out.println("Šios knygos indeksas: "+ knygos.indexOf(k3));
    }
    else 
    {
         System.out.println("Saraše nėra autoriaus " + k3.ImtiAutoriu()+
                " knygos " + k3.ImtiPavadinima());
    }
   
        System.out.println("Paskutinis indeksas knygos "+k2.ImtiPavadinima()
                +": "+knygos.lastIndexOf(k2));
        knygos.minMax();
        System.out.println("Knygos surikiuotos minMax metodu, pagal kainą");
        knygos.println();
         
    
    }
    void Patikrintirikiavima()
    {
      
       knygos.load("Rezultatai.txt");
       knygos.println("Pradinis rinkinys:");
       knygos.sortBuble(Knyga.pagalPavadinima);
       knygos.println("Surikiuotas pagal knygos pavadinimą");
       knygos.sortBuble(Knyga.pagalKainą);
       knygos.println("Surikiuotas pagal kainą didėjimo tvarka");
       knygos.sortBuble();
       knygos.println("Rūšiavimas pagal CompareTo metodą - pagal kainą, mažėjimo "
               + "tvarka");
       knygos.sortBuble(Knyga.pagalPuslapiųSk);
       knygos.println("Rikiuoja pagal puslapių sk.didėjimo tvarka");
    }
    
    void PatikrintiAtranka()
    {
        
        KnygaAtranka atrinkti = new KnygaAtranka();
        atrinkti.visos.load("Rezultatai.txt");
        atrinkti.visos.println("Pradinis rinkinys");
        
        knygos = atrinkti.atrinktiPagalPsl(200, 600);
        knygos.println("Knygos, kurių puslapių skaičius yra nuo 200 iki 600");
        
        knygos = atrinkti.atrinktiNaujausias();
        knygos.println("Naujausios knygos");
        
        knygos = atrinkti.atrinktiPagalZanra("Poezija");
        knygos.println("Knygos pagal pasirnktą žanrą: poezija");
        
        knygos = atrinkti.atrinktiPavadinimus("A");
        knygos.println("Knygos, kurių pirma pavadinimo raidė: A");
        
    
    }
    void PatikrintiVienodus()
    {
        KnygaAtranka atrinkti = new KnygaAtranka();
        knygos.load("Rezultatai.txt");
        knygos.println("Pradinis rinkinys:");
        knygos2 = knygos.clone();
        knygos2.remove(2);
        knygos2.remove(5);
        knygos2.add("Saulėlydis Stephanie Literatūra 2010 600 12,5");
        knygos2.println("Antras rinkinys");
       
        atrinkti.BendruElementuRadimas(knygos, knygos2);
        atrinkti.bendri.load("Rezultatai.txt");
        atrinkti.bendri.println("Bendri elementai");

    }
    
    public String loadAndPrint(File fName, JTextArea ta) {
	String klaidosKodas = null;
	try {
		knygos.clear();
		BufferedReader fReader =  new BufferedReader(new FileReader(fName));
		String line;
			
		ta.append("     D u o m e n y s iš failo <" + fName.getName() + ">\n\n");

		while ((line = fReader.readLine()) != null) {
			knygos.add(new Knyga(line));
			ta.append(line + "\n");
		}
		fReader.close();
	} catch (IOException e) {
		klaidosKodas = "Failo " + fName.getName() + " skaitymo klaida";
	}
	return klaidosKodas;
	}
    
    @Override
    public String toString() {
		String kolekcija = "";
        for (Knyga k: knygos) {
	        kolekcija += k.toString() + "\n";
		}
		return kolekcija;
    };
    
     public static void main(String... args) {
       

      // new KnygaBandymai().PridetiNari();
       // new KnygaBandymai().Patikrintirikiavima();
       // new KnygaBandymai().AtskirosKnygos();
       //  new KnygaBandymai().PatikrintiAtranka();
        new KnygaBandymai().PatikrintiVienodus();
         
    }
    
    
}
