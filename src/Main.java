import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(0);

        // Запуск потока для многократного пополнения
        Thread depositThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                double amount = random.nextDouble() * 100; // Произвольная сумма для пополнения
                account.deposit(amount);
                try {
                    Thread.sleep(1000); // Пауза между пополнениями
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        depositThread.start();

        // Вызов метода для снятия денег, когда указанная сумма будет накоплена
        double targetWithdrawal = 500;
        account.withdraw(targetWithdrawal);

        // Вывод остатка на балансе
        System.out.println("Остаток на балансе: " + account.getBalance() + " руб.");
    }
}
