public class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println("Пополнение на " + amount + " руб. Новый баланс: " + balance + " руб.");
        notifyAll(); // Уведомляем потоки, которые могут ждать изменений баланса
    }

    public synchronized void withdraw(double amount) {
        while (balance < amount) {
            try {
                System.out.println("Ожидание пополнения для снятия " + amount + " руб.");
                wait(); // Ждем, пока не появятся достаточные средства на счете
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        balance -= amount;
        System.out.println("Снятие " + amount + " руб. Новый баланс: " + balance + " руб.");
    }

    public synchronized double getBalance() {
        return balance;
    }
}
