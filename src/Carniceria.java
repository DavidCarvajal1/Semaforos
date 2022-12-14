import java.util.concurrent.Semaphore;

public class Carniceria implements Runnable{
    Semaphore semaforo=new Semaphore(4);

    @Override
    public void run() {
        carniceria();
    }

    /**
     * Atendera a un cliente, ocupando el hueco al inicio y dejandolo libre al terminar
     */
    private void carniceria(){
        try{
            semaforo.acquire();
            System.out.println("El cliente "+Thread.currentThread().getName()+" esta siendo atendido");
            Thread.sleep(10000);
            System.out.println("El cliente "+Thread.currentThread().getName()+" ha sido atendido");
            semaforo.release();
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
    }

    public static void main(String[] args) {
        //Creamos un objeto de tipo carniceria
        Carniceria carniceria=new Carniceria();

        for (int i = 0; i < 10; i++) {//Ejecutamos 10 hilos de carniceria dandole un nombre a cada uno
            Thread th=new Thread(carniceria);
            th.setName("Cliente "+(i+1));
            th.start();
        }
    }
}
