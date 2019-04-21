import org.fool.hellojava.service.AService;
import org.fool.hellojava.service.BService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    private AService aService;
    private BService bService;

    private final CountDownLatch keepAliveLatch = new CountDownLatch(1);
    private final Thread keepAliveThread;
    private static Server instance = null;

    public static synchronized Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }

        return instance;
    }

    private Server() {
        keepAliveThread = new Thread(() -> {
            try {
                keepAliveLatch.await();
            } catch (InterruptedException e) {
                logger.error("Server interrupted", e);
                Thread.currentThread().interrupt();
            }
        }, "Server[keepAlive]");

        keepAliveThread.setDaemon(false);

        Runtime.getRuntime().addShutdownHook(new Thread(keepAliveLatch::countDown));
    }

    public void setup(boolean addShutdownHook) {
        if (addShutdownHook) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (aService != null) {
                    aService.stop();
                }

                if (bService != null) {
                    bService.stop();
                }
            }));
        }
    }

    public void start() {
        aService = new AService();
        aService.start();

        bService = new BService();
        bService.start();

        keepAliveThread.start();
    }

    public static void main(String[] args) {
        try {
            Server server = Server.getInstance();
            server.setup(true);
            server.start();
        } catch (Exception e) {
            logger.error("Server start error", e);
            System.exit(-1);
        }
    }
}
