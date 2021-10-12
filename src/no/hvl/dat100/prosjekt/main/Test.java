package no.hvl.dat100.prosjekt.main;

import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.Kortfarge;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();

        //System.out.println(random.nextInt(3) );

        Kort k1 = new Kort(Kortfarge.Spar, 10);
        Kort k2 = new Kort(Kortfarge.Hjerter, 1);

        System.out.println(k1.compareTo(k2));
    }

}
