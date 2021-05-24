package bo.com.digitalharbor.exception;

public class ValidationException extends ServiceException {

	private static final long serialVersionUID = 4913093L;

	public ValidationException(String m) {
		super(m);
	}

	public ValidationException(Throwable t) {
		super(t);
	}

	public ValidationException(Throwable t, String m) {
		super(t, m);
	}

}
