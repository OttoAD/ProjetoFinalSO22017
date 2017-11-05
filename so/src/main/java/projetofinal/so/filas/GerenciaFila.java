package projetofinal.so.filas;

import projetofinal.so.dados.processo.ListaProcesso;
import projetofinal.so.processos.Processo;

public class GerenciaFila implements Escalonador {

	private ListaProcesso processosTempoReal;
	private ListaProcesso processosPrioridade1;
	private ListaProcesso processosPrioridade2;
	private ListaProcesso processosPrioridade3;
	
	public boolean vazio() {
		return false;
	}

	public boolean escalonarProcesso(Processo processo) {
		return false;
	}

	public Processo proximoProcesso() {
		return null;
	}
	
	public void excluirProcesso(Processo process) {
	}

	public void diminuirPrioridade(Processo process) {
	}

}
