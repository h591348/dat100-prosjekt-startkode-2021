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
	public boolean bunketilTom() {  //FERDIG   Bruker metoden erTom fra KortSamling.java

		return bunkeTil.erTom();
	}

	/**
	 * Sjekker om fra-bunken er tom.
	 * 
	 * @return true om fra-bunken er tom, false ellers.
	 */
	public boolean bunkefraTom() {  //FERDIG  Bruker metoden erTom fra KortSamling.java

		return bunkeFra.erTom();

	}
	
	/**
	 * Gir antall kort i fra-bunken.
	 * 
	 * @return antall kort i fra-bunken.
	 */
	public int antallBunkeFra() {  //FERDIG  Bruker metoden getAntalKort fra KortSamling.java
		
		return bunkeFra.getAntalKort();
	}

	/**
	 * Gir antall kort i til-bunken.
	 * 
	 * @return antall kort i til-bunken.
	 */
	public int antallBunkeTil() {  //FERDIG  Bruker metoden getAntalKort fra KortSamling.java
		
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
	
	public Kort taOversteFraBunke() {  //FERDIG  Bruker metoden taSiste fra KortSamling.java

		return bunkeFra.taSiste();
	}
	
	/**
	 * Metode som leser øverste kortet i til-bunken. Kortet vil fremdeles være
	 * øverst i til-bunken etter at metoden er utført.
	 * 
	 * @return peker/referanse til øverste kortet i til-bunken.
	 */
	public Kort seOversteBunkeTil() {  //FERDIG  Bruker metoden seSiste fra KortSamling.java
		
		return bunkeTil.seSiste();
	}
	
	/**
	 * Når fra-bunken blir tom, tar man vare på kortet pÂ toppen av til-bunken.
	 * Deretter legges alle den andre kortene i til-bunken over i fra-bunken.
	 * Denne stokkes og kortet som man har tatt vare pÂ legges tilbake i
	 * til-bunken. Det vil nå være det eneste kortet i til-bunken.
	 */
	public void snuTilBunken() {
		/*if (bunkefraTom()) {
			Kort oversteKort = bunkeTil.taSiste();
			bunkeFra = bunkeTil;
			bunkeTil.fjernAlle();
			bunkeTil.leggTil(oversteKort);
		}*/

		if (bunkefraTom()){  //implementer start i Spill.java
			Kort oversteKort = bunkeTil.taSiste();
			bunkeFra.leggTilAlle();
			bunkeFra.fjern(oversteKort);
			bunkeTil.fjernAlle();
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
	public void leggNedBunkeTil(Kort k) {  //FERDIG  Bruker metoden leggTil fra KortSamling.java
		
		getBunkeTil().leggTil(k);
				
	}
}
