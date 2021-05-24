package bo.com.digitalharbor.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 4913093L;

	public ServiceException(String m) {
		super(m);
	}

	public ServiceException(Throwable t) {
		super(t);
	}

	public ServiceException(Throwable t, String m) {
		super(m, t);
	}
}
