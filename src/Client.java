public class Client {


    private String ip;
    private int clientNr=0;
    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ip='" + ip + '\'' +
                ", clientNr=" + clientNr +
                ", port=" + port +
                ", name='" + name + '\'' +
                ", connected=" + connected +
                '}'+ "\n";
    }

    public void setPort(int port) {
        this.port = port;
    }


    public void setName(String name) {
        this.name = name;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    private String name;
    private boolean connected=false;


    public Client(String ip, int port, String name, int clientNr, boolean connected) {
        this.ip = ip;
        this.clientNr = clientNr+1;
        this.port = port;
        this.name = name;
        this.connected=connected;
    }

    public int getClientNr() {
        return clientNr;
    }

    public void setClientNr(int clientNr) {
        this.clientNr = clientNr;
    }
}
