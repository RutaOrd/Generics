/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Ordinaite;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import studijosKTU.*;

/**
 *
 * @author rutord
 */
public class Knyga implements KTUable<Knyga>{
    
    final static private int priimtinųMetųRiba = 2000;
    final static private int esamiMetai = LocalDate.now().getYear();

    final static private double valKursas = esamiMetai <= 2014 ? 1 : 1 / 3.4528;
    final static private double minKaina = 10.0;
    final static private double maxKaina = 200.0;
    
    private String pavadinimas;
    private String autorius;
    private String zanras;
    private int isleidimoMetai;
    private int puslapiuSk;
    private double kaina;
    
    public Knyga()
    {
    }
            
    public Knyga(String pavadinimas, String autorius, String zanras, 
            int isleidimoMetai , int puslapiuSk, double kaina)
    {
    
    this.pavadinimas=pavadinimas;
    this.autorius=autorius;
    this.zanras=zanras;
    this.isleidimoMetai=isleidimoMetai;
    this.puslapiuSk=puslapiuSk;
    this.kaina=kaina;
    }
    
     @Override
    public String toString()
    {
    return String.format("%-16s %-15s %-12s %5d %-5d %4.2f %s", pavadinimas, autorius, 
            zanras, isleidimoMetai, puslapiuSk, kaina, validate());
    
    }
   
    public Knyga(String dataString)
    {
       this.parse(dataString);
    }
    
    @Override
    public Knyga create (String dataString)
    {
       return new Knyga(dataString);
    }
    
    


    @Override
    public String validate() {
       String klaidosTipas = "";
	if (isleidimoMetai < priimtinųMetųRiba || isleidimoMetai > esamiMetai) {
		klaidosTipas = "Netinkami išleidimo metai, turi būti ["
				+ priimtinųMetųRiba + ":" + esamiMetai + "]";
		}
	if (kaina < minKaina || kaina > maxKaina) {
		klaidosTipas += " Kaina už leistinų ribų [" + minKaina
				+ ":" + maxKaina + "]";
	}
	return klaidosTipas;
    }

    @Override
    public final void parse(String dataString) {
     
        try {   
			Scanner ed = new Scanner(dataString); 
			
			pavadinimas = ed.next();
			autorius = ed.next();
			zanras = ed.next();
			isleidimoMetai = ed.nextInt();
                        puslapiuSk = ed.nextInt();

                        kaina = ed.nextDouble();
                        
			
		} catch (InputMismatchException e) {
			Ks.ern("Blogas duomenų formatas -> " + dataString);
		} catch (NoSuchElementException e) {
			Ks.ern("Trūksta duomenų apie knygas -> " + dataString);
		}
    }
     public String ImtiPavadinima()
    {return pavadinimas;}
    public String ImtiAutoriu()
    {return autorius; }
    public String ImtiZanra()
    {return zanras;}
    public int ImtiMetus()
    {return isleidimoMetai;}
    public int ImtiPuslapius()
    {return puslapiuSk; }
   public void setPuslapiuSk(int puslapiuSk)
    { 
        this.puslapiuSk=puslapiuSk;
    }
    public double ImtiKaina()
    {return kaina;}

//    Pagal kaina
    @Override
    public int compareTo(Knyga o) {

         double kita= o.ImtiKaina();
         return Double.compare(kita, kaina);
        
    }
//    Pagal pavadinima
    public final static Comparator<Knyga> pagalPavadinima = new Comparator<Knyga>() {
		@Override
		public int compare(Knyga k1, Knyga k2) {
			int cmp = k1.ImtiPavadinima().compareTo(k2.ImtiPavadinima());
			return cmp;
		}
	};
//    Pagal kainą nuo mažiausio
    public final static Comparator<Knyga> pagalKainą = new Comparator<Knyga>() {
		@Override
		public int compare(Knyga kn1, Knyga kn2) {
			double k1 = kn1.ImtiKaina();
			double k2 = kn2.ImtiKaina();
			
			if (k1 < k2) {
				return -1;
			}
			if (k1 > k2) {
				return 1;
			}
			return 0;
		}
	};

    //Pagal puslapių sk. nuo mažiausiai
   public final static Comparator<Knyga> pagalPuslapiųSk = new Comparator<Knyga>() {
		@Override
		public int compare(Knyga kn1, Knyga kn2) {
			int k1 = kn1.ImtiPuslapius();
			int k2 = kn2.ImtiPuslapius();
			
			if (k1 < k2) {
				return -1;
			}
			if (k1 > k2) {
				return 1;
			}
			return 0;
		}
	};
}
