import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("resources")
public class MyREST extends ResourceConfig {
	public MyREST() {
		packages("resource");
	}
}