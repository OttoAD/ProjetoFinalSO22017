package projetofinal.so.dados.operacoes;

import java.util.ArrayList;

public class ListaOperacoes {
	
	private ArrayList<Operacao> lista;

	public ListaOperacoes() {
		lista = new ArrayList<Operacao>();
	}
	
	public void adicionarArquivo(Operacao op){
		this.lista.add(op);
	}
	
	public Operacao getOperacaoPorIndice(int indice) throws OperacaoInexistenteException{
		try {
			return lista.get(indice);
		}catch(IndexOutOfBoundsException iofbex) {
			throw new OperacaoInexistenteException("O índice da operação desejada não é válido", iofbex);
		}
	}
	
	public ArrayList<Operacao> getOperacoesProcesso(int idProcesso){
		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();
		Operacao op = null;
		if(!listaVazia()) {
			for(int indice = 0; indice < this.lista.size(); indice++) {
				op = this.lista.get(indice);
				if(op.getIdProcesso() == idProcesso) {
					operacoes.add(op);
				}
			}
		}
		return operacoes;
	}
	
	public boolean listaVazia() {
		return this.lista.isEmpty();
	}

	public int getQuantidade() {
		return this.lista.size();
	}
	
	
}
