package projetofinal.so.arquivos;

public interface Disco {
	
	public void criarArquivo(int idProcesso, Arquivo arq) throws EspacoDiscoInsuficienteException;
	
	public void removerArquivo(int idProcessoChamador, int prioridadeProcesso, char nomeArquivo) throws PermissaoNegadaException, ArquivoInexistenteException;
	
	public void executaOperacoesProcesso(int idProcesso, int prioridadeProcesso);
	
	public void mostrarDisco();
	
	public void imprimirNaoExecutadas();
}