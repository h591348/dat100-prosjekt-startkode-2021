package no.hvl.dat100.prosjekt.kontroll;

import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.HandlingsType;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortSamling;

import java.util.Random;

/**
 * Klasse som for å representere en vriåtter syd-spiller. Strategien er å lete
 * gjennom kortene man har på hand og spille det første som er lovlig.
 *
 */
public class SydSpiller extends Spiller {

	/**
	 * Konstruktør.
	 * 
	 * @param spiller
	 *            posisjon for spilleren (nord eller syd).
	 */
	public SydSpiller(Spillere spiller) {
		super(spiller);
	}

	/**
	 * Metode for å implementere strategi. Strategien er å spille det første
	 * kortet som er lovlig (også en åtter selv om man har andre kort som også
	 * kan spilles). Dersom man ikke har lovlige kort å spille, trekker man om
	 * man ikke allerede har trukket maks antall ganger. I så fall sier man
	 * forbi.
	 * 
	 * @param topp
	 *            kort som ligg øverst på til-bunken.
	 */
	@Override
	public Handling nesteHandling(Kort topp) {

		// ArrayLister for de kort vi har og kan spille
		Kort[] hand = getHand().getAllekort();
		KortSamling lovlige = new KortSamling();
		KortSamling attere = new KortSamling();

		// Gå igjennom kort å finn ut hvilke som kan spilles
		for (Kort k : hand) {
			if (Regler.kanLeggeNed(k, topp)) {
				if (Regler.atter(k)) {
					attere.leggTil(k);
				} else {
					lovlige.leggTil(k);
				}
			}
		}

		Kort spill = null;
		Kort[] spillFra = null;

		if (!lovlige.erTom()) {
			spillFra = lovlige.getAllekort();
		} else if (!attere.erTom())  {
			spillFra = attere.getAllekort();
		}

		Handling handling = null;

		if (spillFra != null) {

			Random r = new Random();
			int p = r.nextInt(spillFra.length);
			spill = spillFra[p];
			handling = new Handling(HandlingsType.LEGGNED, spill);
			// setAntallTrekk(0);

		} else if (getAntallTrekk() < Regler.maksTrekk()) {
			handling = new Handling(HandlingsType.TREKK, null);
		} else {
			handling = new Handling(HandlingsType.FORBI, null);
			// setAntallTrekk(0);
		}

		return handling;
	}
}
