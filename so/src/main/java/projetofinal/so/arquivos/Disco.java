package projetofinal.so.arquivos;

import java.util.ArrayList;

import projetofinal.so.dados.operacoes.Operacao;

public interface Disco {
	
	void removerArquivo(int idProcessoChamador, int prioridadeProcesso, Arquivo arq) throws PermissaoNegadaException ;//deletar um arquivo do disco

	public void criarArquivo(int idProcesso, Arquivo arq) throws EspacoDiscoInsuficienteException;//criar um arquivo no disco

	void mostrarDisco(); //mostrar disco na tela
	
	public int espacoEmDisco (int quantidadeBlocos); //Procura uma a primeira sequencia de X blocos livres sequenciais no disco e retorna o a posicao inicial

	public void executaOperacoesProcesso(int id) throws EspacoDiscoInsuficienteException;

}