package projetofinal.so.recursos;

import projetofinal.so.processos.Processo;

public interface EntradaSaida {
	
	//retorna true se o recurso está disponível
	public boolean recursoLivre (int recurso_index);
	
	/*retorna o processo ao qual o recurso está alocado
	retorna -1 caso recurso esteja disponível*/
	public Processo getProcessoConsumidor(int recurso_index);
	
	//atribui recurso ao processo em questao
	public boolean setRecursoToProcesso(int recurso_index, Processo processo);
	
	//libera recurso para outros processos
	public void freeRecurso (int recurso_index);
	
	/*Retorna TRUE se todos os recursos exigidos pelo processo estão livres*/
	public boolean recursosLivres (Processo process);
	
	/*Reserva todos os recursos exigidos para execução do processo
	 *Retorna true se der certo
	 *Retorna false se nem todos os recursos estiverem disponiveis*/
	public boolean reservaRecursos(Processo process);
	
	/*retorna true se o recurso está reservao para o processo process
	* retorna false otherwise*/
	public boolean recursoEstaComProcesso (int recurso, Processo process);
	
	/*retorna true se o processo já possui todos os recursos que ele precisa
	* retorna false caso algum deles ainda não tenha sido reservado
	* OBS: Não verifica se está disponível, apenas se está reservado ou não*/
	public boolean possuiRecursos(Processo process);

	//libera todos os recursos já utilizados pelo processo
	public void freeRecursos(Processo processo);

	//mostra os recursos que estao ocupados
	public void mostraRecursos();

	// Registra o processo na fila de espera dos recurso que ele precisa
	public void filaDeEspera (Processo processo);

}
