package projetofinal.so.filas;

import projetofinal.so.processos.Processo;

public class GerenciaFila implements Escalonador {

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
