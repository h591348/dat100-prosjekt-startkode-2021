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

		Kort[] tab = samling.getSamling();

		for (int i = 0; i < samling.getAntalKort(); i++) { //getAntalkort = antall kort som skal stokkes

			double rnd = Math.random() * 10; //TODO pass på at det ikke blir duplikater
			tab[i] = tab[(int) rnd]; //TODO fix
		}
	}
	
}
