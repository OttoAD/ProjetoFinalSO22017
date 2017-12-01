package projetofinal.so.arquivos;

public class Arquivo {

	private String nomeArquivo;
	private int numeroBloco;
	private int tamanho;
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public int getNumeroBloco() {
		return numeroBloco;
	}

	public void setNumeroBloco(int numeroBloco) {
		this.numeroBloco = numeroBloco;
	}

}
