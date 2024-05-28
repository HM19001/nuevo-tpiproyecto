FROM payara/server-full:6.2024.1-jdk17
USER root
RUN apt update && apt install -y wget
USER payara
RUN wget -O $PAYARA_DIR/glassfish/lib/postgresql.jar https://repo1.maven.org/maven2/org/postgresql/postgresql/42.7.2/postgresql-42.7.2.jar
RUN echo 'create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property user="${ENV=POSTGRES_USER}":password="${ENV=POSTGRES_PASSWORD}":serverName=db:portNumber="${ENV=POSTGRES_PORT}":databaseName="${ENV=POSTGRES_DBNAME}" pg_db' >> $CONFIG_DIR/pre-boot-commands.asadmin \
 && echo 'create-jdbc-resource --connectionpoolid pg_db jdbc/pgdb' >> $CONFIG_DIR/pre-boot-commands.asadmin
RUN echo 'deploy /opt/payara/aplicacion.war' >> $CONFIG_DIR/post-boot-commands.asadmin \
 && echo 'ping-connection-pool pg_db' >> $CONFIG_DIR/post-boot-commands.asadmin