package org.zmq;

public class SocketProxy extends Socket {
	public SocketProxy (Context context, int type) {
		super(context, type);
	}

	public void close() {
		this.destroy();
	}
}

