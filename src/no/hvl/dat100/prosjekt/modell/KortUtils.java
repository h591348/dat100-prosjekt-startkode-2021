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
	
	public static void sorter(KortSamling samling) { //FERDIG

		Kort[] nySamling = new Kort[samling.getAntalKort()];

		//Leter igjennom hele tabellen
		for (int tallnr = 0; tallnr < nySamling.length; tallnr++) {

			int minst = 0; //Minste posisjon
			//Finner minste kort i tabellen...
			for (int pos = 1; pos < samling.getAntalKort() - tallnr; pos++) { //Antall synker med 1 for hvert kort som fjernes

				//Hvis objekt A er mindre enn objekt B, så er svaret under 0
				if (samling.getSamling()[pos].compareTo(samling.getSamling()[minst]) < 0) {

					minst = pos; //Setter minst
				}
			}
			nySamling[tallnr] = samling.getSamling()[minst]; //Setter det minste objektet til første ledige posisjoon i nySamling
			samling.fjern(samling.getSamling()[minst]); //Fjerner det kortet som er minst, og senker antall med 1
		}
		//Kopierer ny tabell tilbake til samling tabell, øker antall med 1 for hvert kort
		for (Kort kort : nySamling) {
			samling.leggTil(kort);
		}
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
		Kort[] h = new Kort[samling.getAntalKort() ]; //Hjelpetabell

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
