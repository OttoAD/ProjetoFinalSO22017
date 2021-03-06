package projetofinal.so.processos;

import java.util.logging.Logger;

import projetofinal.so.LogBuffer;
import projetofinal.so.filas.Escalonador;

public class Processo {

	private static Logger LOGGER = LogBuffer.getLogger();

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
	private int estado;
	private Escalonador escalonador;
	
	public static final int PRONTO = 0;
	public static final int BLOQUEADO = 1;
	
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
		this.estado = Processo.PRONTO;
		this.escalonador = null;
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
		this.estado = proc.getEstado();
		this.escalonador = proc.getEscalonador();
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		// Notificar o escalonador sobre a mudança de estado do processo
		if (this.escalonador != null && this.estado != estado) {
			LOGGER.info("Processo " + this.iD + " estado " + this.estado);
			switch (estado) {
			case Processo.PRONTO:
				this.escalonador.desbloquearProcesso(this);
				this.escalonador.diminuirPrioridade(this);
				break;
			case Processo.BLOQUEADO:
				this.escalonador.bloquearProcesso(this);
				break;
			default:
				break;
			}
		}
		this.estado = estado;
		LOGGER.info("Processo " + this.iD + " estado " + this.estado);
	}

	public Escalonador getEscalonador() {
		return escalonador;
	}

	public void setEscalonador(Escalonador escalonador) {
		this.escalonador = escalonador;
	}
	
}
