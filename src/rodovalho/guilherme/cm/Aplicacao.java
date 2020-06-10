package rodovalho.guilherme.cm;

import rodovalho.guilherme.cm.modelo.Tabuleiro;
import rodovalho.guilherme.cm.visao.TabuleiroConsole;

public class Aplicacao {
	
	public static void main(String[] args) {
	
		Tabuleiro tabuleiro = new Tabuleiro(6,6,3);
		
		new TabuleiroConsole(tabuleiro);
		
	}
}
