package no.hvl.dat100.prosjekt.kontroll;

import no.hvl.dat100.prosjekt.modell.KortSamling;
import no.hvl.dat100.prosjekt.modell.KortUtils;
import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.modell.Kort;

/**
 * Klasse som implementerer bordet som spilles på. 
 * 
 * Klassen har objektvariablene bunkeTil og bunkeFra som skal brukes til å representere 
 * kortene som er i de to bunker på border. 
 */
public class Bord {

	private KortSamling bunkeFra;
	private KortSamling bunkeTil;

	/**
	 * Metoden oppretter to bunker, til- og fra-bunken
	 * Alle kortene legges til fra-bunken. 
	 */
	public Bord() {
		this.bunkeFra = new KortSamling();
		this.bunkeTil = new KortSamling();
		bunkeFra.leggTilAlle();
	}
	
	/**
	 * Gir peker/referanse til til-bunken.
	 * 
	 * @return referanse/peker til til-bunken.
	 */
	public KortSamling getBunkeTil() { //FERDIG
		
		return bunkeTil;
		
	}

	/**
	 * Gir peker/referanse til fra-bunken.
	 * 
	 * @return referanse/peker til fra-bunken.
	 */
	public KortSamling getBunkeFra() { //FERDIG
		
		return bunkeFra;
		
	}
	
	/**
	 * Sjekker om til-bunken er tom.
	 * 
	 * @return true om til-bunken er tom, false ellers.
	 */
	public boolean bunketilTom() {  //FERDIG | Sjekker om TilBunken er tom | Bruker metoden erTom fra KortSamling.java

		return bunkeTil.erTom();
	}

	/**
	 * Sjekker om fra-bunken er tom.
	 * 
	 * @return true om fra-bunken er tom, false ellers.
	 */
	public boolean bunkefraTom() {  //FERDIG | Sjekker om FraBunken er tom | Bruker metoden erTom fra KortSamling.java

		return bunkeFra.erTom();

	}
	
	/**
	 * Gir antall kort i fra-bunken.
	 * 
	 * @return antall kort i fra-bunken.
	 */
	public int antallBunkeFra() {  //FERDIG | Henter antall kort i FraBunken | Bruker metoden getAntalKort fra KortSamling.java
		
		return bunkeFra.getAntalKort();
	}

	/**
	 * Gir antall kort i til-bunken.
	 * 
	 * @return antall kort i til-bunken.
	 */
	public int antallBunkeTil() {  //FERDIG | Henter antall kort i TilBunken | Bruker metoden getAntalKort fra KortSamling.java
		
		return bunkeTil.getAntalKort();
	}
	
	/**
	 * Tar øverste kortet fra fra-bunken og legger dettte til til-bunken (med
	 * billedsiden opp, men det trenger ikke gruppen tenke på).
	 */
	public void vendOversteFraBunke() {

		Kort oversteKort = bunkeFra.taSiste();
		bunkeTil.leggTil(oversteKort);
		
	}
		
	/**
	 * Tar øverste kortet fra fra-bunken.
	 * 
	 * @return peker/referanse til det kort som blev tatt fra fra-bunken
	 */
	
	public Kort taOversteFraBunke() {  //FERDIG | Tar øverste kort fra FraBunken | Bruker metoden taSiste fra KortSamling.java

		return bunkeFra.taSiste();
	}
	
	/**
	 * Metode som leser øverste kortet i til-bunken. Kortet vil fremdeles være
	 * øverst i til-bunken etter at metoden er utført.
	 * 
	 * @return peker/referanse til øverste kortet i til-bunken.
	 */
	public Kort seOversteBunkeTil() {  //FERDIG | Ser på øverste kort i TilBunken | Bruker metoden seSiste fra KortSamling.java
		
		return bunkeTil.seSiste();
	}
	
	/**
	 * Når fra-bunken blir tom, tar man vare på kortet pÂ toppen av til-bunken.
	 * Deretter legges alle de andre kortene i til-bunken over i fra-bunken.
	 * Denne stokkes og kortet som man har tatt vare pÂ legges tilbake i
	 * til-bunken. Det vil nå være det eneste kortet i til-bunken.
	 */
	public void snuTilBunken() { //TODO Test feil på linje 152! Skjønner ikke hva som er galt :/

		if (bunkefraTom() ) {  //implementer start i Spill.java
			Kort oversteKort = bunkeTil.seSiste(); //Lagrer øverste kort i til-bunken

			//Kopierer verdiene fra til-bunken til fra-bunken
			for (int i = 0; i < antallBunkeTil()-1; i++) {
				bunkeFra.leggTil(bunkeTil.getSamling()[i]);
			}
			bunkeTil.fjernAlle(); //Fjerner alle kortene i bunkeTil siden de er kopiert til bunkeFra

			//Legger til det øverste kortet til til-bunken
			bunkeTil.leggTil(oversteKort);

			KortUtils.stokk(bunkeFra);
		}
	}
		
	/**
	 * Metode som legger et kort i til-bunken. 
	 * 
	 * @param k
	 * 			kort som skal legges ned. 
	 * 	
	 */
	public void leggNedBunkeTil(Kort k) {  //FERDIG | Legger ned et kort på TilBunken | Bruker metoden leggTil fra KortSamling.java.
		
		bunkeTil.leggTil(k);
				
	}
}
