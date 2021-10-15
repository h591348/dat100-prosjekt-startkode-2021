package no.hvl.dat100.prosjekt.main;

import javax.swing.SwingUtilities;

import no.hvl.dat100.prosjekt.kontroll.spill.Kontroll;
import no.hvl.dat100.prosjekt.modell.KortUtils;
import no.hvl.dat100.prosjekt.utsyn.*;

public class VriOtteSpil {

	public static void main(String[] args) {

		// opprett kontroll delen
		final Kontroll kontroll = new Kontroll();

		// start utsyn (Swing grensesnitt)
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Utsyn(kontroll);
			}
		});
	}
}
