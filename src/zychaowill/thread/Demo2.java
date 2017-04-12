package zychaowill.thread;

public class Demo2 {
	
	private int i = 0;
	
	public static void main(String[] args) {
		Demo2 demo = new Demo2();
		Add add = demo.new Add();
		Sub sub = demo.new Sub();
		
		for (int i = 0; i < 2; i++) {
			new Thread(add, "线程" + i).start();
			new Thread(sub, "线程" + i).start();
		}
	}
	
	class Add implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				addOne();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class Sub implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				subOne();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized void addOne() {
		i++;
		System.out.println(Thread.currentThread().getName() + "加一的值为: " + i);
	}
	
	public synchronized void subOne() {
		i--;
		System.out.println(Thread.currentThread().getName() + "减一的值为: " + i);
	}
	
}
