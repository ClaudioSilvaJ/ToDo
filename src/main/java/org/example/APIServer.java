package org.example;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class APIServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8231);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);


        // Registre seu servlet
        context.addServlet(new ServletHolder(new MyAPIServlet()), "/api/*");

        context.addFilter(CorsFilter.class, "/*", null);

        server.start();
        server.join();
    }
}

