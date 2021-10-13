package no.hvl.dat100.prosjekt.kontroll;

import no.hvl.dat100.prosjekt.modell.KortSamling;
import no.hvl.dat100.prosjekt.modell.KortUtils;
import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;

/**
 * Abstrakt klasse som implementerer alle metodene i kontrakten (interfacet) ISpiller,
 * bortsett fra nesteHandling(). Dette er grunnen til at klassen er abstrakt.
 * For å lage "virkelige" spillere, må vi arve fra denne klassen og implementere
 * nesteHandling (fra ISpiller).
 * 
 * Klassen har objektvariablene hand (Hand), antalltrekk (heltall) og spiller
 * (Spillere). Den har to konstruktører. Se beskrivelse av disse.
 * 
 */
public abstract class Spiller implements ISpiller {

	// hand for spilleren (samling)
	private KortSamling hand; 
	
	// antall trekk spilleren har gjort fra fra-bunken
	private int antalltrekk; 
	
	// hvem spilleren er (Nord,Syd,Ingen) - se oppramsklassen Spillere
	private Spillere spiller;

	/**
	 * Standard konstruktør som oppretter en Spiller med en hånd uten kort,
	 * antalltrekk som 0 og setter spiller til Spillere.INGEN.
	 */
	public Spiller() { //FERDIG


		this.spiller = Spillere.INGEN;
		this.antalltrekk = 0;
		this.hand = new KortSamling();
	}

	/**
	 * Konstruktør der vi kan sette hvilken spiller det er (NORD, SYD eller
	 * INGEN).
	 * 
	 * @param spiller
	 *            hvilken spiller det er.
	 */
	public Spiller(Spillere spiller) {

		this.hand = new KortSamling();
		this.spiller = spiller;
		this.antalltrekk = 0;

	}

	public int getAntallKort() { //FERDIG TODO TEST
		
		return new KortSamling().getAntalKort();
	}

	public KortSamling getHand() { //FERDIG
		
		return hand;
	}

	public int getAntallTrekk() { //FERDIG
		
		return antalltrekk;
	}

	public Spillere hvem() { //FERDIG

		return spiller;
		
	}

	public void setAntallTrekk(int t) { //FERDIG
		
		this.antalltrekk = t;
	}

	public boolean erFerdig() {  //FERDIG
		
		if (hand.erTom()){
		return true;
		}
		return false;
	}

	public void leggTilKort(Kort kort) {
		
		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		// TODO - END
		
	}

	public void fjernKort(Kort kort) {
		
		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		// TODO - END
		
	}

	public void fjernAlleKort() {
		
		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		// TODO - END
	}

	public void trekker(Kort kort) {
		
		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		// TODO - END
		
	}
}
