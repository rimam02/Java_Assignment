import java.util.Scanner;

public class MultiCounterTicketBookingSimulator {

    static TicketPool ticketPool = new TicketPool(10);

    static CounterThread counterA;
    static Thread counterB;

    static class TicketPool {

        private int availableTickets;

        public TicketPool(int availableTickets) {
            this.availableTickets = availableTickets;
        }

        public synchronized boolean sellTicket(String counterName) {

            if (availableTickets > 0) {

                availableTickets--;

                System.out.println(
                        counterName
                                + " sold ticket. Remaining: "
                                + availableTickets
                );

                return true;

            } else {
                return false;
            }
        }

        public synchronized int getAvailableTickets() {
            return availableTickets;
        }

        public synchronized void reset(int tickets) {
            availableTickets = tickets;
        }
    }

    static class CounterThread extends Thread {

        private TicketPool pool;

        public CounterThread(String name, TicketPool pool) {
            super(name);
            this.pool = pool;
        }

        @Override
        public void run() {

            while (true) {

                boolean sold = pool.sellTicket(getName());

                if (!sold) {
                    break;
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(
                            getName()
                                    + " was interrupted while selling."
                    );
                    break;
                }
            }
        }
    }

    static class CounterRunnable implements Runnable {

        private TicketPool pool;

        public CounterRunnable(TicketPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {

            while (true) {

                boolean sold = pool.sellTicket(
                        Thread.currentThread().getName()
                );

                if (!sold) {
                    break;
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(
                            Thread.currentThread().getName()
                                    + " was interrupted while selling."
                    );
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {

            System.out.println(
                    "\n===== Multi-Counter Ticket Booking Simulator ====="
            );

            System.out.println("Available tickets: "
                    + ticketPool.getAvailableTickets());

            System.out.println("\n1. Start Counter using Thread Class");
            System.out.println("2. Start Counter using Runnable Interface");
            System.out.println("3. Set Thread Priority");
            System.out.println("4. Display Thread Status");
            System.out.println("5. Display Available Tickets");
            System.out.println("6. Wait for All Counters to Finish");
            System.out.println("7. Reset Ticket Pool");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    if (counterA != null && counterA.isAlive()) {
                        System.out.println(
                                "Counter-A is already running."
                        );
                    } else {

                        counterA = new CounterThread(
                                "Counter-A",
                                ticketPool
                        );

                        System.out.println(
                                "Starting Counter-A (Thread class)..."
                        );

                        counterA.start();
                    }

                    break;

                case 2:

                    if (counterB != null && counterB.isAlive()) {
                        System.out.println(
                                "Counter-B is already running."
                        );
                    } else {

                        CounterRunnable runnable =
                                new CounterRunnable(ticketPool);

                        counterB = new Thread(
                                runnable,
                                "Counter-B"
                        );

                        System.out.println(
                                "Starting Counter-B (Runnable interface)..."
                        );

                        counterB.start();
                    }

                    break;

                case 3:

                    if (counterA == null || counterB == null) {

                        System.out.println(
                                "Please start both counters first."
                        );

                    } else {

                        counterA.setPriority(8);
                        counterB.setPriority(5);

                        System.out.println(
                                "Counter-A priority set to 8."
                        );

                        System.out.println(
                                "Counter-B priority set to 5."
                        );
                    }

                    break;

                case 4:

                    System.out.println("\n===== Thread Status =====");

                    if (counterA != null) {

                        System.out.println(
                                "Name: "
                                        + counterA.getName()
                                        + " | Priority: "
                                        + counterA.getPriority()
                                        + " | Alive: "
                                        + counterA.isAlive()
                        );

                    } else {

                        System.out.println(
                                "Counter-A has not been started."
                        );
                    }

                    if (counterB != null) {

                        System.out.println(
                                "Name: "
                                        + counterB.getName()
                                        + " | Priority: "
                                        + counterB.getPriority()
                                        + " | Alive: "
                                        + counterB.isAlive()
                        );

                    } else {

                        System.out.println(
                                "Counter-B has not been started."
                        );
                    }

                    break;

                case 5:

                    System.out.println(
                            "Available tickets: "
                                    + ticketPool.getAvailableTickets()
                    );

                    break;

                case 6:

                    System.out.println(
                            "Waiting for all counters to finish (join)..."
                    );

                    try {

                        if (counterA != null) {
                            counterA.join();
                        }

                        if (counterB != null) {
                            counterB.join();
                        }

                        System.out.println(
                                "All counters finished selling."
                        );

                        System.out.println(
                                "Final available tickets: "
                                        + ticketPool.getAvailableTickets()
                        );

                    } catch (InterruptedException e) {

                        System.out.println(
                                "Main thread was interrupted while waiting."
                        );
                    }

                    break;

                case 7:

                    if ((counterA != null && counterA.isAlive())
                            || (counterB != null && counterB.isAlive())) {

                        System.out.println(
                                "Cannot reset while counters are running."
                        );

                    } else {

                        ticketPool.reset(10);

                        counterA = null;
                        counterB = null;

                        System.out.println(
                                "Ticket pool reset successfully."
                        );

                        System.out.println(
                                "Available tickets: "
                                        + ticketPool.getAvailableTickets()
                        );
                    }

                    break;

                case 8:

                    System.out.println(
                            "Thank you for using Multi-Counter "
                                    + "Ticket Booking Simulator."
                    );

                    System.out.println("Program terminated.");

                    break;

                default:

                    System.out.println(
                            "Invalid choice! Please enter a number "
                                    + "between 1 and 8."
                    );
            }

        } while (choice != 8);

        scanner.close();
    }
}