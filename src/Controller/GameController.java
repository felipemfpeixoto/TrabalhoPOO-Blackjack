package Controller;

import FacadeModel.*;

public class GameController {
	FacadeModel facade = FacadeModel.INSTANCIA_UNICA;
	
	public void aa() {
		facade.baralho_SortCards();
	}
}
