package projetofinal.so.memoria;

public class GerenciaMemoria implements MemoriaRAM {
	
	private Memoria memoria; //Verificar como a memoria eh tratada pelo gerenciador; Primeiro palpite: objeto private do gerenciador
	
	/*Mostra na tela a representacao dos vetores de memoria*/
	
	public void mostrarMemoria () {
		int[] memReal = memoria.getMemReal();
		int[] memUser = memoria.getMemUsuario();
		
		System.out.println("O vetor de memoria de tempo real esta representado por: " + memReal);
		System.out.println("O vetor de memoria de usuario esta representado por: " + memUser);
		
	}
	
	/*
	 * Aloca o processo 'processoID' para a primeira sequencia de n blocos livres encontrada na memoria 
	 * A prioridade do processo define se a alocacao deve ser feita na memoria de usuario ou de tempo real*/
	
	public boolean reservarMemoria(int processoID, int quantidadeBlocos, int prioridade) { 
		
		int[] memAtual = null;
		if (prioridade == 0) { //processo de prioridade 0 define processo de tempo real
			memAtual = memoria.getMemReal(); //obtem o estado atual da memoria de tempo real
		}
		else { //processo de usuario
			memAtual = memoria.getMemUsuario(); //obtem o estado atual da memoria de usuario
		}
		
		int blCorrente = 0, nVazios = 0;
		boolean alocou = false;
		while (blCorrente < memAtual.length && alocou == false) { //percorre toda a memoria buscando alocacao
			if (memAtual[blCorrente] == 0) { //bloco de memoria livre
				nVazios++;
				if (nVazios == quantidadeBlocos) { //encontrado o 'First Fit'
					if (prioridade == 0)
						memoria.setMemRealRange(processoID, blCorrente-quantidadeBlocos, blCorrente); //Define processo 'processoID' para os 'quantidadeBlocos' blocos de memoria, onde o bloco corrente eh o ultimo deles
					else 
						memoria.setMemUsuarioRange(processoID, blCorrente-quantidadeBlocos, blCorrente);//Define processo 'processoID' para os 'quantidadeBlocos' blocos de memoria, onde o bloco corrente eh o ultimo deles
					return true;
				}
			}
			else { //bloco ocupado
				nVazios = 0;
			}
			blCorrente++;
		}
		return false; //alocacao impossibilitada
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
