package core.java.producer_consumer.messageQueue;

public class Message {
	
	private final String data;

	public Message(String data) {
		this.data = data;
	}

	public String getData() {
		return this.data;
	}

	@Override
	public String toString() {
		return "Message [data=" + data + "]";
	}
	
}
