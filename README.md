# servicoSMS #

	Esse Módulo está configurado para servir na porta 8080, contudo pode ser alterado em application.properties.
	
	Sua responsabilidade é receber/enviar requisições do módulo Client, processar tais dados recebidos, e envia-los 
	ao módulo que simula a Operadora Móvel de Celular (Dummy).
	
	O retorno da operadora móvel é então capturado, e enviado de volta ao Client com o status de sua 
	ação pretendida ( Enviar 1(um) ou mais SMS´s ).
 
	/*************************************************************/
			responses:
 
 				"201": description: SMS sent 
				"500": description: Internal Server Error 
				"405": description: Validation exception 
				"404": description: Mobile User not found
				...

	/*************************************************************/


## 1: Instalacao ##

	+JAVA
 	
 	O projeto foi desenvolvido e testado com JAVA 1.8.0_131.
 	Caso tenha mais de um JAVA instalada em sua máquina, por favor
 	utilize a última versão do java8.
 	
	+ Maven
	
 	O projeto foi desenvolvido com o maven version 3.5.0
 	Certifique-se que tenha o Maven instalado e configurado corretamente. https://maven.apache.org/install.html
 	  
	+ H2
	
	A versão embarcada no projeto é 1.4.192
	Certifique-se que tenha o banco de dados H2 instalado. http://www.h2database.com/html/download.html
	
 
## 2: Execucao ##

	# Utilizando o MAVEN #
	1- Abra um prompt/terminal e realize o clone do projeto [ git clone https://github.com/JulioKhichfy/servicoRestSMS.git ]
	2- Depois de realizado o clone do projeto em sua pasta de preferência, acesse a pasta raiz.
	3- Execute a goal 'mvn package'
	4- Acesse a pasta target gerada
	5- Execute java -jar servicoSMS-0.0.1-SNAPSHOT.jar
	6- Para certificar que o serviço está no ar acesse http://localhost:8080 e verifique se exibe "Servico SMS - L I G A D O"
	
	# Utilizando o ECLIPSE #
	1-	Importe o projeto como "Existing Maven Project"
	2- Acesse a pasta raiz do projeto e clique em next.
	3- Procure pela Main do projeto(ServicoSMSApplication) 
	4- Configure em 'Run Configurations'
	5- Na aba "Main" Escolha o 'Project' servicoSMS, assim como sua 'Main Class'(br.com.sms.configuracao.ServicoSMSApplication)
	6- Na aba "JRE" aproveite para verificar se a JRE está na versão 1.8
	7- Por fim clique na classe Main e execute em 'Run As' como 'Java Application'.
	8- Para certificar que o serviço está no ar acesse http://localhost:8080 e verifique se exibe "Servico SMS - L I G A D O" 

	

