package rodovalho.guilherme.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import rodovalho.guilherme.cm.excecao.ExplosaoException;

public class Campo {
	
	private final int linha;
	private final int coluna;
	
	private boolean minado =  false;
	private boolean aberto = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>(); /*autoRelacionamento tenho uma variavel do 
	tipo campo que e a mesma clase que eu estou*/
	
	Campo (int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho (Campo vizinho) {
		boolean linhaDiferente = this.linha != vizinho.linha;
		
		boolean colunaDiferente = this.coluna != vizinho.coluna; 
		boolean diagonal = linhaDiferente && colunaDiferente; 
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha; 
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else {
			return false;
		}
	}
	
	void alternarMarcacao () {
		if(!aberto) {
			marcado = !marcado;
		}
	}
	 
	boolean abrir () {
		if (!this.aberto && !this.marcado) {
			aberto = true;
			if(this.minado) {
				throw new ExplosaoException();
			}
			if(vizinhacaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		} else {			
			return  false;
		}
	}
	
	
	boolean vizinhacaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	void minar() {
	minado = true;
	}
	
	public boolean isMarcado () {
		return this.marcado;
	}
	
	void setAberto (boolean aberto) {
		this.aberto = aberto;
	}
	
	public boolean isAberto() {
		return this.aberto;
	}
	public boolean isFechado () {
		return !isAberto();
	}
	
	boolean objetivoAlcancado () {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	long minasNaVizinhaca () {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	
	public boolean isMinado () {
		return this.minado; 
	}
	
	void reiniciar () {
		//zerando os campos
		aberto = false;
		minado = false;
		marcado = false;
	}	
	
	@Override
	public String toString() {
		if(marcado) {
			return "x";
		} else if(aberto && minado) {
			return "*";
		} else if (aberto && minasNaVizinhaca() > 0) {
			return Long.toString(minasNaVizinhaca());
		} else if(aberto) {
			return " ";
		} else  {
			return "?";
		}
		
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	
	
}
