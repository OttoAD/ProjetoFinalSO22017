package projetofinal.so.arquivos;

public class Arquivo {

	private char nomeArquivo;
	private int numeroBloco;
	private int tamanho;
	private int processoDono;
	
	public Arquivo() {
		this.nomeArquivo = '\0';
		this.numeroBloco = -1;
		this.tamanho = 0;
		this.processoDono = -1; //os arquivos iniciais nao tem dono
	}
	
	public Arquivo(char nomeArquivo, int numeroBloco, int tamanho, int processoDono) {
		this.nomeArquivo = nomeArquivo;
		this.numeroBloco = numeroBloco;
		this.tamanho = tamanho;
		this.processoDono = processoDono; //os arquivos iniciais nao tem dono
	}
	
	public char getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(char nomeArquivo) {
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

	public int getProcessoDono() {
		return processoDono;
	}

	public void setProcessoDono(int processoDono) {
		this.processoDono = processoDono;
	}

}
