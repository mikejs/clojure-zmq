package org.zmq;

public class ContextProxy extends Context {
	public ContextProxy (int appThreads, int ioThreads, int flags) {
		super(appThreads, ioThreads, flags);
	}

	public void close() {
		this.destroy();
	}
}

