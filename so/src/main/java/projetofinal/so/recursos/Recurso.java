package projetofinal.so.recursos;

import java.util.LinkedList;

import projetofinal.so.processos.Processo;

public class Recurso {

	private EntradaSaida gerenciaRecurso;
	private LinkedList<Processo> filaProcessos = new LinkedList<Processo>();
	private boolean disponivel;
	private Processo processoProprietario;
	
	public boolean disponivel() {
		return this.disponivel;
	}
	
	public Recurso(EntradaSaida gerenciaRecurso, int tipoRecurso) {
		this.gerenciaRecurso = gerenciaRecurso;
		disponivel = true;
	}
	
	public boolean reservar(Processo processo) {
		if (disponivel) {
			filaProcessos.remove(processo); // Remove o processo da fila, caso já estivesse antes.
			setProcessoProprietario(processo);
			disponivel = false;
			return true;
		} else {
			return false;
		}
	}
	
	public void entrarNaEspera(Processo processo) {
		if (!filaProcessos.contains(processo)) {			
			filaProcessos.addFirst(processo);
		}
	}
	
	public void liberar() {
		setProcessoProprietario(null);
		disponivel = true;
		
		if (!filaProcessos.isEmpty()) {
			// Há processos esperando pelo recurso
			for (Processo processo : filaProcessos) { // Percorre a partir do primeiro
				// Verifica se os outros recursos requisitados estão disponíveis
				if (gerenciaRecurso.recursosLivres(processo)) {
					gerenciaRecurso.reservaRecursos(processo);
					setProcessoProprietario(processo);
					disponivel = false;
					filaProcessos.remove(processo);
					processo.setEstado(Processo.PRONTO);
					break;
				}
			}
		}
	}

	public Processo getProcessoProprietario() {
		return processoProprietario;
	}

	public void setProcessoProprietario(Processo processoProprietario) {
		this.processoProprietario = processoProprietario;
	}
}
