# jdbc-query
Jar para ajudar a fazer consultas pelo terminal

Passar os parametros url=<URL> usuario=<usuario> senha=<senha> driver=<driver> sql="<sql>" (use aspas) 
execute=<Apenas se o sql não tiver resultset> ou help=? 

Exemplo de uso:

 java -jar jdbc-query.jar url=jdbc:sqlite:test.db driver=org.sqlite.JDBC execute=? sql="CREATE TABLE USUARIO(ID INT PRIMARY KEY, NOME VARCHAR(100))"

java -jar jdbc-query.jar url=jdbc:sqlite:test.db driver=org.sqlite.JDBC sql="SELECT * FROM USUARIO"
