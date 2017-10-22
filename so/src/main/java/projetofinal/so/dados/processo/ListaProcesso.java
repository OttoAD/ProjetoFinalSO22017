package projetofinal.so.dados.processo;

import java.util.ArrayList;

import projetofinal.so.processos.Processo;

public class ListaProcesso {

	ArrayList<Processo> lista = new ArrayList<Processo> ();
	
	public void adicionarProcesso(Processo proc) throws ProcessoInexistenteException{
		if (proc != null) {
			lista.add(proc);
		}else {
			throw new ProcessoInexistenteException("Não se pode adicionar processo nulo");
		}
	}
}
