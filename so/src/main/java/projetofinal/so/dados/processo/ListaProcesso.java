package projetofinal.so.dados.processo;

import java.util.ArrayList;

import projetofinal.so.processos.Processo;
import projetofinal.so.processos.ProcessoInexistenteException;

public class ListaProcesso {

	ArrayList<Processo> lista;
	
	public ListaProcesso() {
		this.lista = new ArrayList<Processo>();
	}
	
	public void adicionarProcessoListaOrdenado(Processo proc){
		int tempo = proc.getTempoInicializacao();
		int i = 0;
		try {
			while(tempo > lista.get(i).getTempoInicializacao()) {
				i++;
			}
			this.lista.add(i, proc);
		} catch (IndexOutOfBoundsException iobe) {//caso da primeira inserção
			this.lista.add(proc);
		}
		
	}
	
	public void adicionarProcessolista(Processo proc){
		this.lista.add(proc);
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
