package it.meet.servlets;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import it.meet.service.user.UserService;
import it.meet.service.user.UserServiceImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The servlet reveice the json message from client e call the
 * right method
 * 
 * @author Luigi Vorraro
 */
public class JSONServlet extends HttpServlet {

//    /**
//     * Processes requests for both HTTP
//     * <code>GET</code> and
//     * <code>POST</code> methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet JSONServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet JSONServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {            
//            out.close();
//        }
//    }


    private UserService userService;
    private JsonRpcServer jsonRpcServer;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            jsonRpcServer.handle(req, resp);
        } catch (IOException ex) {
            Logger.getLogger(JSONServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init(ServletConfig config) {
        this.userService = new UserServiceImpl();
        this.jsonRpcServer = new JsonRpcServer(this.userService, UserService.class);
    }
    
}
