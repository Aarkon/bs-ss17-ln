package mensa;

/**
 * This class sets up and starts the whole mensa simulation.
 */
public class MensaRunner {

	public static void main(String[] args) {

		// The time students are able to use the mensa
		int runtimeMillis = 3000;

		// The amount of cash registers in the mensa
		int amountCashRegisters = 3;

		Mensa mensa = new Mensa(amountCashRegisters);

		// 10 Students
		Student anton = new Student("Anton", mensa);
		Student bert = new Student("Bert", mensa);
		Student chris = new Student("Chris", mensa);
		Student dave = new Student("Dave", mensa);
		Student emil = new Student("Emil", mensa);
		Student flo = new Student("Flo", mensa);
		Student gustav = new Student("Gustav", mensa);
		Student hendrik = new Student("Hendrik", mensa);
		Student ilya = new Student("Ilya", mensa);
		Student jonas = new Student("Jonas", mensa);

		anton.start();
		bert.start();
		chris.start();
		dave.start();
		emil.start();
		flo.start();
		gustav.start();
		hendrik.start();
		ilya.start();
		jonas.start();

		try {
			Thread.sleep(runtimeMillis);
			anton.interrupt();
			bert.interrupt();
			chris.interrupt();
			dave.interrupt();
			emil.interrupt();
			flo.interrupt();
			gustav.interrupt();
			hendrik.interrupt();
			ilya.interrupt();
			jonas.interrupt();
		} catch (InterruptedException e) {
			System.err.println("Main-Thread got interrupted during sleep time!");
		}
	}
	
}
