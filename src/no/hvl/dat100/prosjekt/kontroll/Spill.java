package no.hvl.dat100.prosjekt.kontroll;

import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortUtils;

/**
 * Klassen har objektvariaber som er referanser til de spillerne, nord og syd
 * (type ISpiller). Den har ogsÂ en bunke man deler/trekker fra og en bunke man
 * spiller til.
 * 
 */
public class Spill {

	private ISpiller nord;
	private ISpiller syd;
	
	private Bord bord;
	
	// antall kort som skal deles ut til hver spiller ved start
	public final static int ANTALL_KORT_START = Regler.ANTALL_KORT_START;
	
	public Spill() { //FERDIG

		this.bord = new Bord();
		this.nord = new NordSpiller(Spillere.NORD);
		this.syd = new SydSpiller(Spillere.SYD);

	}

	
	/**
	 * Gir referanse/peker til bord.
	 * 
	 * @return referanse/peker bord objekt.
	 */
	public Bord getBord() { //FERDIG
		
		return bord;
		
	}
	
	/**
	 * Gir referanse/peker til syd spilleren.
	 * 
	 * @return referanse/peker til syd spiller.
	 */
	public ISpiller getSyd() { //FERDIG
		
		return syd;
		
	}

	/**
	 * Gir referanse/peker til nord.
	 * 
	 * @return referanse/peker til nord.
	 */
	public ISpiller getNord() { //FERDIG
		
		return nord;
	}

	/**
	 * Metoden oppretter to spillere, nord og syd. Det opprettes to bunker, fra
	 * og til. Alle kortene legges til fra. Bunken fra stokkes. Deretter deles
	 * det ut kort fra fra-bunken til nord og syd i henhold til regler. Til
	 * slutt tas øverste kortet fra fra-bunken og legges til til-bunken.
	 * 
	 * Nord har type RandomSpiller (som er forhåndefinert). Syd vil være spiller
	 * av en klasse laget av gruppen (implementeres i oppgave 3).
	 */
	public void start() { //FERDIG

		Spill spill = new Spill(); //Lager nytt bord med 2 spillere

		KortUtils.stokk(bord.getBunkeFra() );
		delutKort();
		getBord().vendOversteFraBunke();
	}

	/**
	 * Deler ut kort til nord og syd.
	 * 
	 */
	private void delutKort() { //FERDIG

		//Deler ut 3 kort til hver spiller
		for (int i = 0; i < ANTALL_KORT_START; i++) {

			//Henter ett kort fra fra-bunken og gir til spiller nord
			Kort n = bord.getBunkeFra().taSiste();
			nord.leggTilKort(n);

			//Henter ett kort fra fra-bunken og gir til spiller syd
			Kort s = bord.getBunkeFra().taSiste();
			syd.leggTilKort(s);
		}
	}

	/**
	 * Trekker et kort fra fra-bunken til spilleren gitt som parameter. Om
	 * fra-bunken er tom, må man "snu" til-bunken. Se info om metoden
	 * snuTilBunken().
	 * 
	 * @param spiller
	 *            spilleren som trekker.
	 * 
	 * @return kortet som trekkes.
	 */
	public Kort trekkFraBunke(ISpiller spiller) { //FERDIG

		bord.snuTilBunken(); //Metoden sjekker selv om bunken må snus eller ikke.
		Kort overst = bord.taOversteFraBunke();
		spiller.trekker(overst); //Tar det øverste i fra-bunken og legger på hånden

		return overst;		   //Returnerer kortet som trekkes

	}

	/**
	 * Gir neste handling for en spiller (spilt et kort, trekker et kort, forbi)
	 * 
	 * @param spiller
	 *            spiller som handle.
	 * 
	 * @return handlingen som blir utført.
	 */
	public Handling nesteHandling(ISpiller spiller) { //FERDIG

		return spiller.nesteHandling(getBord().seOversteBunkeTil() );
		
	}

	/**
	 * Metoden spiller et kort. Den sjekker at spiller har kortet. Dersom det er
	 * tilfelle, fjernes kortet fra spilleren og legges til til-bunken. Metoden
	 * nulltiller også antall ganger spilleren har trukket kort.
	 * 
	 * @param spiller
	 *            den som spiller.
	 * @param kort
	 *            kort som spilles.
	 * 
	 * @return true dersom spilleren har kortet, false ellers.
	 */
	public boolean leggnedKort(ISpiller spiller, Kort kort) { //FERDIG

		spiller.setAntallTrekk(0); //Nullstiller antall trekk

		if (spiller.getHand().har(kort) ) {

			bord.leggNedBunkeTil(kort); //Legger kortet til til-bunken
			spiller.getHand().fjern(kort); //Fjerner kortet fra hand

			return true;
		}
		return false;
	}

	/**
	 * Metode for å si forbi. Må nullstille antall ganger spilleren har trukket
	 * kort.
	 * 
	 * @param spiller
	 *            spilleren som er i tur.
	 */
	public void forbiSpiller(ISpiller spiller) { //FERDIG?
		
		spiller.setAntallTrekk(0); //Nullstiller antall trekk

	}

	/**
	 * Metode for å utføre en handling (trekke, spille, forbi). Dersom handling
	 * er kort, blir kortet også spilt.
	 * 
	 * @param spiller
	 *            spiller som utfører handlingen.
	 * @param handling
	 *            handling som utføres.
	 * 
	 * @return kort som trekkes, kort som spilles eller null ved forbi.
	 */
	public Kort utforHandling(ISpiller spiller, Handling handling) { //FERDIG

		switch (handling.getType() ) {

			//Returnerer kort til spilleren
			case TREKK -> {
				return trekkFraBunke(spiller);
			}
			case LEGGNED -> {

				Kort[] tab = spiller.getHand().getSamling();

				//Sjekker om spilleren har kortet fra Handling og hvis spilleren har det, legger det ned til til-bunken
				for (int i = 0; i < spiller.getAntallKort(); i++) {
					if (handling.getKort() == tab[i]) {

						leggnedKort(spiller, tab[i]);
						return tab[i];
					}
				}
			}
			default -> {
				forbiSpiller(spiller);
			}

		}

		return null;
	}
}