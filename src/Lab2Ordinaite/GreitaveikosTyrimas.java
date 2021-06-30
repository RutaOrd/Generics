/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Ordinaite;

/**
 *
 * @author rordi
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import studijosKTU.*;

public class GreitaveikosTyrimas {
        Knyga[] knygos;
	ListKTU<Knyga> aSeries = new ListKTU<>();
	Random ag = new Random();  // atsitiktinių generatorius
	int[] tiriamiKiekiai = {2_000, 4_000, 8_000, 16_000, 32_000};


	void generuotiKnygas(int kiekis) {
		String[][] am = { 
			{"Stephen-King", "Kerė", "Klounas", "Tai", "Vaikai"},
			{"Robert-Bloch", "Burtininkas", "Psichopatas1", "Apie", "Mes", "Istorija"},
			{"Antanas-Škėma", "Balta-Drobulė", "Biografija"},
                        {"Stephen-Meyer", "Saulėlydis", "Jaunatis", "Užtemimas"},
			{"Arthur-Doyle", "Keturi", "Šerlokas-Holmsas", "Biografija", "Dingęs"},
			{"Edgar-Poe", "Varnas", "Išdavikė", "Juoda katė"} };
                
                String[] zanras ={"romanas", "poezija", "drama", "trileris", 
                    "siaubo", "Pasakos"};
                knygos = new Knyga[kiekis];
                ag.setSeed(2016);
                for (int i = 0; i < kiekis; i++) {
			int au = ag.nextInt(am.length);        
			int pav = ag.nextInt(am[au].length - 1) + 1;
                        int za = ag.nextInt(zanras.length);
			knygos[i] = new Knyga(am[au][0], am[au][pav], zanras[za],
					2000 + ag.nextInt(20), 
					300 + ag.nextInt(200), 
					15 + ag.nextDouble() * 2); 
		} 
                //Gali but klaida su aseriesx
		Collections.shuffle(Arrays.asList(knygos));
		aSeries.clear();
		for (Knyga a : knygos) {
			aSeries.add(a);
		}
        }
        
        void paprastasTyrimas(int elementųKiekis) {
		 //Paruošiamoji tyrimo dalis
		long t0 = System.nanoTime();
		generuotiKnygas(elementųKiekis);
		ListKTU<Knyga> aSeries2 = aSeries.clone();
		ListKTU<Knyga> aSeries3 = aSeries.clone();
		ListKTU<Knyga> aSeries4 = aSeries.clone();
                ListKTU<Knyga> aSeries5 = aSeries.clone();
		long t1 = System.nanoTime();
		System.gc();
		System.gc();
		System.gc();
		long t2 = System.nanoTime();

		//  Greitaveikos bandymai ir laiko matavimai
		aSeries.sortJava();
		long t3 = System.nanoTime();
		aSeries2.sortJava(Knyga.pagalKainą);
		long t4 = System.nanoTime();
		aSeries3.sortBuble();
		long t5 = System.nanoTime();
		aSeries4.sortBuble(Knyga.pagalKainą);
		long t6 = System.nanoTime();
                aSeries5.minMax();
		long t7 = System.nanoTime();
		Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
				(t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
				(t4 - t3) / 1e9, (t5 - t4) / 1e9, (t6 - t5) / 1e9, 
                                (t7 - t6) / 1e9);
	}
        
        void apdorojimas(int elemKiekis)
        {
            long t0 = System.nanoTime();
            generuotiKnygas(elemKiekis);
            ListKTU<Knyga> aSeries2 = aSeries.clone();
            ListKTU<Knyga> aSeries3 = aSeries.clone();
            
            long t1 = System.nanoTime();
            System.gc();
            System.gc();
            System.gc();
            long t2 = System.nanoTime();
            
            aSeries2.contains(aSeries.get(286));
            long t3 = System.nanoTime();
            
            aSeries3.indexOf(aSeries.get(190));
            long t4 = System.nanoTime();
            
           Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elemKiekis,
				(t1 - t0) / 1e6, (t2 - t1) / 1e6, (t3 - t2) / 1e6,
				(t4 - t3) / 1e6);
            
            
              
            
        
        }
        void tyrimoPasirinkimas() {
		long memTotal = Runtime.getRuntime().totalMemory();
		Ks.oun("memTotal= " + memTotal); 
		generuotiKnygas(300);
		for (Knyga a : aSeries) {
			Ks.oun(a);
		}
		Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
		Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
		Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
		Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
		Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
		Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
                Ks.oun("7 - Rūšiavimas minMax su Comparator");
		Ks.ouf("%6d %7d %7d %7d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4, 5, 6, 7);
		for (int n : tiriamiKiekiai) {
			paprastasTyrimas(n);
		}
		Ks.oun("Rikiavimo metodų greitaveikos tyrimas baigtas.");
		
                Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
		Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
		Ks.oun("3 - Contains metodas");
                Ks.oun("4 - IndexOf metodas");
                Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
		for (int n : tiriamiKiekiai) {
			apdorojimas(n);
		}
		Ks.oun("Sąrašo apdorojimų metodų greitaveikos tyrimas baigtas.");
	}

	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("LT"));
		new GreitaveikosTyrimas().tyrimoPasirinkimas();
	}
}
