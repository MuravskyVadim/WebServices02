package main;

import base.AccountService;
import base.DBService;
import dbService.DBServiceImpl;
import frontend.AccountServiceImpl;
import frontend.servlets.SignInServlet;
import frontend.servlets.SignUpServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) {
        try {
            DBService dbService = new DBServiceImpl();
            dbService.create();

            AccountService accountService = new AccountServiceImpl(dbService);

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
            context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

            ResourceHandler handler = new ResourceHandler();
            handler.setResourceBase("html");

            HandlerList handlerList = new HandlerList();
            handlerList.setHandlers(new Handler[]{handler, context});

            Server server = new Server(8080);
            server.setHandler(handlerList);

            server.start();
            java.util.logging.Logger.getGlobal().info("Server started");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
