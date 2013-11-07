package fr.upem.poo.td1;

public class Contract {
	private final long price;
	private final long age;

	public Contract(long price, long age) {
		this.price = price;
		this.age = age;
	}

	public long getPrice() {
		return price;
	}

	public long getAge() {
		return age;
	}

}
