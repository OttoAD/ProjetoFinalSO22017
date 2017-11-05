package projetofinal.so.processos;
import projetofinal.so.dados.LeituraArquivoException;
import projetofinal.so.dados.processo.LeituraArquivoProcesso;
import projetofinal.so.dados.processo.ListaProcesso;
public class GerenciaProcesso implements BancoDeProcessos{
	
	private ListaProcesso processosNaoAlocados;
	private ListaProcesso processosAlocados;
	
	public GerenciaProcesso(String nomeArquivo) throws LeituraArquivoException {
		LeituraArquivoProcesso listaArquivo = new LeituraArquivoProcesso(nomeArquivo);
		ListaProcesso lista = listaArquivo.lerArquivo();
		this.processosNaoAlocados = lista;
		this.processosAlocados = new ListaProcesso();
	}
	
	public boolean temNovosProcessos(){
		return processosNaoAlocados.ListaVazia();
	}

	public void excluirProcesso(Processo proc) throws ProcessoInexistenteException {
		processosNaoAlocados.removeProcessoLista(proc);
	}

	public void moverProcesso(Processo proc) throws ProcessoInexistenteException{
		Processo aux = processosNaoAlocados.getProcessoPorIdentificador(proc.getID());
		processosAlocados.adicionarProcessolista(aux);
		processosNaoAlocados.removeProcessoLista(proc);
	}

	public Processo proximoProcesso(int clock, int indice) throws ProcessoInexistenteException{
		Processo proc = processosNaoAlocados.getProcessoPorIndice(indice);
		if(proc.getTempoInicializacao() <= clock) {
			return proc;
		}else {
			return null;
		}

	}
	
 
	
}
