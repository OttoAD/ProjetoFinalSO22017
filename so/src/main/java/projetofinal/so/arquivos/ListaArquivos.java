package projetofinal.so.arquivos;

import java.util.ArrayList;

public class ListaArquivos {

	private ArrayList<Arquivo> lista;

	public ListaArquivos() {
		lista = new ArrayList<Arquivo>();
	}
	
	public void adicionarArquivo(Arquivo arq){
		this.lista.add(arq);
	}
	
	public Arquivo getArquivoPorIndice(int indice) throws ArquivoInexistenteException{
		try {
			return lista.get(indice);
		}catch(IndexOutOfBoundsException iofbex) {
			throw new ArquivoInexistenteException("O índice do arquivo desejado não é válido", iofbex);
		}
	}
	
	public Arquivo getArquivoPorNome(String nomeArquivo) throws ArquivoInexistenteException{
		Arquivo arq = null;
		if(!listaVazia()) {
			for(int indice = 0; indice < this.lista.size(); indice++) {
				arq = this.lista.get(indice);
				if(arq.getNomeArquivo().equals(nomeArquivo)) {
					return arq;
				}
			}
		}
		throw new ArquivoInexistenteException("O arquivo não foi encontrado na lista");
	}
	
	public boolean listaVazia() {
		return this.lista.isEmpty();
	}

	public int getQuantidade() {
		return this.lista.size();
	}
	
}
