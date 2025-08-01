public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Invoker invoker = new Invoker();
        Receiver receiver = new Receiver();




        invoker.setCommandsForExecution();

    }
}
