package projetofinal.so.memoria;

public interface MemoriaRAM {

	/*
	 * Reserva a memória para o processo.
	 * Retorna true se havia memória suficiente,
	 * retorna false caso contrário.
	 */
	public boolean reservarMemoria(int processoID, int quantidadeBlocos);
}
