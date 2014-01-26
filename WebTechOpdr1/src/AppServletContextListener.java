import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.Model;
 
/**
 * @author Maickel
 */
public class AppServletContextListener implements ServletContextListener{
 
	ServletContext sc;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}
 
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		sc = arg0.getServletContext();
		
		try {
			sc.setAttribute("model", new Model());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ServletContextListener gestart");
		
	}
}