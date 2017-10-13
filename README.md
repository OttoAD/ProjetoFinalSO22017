#PROJETO FINAL - SISTEMAS OPERACIONAIS

----- ESPECIFICAÇÃO -----

- Gerenciador de processos: agrupar em quatro níveis de prioridade
- Gerenciador de memória: garantir que um processo não acesse região de memória de outro
- Gerenciador de arquivos: criar e deletar arquivos de acordo com o modelo de alocação determinado
- Gerenciador de E/S: garantir liberação e alocação, assim como uso exclusivo dos dispositivos

Filas: valores menores = maiores prioridades
- processos de tempo real (prioridade 0, uma fila, FIFO sem preempção)
- processos de usuários (três filas com prioridades distintas e realimentação, usar aging ou outra técnica para evitar starvation)
- quantum de 1 segundo
- No máximo 1000 processos nas filas
- Recomenda-se usar uma fila global (ver figura 1)

Memória: conjunto de blocos contiguos. Não será utilizado swap ou páginas.
- fixa de 1024 blocos
- 64 blocos para processos de tempo real
- 960 blocos para processos de usuário
- cada bloco tem 4 bytes (um int)

Recursos de E/S:
- 1 scanner
- 2 impressores
- 1 modem
- 2 SATA

- Sem preempção
- Processos de tempo real não fazem uso
- Cada recurso seja alocado para um proceso por vez
- Deve ser utilizada alguma técnica para o gerenciamento (semáforo, etc)

Arquivos:
- criar e deletar
- alocação contígua
- arquivo deve ser tratado como uma unidade de manipulação
- first fit
- processos de tempo real podem fazer tudo, até com processos de terceiros
- entrada é um TXT: contém a quantidade total de blocos no disco, a especificação dos segmentos ocupados por cada arquivo, as operações a serem realizadas por cada processo
- deve mostrar na tela do computador um mapa com a atual ocupação do disco, descrevendo quais arquivos estão em cada bloco, e quais são os blocos vazios (identificados por 0)


ESTRUTURA:
- Módulo de Processos – classes e estruturas de dados relativas ao processo. Basicamente, mantém informações específicas do processo.
- Módulo de Filas – mantém as interfaces e funções que operam sobre os processos
- Módulo de Memória – provê uma interface de abstração de memória RAM
- Módulo de Recurso – trata a alocação e liberação dos recursos de E/S para os processos
- Módulo de Arquivos – trata as operações create e delete sobre os arquivos

INTERFACE
Entrada:

Ler os arquivos .txt: contendo as informações dos processos a serem criados e descrição das operações a serem realizadas pelo sistema de arquivos

Processos
- cada linha contém as informações de um único processo
- linha: <tempo de inicialização>, <prioridade>, <tempo de processador>, <blocos em memória>, <númerocódigo da impressora requisitada>, <requisição do scanner>, <requisição do modem>, <númerocódigo do disco>

Arquivos
- Haverá a quantidade total de blocos no disco, a quantidade de segmentos ocupados, a identificação de quais arquivos já estão gravados no disco, a localização dos blocos usados por cada arquivo, a identificação de qual processo efetuará cada operação, a identificação das operações, sendo código 0 para criar um arquivo, e código 1 para deletar um arquivo.
Além disso, para as operações de criação deve constar o nome do arquivo a ser criado, e a quantidade de blocos ocupada pelo arquivo. Por outro lado, na operação de deletar um arquivo, deve constar apenas o nome do arquivo a ser deletado.

- Linha 1: Quantidade de blocos do disco
- Linha 2: Quantidade de segmentos ocupados no disco (n);
- A partir da Linha 3 até Linha n + 2: arquivo (a ser identificado por uma letra), número do primeiro bloco gravado, quantidade de blocos ocupados por este arquivo;
- A partir da linha n + 3: cada linha representa uma operação a ser efetivada pelo sistema de arquivos do pseudo-SO. Para isso, essas linhas vão conter: <ID_Processo>, <Código_Operação>, <Nome_arquivo>, <se_operacaoCriar_numero_blocos>.


Saída:
O processo principal é o “despachante", este deve exibir uma mensagem de identificação contendo as seguintes informações:

- PID (int)
- Prioridade do processo (int)
- Offset da memória (int)
- Quantidade de blocos alocados na memória (int)
- Utilização de impressora (bool)
- Utilização de scanner (bool)
- Utilização de drivers (bool)
- O processo deve exibir alguma mensagem que indique sua execução

---- METODOLOGIA DO GRUPO ----
- JDK 1.8: http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html
- Eclipse IDE: http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/oxygen/1/eclipse-java-oxygen-1-win32-x86_64.zip
- SonarQube(baixar plugin pro eclipse também): https://www.sonarqube.org/
- Git(procurar pelo plugin para o eclipse): https://git-scm.com/downloads
- Maven(já vem no eclipse, mas por via de duvidas): https://maven.apache.org/download.cgi

Tutorial de instalação JAVA E MAVEN:

WINDOWS 7:

- 1 - Baixar a JDK(Java Development Kit)
- 2 - Baixar os binários do Maven
- 3 - Painel de Controle -> Sistema e Segurança -> Sistema -> Configurações Avançadas do Sistema -> Variáveis de Ambiente
- 4 - New Path em Variáveis do Sistema -> nome: JAVA_HOME -> apontar para o diretório jdk(C:\Program Files\Java\jdk1.8)
- 5 - New Path em Variáveis do Sistema -> nome: CLASSPATH -> apontar para %JAVA_HOME%
- 6 - Na variável PATH, adicionar ao final: %JAVA_HOME%\bin
- 7 - New Path em Variáveis do Sistema -> nome: MAVEN_HOME -> apontar para o diretório do maven(não o bin, a raiz)
- 8 - Na variável PATH, adicionar ao final: %MAVEN_HOME%\bin
- 9 - no prompt(cmd), digitar maven -version, javac e java -version pra checar as instalações

UBUNTU 16:
JDK:
- 1 - sudo apt-get update
- 2 - sudo apt-get install default-jre
- 3 - sudo apt-get install default-jdk
- 4 - java -version
- 5 - javac
- 6 - sudo apt-get install openjdk-7-jre
- 7 - sudo apt-get install openjdk-<VERSAO>-jdk (versão pode ser 6, 7 ou 8 no momento)
- 8 - export JAVA_HOME=/usr/lib/jvm/java-8-oracle/?
- 9 - export CLASSPATH=JAVA_HOME/lib/:$CLASSPATH
    export PATH=$JAVA_HOME/bin/:$PATH
    export M2_HOME=/usr/share/maven/
    export M2=$M2_HOME/bin
    export PATH=$M2:$PATH
- 10 - echo $JAVA_HOME

Se nada acima der certo
OBS: CTRL H revela arquivos escondidos; arquivos etc/environment tem que ser alterado manualmente e em "pasta pessoal"->bashrc tem que colocar o export M2_HOME=/
