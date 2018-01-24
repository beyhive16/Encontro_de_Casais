import java.io.IOException;
import java.sql.SQLException;

import dao.BaseDAO;

public class Main {
	public static void main(String[] args) throws SQLException, IOException {
		BaseDAO baseDAO = new BaseDAO();
		baseDAO.createTables();
		baseDAO.getConnection().close();
	}
}
