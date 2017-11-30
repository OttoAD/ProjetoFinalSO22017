package projetofinal.so.recursos;

import projetofinal.so.processos.Processo;

public class GerenciaRecurso implements EntradaSaida{

	private int[] recursos;
	public static final int IMPRESSORA_1 = 0;
	public static final int IMPRESSORA_2 = 1;
	public static final int SCANNER = 2;
	public static final int MODEM = 3;
	public static final int SATA_1 = 4;
	public static final int SATA_2 = 5;
	
	public GerenciaRecurso() {
		recursos = new int[6];
		for (int i = 0; i < recursos.length; i++) {
			recursos[i] = -1; //indice -1 significa que recurso está disponivel
		}
	}
	
	//retorna true se o recurso está disponível
	public boolean recursoLivre (int recurso_index) {
		return recursos[recurso_index] == -1 ? true : false;
	}
	
	/*retorna o processo ao qual o recurso está alocado
	retorna -1 caso recurso esteja disponível*/
	public int getProcessoConsumidor(int recurso_index) {		
		return recursos[recurso_index];
	}
	
	//atribui recurso ao processo em questao
	public void setRecursoToProcesso(int recurso_index, int processo_index) {		
		recursos[recurso_index] = processo_index;
	}
	
	//libera recurso para outros processos
	public void freeRecurso (int recurso_index) {
		recursos[recurso_index] = -1;
	}
	
	/*Retorna TRUE se todos os recursos exigidos pelo processo estão livres*/
	public boolean recursosLivres (Processo process) {
		
		if (process.getRequisicaoModem() && !recursoLivre(MODEM))
			return false; //quero Modem mas ele nao está livre
		
		if (process.getRequisicaoScanner() && !recursoLivre(SCANNER))
			return false; //quero Scanner mas ele nao está livre
		
		boolean resultado = true;
		
		switch (process.getNumeroCodigoImpressora()) {
			case 1:
				resultado &= recursoLivre(IMPRESSORA_1); //verificar se impressora 1 está livre 
				break;
			case 2:
				resultado &= recursoLivre(IMPRESSORA_2); //verificar se impressora 2 está livre
				break;
		}
		
		switch (process.getNumeroCodigoDisco()) {
		case 1:
			resultado &= recursoLivre(SATA_1); //verificar se disco 1 está livre 
			break;
		case 2:
			resultado &= recursoLivre(SATA_2); //verificar se disco 2 está livre
			break;
		}						
			
		return resultado; //ainda será true caso esteja tudo ok
	}
	
	/*Reserva todos os recursos exigidos para execução do processo*/
	public void reservaRecursos(Processo process) {
		int processID = process.getID();
		
		if (process.getRequisicaoModem())
			setRecursoToProcesso(MODEM, processID); //atribui modem ao processo, se necessário
		if (process.getRequisicaoScanner())
			setRecursoToProcesso(SCANNER, processID); //atribui scanner ao processo, se necessário
		
		switch (process.getNumeroCodigoImpressora()) { //verifica qual impressora o processo quer, se houver
		case 1:
			setRecursoToProcesso(IMPRESSORA_1, processID); //atribui impressora 1 
			break;
		case 2:
			setRecursoToProcesso(IMPRESSORA_2, processID); //atribui impressora 2
			break;
		}
		
		switch (process.getNumeroCodigoDisco()) { //verifica qual disco SATA o processo quer, se houver
		case 1:
			setRecursoToProcesso(SATA_1, processID); //atribui SATA1
			break;
		case 2:
			setRecursoToProcesso(SATA_2, processID); //atribui SATA2
			break;
		}
		
		return;
	}
		
}