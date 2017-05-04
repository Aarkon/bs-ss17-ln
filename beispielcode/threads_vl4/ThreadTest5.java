package threads_vl4;/* ThreadTest5.java
 Version 1.0
 Autor: M. Hübner
 Zweck: Beispiel für die Verwendung der join-Methode / isAlive-Methode
 */

public class ThreadTest5 {
    /* Beispiel für die Verwendung der join-Methode / isAlive-Methode */
    public static void main(String[] args) {
        MyThread5 testThread = new MyThread5();
        testThread.setName("Der einzige MyThread5");

        System.err.println("Name des ausgeführten Threads: " + Thread.currentThread().getName());
        System.err.println("Name des Test-Threads: " + testThread.getName());
        System.err.println("TestThread alive? " + testThread.isAlive());

        testThread.start();
        System.err.println("TestThread alive? " + testThread.isAlive());

    /* Startzeit ermitteln */
        long startTime = System.nanoTime();
        try {
      /* main-Thread auf Ende des testThreads (Zielobjekt) warten lassen */
            testThread.join();
        } catch (InterruptedException e) {
            // nichts
        }
        System.err.println("TestThread alive? " + testThread.isAlive());
    /* Ende mit Ausgabe der Wartezeit */
        long usedTime = System.nanoTime() - startTime;
        System.err.println("Der main-Thread hat brav " + usedTime / 1000000.0
                + " ms gewartet!");
    }
}

class MyThread5 extends Thread {
    /* Bis 100 hochzählen und Zahlen ausgeben */
    public void run() {
        int i;
        for (i = 0; i < 100; i++) {
            System.err.println(i);
        }
        System.err.println("MyThread5 wird beendet!");
    }
}
