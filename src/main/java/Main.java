import dao.BaseDAO;
import views.Login;

public class Main {
	public static void main(String[] args) throws Exception {
		BaseDAO baseDAO = new BaseDAO();
		baseDAO.createTables();
		baseDAO.getConnection().close();
		
		Login login = new Login();
		login.start();
	}
}
