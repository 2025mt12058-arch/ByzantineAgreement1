import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ByzantineSimulation {
    public static void main(String[] args) throws Exception {
        List<Integer> ports = Arrays.asList(8001, 8002, 8003, 8004);
        List<Boolean> byzantines = Arrays.asList(false, false, false, true); // last is malicious

        // Send order from General
        String generalOrder = "ATTACK";
        for (int i = 0; i < ports.size(); i++) {
            if (!byzantines.get(i)) {
                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
                config.setServerURL(new URL("http://localhost:" + ports.get(i)));
                XmlRpcClient client = new XmlRpcClient();
                client.setConfig(config);
                client.execute("Node.receiveOrder", Arrays.asList(generalOrder, 0));
            }
        }

        // Vote exchange among Lieutenants (simplified)
        for (int i = 0; i < ports.size(); i++) {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:" + ports.get(i)));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);
            String decision = (String) client.execute("Node.getDecision", Arrays.asList());
            System.out.println("Node " + i + " decided: " + decision);
        }
    }
}
