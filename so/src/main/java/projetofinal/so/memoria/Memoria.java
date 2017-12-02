package projetofinal.so.memoria;

public class Memoria {
	
	private int[] processosReal;
    private int[] processosUsuario;
	
	public Memoria() {
		processosReal = new int[64]; //64 blocos de memoria de tempo real
		setMemRealRange(-1, 0, 63);
		processosUsuario = new int[960]; //960 blocos de memoria de usuario
		setMemUsuarioRange(-1, 0, 959);

	}

	public int[] getMemReal() { //retorna o estado da memoria de processos em tempo real 
		return processosReal;
	}
	public int[] getMemUsuario() { //retorna o estado da memoria de processos de usuario
		return processosUsuario;
	}
	
	public int getMemReal(int position) { //retorna o estado de um bloco da memoria de processos em tempo real
		return processosReal[position];
	}
	public int getMemUsuario(int position) { //retorna o estado de um bloco da memoria de processos de usuario
		return processosUsuario[position];
	}
	
	public void setMemReal(int i, int posicao) { //define um processo para um bloco de memoria de processos em tempo real
		processosReal[posicao] = i;
	}
	public void setMemRealRange(int processoID, int posicaoinicial, int posicaofinal) { //define um processo para uma sequ�ncia de blocos de memoria de processos em tempo real
		for (int i = posicaoinicial; i <= posicaofinal; i++) {
			processosReal[i] = processoID;
		}
	}
	
	public void setMemUsuario(int i, int posicao) { //define um processo para um bloco de memoria de processos de usuario
		processosUsuario[posicao] = i;
	}
	public void setMemUsuarioRange(int processoID, int posicaoinicial, int posicaofinal) { //define um processo para uma sequencia de blocos de memoria de processos de usuario
		for (int i = posicaoinicial; i <= posicaofinal; i++) {
			processosUsuario[i] = processoID;
		}
	}
	
}
