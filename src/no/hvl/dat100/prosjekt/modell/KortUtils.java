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

		//Leter igjennom alle Kort i tabellen
		for (int tallnr = 0; tallnr < nySamling.length; tallnr++) {

			int minst = 0; //Minste posisjon
			//Finner minste kort i tabellen...
			for (int pos = 1; pos < samling.getAntalKort() - tallnr; pos++) { //Antall kort i "samling" synker med 1 for hvert kort som fjernes

				//Returnerer objekt A minus objekt B. Hvis svaret er mindre enn 0, setter vi minst til posisjon A.
				if (samling.getSamling()[pos].compareTo(samling.getSamling()[minst]) < 0) {

					minst = pos; //Setter posisjonen til A som ny minst.
				}
			}
			nySamling[tallnr] = samling.getSamling()[minst]; //Setter det minste objektet til første ledige posisjon i nySamling
			samling.fjern(samling.getSamling()[minst]); //Fjerner kortet minst etter å ha satt de inn i ny tabell, og senker antall med 1
		}
		//Kopierer ny tabell tilbake til samling tabell, øker antall med 1 for hvert kort som settes inn.
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
	public static void stokk(KortSamling samling) { //(FERDIG)

		Kort[] tab = samling.getSamling();

		Random rnd = new Random(); //Random tall generator

		for (int i = 0; i < samling.getAntalKort(); i++) { //getAntalkort = antall kort som skal stokkes

			int h;

			do {
				h = rnd.nextInt(samling.getAntalKort() ); //nextInt, lager entilfeldig int fra 0 til antall og ulikt i verdien
			} while (h == i);

			//Bytter posisjon
			Kort hjelpevariabel;

			hjelpevariabel = tab[i];
			tab[i] = tab[h];
			tab[h] = hjelpevariabel;
		}
	}
}
