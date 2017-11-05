package projetofinal.so.processos;

public interface BancoDeProcessos {
	
	public boolean temNovosProcessos();
	
	public Processo proximoProcesso(int clock,int indice) throws ProcessoInexistenteException;

	public void excluirProcesso(Processo processo) throws ProcessoInexistenteException;
	
	public void moverProcesso(Processo process) throws ProcessoInexistenteException;

}
