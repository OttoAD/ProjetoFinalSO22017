package projetofinal.so.filas;

import projetofinal.so.dados.processo.ListaProcesso;
import projetofinal.so.processos.Processo;

public class GerenciaFila implements Escalonador {

	public static final int QUANTUM = 1;
	public static final int QUANTIDADEMAXIMA = 1000;
	private ListaProcesso processosTempoReal;
	private ListaProcesso processosPrioridade1;
	private ListaProcesso processosPrioridade2;
	private ListaProcesso processosPrioridade3;
	private int quantidade;
	
	public boolean vazio() {
		return quantidade == 0 ? true : false;
	}
	
	public boolean escalonarProcesso(Processo process) {
		if((process != null) && (this.quantidade < QUANTIDADEMAXIMA)) {
			int prioridade = process.getPrioridade();
			
			if(prioridade == 0) {
				processosTempoReal.adicionarProcessolista(process);
				quantidade++;
				return true;
			}else if(prioridade == 1) {
				processosPrioridade1.adicionarProcessolista(process);
				quantidade++;
				return true;
			}else if(prioridade == 2) {
				processosPrioridade2.adicionarProcessolista(process);
				quantidade++;
				return true;
			}else if(prioridade == 3) {
				processosPrioridade3.adicionarProcessolista(process);
				quantidade++;
				return true;
			}		
		}
		
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
