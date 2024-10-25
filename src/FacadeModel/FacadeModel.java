package FacadeModel;

import Model.*;

public enum FacadeModel {
	INSTANCIA_UNICA;
	
	private Baralho baralho = new Model.Baralho();
    private Banca banca = new Model.Banca();
    private Jogador jogador = new Model.Jogador(banca);
    private Dealer dealer = new Model.Dealer();
	
    // BARALHO
	public void baralho_SortCards() {
		baralho.sortBaralho();
	}
	
	public void baralho_GiveCard() {
		// Está assim pois o método é static
		Baralho.giveCard();
	}
}
