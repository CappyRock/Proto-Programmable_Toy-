package Background;

public class Box<T> {
	public T t;
	public String name;

	public Box(String blame, T t) {
		this.name = blame;
		this.t = t;
		
	}
	public Box(String blame) {
		this.name = blame;
		this.t = null;
	}
}
