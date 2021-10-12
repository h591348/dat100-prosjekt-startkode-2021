package no.hvl.dat100.prosjekt.modell;

import java.util.Random;

import no.hvl.dat100.prosjekt.TODO;

public class KortUtils {

	/**
	 * Sorterer en samling. Rekkefølgen er bestemt av compareTo() i Kort-klassen.
	 * 
	 * @see Kort
	 * 
	 * @param samling
	 * 			samling av kort som skal sorteres. 
	 */
	
	public static void sorter(KortSamling samling) {

		int[] nySamling = new int[samling.getSamling().length];

		for (int tallnr = 0; tallnr < samling.getSamling().length; tallnr++) {

			for (int pos = 1; pos < samling.getSamling().length; pos++) {

				if (samling.getSamling()[pos].compareTo(samling.getSamling()[tallnr]
						< samling.getSamling()[pos+1].compareTo(samling.getSamling()[tallnr+1] )) {

				}
			}
		}


		
		throw new UnsupportedOperationException(TODO.method());
		// TODO - END
	}
	
	/**
	 * Stokkar en kortsamling. 
	 * 
	 * @param samling
	 * 			samling av kort som skal stokkes. 
	 */
	public static void stokk(KortSamling samling) { //Martin (FERDIG)

		if ( samling.erTom() ) { //Hvis samling inneholder 0 kort, avslutt
			return;
		}

		Kort[] tab = samling.getSamling();
		Kort[] h = new Kort[samling.getAntalKort() ]; //Hjelpemetode

		int[] r = new int[samling.getAntalKort() ]; //Random metoden

		Random rnd = new Random(); //Random tall generator

		for (int i = 0; i < samling.getAntalKort(); i++) { //getAntalkort = antall kort som skal stokkes

			do {
				r[i] = rnd.nextInt(samling.getAntalKort() ); //nextInt, lager entilfeldig int fra 0 til antall
			} while (!sjekkDupe(r, r[i] ) ); //Sjekker at r ikke har lik verdi som tidligere

			//Bytter posisjon
			h[i] = tab[i];
			tab[i] = tab[r[i] ];
			tab[r[i] ] = h[i];

		}
	}

	private static boolean sjekkDupe(int[] tab, int r) {

		for (int i : tab) {
			if (i == r) {
				return true;
			}
		}
		return false;
	}
	
}
