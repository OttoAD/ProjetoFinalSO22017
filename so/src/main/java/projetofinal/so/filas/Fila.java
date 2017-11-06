package projetofinal.so.filas;

import java.util.ArrayList;

import projetofinal.so.processos.Processo;
import projetofinal.so.processos.ProcessoInexistenteException;

public class Fila {
	
	private ArrayList<Processo> processos;
	
	
	public Fila() {
		this.processos = new ArrayList<Processo>();
	}
	
	public void inserirProcesso(Processo proc) throws ProcessoInexistenteException {
		if(proc!=null) {
			processos.add(proc);
		}else {
			throw new ProcessoInexistenteException("Não se pode inserir processos nulos");
		}
	}
	
	public void removerProcesso(int identificador) {
		
	}
	
	public void getProcesso(int identificador) {
		
	}
}
