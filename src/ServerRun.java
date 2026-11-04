public class ServerRun {
    public static void main(String[] args) {
        DataFile.loadPositionsDB();
        AppServer myServerRun = new AppServer();
        myServerRun.server();
    }
}