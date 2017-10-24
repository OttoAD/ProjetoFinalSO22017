package projetofinal.so.processos;

public interface BancoDeProcessos {
	
	/*
	 * Verifica se tem processos que ainda não foram criados
	 * Retorna true se sim,
	 * retorna false caso contrário.
	 */
	public boolean temNovosProcessos();
	
	/*
	 * Verifica os processos cujo tempo de inicialização é
	 * menor ou igual ao clock.
	 * Retorna o processo com o menor tempo de inicialização,
	 * se não houverem processos retorna null.
	 */
	public Processo proximoProcesso(int clock);

	/*
	 * Marca o processo como excluído.
	 */
	public void excluirProcesso(Processo processo);
	
}
