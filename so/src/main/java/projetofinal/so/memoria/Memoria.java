package projetofinal.so.memoria;

public class Memoria {
	
	private char[] processosReal;
    private char[] processosUsuario;
	
	public Memoria() {
		processosReal = new char[64]; //64 blocos de memoria de tempo real
		setMemRealRange('X', 0, 63);
		processosUsuario = new char[960]; //960 blocos de memoria de usuario
		setMemUsuarioRange('X', 0, 959);

	}

	public char[] getMemReal() { //retorna o estado da memoria de processos em tempo real 
		return processosReal;
	}
	public char[] getMemUsuario() { //retorna o estado da memoria de processos de usuario
		return processosUsuario;
	}
	
	public char getMemReal(int position) { //retorna o estado de um bloco da memoria de processos em tempo real
		return processosReal[position];
	}
	public char getMemUsuario(int position) { //retorna o estado de um bloco da memoria de processos de usuario
		return processosUsuario[position];
	}
	
	public void setMemReal(char processo, int posicao) { //define um processo para um bloco de memoria de processos em tempo real
		processosReal[posicao] = processo;
	}
	public void setMemRealRange(char processo, int posicaoinicial, int posicaofinal) { //define um processo para uma sequ�ncia de blocos de memoria de processos em tempo real
		for (int i = posicaoinicial; i <= posicaofinal; i++) {
			processosReal[i] = processo;
		}
	}
	
	public void setMemUsuario(char processo, int posicao) { //define um processo para um bloco de memoria de processos de usuario
		processosUsuario[posicao] = processo;
	}
	public void setMemUsuarioRange(char processo, int posicaoinicial, int posicaofinal) { //define um processo para uma sequencia de blocos de memoria de processos de usuario
		for (int i = posicaoinicial; i <= posicaofinal; i++) {
			processosUsuario[i] = processo;
		}
	}
	
}
