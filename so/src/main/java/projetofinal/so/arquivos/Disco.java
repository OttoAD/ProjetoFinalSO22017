package projetofinal.so.arquivos;

import projetofinal.so.dados.operacoes.OperacaoInexistenteException;

public interface Disco {
	
	public void criarArquivo(int idProcesso, Arquivo arq) throws EspacoDiscoInsuficienteException;
	
	public void removerArquivo(int idProcessoChamador, int prioridadeProcesso, char nomeArquivo) throws PermissaoNegadaException, ArquivoInexistenteException;
	
	public void executaOperacoesProcesso(int idProcesso, int prioridadeProcesso) throws EspacoDiscoInsuficienteException, PermissaoNegadaException, ArquivoInexistenteException, OperacaoInexistenteException;
	
	public void mostrarDisco();


}