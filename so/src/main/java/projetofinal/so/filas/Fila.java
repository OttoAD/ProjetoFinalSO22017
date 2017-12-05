package projetofinal.so.filas;

import java.util.LinkedList;

import projetofinal.so.processos.Processo;

public class Fila {
	
	private LinkedList<Processo> processos;
	
	
	public Fila() {
		this.processos = new LinkedList<Processo>();
	}
	
	public void inserirProcesso(Processo proc){
			this.processos.addLast(proc);
	}
		
	public Processo getProcesso(){
		if(!filaVazia()) {
			return this.processos.removeFirst();
		}
		return null;
	}
		
	public boolean filaVazia() {
		return this.processos.isEmpty();
	}
	
	public boolean removerProcesso(Processo proc){
		return this.processos.remove(proc);
	}
}
