package projetofinal.so;

import java.util.logging.Logger;

import projetofinal.so.arquivos.GerenciaArquivo;
import projetofinal.so.dados.processo.ListaProcesso;
import projetofinal.so.filas.Escalonador;
import projetofinal.so.filas.GerenciaFila;
import projetofinal.so.memoria.GerenciaMemoria;
import projetofinal.so.memoria.MemoriaRAM;
import projetofinal.so.processos.BancoDeProcessos;
import projetofinal.so.processos.GerenciaProcesso;
import projetofinal.so.processos.Processo;
import projetofinal.so.processos.ProcessoInexistenteException;
import projetofinal.so.recursos.GerenciaRecurso;

public class Dispatcher{

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
		
		while(meusProcessos.temNovosProcessos()/* || !escalonador.vazio()*/) {
			//Verifica se tem processos para serem criados
			//ou processos no escalonador
			LOGGER.info("Ainda tem processos, Clock " + clock);
			criarProcesso();
			
			executarProcesso();
			
			clock++;
		}
		
		//memoriaDoPC.mostrarMemoria();
		
	}
	
	private void criarProcesso() {
		Processo processo = null;
		int indiceProcesso = 0;
		int posicaoMemoria = 0;
		
		LOGGER.info("Criando processos");
		
		do { //Cria todos os processos que já surgiram
			
			processo = meusProcessos.proximoProcesso(clock, indiceProcesso);
			
			if (processo != null) {
				
				//TODO: verificar se o processo exige um tamanho de memoria aceitavel
				//Prioridade 0: maximo 64
				//Outras prioridades: maximo 960
				
				LOGGER.info("Criando o processo " + processo.getID());
				try {
					posicaoMemoria = memoriaDoPC.encontraMemoria(processo.getBlocosMemoria(), processo.getPrioridade());					
				} catch (Exception e) {
					// TODO Definir exceção
					// Se o tamanho da memória não é suficiente
					try {
						meusProcessos.excluirProcesso(processo);						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
				//Não tinha memória disponível
				if (posicaoMemoria == -1) {
					indiceProcesso++;
					continue;					
				}
				
				memoriaDoPC.alocaMemoria(processo.getID(), posicaoMemoria, processo.getBlocosMemoria(), processo.getPrioridade());
				try {
					meusProcessos.moverProcesso(processo);
				} catch (ProcessoInexistenteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
					
				try {
					escalonador.escalonarProcesso(processo);
				} catch (Exception e) {
					// TODO: definir exception
					// Escalonador cheio
					try {
						meusProcessos.excluirProcesso(processo);
					} catch (ProcessoInexistenteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
				
		} while (processo != null);
	}
	
	private void executarProcesso() {
		Processo processo;
		
		processo = escalonador.proximoProcesso();
		if (processo != null) {
			if (processo.getPrioridade() == 0) {
				//Executa até acabar
			} else {
				//Executa até acabar o quantum
			}
		}
	}	
}