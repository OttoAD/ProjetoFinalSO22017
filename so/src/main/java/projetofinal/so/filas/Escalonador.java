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
	public void escalonarProcesso(Processo processo);
	
	/*
	 * Retorna o primeiro processo do escalonador,
	 * returna null se não houver processos.
	 */
	public Processo proximoProcesso(); //verifia as filas e ve quem é prioritario
		//retorna null se nao tem processo em nenhuma fila do escalonador
	
	public void diminuirPrioridade (Processo process); //quantum acabou, mas o processo nao
		//utilizar process.prioridade para saber em que fila ele esta
		//diminuir a prioridade e trocar de fila
		
}
