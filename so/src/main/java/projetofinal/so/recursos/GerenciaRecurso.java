package projetofinal.so.recursos;

import projetofinal.so.processos.Processo;

public class GerenciaRecurso implements EntradaSaida{

	private Recurso[] recursos;
	public static final int IMPRESSORA_1 = 0;
	public static final int IMPRESSORA_2 = 1;
	public static final int SCANNER = 2;
	public static final int MODEM = 3;
	public static final int SATA_1 = 4;
	public static final int SATA_2 = 5;
	
	public GerenciaRecurso() {
		recursos = new Recurso[6];
		for (int i = 0; i < recursos.length; i++) {
			recursos[i] = new Recurso(this, i);
		}
	}
	
	//retorna true se o recurso está disponível
	public boolean recursoLivre (int recurso_index) {
		return recursos[recurso_index].disponivel();
	}
	
	/*retorna o processo ao qual o recurso está alocado
	retorna -1 caso recurso esteja disponível*/
	public Processo getProcessoConsumidor(int recurso_index) {		
		return recursos[recurso_index].getProcessoProprietario();
	}
	
	//atribui recurso ao processo em questao
	public boolean setRecursoToProcesso(int recurso_index, Processo processo) {		
		return recursos[recurso_index].reservar(processo);
	}
	
	//libera recurso para outros processos
	public void freeRecurso (int recurso_index) {
		recursos[recurso_index].liberar();
	}
	
	//retorna TRUE se o recurso está reservado ao processo process
	public boolean recursoEstaComProcesso (int recurso, Processo process) {
		return this.getProcessoConsumidor(recurso).getID() == process.getID();
	}
	
	/*Retorna TRUE se todos os recursos exigidos pelo processo estão livres*/
	public boolean recursosLivres (Processo process) {
		
		if (process.getRequisicaoModem() && !this.recursoLivre(MODEM))
			return false; //quero Modem mas ele nao está livre
		
		if (process.getRequisicaoScanner() && !this.recursoLivre(SCANNER))
			return false; //quero Scanner mas ele nao está livre
		
		boolean resultado = true;
		
		switch (process.getNumeroCodigoImpressora()) {
			case 1:
				resultado &= this.recursoLivre(IMPRESSORA_1); //verificar se impressora 1 está livre 
				break;
			case 2:
				resultado &= this.recursoLivre(IMPRESSORA_2); //verificar se impressora 2 está livre
				break;
		}
		
		switch (process.getNumeroCodigoDisco()) {
			case 1:
				resultado &= this.recursoLivre(SATA_1); //verificar se disco 1 está livre 
				break;
			case 2:
				resultado &= this.recursoLivre(SATA_2); //verificar se disco 2 está livre
				break;
		}						
			
		return resultado; //ainda será true caso esteja tudo ok
	}
	
	/*retorna true se o processo já possui todos os recursos que ele precisa*/
	/*retorna false caso algum deles ainda não tenha sido reservado*/
	/*OBS: Não verifica se está disponível, apenas se está reservado ou não*/
	public boolean possuiRecursos(Processo process) {
		
		if (process.getRequisicaoModem() && !recursoEstaComProcesso(MODEM, process))
			return false; //quero Modem && ele não está comigo
		
		if (process.getRequisicaoScanner() && !recursoEstaComProcesso(SCANNER, process))
			return false; //quero Scanner && ele não está comigo
		
		if ((process.getNumeroCodigoImpressora() == 1) && !recursoEstaComProcesso(IMPRESSORA_1, process))
			return false; //quero Imressora 1 && ela não está comigo
		
		if ((process.getNumeroCodigoImpressora() == 2) && !recursoEstaComProcesso(IMPRESSORA_2, process))
			return false; //quero Imressora 2 && ela não está comigo
		
		if ((process.getNumeroCodigoDisco() == 1) && !recursoEstaComProcesso(SATA_1, process))
			return false; //quero SATA 1 && ele não está comigo
		
		if ((process.getNumeroCodigoDisco() == 2) && !recursoEstaComProcesso(SATA_2, process))
			return false; //quero SATA 1 && ele não está comigo
		
		return true;
	}
	
	/*Reserva todos os recursos exigidos para execução do processo*/
	public void reservaRecursos(Processo process) {

		int processID = process.getID();
		
		if (process.getRequisicaoModem())
			this.setRecursoToProcesso(MODEM, process); //atribui modem ao processo, se necessário
		if (process.getRequisicaoScanner())
			this.setRecursoToProcesso(SCANNER, process); //atribui scanner ao processo, se necessário
		
		switch (process.getNumeroCodigoImpressora()) { //verifica qual impressora o processo quer, se houver
		case 1:
			this.setRecursoToProcesso(IMPRESSORA_1, process); //atribui impressora 1 
			break;
		case 2:
			this.setRecursoToProcesso(IMPRESSORA_2, process); //atribui impressora 2
			break;
		}
		
		switch (process.getNumeroCodigoDisco()) { //verifica qual disco SATA o processo quer, se houver
		case 1:
			this.setRecursoToProcesso(SATA_1, process); //atribui SATA1
			break;
		case 2:
			this.setRecursoToProcesso(SATA_2, process); //atribui SATA2
			break;
		}
		
		return;
	}
}