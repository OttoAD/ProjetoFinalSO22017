package projetofinal.so;

import java.util.logging.Level;
import java.util.logging.Logger;

import projetofinal.so.arquivos.Disco;
import projetofinal.so.arquivos.GerenciaArquivo;
import projetofinal.so.dados.LeituraArquivoException;
import projetofinal.so.dados.operacoes.OperacaoInexistenteException;
import projetofinal.so.filas.Escalonador;
import projetofinal.so.filas.GerenciaFila;
import projetofinal.so.memoria.GerenciaMemoria;
import projetofinal.so.memoria.MemoriaInsuficienteException;
import projetofinal.so.memoria.MemoriaRAM;
import projetofinal.so.processos.BancoDeProcessos;
import projetofinal.so.processos.GerenciaProcesso;
import projetofinal.so.processos.Processo;
import projetofinal.so.processos.ProcessoInexistenteException;
import projetofinal.so.recursos.EntradaSaida;
import projetofinal.so.recursos.GerenciaRecurso;

public class Dispatcher{

	public static final int QUANTUM = 1;
	private static Logger LOGGER = LogBuffer.getLogger();
	private static Dispatcher instancia;
	
	private MemoriaRAM memoriaDoPC;
	private BancoDeProcessos meusProcessos;
	private Disco gerenciadorArquivo;
	private Escalonador escalonador;
	private EntradaSaida gerenciadorRecurso;
	
	private int clock;
	
	private void iniciaGerencia() throws GerenciaException {
		try {
		this.memoriaDoPC = new GerenciaMemoria();
		this.meusProcessos = new GerenciaProcesso();
		this.gerenciadorArquivo = new GerenciaArquivo();
		this.escalonador = new GerenciaFila();
		this.gerenciadorRecurso = new GerenciaRecurso();
		}catch(LeituraArquivoException lae) {
			throw new GerenciaException("Houve um erro na criação das gerências",lae);
		}
		
	}
	
	private Dispatcher() throws GerenciaException {		
		iniciaGerencia();
	}
	
	public static Dispatcher obterInstancia() throws GerenciaException {
		if (instancia == null) {
			instancia = new Dispatcher();
		}
		return instancia;
	}
	
	public void executarProcessos() {
		clock = 0;
		
		//LOGGER.info("Cheguei aqui");
		gerenciadorArquivo.mostrarDisco();
		
		while(meusProcessos.temNovosProcessos() || !escalonador.vazio()) {
			//Verifica se tem processos para serem criados
			//ou processos no escalonador
			//LOGGER.info("Clock " + clock);
			criarProcesso();
			executarProcesso();
		}	
	}
	
	private void criarProcesso() {
		Processo processo = null;
		int indiceProcesso = 0;
		int posicaoMemoria = 0;
		
		do { //Cria todos os processos que já surgiram
			
			processo = meusProcessos.proximoProcesso(clock, indiceProcesso);
			
			if (processo != null) {
				
				LOGGER.info("Criando o processo " + processo.getID());
				try {
					posicaoMemoria = memoriaDoPC.encontraMemoria(processo.getBlocosMemoria(), processo.getPrioridade());
				} catch (MemoriaInsuficienteException e) {
					LOGGER.warning("Erro - Memória pequena demais para o processo " + processo.getID());
					try {
						meusProcessos.excluirProcesso(processo);						
					} catch (ProcessoInexistenteException e2) {
						e2.printStackTrace();
					}
					indiceProcesso++;
					printProcess(processo);
					continue;
				}
				
				if (posicaoMemoria == -1) { //Não há memória disponível no momento, mas haverá posteriormente...
					indiceProcesso++;
					LOGGER.warning("Memoria insuficiente para o processo " + processo.getID());
					continue;
				}
				
				LOGGER.info("Memoria disponivel na posicao " + posicaoMemoria);
				memoriaDoPC.alocaMemoria(processo.getID(), posicaoMemoria, processo.getBlocosMemoria(), processo.getPrioridade());
				printProcess(processo);
				memoriaDoPC.mostrarMemoria();
				try {
					meusProcessos.moverProcesso(processo);
				} catch (ProcessoInexistenteException e2) {
					e2.printStackTrace();
				}
				
				escalonador.escalonarProcesso(processo);
			}
				
		} while (processo != null);
	}

