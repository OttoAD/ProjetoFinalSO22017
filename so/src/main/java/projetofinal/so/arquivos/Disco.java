package projetofinal.so.arquivos;

import java.util.ArrayList;

import projetofinal.so.dados.operacoes.Operacao;

public interface Disco {
	
	public ArrayList<Operacao> getOperacoesProcesso(int idProcesso);
	
	public void removerArquivo(int idProcesso, Arquivo arq);//deletar um arquivo do disco

	public void criarArquivo(int idProcesso, Arquivo arq);//criar um arquivo no disco
	
	//public ? lerArquivo(int idProcesso);

}