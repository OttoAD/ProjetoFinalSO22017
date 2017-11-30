package projetofinal.so;

import java.util.logging.Logger;

import projetofinal.so.arquivos.GerenciaArquivo;
import projetofinal.so.dados.processo.ListaProcesso;
import projetofinal.so.filas.Escalonador;
import projetofinal.so.filas.GerenciaFila;
import projetofinal.so.memoria.GerenciaMemoria;
import projetofinal.so.memoria.MemoriaInsuficienteException;
import projetofinal.so.memoria.MemoriaRAM;
import projetofinal.so.processos.BancoDeProcessos;
import projetofinal.so.processos.GerenciaProcesso;
import projetofinal.so.processos.Processo;
import projetofinal.so.processos.ProcessoInexistenteException;
import projetofinal.so.recursos.GerenciaRecurso;

public class Dispatcher{

	public static final int QUANTUM = 1;
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static Dispatcher instancia;
	
	private MemoriaRAM memoriaDoPC;
	private BancoDeProcessos meusProcessos;
	private GerenciaArquivo gerenciadorArquivo;
	private Escalonador escalonador;
	private GerenciaRecurso gerenciadorRecurso;
	
	private int clock;
	
	private void iniciaGerencia() {
		this.memoriaDoPC = new GerenciaMemoria();
		this.meusProcessos = new GerenciaProcesso();
		this.gerenciadorArquivo = new GerenciaArquivo();
		this.escalonador = new GerenciaFila();
		this.gerenciadorRecurso = new GerenciaRecurso();
		
	}
	
	private Dispatcher() {		
		iniciaGerencia();
	}
	
	public static Dispatcher obterInstancia() {
		if (instancia == null) {
			instancia = new Dispatcher();
		}
		return instancia;
	}
	
	public void novosProcessos(ListaProcesso processos) {
		this.meusProcessos = new GerenciaProcesso(processos);
	}
	
	public void executarProcessos() {
		clock = 0;
		
		LOGGER.info("Cheguei aqui");
		
		while(meusProcessos.temNovosProcessos() || !escalonador.vazio()) {
			//Verifica se tem processos para serem criados
			//ou processos no escalonador
			LOGGER.info("Clock " + clock);
			criarProcesso();
			
			executarProcesso();
			
		}
		
		//memoriaDoPC.mostrarMemoria();
		
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
					e.printStackTrace();
					try {
						meusProcessos.excluirProcesso(processo);						
					} catch (ProcessoInexistenteException e2) {
						e2.printStackTrace();
					}
					indiceProcesso++;
					LOGGER.info("Memória pequena demais para o processo " + processo.getID());
					continue;
				}
				
				if (posicaoMemoria == -1) { //Não há memória disponível NO MOMENTO OTÁVIO, haverá um dia...
					indiceProcesso++;
					LOGGER.info("Memoria insuficiente para o processo " + processo.getID());
					continue;
				}
				
				LOGGER.info("Memoria disponivel na posicao " + posicaoMemoria);
				memoriaDoPC.alocaMemoria(processo.getID(), posicaoMemoria, processo.getBlocosMemoria(), processo.getPrioridade());
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
			
			/*JONAS: ANTES DE EXECUTAR O PROCESSO ELE DEVE VERIFICAR SE OS RECURSOS ESTAO DISPONIVEIS
			 * DEVE ALOCAR CASO ESTEJAM; NÃO EXECUTAR CASO CONTRARIO*/
			
			clock += executarProcesso(processo);
			
			if (processo.getTempoProcessador() == 0) { //esgotou o processo
				
				//REALIZA TODAS AS OPERAÇÕES DE ARQUIVOS REFERENTES AO PROCESSO FINALIZADO
				//gerenciaArquivos.fazTudo(processo.getID()) ou algo assim
				
				memoriaDoPC.desalocarProcesso(processo.getID(), processo.getPrioridade()); //desaloca processo da memoria
				LOGGER.info("Processo "+processo.getID()+" finalizou no clock " +(clock-1)); //clock ja foi incrementado, decrementar para exibicao
				memoriaDoPC.mostrarMemoria();
			} else { //mais 'Quantum's serão necessarios
				escalonador.diminuirPrioridade(processo);
			}
		}
		else { //nenhum processo a ser executado
			clock++;
		}
	}	

	public int executarProcesso(Processo processo) {
		int tempoTotal = processo.getTempoProcessador();
		if (processo.getPrioridade() == 0) {
			processo.setTempoProcessador(0);
			LOGGER.info("Processo "+processo.getID()+ " foi executado por "+tempoTotal+ " unidades de tempo");
			return tempoTotal;
		} else {
			int tempoExecutado = tempoTotal <= QUANTUM ? tempoTotal : QUANTUM; //executa durante o minimo entre restante e QUANTUM 
			processo.setTempoProcessador(tempoTotal-tempoExecutado); //atualiza o tempo restante
			LOGGER.info("Processo "+processo.getID()+ " ainda deve ser executado por "+tempoTotal+ " unidades de tempo");
			return tempoExecutado;
		}
	}
}