	private void executarProcesso() {
		
		Processo processo;
		
		processo = escalonador.proximoProcesso(); //tira processo da fila do escalonador
		if (processo != null) {
			
			/*Processo já tem os recursos OU Processo conseguiu alocar os recursos*/
			if (gerenciadorRecurso.possuiRecursos(processo) || gerenciadorRecurso.reservaRecursos(processo)) {
				/*EXECUTANDO PROCESSO QUE JA TEM TUDO QUE PRECISA*/
				clock += run(processo);

				if (processo.getTempoRestante() == 0) { //esgotou o processo

					try {
						gerenciadorArquivo.executaOperacoesProcesso(processo.getID(), processo.getPrioridade());
					} catch (OperacaoInexistenteException excep) {
						System.out.println("O processo tentou realizar uma operação inválida em arquivos");
						LOGGER.log(Level.SEVERE, "O processo tentou realizar uma operação inválida em arquivos", excep);
					}
					
					gerenciadorArquivo.mostrarDisco();
					memoriaDoPC.desalocarProcesso(processo.getID(), processo.getPrioridade()); //desaloca processo da memoria
					gerenciadorRecurso.freeRecursos(processo);
					memoriaDoPC.mostrarMemoria();
					
				} else { //mais 'Quantum's serão necessarios
					escalonador.diminuirPrioridade(processo);
				}
			}
			else {
				LOGGER.info("Processo "+processo.getID()+" bloqueado por falta de recursos");
				gerenciadorRecurso.mostraRecursos();
				//processo está bloqueado por falta de recursos
				processo.setEstado(Processo.BLOQUEADO);
				System.out.println("\nProcesso " + processo.getID() + " bloqueado, recursos indisponíveis.");
			}
		}
		else { //nenhum processo a ser executado
			clock++;
		}
	}

	public int run(Processo processo) {
		
		int tempoRestante = processo.getTempoRestante();
		int tempoExecutado;
		int tempoTotal = processo.getTempoProcessador();
		
		System.out.println("\nExecução Processo " + processo.getID() + " ->");
		
		LOGGER.info("Processo " + processo.getID() + " tempoProcessador " + processo.getTempoProcessador() + " tempoRestante " + processo.getTempoRestante());
		
		// Se é a primeira execução imprime o sinal de início
		if (tempoRestante == tempoTotal) {
			System.out.println("\tP" + processo.getID() + " STARTED");
		}
		
		if (processo.getPrioridade() == 0) {
			processo.setTempoRestante(0);
			LOGGER.info("Processo " + processo.getID() + " finalizado após " + tempoRestante + " unidades de tempo");
			tempoExecutado = tempoRestante;
		} else {
			tempoExecutado = tempoRestante <= QUANTUM ? tempoRestante : QUANTUM; //executa durante o minimo entre restante e QUANTUM 
			int tempoQueSobrou = tempoRestante - tempoExecutado;
			processo.setTempoRestante(tempoQueSobrou); //atualiza o tempo restante
			LOGGER.info("Processo "+processo.getID()+ " possui " + tempoQueSobrou + " unidades de tempo restantes");
		}
		
		// Imprimindo as instruções que foram executadas
		for (int i = 1; i <= tempoExecutado; i++) {
			System.out.println("\tP" + processo.getID() + " instrução " + (tempoTotal - tempoRestante + i));
		}
		
		// Se acabou o processo imprime o sinal de fim
		if (processo.getTempoRestante() == 0) {
			System.out.println("\tP" + processo.getID() + " retornou SIGINT");
		}
		
		return tempoExecutado;
	}
	
	private void printProcess(Processo process) {
		System.out.println("\nCrição de processo ->");
		System.out.println("\tPID: "+process.getID());
		System.out.println("\tpriority: "+process.getPrioridade());
		System.out.println("\ttime: "+process.getTempoProcessador());
		System.out.println("\tprinter: "+process.getNumeroCodigoImpressora());
		System.out.println("\tscanner: "+process.getRequisicaoScanner());
		System.out.println("\tmodem: "+process.getRequisicaoModem());
		System.out.println("\tSATA disc: "+process.getNumeroCodigoDisco());
		return;
	}
}