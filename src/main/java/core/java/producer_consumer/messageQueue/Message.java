package core.java.producer_consumer.messageQueue;

public class Message {
	
	private String data;

	public Message(String data) {
		this.data = data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Message [data=" + data + "]";
	}
	
}
