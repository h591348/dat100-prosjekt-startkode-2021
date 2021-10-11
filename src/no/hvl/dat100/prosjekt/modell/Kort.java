package no.hvl.dat100.prosjekt.modell;

/**
 * Klasse for å representere et spillko 	rt. Kortene har en farge (bestemt av
 * oppramstypen Kortfarge) og en verdi (1,..., 13) der 1 = ess, 11 = knekt, 12 =
 * dame, 13 = konge.
 *
 * @see Kortfarge
 */
public class Kort implements Comparable<Kort> {

	private Kortfarge farge;
	private int verdi;

	/**
	 * Oppretter et kort med gitt farge og verdi.
	 * 
	 * @param farge
	 *            fargen pÂ kortet (kløver, hjerter, ruter, spar).
	 * @param verdi
	 *            heltall 1,...13
	 */
	public Kort(Kortfarge farge, int verdi) {
		this.farge = farge;
		this.verdi = verdi;
	}

	/**
	 * Gir fargen på kortet.
	 * 
	 * @return fargen
	 */
	public Kortfarge getFarge() {
		return farge;
	}

	/**
	 * Setter ny farge
	 * 
	 * @param farge
	 *            den nye fargen
	 */
	public void setFarge(Kortfarge farge) {
		this.farge = farge;
	}

	/**
	 * Gir verdien på kortet
	 * 
	 * @return verdi (1 = ess, 2,...,10, 11 = knekt, 12 = dame, 13 = konge)
	 */
	public int getVerdi() {
		return verdi;
	}

	/**
	 * Setter ny verdi
	 * 
	 * @param verdi
	 *            ny verdi
	 */
	public void setVerdi(int verdi) {
		this.verdi = verdi;
	}

	/**
	 * Sjekker om to kort har samme farge.
	 * 
	 * @param kort
	 *            kortet som det skal sammenliknes med.
	 * 
	 * @return true om kortene har samme farge, false ellers.
	 */
	public boolean sammeFarge(Kort kort) {
		return this.farge == kort.farge;
	}

	/**
	 * Sjekker om to kort har samme verdi.
	 * 
	 * @param kort
	 *            kortet som det skal sammenliknes med.
	 * 
	 * @return true om kortene har samme verdi, false ellers.
	 */
	public boolean sammeVerdi(Kort kort) {
		return this.verdi == kort.verdi;
	}

	/**
	 * NÂr vi overkjører equals(), skal vi ogsÂ som hovedregel overkjøre
	 * hashCode(). Denne er automatisk generert i Eclipse. Trenger ikke brukes i
	 * prosjektet, men er korrekt om noen ønsker å bruke den.
	 */
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((farge == null) ? 0 : farge.hashCode());
		result = prime * result + verdi;
		
		return result;
	}

	/**
	 * Sjekker om to kort er like.
	 * 
	 * @param kort
	 *            kortet vi sammenligner med.
	 * 
	 * @return true om kortene ha samme farge og samme verdi, false ellers.
	 */
	public boolean lik(Kort kort) {
		
		return ((this.farge == kort.getFarge()) && (this.verdi == kort.getVerdi()));
		
	}

	@Override
	/**
	 * Sjekker om to kort er like. De er like om de har samme farge og samme
	 * verdi.
	 * 
	 */
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Kort other = (Kort) obj;
		return this.farge == other.farge && this.verdi == other.verdi;
	}

	@Override
	/**
	 * Sammenliger this kort med kort. Kortene ordnes først etter farge,
	 * deretter verdi.
	 * 
	 * @param kort
	 *            objektet som this blir sammenlignet med.
	 * 
	 * @return Verdien 0 om korta er like, en verdi mindre enn 0 om this kjem
	 *         først og en verdi større enn 0 om this kjem sist.
	 */
	public int compareTo(Kort kort) {
		
		if (this.farge != kort.farge) {
			return this.farge.ordinal() - kort.farge.ordinal();
		} else {
			return this.verdi - kort.verdi;
		}
		
	}

	@Override
	/**
	 * Representer kortet som ein tekststreng. Brukes for debugging/logging.
	 */
	public String toString() {
		char v;

		switch (verdi) {
		case 10:
			v = 'T';
			break;
		case 11:
			v = 'J';
			break;
		case 12:
			v = 'Q';
			break;
		case 13:
			v = 'K';
			break;
		case 1:
			v = 'A';
			break;
		default:
			v = (char) verdi;
			v += '0';
		}

		return "" + farge + v;
	}
}
