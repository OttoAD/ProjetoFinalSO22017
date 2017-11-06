package projetofinal.so.filas;

import java.util.ArrayList;

import projetofinal.so.processos.Processo;
import projetofinal.so.processos.ProcessoInexistenteException;

public class Fila {
	
	private ArrayList<Processo> processos;
	
	
	public Fila() {
		this.processos = new ArrayList<Processo>();
	}
	
	public void inserirProcesso(Processo proc){
			this.processos.add(proc);
	}
	
	public void removerProcesso(int identificador) throws ProcessoInexistenteException {
		this.processos.remove(getProcesso(identificador));
	}
	
	public Processo getProcesso(int identificador) throws ProcessoInexistenteException{
		Processo proc = null;
		int tamanho = processos.size();
		for(int indice = 0; indice < tamanho ;indice++) {
			proc = processos.get(indice);
			if(proc.getID() == identificador) {
				return proc;
			}
		}
		throw new ProcessoInexistenteException("O processo não se encontra na fila");
	}
	
	public boolean filaVazia() {
		return this.processos.isEmpty();
	}
}
