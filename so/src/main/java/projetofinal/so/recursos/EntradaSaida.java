package projetofinal.so.recursos;

import projetofinal.so.processos.Processo;

public interface EntradaSaida {
	
	//retorna true se o recurso está disponível
	public boolean recursoLivre (int recurso_index);
	
	/*retorna o processo ao qual o recurso está alocado
	retorna -1 caso recurso esteja disponível*/
	public int getProcessoConsumidor(int recurso_index);
	
	//atribui recurso ao processo em questao
	public void setRecursoToProcesso(int recurso_index, int processo_index);
	
	//libera recurso para outros processos
	public void freeRecurso (int recurso_index);
	
	/*Retorna TRUE se todos os recursos exigidos pelo processo estão livres*/
	public boolean recursosLivres (Processo process);
	
	/*Reserva todos os recursos exigidos para execução do processo*/
	public void reservaRecursos(Processo process);

}
