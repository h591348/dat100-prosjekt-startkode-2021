package no.hvl.dat100.prosjekt.kontroll;

import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.HandlingsType;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortSamling;
import no.hvl.dat100.prosjekt.modell.Kortfarge;

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

		Handling nesteH = null;
		Kort[] hand = getHand().getSamling();

		KortSamling sammeFarge;
		KortSamling sammeVerdi = new KortSamling();
		KortSamling kort8 = new KortSamling();

		KortSamling hjerter = new KortSamling();
		KortSamling spar = new KortSamling();
		KortSamling klover = new KortSamling();
		KortSamling ruter = new KortSamling();


		for (int i = 0; i < getAntallKort(); i++) { //Lagrer kort i ulike tabeller

			if (Regler.kanLeggeNed(hand[i], topp)) {

				if (hand[i].getVerdi() == 8) {
					kort8.leggTil(hand[i]);
				}
				else if (hand[i].sammeVerdi(topp) && !hand[i].sammeFarge(topp) ) {
					sammeVerdi.leggTil(hand[i]);
				}

				if (hand[i].getVerdi() != 8) { //Lagrer kort for hver type i egen tabell med unntak av 8ere

					if (hand[i].getFarge() == Kortfarge.Hjerter) {

						hjerter.leggTil(hand[i]);

					} else if (hand[i].getFarge() == Kortfarge.Spar) {

						spar.leggTil(hand[i]);

					} else if (hand[i].getFarge() == Kortfarge.Klover) {

						klover.leggTil(hand[i]);

					} else if (hand[i].getFarge() == Kortfarge.Ruter) {

						ruter.leggTil(hand[i]);

					}
				}
			}
		}

		Kort kort = null;

		//Finner beste mulige kort
		switch (topp.getFarge() ) {
			case Hjerter -> {
				sammeFarge = hjerter;
				kort = findKort(sammeFarge, spar, ruter, klover, kort8, topp);
			}
			case Spar -> {
				sammeFarge = spar;
				kort = findKort(sammeFarge, hjerter, ruter, klover, kort8, topp);
			}
			case Ruter -> {
				sammeFarge = ruter;
				kort = findKort(sammeFarge, spar, hjerter, klover, kort8, topp);
			}
			case Klover -> {
				sammeFarge = klover;
				kort = findKort(sammeFarge, spar, ruter, hjerter, kort8, topp);
			}
		}

		if (kort == null && getAntallTrekk() < Regler.maksTrekk()) {
			return new Handling(HandlingsType.TREKK, null);
		}
		else if (kort == null && getAntallTrekk() == Regler.maksTrekk()) {
			return new Handling(HandlingsType.FORBI, null);
		}
		else {
			return new Handling(HandlingsType.LEGGNED, kort);
		}



	}

	private Kort findKort(KortSamling sammeFarge, KortSamling farge1, KortSamling farge2, KortSamling farge3, KortSamling kort8, Kort topp) {

		int samme = sammeFarge.getAntalKort();

		int max = Integer.max(samme, farge1.getAntalKort());
		max = Integer.max(max, farge2.getAntalKort());
		max = Integer.max(max, farge3.getAntalKort());

		if (max != 0) {

			if (max == samme) {

				return sammeFarge.taSiste();

			}
			else if (max == farge1.getAntalKort()) {

				for (Kort k : farge1.getSamling() ) {
					if (k.sammeVerdi(topp) ) {
						return k;
					}
				}
			}
			else if (max == farge2.getAntalKort()) {

				for (Kort k : farge2.getSamling() ) {
					if (k.sammeVerdi(topp) ) {
						return k;
					}
				}
			}
			else if (max == farge3.getAntalKort()) {

				for (Kort k : farge3.getSamling() ) {
					if (k.sammeVerdi(topp) ) {
						return k;
					}
				}
			}
		}
		if (!kort8.erTom() ) {
			for (Kort k : kort8.getSamling() ) {
				return k;
			}
		}

		return null;
	}

}
