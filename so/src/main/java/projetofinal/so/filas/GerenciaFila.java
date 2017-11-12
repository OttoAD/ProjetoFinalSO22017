package projetofinal.so.filas;

import projetofinal.so.processos.Processo;

public class GerenciaFila implements Escalonador {

	public static final int QUANTUM = 1;
	public static final int QUANTIDADEMAXIMA = 1000;
	private Fila processosTempoReal;
	private Fila processosPrioridade1;
	private Fila processosPrioridade2;
	private Fila processosPrioridade3;
	private int quantidade;
	
	public GerenciaFila() {
		this.quantidade = 0;
		this.processosTempoReal = new Fila();
		this.processosPrioridade1 = new Fila();
		this.processosPrioridade2 = new Fila();
		this.processosPrioridade3 = new Fila();
	}
	
	public boolean vazio() {
		return quantidade == 0 ? true : false;
	}
	
	public boolean escalonarProcesso(Processo process) {
		if((process != null) && (this.quantidade < QUANTIDADEMAXIMA)) {
			int prioridade = process.getPrioridade();
			
			if(prioridade == 0) {
				processosTempoReal.inserirProcesso(process);
				this.quantidade++;
				return true;
			}else if(prioridade == 1) {
				processosPrioridade1.inserirProcesso(process);
				this.quantidade++;
				return true;
			}else if(prioridade == 2) {
				processosPrioridade2.inserirProcesso(process);
				this.quantidade++;
				return true;
			}else if(prioridade == 3) {
				processosPrioridade3.inserirProcesso(process);
				this.quantidade++;
				return true;
			}		
		}
		
		return false;
	}

	public Processo proximoProcesso() {
		if(!processosTempoReal.filaVazia()) {
			this.quantidade--;
			return processosTempoReal.getProcesso();
		}else if(!processosPrioridade1.filaVazia()) {
			this.quantidade--;
			return processosPrioridade1.getProcesso();
		}else if(!processosPrioridade2.filaVazia()) {
			this.quantidade--;
			return processosPrioridade2.getProcesso();
		}else if(!processosPrioridade3.filaVazia()) {
			this.quantidade--;
			return processosPrioridade3.getProcesso();
		}
		return null;
	}

	public void diminuirPrioridade(Processo process) {// maior 0 1 2 3 menor
		if (process != null) {
			int prioridade = process.getPrioridade();
			
			if(prioridade>=0 && prioridade<3) {
				prioridade++;
				process.setPrioridade(prioridade);
				escalonarProcesso(process);
			}
		}
	}
	
	public int executarProcesso(Processo processo) {
		int tempoTotal = processo.getTempoProcessador();
		processo.setTempoProcessador(0);
		return tempoTotal;
	}
	
	public int executarQuantum(Processo processo) {
		int tempoRestante = processo.getTempoProcessador();
		int tempoTotal = tempoRestante <= QUANTUM ? tempoRestante : QUANTUM; //executa durante o minimo entre restante e QUANTUM
		processo.setTempoProcessador(tempoRestante-tempoTotal); //atualiza o tempo restante
		return tempoTotal;
	}
	

}
