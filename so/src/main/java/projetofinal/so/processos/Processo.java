package projetofinal.so.processos;

public class Processo {

	private int iD;
	private int tempoInicializacao;
	private int prioridade;
	private int tempoProcessador;
	private int tempoRestante;
	private int blocosMemoria;
	private int numeroCodigoImpressora;
	private boolean requisicaoScanner;
	private boolean requisicaoModem;
	private int numeroCodigoDisco;
	
	public Processo() {
		this.iD = -1;
		this.tempoInicializacao = 0;
		this.prioridade = -1;
		this.tempoProcessador = 0;
		this.tempoRestante = this.tempoProcessador;
		this.blocosMemoria = 0;
		this.numeroCodigoImpressora = 0;
		this.requisicaoScanner = false;
		this.requisicaoModem = false;
		this.numeroCodigoDisco = 0;
	}
	
	public Processo(Processo proc) {
		this.iD = proc.getID();
		this.tempoInicializacao = proc.getTempoInicializacao();
		this.prioridade = proc.getPrioridade();
		this.tempoProcessador = proc.getTempoProcessador();
		this.tempoRestante = this.tempoProcessador;
		this.blocosMemoria = proc.getBlocosMemoria();
		this.numeroCodigoImpressora = proc.getNumeroCodigoImpressora();
		this.requisicaoScanner = proc.getRequisicaoScanner();
		this.requisicaoModem = proc.getRequisicaoModem();
		this.numeroCodigoDisco = proc.getNumeroCodigoDisco();
	}

	public int getTempoInicializacao() {
		return this.tempoInicializacao;
	}
	
	public int getPrioridade() {
		return this.prioridade;
	}
	
	public int getTempoProcessador(){
		return this.tempoProcessador;
	}
	
	public int getBlocosMemoria() {
		return this.blocosMemoria;
	}
	
	public int getNumeroCodigoImpressora() {
		return this.numeroCodigoImpressora;
	}
	
	public boolean getRequisicaoScanner() {
		return this.requisicaoScanner;
	}

	public boolean getRequisicaoModem() {
		return this.requisicaoModem;
	}
	
	public int getNumeroCodigoDisco() {
		return this.numeroCodigoDisco;
	}
	
	public void setTempoInicializacao(int tempoInicializacao) {
		this.tempoInicializacao = tempoInicializacao;
	}
	
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	
	public void setTempoProcessador(int tempoProcessador){
		this.tempoProcessador = tempoProcessador;
	}
	
	public void executarQuantum() {
		this.tempoProcessador -= tempoProcessador; 
	}
	
	public void setBlocosMemoria(int blocosMemoria) {
		this.blocosMemoria = blocosMemoria;
	}
	
	public void setNumeroCodigoImpressora(int numeroCodigoImpressora) {
		this.numeroCodigoImpressora = numeroCodigoImpressora;
	}
	
	public void setRequisicaoScanner(boolean requisicaoScanner) {
		this.requisicaoScanner = requisicaoScanner;
	}

	public void setRequisicaoModem(boolean requisicaoModem) {
		this.requisicaoModem = requisicaoModem;
	}
	
	public void setNumeroCodigoDisco(int numeroCodigoDisco) {
		this.numeroCodigoDisco = numeroCodigoDisco;
	}

	public int getID() {
		return iD;
	}

	public void setID(int iD) {
		this.iD = iD;
	}

	public int getTempoRestante() {
		return tempoRestante;
	}

	public void setTempoRestante(int tempoRestante) {
		this.tempoRestante = tempoRestante;
	}
	
}
