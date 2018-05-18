import java.io.IOException;
import java.sql.SQLException;

import Dao.BaseDAO;
import Dao.UsuarioDAO;
import Dao.UsuarioDAOImp;
import Entidade.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	  @Override
	    public void start(Stage stage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
	        
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }

	    /**
	     * @param args the command line arguments
	     * @throws SQLException 
	     * @throws IOException 
	     */
	    public static void main(String[] args) throws SQLException, IOException {
	    	BaseDAO baseDAO = new BaseDAO();
	    	baseDAO.createTables();
	    	UsuarioDAO usuarioDAO = new UsuarioDAOImp(baseDAO);
	    	Usuario user = usuarioDAO.login("admin", "admin");
	    	if(user==null)
	    	{
	    		user = new Usuario();
	    		user.setNome("admin");
	    		user.setNomeUsual("admin");
	    		user.setSenha("admin");
	    		usuarioDAO.create(user);
	    	}
	    	baseDAO.getConnection().close();
	        launch(args);
	    }
	

}
