package projetofinal.so.filas;

import projetofinal.so.processos.Processo;

public interface Escalonador {

	/*
	 * Verifica a quantidade de processos no escalonador.
	 * Retorna true se não houver processos,
	 * retorna false caso contrário.
	 */
	public boolean vazio();
	
	/*
	 * Coloca o processo em alguma fila de processos.
	 * Se as filas estiverem cheias retorna false,
	 * retorna true caso contrário.
	 */
	public boolean escalonarProcesso(Processo processo);
	
	/*
	 * Retorna o primeiro processo do escalonador,
	 * returna null se não houver processos.
	 */
	public Processo proximoProcesso();
	
}
