package no.hvl.dat100.prosjekt.modell;

import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;

/**
 * Struktur for å lagre ei samling kort. Kan lagre hele kortstokken. Det finnes
 * en konstant i klassen Regler som angir antall kort i hver av de 4 fargene. Når
 * programmet er ferdig settes denne til 13, men under utvikling / testing kan
 * det være praktisk å ha denne mindre.
 * 
 */
public class KortSamling {

	private final int MAKS_KORT = 4 * Regler.MAKS_KORT_FARGE;

	private Kort[] samling;
	private int antall;

	/**
	 * Oppretter en tom Kortsamling med plass til MAKS_KORT (hele kortstokken).
	 */
	public KortSamling() { //Martin (FERDIG)
		antall = 0;
		this.samling = new Kort[MAKS_KORT];
	}

	/**
	 * Returnerer en tabell med kortene i samlinga. Tabellen trenger ikke være
	 * full. Kortene ligger sammenhengende fra starten av tabellen. Kan få
	 * tilgang til antallet ved å bruke metoden getAntallKort(). Metoden er
	 * først og fremst ment å brukes i testklasser. Om man trenger
	 * kortene utenfor, anbefales metoden getAlleKort().
	 * 
	 * @return tabell av kort.
	 */
	public Kort[] getSamling() { //FERDIG
		
		return samling;
		
	}
	
	/**
	 * Antall kort i samlingen.
	 * 
	 * @return antall kort i samlinga.
	 */
	public int getAntalKort() { //Martin (FERDIG)
		
		return antall;
	}
	
	/**
	 * Sjekker om samlinga er tom.
	 * 
	 * @return true om samlinga er tom, false ellers.
	 */
	public boolean erTom() { //Martin (FERDIG)

		return antall == 0;
	}

	/**
	 * Legg et kort til samlinga.
	 * 
	 * @param kort
	 *            er kortet som skal leggast til.
	 */
	public void leggTil(Kort kort) { //Martin (FERDIG)
		
		samling[antall] = kort;
		antall++;
	}
	
	/**
	 * Legger alle korta (hele kortstokken) til samlinga. Korta vil være sortert
	 * slik at de normalt må stokkes før bruk.
	 */
	public void leggTilAlle() { //Martin (TODO legg til kortstokken slik at den er full (12 kort)
		//TODO Husk: bruk Regler.MAKS_KORT_FARGE for å få antall kort per farge

		for (int f = 0; f < Regler.MAKS_KORT_FARGE+1; f++) {
			for (int v = 0; v < Regler.MAKS_KORT_FARGE; v++) {
				//Kort k = new Kort(Kortfarge.f, v);
			}
		}

		for (Kort k : samling) { //TODO test
			System.out.println(k.getVerdi() + " " + k.getFarge());
		}


		for (int i = 0; i < samling.length; i++) {
			//samling[i] = leggTil();
			//KortUtils.stokk(samling[i]);
		}

	}

	/**
	 * Fjerner alle korta fra samlinga slik at den blir tom.
	 */
	public void fjernAlle() {
		
		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		// TODO - END
	}
	
	/**
	 * Ser på siste kortet i samlinga.
	 * 
	 * @return siste kortet i samlinga, men det blir ikke fjernet. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort seSiste() {
		if (samling == null || antall==0) {
			return null;
		}
			else {

			return samling[antall-1];
			}



	}

	/**
	 * Tek ut siste kort fra samlinga.
	 * 
	 * @return siste kortet i samlinga. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort taSiste() {
		
		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - END
	}
	
	/**
	 * Undersøker om et kort finst i samlinga.
	 * 
	 * @param kort
	 *
	 * @return true om kortet finst i samlinga, false ellers.
	 */

	public boolean har(Kort kort) { //ERIK (FERDIG)

		if (kort != null) {

			for (Kort k : samling) {

				if (kort.equals(k) ) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Fjernar et kort frå samlinga. Dersom kortet ikke finnest i samlinga,
	 * skjer ingenting med samilingen
	 * 
	 * @param kort
	 *            kortet som skal fjernast. Dersom kortet ikke finnes, skjer
	 *            ingenting.
	 * @return true om kortet blev fjernet fra samlinga, false ellers.
	 */
			 
	public boolean fjern(Kort kort) {

		for (int k = 0; k < antall; k++){

			if (samling[k].equals(kort)) {

				samling[k] = samling[antall-1];

				samling[antall-1] = null;

				antall --;

				return true;
			}
		}
		return false;
	}

	/**
	 * Gir kortene som en tabell av samme lengde som antall kort i samlingen
	 * 
	 * @return tabell av kort som er i samlingen, der kort skal ha samme rekkefølge
	 *         som i kortsamlinga.
	 */
	//Sigve
	public Kort[] getAllekort() {

		Kort[]tabell = new Kort[antall];
		
		for (int i = 0; i < tabell.length; i++) {
			tabell[i] = samling[i];
		}
	return tabell;
	}
	
}
