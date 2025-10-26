import org.apache.xmlrpc.webserver.WebServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;

public class NodeServer {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("Usage: java NodeServer ");
            return;
        }

        int port = Integer.parseInt(args[0]);
        boolean isByzantine = Boolean.parseBoolean(args[1]);
        int nodeId = Integer.parseInt(args[2]);


        // Create web server
        WebServer server = new WebServer(port);
        XmlRpcServer xmlRpcServer = server.getXmlRpcServer();

        // Create handler mapping and register class
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        phm.addHandler("Node", LieutenantNode.class);

        xmlRpcServer.setHandlerMapping(phm);

        // Start the server
        server.start();

        System.out.println("Node  " + nodeId + " started on port " + port + " Byzantine=" + isByzantine);
    }
}

