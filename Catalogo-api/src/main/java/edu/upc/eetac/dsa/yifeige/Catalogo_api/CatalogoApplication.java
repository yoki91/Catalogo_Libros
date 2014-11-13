package edu.upc.eetac.dsa.yifeige.Catalogo_api;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
public class CatalogoApplication extends ResourceConfig
{	public CatalogoApplication() 
{
	super();
	register(DeclarativeLinkingFeature.class);
	}

}
