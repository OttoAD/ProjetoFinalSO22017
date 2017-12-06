package projetofinal.so.dados.operacoes;

import java.util.ArrayList;

public class ListaOperacoes {
	
	private ArrayList<Operacao> lista;

	public ListaOperacoes() {
		lista = new ArrayList<Operacao>();
	}
	
	public void adicionarOperacao(Operacao op){
		this.lista.add(op);
	}
	
	public void removerOperacao(Operacao op) {
		lista.remove(op);
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
	
	public void imprimirOperacoes() {
		for (Operacao operacao : lista) {
			if (operacao.getCodigoOperacao() == 0) {
				System.out.println("\nOperação de escrita do processo "
						+ operacao.getIdProcesso() + " no arquivo "
						+ operacao.getNomeArquivo());
			} else {
				if (operacao.getCodigoOperacao() == 1) {
					System.out.println("\nOperação de remoção do processo "
							+ operacao.getIdProcesso() + " no arquivo "
							+ operacao.getNomeArquivo());
				} else {
					System.out.println("\nOperação inválida do processo "
							+ operacao.getIdProcesso() + " no arquivo "
							+ operacao.getNomeArquivo());
				}
			}
		}
	}
	
	public boolean listaVazia() {
		return this.lista.isEmpty();
	}

	public int getQuantidade() {
		return this.lista.size();
	}
	
	
}
