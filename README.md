# jdbc-query
Jar para ajudar a fazer consultas pelo terminal

Passar os parametros:

url=&lt;URL&gt; <br />
usuario=&lt;usuario&gt; <br />
senha=&lt;senha&gt; <br />
driver=&lt;driver&gt; <br />
sql=&quot;&lt;sql&gt;&quot; (use aspas)  <br />
execute=&lt;Apenas se o sql n&atilde;o tiver resultset&gt; <br />
help=? <br />

Exemplo de uso:

 java -jar jdbc-query.jar url=jdbc:sqlite:test.db driver=org.sqlite.JDBC execute=? sql=&quot;CREATE TABLE USUARIO(ID INT PRIMARY KEY, NOME VARCHAR(100))&quot;

java -jar jdbc-query.jar url=jdbc:sqlite:test.db driver=org.sqlite.JDBC sql=&quot;SELECT * FROM USUARIO&quot;
