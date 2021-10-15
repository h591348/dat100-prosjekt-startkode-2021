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

		Kort[] hand = getHand().getSamling();

		KortSamling sammeVerdi = new KortSamling();
		KortSamling kort8 = new KortSamling();

		KortSamling[] farger = {new KortSamling(), new KortSamling(), new KortSamling(), new KortSamling()};

		//Går igjennom kortene på hånden og kopierer de i ulike tabeller
		for (int i = 0; i < getAntallKort(); i++) {

			if (Regler.kanLeggeNed(hand[i], topp) ) { //Kun de kortene som kan legges ned på til-bunken

				//Lagrer 8-erne
				if (hand[i].getVerdi() == 8) {
					kort8.leggTil(hand[i] );
				}
				//Lagrer de som har samme verdi men ikke samme farge eller 8-ere
				else if (hand[i].sammeVerdi(topp) && !hand[i].sammeFarge(topp) ) {
					sammeVerdi.leggTil(hand[i] );
				}

				//Lagrer kort etter farge men ikke 8-ere
				if (hand[i].getVerdi() != 8) {

					for (int j = 0; j < Kortfarge.values().length; j++) { //Sjekker for hver av kortFargene

						if (hand[i].getFarge() == Kortfarge.values()[j] ) {

							farger[j].leggTil(hand[i] );

						}
					}
				}
			}
		}

		Kort kort = null;
		KortSamling sammeFarge;

		//Finner beste mulige kort
		switch (topp.getFarge() ) {
			case Hjerter -> {
				sammeFarge = farger[0];
				kort = finnKort(sammeFarge, farger[1], farger[2], farger[3], kort8, topp);
			}
			case Ruter -> {
				sammeFarge = farger[1];
				kort = finnKort(sammeFarge, farger[0], farger[2], farger[3], kort8, topp);
			}
			case Klover -> {
				sammeFarge = farger[2];
				kort = finnKort(sammeFarge, farger[0], farger[1], farger[3], kort8, topp);
			}
			case Spar -> {
				sammeFarge = farger[3];
				kort = finnKort(sammeFarge, farger[0], farger[1], farger[2], kort8, topp);
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

	/*	Metoden prøver å finne det beste kortet å legge.
	* 	Den vil prøve først å finne kort som har samme farge som 'topp'.
	* 	Hvis vi ikke har minst ett kort med lik farge, eller vi har flere kort i en annen farge og ett av de har samme verdi som 'topp',
	* 		så vil det kortet bli lagt.
	* 	Hvis vi har minst en 8-er vil den bli lagt kun hvis vi ikke har noe annet å legge.
	* */
	private Kort finnKort(KortSamling sammeFarge, KortSamling farge1, KortSamling farge2, KortSamling farge3, KortSamling kort8, Kort topp) {

		int samme = sammeFarge.getAntalKort();
		KortSamling[] bunke = {sammeFarge, farge1, farge2, farge3};

		//Finner ut hvilken farge vi har mest av, ignorerer 8-ere
		int max = samme;
		for (KortSamling ks : bunke) {
			max = Integer.max(max, ks.getAntalKort());
		}

		//Hvis max er 0 så må alle de 4 kortsamlingene være tomme, men vi kan ha en eller flere 8-ere
		if (max != 0) {

			for (KortSamling ks : bunke) {
				if (max == samme) {

					return sammeFarge.taSiste();

				}
				else if (max == ks.getAntalKort() ) {

					for (Kort k : ks.getSamling() ) {

						if (k.sammeVerdi(topp) ) {
							return k;
						}
					}
				}
			}
		}

		//Hvis vi har minst en 8-er så vil vi legge på den første
		if (!kort8.erTom() ) {
			for (Kort k : kort8.getSamling() ) {
				return k;
			}
		}

		return null;
	}
}
