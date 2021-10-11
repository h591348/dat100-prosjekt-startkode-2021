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
		
		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		// TODO - END
	}
	
	/**
	 * Stokkar en kortsamling. 
	 * 
	 * @param samling
	 * 			samling av kort som skal stokkes. 
	 */
	public static void stokk(KortSamling samling) { //TODO

		if ( samling.erTom() ) { //Hvis inneholder ingen kort, avslutt
			return;
		}

		Kort[] tab = samling.getSamling();
		Kort[] h = new Kort[samling.getAntalKort() ]; //Hjelpemetode

		Random rnd = new Random(); //Random tall generator

		for (int i = 0; i < samling.getAntalKort(); i++) { //getAntalkort = antall kort som skal stokkes

			int r;

			do {
				r = rnd.nextInt(samling.getAntalKort() ); //nextInt, lager entilfeldig int fra 0 til antall
			} while (false /*TODO sjekk etter duplikater*/);

			//Bytter posisjon
			h[i] = tab[i];
			tab[i] = tab[r];
			tab[r] = h[i];

		}
	}
	
}
