package projetofinal.so.dados.processo;

import java.util.ArrayList;

import projetofinal.so.processos.Processo;
import projetofinal.so.processos.ProcessoInexistenteException;

public class ListaProcesso {

	ArrayList<Processo> lista;
	private int quantidade;
	
	public ListaProcesso() {
		this.lista = new ArrayList<Processo>();
		this.quantidade = 0;
	}
	
	public void adicionarProcesso(Processo proc) throws ProcessoInexistenteException{
		if (proc != null) {
			lista.add(proc);
			quantidade++;
		}else {
			throw new ProcessoInexistenteException("Não se pode adicionar processo nulo");
		}
	}
	
	public Processo getProcesso(int indice) throws ProcessoInexistenteException {
		if(lista != null && lista.size()<=quantidade) {
			return this.lista.get(indice);
		}else {
			throw new ProcessoInexistenteException("Lista sem processos ou índice fora do limite");
		}
	}
	
}
