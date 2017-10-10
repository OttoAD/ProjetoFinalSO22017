#PROJETO FINAL - SISTEMAS OPERACIONAIS

----- ESPECIFICA��O -----

- Gerenciador de processos: agrupar em quatro n�veis de prioridade
- Gerenciador de mem�ria: garantir que um processo n�o acesse regi�o de mem�ria de outro
- Gerenciador de arquivos: garantir libera��o e aloca��o, assim como uso exclusivo dos dispositivos
- Gerenciador de E/S: criar e deletar arquivos de acordo com o modelo de aloca��o determinado

Filas: valores menores = maiores prioridades
- processos de tempo real (maior pioridade, FIFO sem preemp��o)
- processos de usu�rios (tr�s filas com prioridades distintas e realimenta��o, usar aging para evitar starvation)

- No m�ximo 1000 processos nas filas
- Recomenda-se usar uma filha global (ver figura 1)

Mem�ria: conjunto de blocos contiguos. N�o ser� utilizado swap ou p�ginas.
- fixa de 1024 blocos com 64
- cada bloco tem 1mb

Recursos de E/S:
- scanner
- 2 impressores
- 1 modem
- 2 SATA

- Sem preemp��o
- Processos de tempo real n�o fazem uso
- Cada recurso seja alocado para um proceso por vez

Arquivos:
- criar e deletar
- aloca��o cont�gua
- arquivo deve ser tratado como uma unidade de manipula��o
- first fit
- processos de tempo real podem fazer tudo, at� com processos de terceiros
- entrada � um TXT: cont�m a quantidade total de blocos no disco, a especifica��o dos segmentos ocupados por cada arquivo, as opera��es a serem realizadas por cada processo
- deve mostrar na tela do computador um mapa com a atual ocupa��o do disco, descrevendo quais arquivos est�o em cada bloco, e quais s�o os blocos vazios (identificados por 0)


ESTRUTURA:
- M�dulo de Processos � classes e estruturas de dados relativas ao processo. Basicamente, mant�m informa��es espec�ficas do processo.

- M�dulo de Filas � mant�m as interfaces e fun��es que operam sobre os processos
- M�dulo de Mem�ria � prov� uma interface de abstra��o de mem�ria RAM
- M�dulo de Recurso � trata a aloca��o e libera��o dos recursos de E/S para os processos
- M�dulo de Arquivos � trata as opera��es create e delete sobre os arquivos

INTERFACE
Entrada:

Ler os arquivos .txt: contendo as informa��es dos processos a serem criados e descri��o das opera��es a serem realizadas pelo sistema de arquivos

Processos
- cada linha cont�m as informa��es de um �nico processo
- linha: <tempo de inicializa��o>, <prioridade>, <tempo de processador>, <blocos em mem�ria>, <n�meroc�digo da impressora requisitada>, <requisi��o do scanner>, <requisi��o do modem>, <n�meroc�digo do disco>

Arquivos
- Haver� a quantidade total de blocos no disco, a quantidade de segmentos ocupados, a identifica��o de quais arquivos j� est�o gravados no disco, a localiza��o dos blocos usados por cada arquivo, a identifica��o de qual processo efetuar� cada opera��o, a identifica��o das opera��es, sendo c�digo 0 para criar um arquivo, e c�digo 1 para deletar um arquivo.
Al�m disso, para as opera��es de cria��o deve constar o nome do arquivo a ser criado, e a quantidade de blocos ocupada pelo arquivo. Por outro lado, na opera��o de deletar um arquivo, deve constar apenas o nome do arquivo a ser deletado.

- Linha 1: Quantidade de blocos do disco
- Linha 2: Quantidade de segmentos ocupados no disco (n);
- A partir da Linha 3 at� Linha n + 2: arquivo (a ser identificado por uma letra), n�mero do primeiro bloco gravado, quantidade de blocos ocupados por este arquivo;
- A partir da linha n + 3: cada linha representa uma opera��o a ser efetivada pelo sistema de arquivos do pseudo-SO. Para isso, essas linhas v�o conter: <ID_Processo>, <C�digo_Opera��o>, <Nome_arquivo>, <se_operacaoCriar_numero_blocos>.


Sa�da:
O processo principal � o �despachante", este deve exibir uma mensagem de identifica��o contendo as seguintes informa��es:

- PID (int)
- Prioridade do processo (int)
- Offset da mem�ria (int)
- Quantidade de blocos alocados na mem�ria (int)
- Utiliza��o de impressora (bool)
- Utiliza��o de scanner (bool)
- Utiliza��o de drivers (bool)
- O processo deve exibir alguma mensagem que indique sua execu��o

---- METODOLOGIA DO GRUPO ----
- JDK 1.8: http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html
- Eclipse IDE: http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/oxygen/1/eclipse-java-oxygen-1-win32-x86_64.zip
- SonarQube(baixar plugin pro eclipse tamb�m): https://www.sonarqube.org/
- Git(procurar pelo plugin para o eclipse): https://git-scm.com/downloads
- Maven(j� vem no eclipse, mas por via de duvidas): https://maven.apache.org/download.cgi

Tutorial de instala��o JAVA E MAVEN:

WINDOWS 7:

- 1 - Baixar a JDK(Java Development Kit)
- 2 - Baixar os bin�rios do Maven
- 3 - Painel de Controle -> Sistema e Seguran�a -> Sistema -> Configura��es Avan�adas do Sistema -> Vari�veis de Ambiente
- 4 - New Path em Vari�veis do Sistema -> nome: JAVA_HOME -> apontar para o diret�rio jdk(C:\Program Files\Java\jdk1.8)
- 5 - New Path em Vari�veis do Sistema -> nome: CLASSPATH -> apontar para %JAVA_HOME%
- 6 - Na vari�vel PATH, adicionar ao final: %JAVA_HOME%\bin
- 7 - New Path em Vari�veis do Sistema -> nome: MAVEN_HOME -> apontar para o diret�rio do maven(n�o o bin, a raiz)
- 8 - Na vari�vel PATH, adicionar ao final: %MAVEN_HOME%\bin
- 9 - no prompt(cmd), digitar maven -version, javac e java -version pra checar as instala��es

UBUNTU 16:
JDK:
- 1 - sudo apt-get update
- 2 - sudo apt-get install default-jre
- 3 - sudo apt-get install default-jdk
- 4 - java -version
- 5 - javac
- 6 - sudo apt-get install openjdk-7-jre
- 7 - sudo apt-get install openjdk-<VERSAO>-jdk (vers�o pode ser 6, 7 ou 8 no momento)
- 8 - export JAVA_HOME=/usr/lib/jvm/java-8-oracle/?
- 9 - export CLASSPATH=JAVA_HOME/lib/:$CLASSPATH
    export PATH=$JAVA_HOME/bin/:$PATH
    export M2_HOME=/usr/share/maven/
    export M2=$M2_HOME/bin
    export PATH=$M2:$PATH
- 10 - echo $JAVA_HOME

Se nada acima der certo
OBS: CTRL H revela arquivos escondidos; arquivos etc/environment tem que ser alterado manualmente e em "pasta pessoal"->bashrc tem que colocar o export M2_HOME=/