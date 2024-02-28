package db;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DbException(String msg) {
		super(msg);
	}
	// quando tiver uma exceçao será passado a mensagem do proprio sistema, de
	// acordo com o erro

}
