package projetofinal.so.processos;
import projetofinal.so.dados.processo.ListaProcesso;
public class GerenciaProcesso implements BancoDeProcessos{
	
	private ListaProcesso processosNaoAlocados;
	private ListaProcesso processosAlocados; //??????
	
	public GerenciaProcesso(ListaProcesso lista) {
		this.processosNaoAlocados = lista;
	}
	
	public boolean temNovosProcessos(){
		try {
			this.lista.getProcesso(0);
			return true;
		}catch(ProcessoInexistenteException pie) {
			return false;
		}
	}
	
}
