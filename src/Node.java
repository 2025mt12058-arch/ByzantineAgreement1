public interface Node {
    // General sends order to Lieutenant
    void receiveOrder(String order, int senderId) throws Exception;

    // Lieutenant sends votes to others
    void vote(String order, int senderId) throws Exception;

    // Get the final decided order
    String getDecision() throws Exception;
}
