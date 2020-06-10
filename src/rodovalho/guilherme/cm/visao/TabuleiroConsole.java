package rodovalho.guilherme.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import rodovalho.guilherme.cm.excecao.ExplosaoException;
import rodovalho.guilherme.cm.excecao.SairException;
import rodovalho.guilherme.cm.modelo.Tabuleiro;

public class TabuleiroConsole {
	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		
		try {
			
			boolean continuar = true;
			
			while(continuar) {
				
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n)");
				
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reinciar();
				}				
				
			}
			System.out.println("Você Ganhou!!!");
		} catch (SairException e) {
			System.out.println("Tchau!!!!");
		}finally {
			entrada.close();
		}
	}
	
	private void cicloDoJogo () {
		try {
			
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("digite (x, y): ");
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 -(Des)Marcar: ");
				if("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abri(xy.next(), xy.next());
				}else if ("2".equalsIgnoreCase(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}
					
			}
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!!!!");
		} catch (ExplosaoException e) {
			
			System.out.println(tabuleiro);
			System.out.println("Você Perdeu!!");
		}
	}
			
	private String capturarValorDigitado (String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		} 
		return digitado;
	}

}
