package projetofinal.so.processos;

import projetofinal.so.dados.LeituraArquivoException;
import projetofinal.so.dados.processo.LeituraArquivoProcesso;
import projetofinal.so.dados.processo.ListaProcesso;

public class GerenciaProcesso implements BancoDeProcessos{
	
	public static final String NOMEARQUIVOPROCESSOS = "processes.txt";
	private ListaProcesso processosNaoAlocados;
	private ListaProcesso processosAlocados;
	
	public GerenciaProcesso() throws LeituraArquivoException {
		LeituraArquivoProcesso processosLidos = new LeituraArquivoProcesso(NOMEARQUIVOPROCESSOS);
		this.processosNaoAlocados = processosLidos.lerArquivo();
		this.processosAlocados = new ListaProcesso();
	}
	
	public GerenciaProcesso(ListaProcesso lista) {
		this.processosNaoAlocados = lista;
		this.processosAlocados = new ListaProcesso();
	}
	
	public boolean temNovosProcessos(){
		return !processosNaoAlocados.listaVazia();
	}

	public void excluirProcesso(Processo proc) throws ProcessoInexistenteException {
		processosNaoAlocados.removeProcessoLista(proc);
	}

	public void moverProcesso(Processo proc) throws ProcessoInexistenteException{
		Processo aux = processosNaoAlocados.getProcessoPorIdentificador(proc.getID());
		processosAlocados.adicionarProcessolista(aux);
		processosNaoAlocados.removeProcessoLista(proc);
	}

	public Processo proximoProcesso(int clock, int indice) {
		try {
			Processo proc = processosNaoAlocados.getProcessoPorIndice(indice);
			if(proc.getTempoInicializacao() <= clock) {
				return proc;
			}
		} catch (ProcessoInexistenteException pie) {}
		
		return null;
	}
	
 
	
}
