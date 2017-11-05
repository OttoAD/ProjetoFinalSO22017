package projetofinal.so.dados.processo;

import java.util.ArrayList;

import projetofinal.so.processos.Processo;
import projetofinal.so.processos.ProcessoInexistenteException;

public class ListaProcesso {

	ArrayList<Processo> lista;
	
	public ListaProcesso() {
		this.lista = new ArrayList<Processo>();
	}
	
	/*
	 * Adicionada a inserção ordenada. Se o tempo de inicialização do processo a ser adicionado for maior que a posição atual,
	 * então avança uma posição e verifica novamente até que a condição seja falsa. O método add(indice,objeto) faz um shift pra direita
	 * com os processos.
	 * Caso seja a primeira inserção, aux == null, então só usa o add(objeto) 
	 * */
	public void adicionarProcessoListaOrdenado(Processo proc) throws ProcessoInexistenteException{
		if (proc != null) {
			int tempo = proc.getTempoInicializacao();
			int i = 0;
			try {
				while(tempo > lista.get(i).getTempoInicializacao()) {
					i++;
				}
				this.lista.add(i, proc);
			} catch (IndexOutOfBoundsException iobe) {
				this.lista.add(proc);
			}
		}else {
			throw new ProcessoInexistenteException("Não se pode adicionar processo nulo");
		}
	}
	
	public void adicionarProcessolista(Processo proc) throws ProcessoInexistenteException{
		if(proc!=null) {
			this.lista.add(proc);
		}else {
			throw new ProcessoInexistenteException("Não se pode adicionar processo nulo");
		}
	}
	

	
	public Processo getProcessoPorIdentificador(int identificador) throws ProcessoInexistenteException {
		Processo proc = null;
		if(!ListaVazia()) {
			for(int indice = 0; indice < this.lista.size(); indice++) {
				proc = this.lista.get(indice);
				if(proc.getID() == identificador) {
					return proc;
				}
			}
		}
		throw new ProcessoInexistenteException("O processo não foi encontrado na lista");
	}
	
	public Processo getProcessoPorIndice(int indice) throws ProcessoInexistenteException{
		try {
			return lista.get(indice);
		}catch(IndexOutOfBoundsException iofbex) {
			throw new ProcessoInexistenteException("O índice do processo desejado não é válido", iofbex);
		}
	}
	
	
	public void removeProcessoLista(int indice) throws ProcessoInexistenteException{
		try {
			this.lista.remove(indice);
		}catch(IndexOutOfBoundsException iofbex) {
			throw new ProcessoInexistenteException("O índice do processo desejado não é válido", iofbex);
		}
	}
	
	public void removeProcessoLista(Processo proc) throws ProcessoInexistenteException{
		if (!this.lista.remove(proc)) {
			throw new ProcessoInexistenteException("O objeto do processo desejado não é válido ou não foi encontrado");
		}
	}
	
	public boolean ListaVazia() {
		return lista.isEmpty();
	}
	
	
}
