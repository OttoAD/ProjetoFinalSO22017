package projetofinal.so.memoria;

import projetofinal.so.processos.ProcessoInexistenteException;

public class GerenciaMemoria implements MemoriaRAM {
	
	private Memoria memoria; //Verificar como a memoria eh tratada pelo gerenciador; Primeiro palpite: objeto private do gerenciador
	
	/*Mostra na tela a representacao dos vetores de memoria*/
	
	public void mostrarMemoria () {
		int[] memReal = memoria.getMemReal();
		int[] memUser = memoria.getMemUsuario();
		
		System.out.println("O vetor de memoria de tempo real esta representado por: " + memReal);
		System.out.println("O vetor de memoria de usuario esta representado por: " + memUser);
	}
	
	/* Procura uma a primeira sequencia de X blocos livres sequenciais na memoria
	 * Retorna a posição incial da sequencia no vetor de memoria */
	public int encontraMemoria (int quantidadeBlocos, int prioridade) {
		
		int[] memAtual = null;
		if (prioridade == 0) { //processo de prioridade 0 define processo de tempo real
			memAtual = memoria.getMemReal(); //obtem o estado atual da memoria de tempo real
		}
		else { //processo de usuario
			memAtual = memoria.getMemUsuario(); //obtem o estado atual da memoria de usuario
		}
		
		int blCorrente = 0;
		int nVazios = 0;
		while (blCorrente < memAtual.length) { //percorre toda a memoria buscando alocacao
			if (memAtual[blCorrente] == 0) { //bloco de memoria livre
				nVazios++;
				if (nVazios == quantidadeBlocos) { //encontrado o 'First Fit'
					return (blCorrente-nVazios); //retorna a posicao inicial do vetor de blocos livres
				}
			}
			else { //bloco ocupado; reiniciar contagem
				nVazios = 0;
			}
			blCorrente++;
		}
		
		/*Retornei um inteiro em vez de lancar uma exception
		Fiz isso pois essa situação não é motivo para encerrar o programa
		throw new MemoriaIndisponivelException("Nao ha memoria suficiente para o processo no momento");*/
		return -1;
	}
	
	/* Aloca 'tamanhoBloco' espacos de memoria a partir de uma posicao inicial do vetor
	 * Tais espacos serao atribuidos ao processoID */
	public void alocaMemoria (int processoID, int posicaoInicial, int tamanhoBloco, int prioridade) { 
		
		if (prioridade == 0) { //processo de prioridade 0 define processo de tempo real
			memoria.setMemRealRange(processoID, posicaoInicial, posicaoInicial+tamanhoBloco);
		}
		else { //processo de usuario
			memoria.setMemUsuarioRange(processoID, posicaoInicial, posicaoInicial+tamanhoBloco);
		}
	}
	
	/*
	 * desaloca todos os blocos de memoria alocados ao processo de um ID especificado
	 * A prioridade do processo define se o processo de desalocacao deve ser feito na memoria de usuario ou de tempo real*/
	
	public boolean desalocarProcesso(int processoID, int prioridade) {
		
		int[] memAtual = null;
		if (prioridade == 0) { //processo de prioridade 0 define processo de tempo real
			memAtual = memoria.getMemReal(); //obtem o estado atual da memoria de tempo real
		}
		else { //processo de usuario
			memAtual = memoria.getMemUsuario(); //obtem o estado atual da memoria de usuario
		}
		
		for (int i = 0; i < memAtual.length; i++) { //percorre toda a memoria
			if (memAtual[i] == processoID) { //encontrou bloco de memoria a ser desalocado
				if (prioridade == 0)
					memoria.setMemReal(0, i); //Define processo 'processoID' para os 'quantidadeBlocos' blocos de memoria, onde o bloco corrente eh o ultimo deles
				else 
					memoria.setMemUsuario(0, i); //Define processo 'processoID' para os 'quantidadeBlocos' blocos de memoria, onde o bloco corrente eh o ultimo deles
				return true;
			}
		}
		return false;
	}

}