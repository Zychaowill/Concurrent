package zychaowill.discover.smallskill.pc.example02;

public class ThreadC extends Thread {
	private Consumer consumer;

	public ThreadC(Consumer consumer) {
		super();
		this.consumer = consumer;
	}
	
	@Override
	public void run() {
		while (true) {
			consumer.getValue();
		}
	}
}