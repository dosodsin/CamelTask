import bitronix.tm.resource.jdbc.PoolingDataSource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.sql.Connection;
import java.sql.Statement;

public class TxtFileProcessor implements Processor {
    PoolingDataSource poolingDataSource;
    Connection connection;
    Statement statement;

    public PoolingDataSource getPoolingDataSource() {
        return poolingDataSource;
    }

    public void setPoolingDataSource(PoolingDataSource poolingDataSource) {
        this.poolingDataSource = poolingDataSource;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        connection=poolingDataSource.getConnection();
        statement=connection.createStatement();
        statement.executeUpdate("insert into test(txt_body) value ('"+exchange.getIn().getBody()+ "')");
    }
}
