package projetofinal.so.processos;
import projetofinal.so.dados.processo.ListaProcesso;
public class GerenciaProcesso implements BancoDeProcessos{
	
	private ListaProcesso lista;
	
	public GerenciaProcesso(ListaProcesso lista) {
		this.lista = lista;
	}
	
	public void setLista(ListaProcesso lista) {
		this.lista = lista;
	}
	
	public boolean temNovosProcessos(){
		try {
			this.lista.getProcesso(0);
			return true;
		}catch(ProcessoInexistenteException pie) {
			return false;
		}
	}

	public Processo proximoProcesso(int clock) {

		return null;
	}

	public void excluirProcesso(Processo processo) {

	}

	
	
}
