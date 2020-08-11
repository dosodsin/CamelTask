import org.apache.camel.Exchange;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CounterProcessor implements org.apache.camel.Processor {

    private static int txtCounter = 0;
    private static int xmlCounter = 0;
    private static int otherCounter = 0;

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void process(Exchange exchange) throws Exception {

        long start = System.currentTimeMillis();

        String fileName = exchange.getIn().getHeader("CamelFIleName").toString();
        if (fileName.endsWith(".txt")) {
            txtCounter++;
        } else if (fileName.endsWith(".xml")) {
            xmlCounter++;
        } else {
            otherCounter++;
            exchange.setException(new Exception("wrong file extension"));
        }
        long end = System.currentTimeMillis();
        if ((txtCounter + xmlCounter + otherCounter) % 100 == 0) {
            logger.info("txt files: " + txtCounter + ", xml files: " + xmlCounter + ", other files: " + otherCounter + ", time: " + (end - start));

        }
    }
}
