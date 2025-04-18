package projet.jsf.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import projet.jsf.util.CompteActif;
import projet.jsf.util.UtilJsf;

@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
					, urlPatterns = { "/pages/*", "/javax.faces.resource/pdf/*" })
public class Filter1Pages implements Filter {

	//-------
	// Champs
	//-------
	
	@Inject
	private CompteActif		compteActif;

	//-------
	// Initialisation et clôture
	//-------
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	//-------
	// Actions
	//-------
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if ( compteActif.isLoggedIn() ) {
	        // si OK, on traite l'URL normalement
	        chain.doFilter(request, response);
	    } else {
	        // sinon on affiche la page de connexion avec un message
			request.setAttribute( UtilJsf.MSG_ERROR, "Vous n'êtes pas connecté."  );
			request.getRequestDispatcher( "/connexion.xhtml" ).forward(request, response);
	    }
	}

}
