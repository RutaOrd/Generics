/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Ordinaite;

import studijosKTU.ListKTUx;
import java.util.Locale;

/**
 *
 * @author rordi
 */
public class KnygaAtranka {

    ListKTUx<Knyga> visos = new ListKTUx<> (new Knyga());
    ListKTUx<Knyga> bendri = new ListKTUx<>(new Knyga());
    
    private static final Knyga bazinis = new Knyga();
    
    public ListKTUx<Knyga> atrinktiPagalPsl (int riba1, int riba2)
    {
        ListKTUx<Knyga> atitinkantys = new ListKTUx(bazinis);
        for(Knyga k : visos)
        {
           if(k.ImtiPuslapius() >= riba1 && k.ImtiPuslapius() <= riba2)
           {
                atitinkantys.add(k);
           
           }
        }
        return atitinkantys;
    }
    
    public ListKTUx<Knyga> atrinktiPagalZanra(String zanras)
    {
        ListKTUx<Knyga> atitinkantys = new ListKTUx(bazinis);
        for(Knyga k : visos)
        {
            if(k.ImtiZanra().equals(zanras))
                atitinkantys.add(k);
        }
        return atitinkantys;

    }
    
    public ListKTUx<Knyga> atrinktiPavadinimus(String pirmaRaide)
    {
        ListKTUx<Knyga> atitinkantys = new ListKTUx(bazinis);
        for(Knyga k : visos)
        {
            if(k.ImtiPavadinima().startsWith(pirmaRaide))
                atitinkantys.add(k);
        }
        return atitinkantys;
    }

    public ListKTUx<Knyga> atrinktiNaujausias ()
    {
        ListKTUx<Knyga> naujausios = new ListKTUx(bazinis);
        int maxMetai = 0;
        
        for(Knyga k : visos)
        {
            if(k.ImtiMetus()>=maxMetai)
            {
                if(k.ImtiMetus() > maxMetai)
                {
                    naujausios.clear();
                    maxMetai = k.ImtiMetus();
                }
                naujausios.add(k);
            }
        }
        return naujausios;
    }
    
    ListKTUx<Knyga> BendruElementuRadimas(ListKTUx<Knyga> knygos1, 
            ListKTUx<Knyga> knygos2) 
    {

        for(Knyga k : knygos1)
        {
            if(bendri.contains(k))
                bendri.add(k);
        }
    return bendri;
    }
}