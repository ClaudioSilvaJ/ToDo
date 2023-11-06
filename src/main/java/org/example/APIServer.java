package org.example;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.classes.LoginDTO;
import org.glassfish.jersey.servlet.ServletContainer;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class APIServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8231);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Jersey Jax-RS
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/api/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "org.example");

        //Cors Filter
        context.addFilter(CorsFilter.class, "/*", null);

        //Morphia MongoDB
        Datastore datastore = new MorphiaConfig().getDatastore();
        //criaAdmin(datastore);

        server.start();
        server.join();
    }

    public static void criaAdmin(Datastore datastore){
        LoginDTO login1 = new LoginDTO();
        login1.setEmail("admin");
        login1.setPassword("admin");
        datastore.save(login1);
        procura(datastore, login1);
    }

    public static void procura(Datastore datastore, LoginDTO login){
        Query<LoginDTO> query = datastore.createQuery(LoginDTO.class);
        query.field("email").equal("admin");
        List<LoginDTO> resultados = query.asList();
        if (!resultados.isEmpty()) {
            System.out.println("Encontrado: O valor existe no campo 'campo1' em algum documento.");
            System.out.println(resultados.get(0).getPassword());
        } else {
            System.out.println("Não encontrado: O valor não existe no campo 'campo1' em nenhum documento.");
        }
    }
}
