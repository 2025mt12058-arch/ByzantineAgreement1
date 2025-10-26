import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LieutenantNode implements Node {
    private int nodeId;
    private boolean isByzantine;
    private String finalDecision;
    private Map<Integer, String> receivedOrders = new ConcurrentHashMap<>();

    public LieutenantNode(int nodeId, boolean isByzantine) {
        this.nodeId = nodeId;
        this.isByzantine = isByzantine;
    }

    @Override
    public void receiveOrder(String order, int senderId) {
        if (isByzantine) {
            order = (Math.random() > 0.5) ? "ATTACK" : "RETREAT"; // send conflicting info
        }
        receivedOrders.put(senderId, order);
        System.out.println("Node " + nodeId + " received order '" + order + "' from " + senderId);
    }

    @Override
    public void vote(String order, int senderId) {
        receivedOrders.put(senderId, order);
    }

    @Override
    public String getDecision() {
        // Majority vote among received orders
        Map<String, Integer> count = new HashMap<>();
        for (String order : receivedOrders.values()) {
            count.put(order, count.getOrDefault(order, 0) + 1);
        }
        finalDecision = count.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        return finalDecision;
    }
}
