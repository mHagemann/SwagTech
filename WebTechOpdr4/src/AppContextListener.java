
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import model.Model;

/**
 * Application Lifecycle Listener implementation class AppContextListener
 *
 */
@WebListener
public class AppContextListener implements ServletContextListener {
	
	private ServletContext context; 

    /**
     * Default constructor. 
     */
    public AppContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
	public void contextInitialized(ServletContextEvent arg0) {
		
		context = arg0.getServletContext();
		System.out.println("context init");

		
		try {
			context.setAttribute("model", new Model());
			System.out.println("Model added to context");
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
		}
		
		System.out.println("ServletContextListener started");
	}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	System.out.println("Context destroyed!");
    }
	
}
