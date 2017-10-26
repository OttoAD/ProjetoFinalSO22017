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
	
	public void adicionarProcessoLista(Processo proc) throws ProcessoInexistenteException{
		if (proc != null) {
			this.lista.add(proc);
			quantidade++;
		}else {
			throw new ProcessoInexistenteException("Não se pode adicionar processo nulo");
		}
	}
	
	public Processo getProcesso(int indice) throws ProcessoInexistenteException {
		if((lista != null) && (quantidade > 0) && (indice >= 0) && (indice < quantidade)) {
			return this.lista.get(indice);
		}else {
			throw new ProcessoInexistenteException("Lista sem processos ou índice fora do limite");
		}
	}
	
	public void removeProcessoLista(int indice) throws ProcessoInexistenteException{
		if (this.lista.get(indice) != null){
			this.lista.remove(indice);
			quantidade--;
		}else {
			throw new ProcessoInexistenteException("O índice do processo desejado não é válido");
		}

	}
	
}
