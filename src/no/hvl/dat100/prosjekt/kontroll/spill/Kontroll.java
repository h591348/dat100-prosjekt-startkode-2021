package no.hvl.dat100.prosjekt.kontroll.spill;

import java.util.ArrayList;

import no.hvl.dat100.prosjekt.kontroll.ISpiller;
import no.hvl.dat100.prosjekt.kontroll.Spill;
import no.hvl.dat100.prosjekt.kontroll.dommer.Dommer;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.dommer.Tur;
import no.hvl.dat100.prosjekt.modell.Kort;

public class Kontroll {

	public static int runde = 1;
	public static int nordVinner = 0;
	public static int sydVinner = 0;

	private Dommer dommer;
	private Spill spill;

	public Kontroll() {

	}

	public void startSpill() {

		spill = new Spill();
		dommer = new Dommer(spill);
		
		spill.start();

		dommer.sjekkStart();

		Tur.velgSpiller();
	}

	//
	// metoder som gir info for oppdatering av grensesnitt
	//

	public int antallNord() {
		return spill.getNord().getAntallKort();
	}

	public int antallBunkeTil() {
		return spill.getBord().antallBunkeTil();
	}

	public int antallBunkeFra() {
		return spill.getBord().antallBunkeFra();
	}

	public boolean erSydTur() {
		return Tur.erSydTur();
	}

	public boolean erNordTur() {
		return Tur.erNordTur();
	}

	public Spillere aktivSpiller() {
		return Tur.tur();
	}

	private ISpiller turSpiller() {

		ISpiller spiller = null;

		if (erSydTur()) {
			spiller = spill.getSyd();
		} else if (erNordTur()) {
			spiller = spill.getNord();
		}

		return spiller;

	}

	public Kort hentOverste() {
		return spill.getBord().seOversteBunkeTil();
	}

	public boolean bunketilTom() {
		return spill.getBord().bunketilTom();
	}

	public boolean bunkefraTom() {
		return spill.getBord().bunkefraTom();
	}

	public Spillere vinner() {

		Spillere vinner = Regler.vinner(spill.getNord(), spill.getSyd());

		if (vinner != Spillere.INGEN) {
			Tur.ferdig();
		}

		return vinner;
	}

	public Kort[] kortSyd() {
		return spill.getSyd().getHand().getAllekort();
	}

	//
	// metoder som kaldes ved klikk på ikoner
	//

	// kaldes ved klikk på fra bunken med syd aktiv spiller
	public Kort trekkFraBunke() {

		Kort kort = null;

		ISpiller spiller = turSpiller();

		Handling handling = new Handling(HandlingsType.TREKK, null);

		if (spiller != null) {

			boolean ok = dommer.sjekkHandling(spiller, handling);

			if (ok) {
				kort = spill.trekkFraBunke(turSpiller());

				if (kort != null) {
					dommer.utforHandling(spiller, handling, kort);
				}
			}
		}

		return kort;
	}

	public void handlingSpiller(ISpiller spiller) {
		Handling handling = spill.nesteHandling(spiller);

		if (dommer.sjekkHandling(spiller, handling)) {
			Kort kort = spill.utforHandling(spiller, handling);
			dommer.utforHandling(spiller, handling, kort);
		}

		if (handling.skifteTur()) {
			Tur.skiftSpiller();
			spiller.setAntallTrekk(0);
		}
	}

	// kaldes ved klikk på nord spiller ikon
	public void handlingNord() {
		ISpiller nord = spill.getNord();
		handlingSpiller(nord);
	}

	/**
	 * Gir syds hand som en ArrayList av Kort.
	 * 
	 * @return syds hand som en ArrayList av Kort.
	 */
	
	// kaldes ved klikk på tur ikon når syd sin tur
	public boolean sydForbi() {

		Handling handling = new Handling(HandlingsType.FORBI, null);

		ISpiller syd = spill.getSyd();

		boolean ok = dommer.sjekkHandling(syd, handling);

		if (ok) {
			spill.forbiSpiller(syd);
			dommer.utforHandling(syd, handling, null);
			Tur.skiftSpiller();
			syd.setAntallTrekk(0);
		}

		return ok;
	}

	// kaldes ved klikk på spillerikon for syd.
	public Kort foreslaaSyd() {
		return foreslaaKortSyd();
	}

	/**
	 * Foreslår hva syd skal spille.
	 * 
	 * @return kort som blir foreslått.
	 */
	private Kort foreslaaKortSyd() {
		
		// TODO - START
		// Hint: bruk nesteHandling metoden for en spiller
		
		Kort kort = null;
		Handling handling = spill.getSyd().nesteHandling(spill.getBord().seOversteBunkeTil());

		switch (handling.getType()) {
		
		case LEGGNED:
			kort = handling.getKort();
			break;
		default:
			break;
			
		}

		return kort;
		
		// throw new UnsupportedOperationException(TODO.method());
		// return null;
		// TODO - END
		
	}
	
	/**
	 * Spiller et kort fra syd.
	 * 
	 * @param kort
	 *            kort som spilles.
	 * 
	 * @return true dersom kortet er lovlig å spille, false ellers.
	 */
	private boolean spillkortSyd(Kort kort) {
		
		// TODO - START
		return spill.leggnedKort(spill.getSyd(), kort);
		
		// throw new UnsupportedOperationException(TODO.method());
		// return null;
		// TODO - END
	}
	
	// kaldes ved klikk på et kort hos syd.
	public boolean leggnedKortSyd(Kort kort) {

		boolean ok;

		ISpiller spiller = spill.getSyd();

		Handling handling = new Handling(HandlingsType.LEGGNED, kort);

		ok = dommer.sjekkHandling(spiller, handling);

		if (ok) {
			spillkortSyd(kort);
			dommer.utforHandling(spiller, handling, null);
			Tur.skiftSpiller();
			spiller.setAntallTrekk(0);
		}

		return ok;
	}

	//
	// metoder for automatisk spill uten feedback i GUI
	//

	// spiller spiller inntil tur skifter
	public void spillSpiller(ISpiller spiller) {

		Spillere hvem = spiller.hvem();

		while (aktivSpiller() == hvem) {
			handlingSpiller(spiller);
		}
	}

	public boolean harVinner() {

		Spillere vinner = Regler.vinner(spill.getNord(), spill.getSyd());
		if (spill.getNord().hvem() == vinner) {
			nordVinner++;
		}
		else if (spill.getSyd().hvem() == vinner) {
			sydVinner++;
		}
		return (vinner != Spillere.INGEN);
	}

	public void spillAuto() {

		while (!harVinner()) {

			ISpiller spiller = turSpiller();

			if (spiller != null) {
				spillSpiller(spiller);
			} else {
				System.out.println("Feil i spillAuto - ingen spiller aktiv");
			}
		}

		System.out.println("Runde: " + runde + " Nord har vunnet: " + nordVinner + " Syd har vunnet: " + sydVinner); runde++;
	}
}
