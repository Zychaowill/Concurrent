package zychaowill.discover.smallskill.pc.example02;

import zychaowill.discover.smallskill.pc.ValueObject;

public class Producer {
	private String lock;

	public Producer(String lock) {
		super();
		this.lock = lock;
	}
	
	public void setValue() {
		try {
			synchronized (lock) {
				while (!ValueObject.value.equals("")) {
					System.out.println("Producer " + Thread.currentThread().getName() + " WAITING");
					lock.wait();
				}
				System.out.println("Producer " + Thread.currentThread().getName() + " RUNNABLE");
				String value = System.currentTimeMillis() + "_" + System.nanoTime();
				ValueObject.value = value;
				lock.notifyAll();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
