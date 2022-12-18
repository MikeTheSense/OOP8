package com.example.OOP8.Servlets;

import com.example.OOP8.Service.AlbumService;
import com.example.OOP8.Service.CompositionService;
import com.example.OOP8.ServiceImplementation.AlbumServiceImplementation;
import com.example.OOP8.ServiceImplementation.CompositionServiceImplementation;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns ={"/compositionsByAlbum", "/compositionalbumform", "/checkcomposition","/addcompositiontoalbum","/editcomposition","/deletecomposition","/compositions"})
public class CompositionServlet extends HttpServlet {
    private final AlbumService albumService = new AlbumServiceImplementation();
    private final CompositionService compositionService = new CompositionServiceImplementation();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/compositionsByAlbum":
                getCompositionsByAlbum(request, response);
                break;
            case "/compositionalbumform":
                getCompositionEdit(request, response);
            break;
            case "/editcomposition":
                getCompositionEditWithData(request, response);
                break;
            case "/deletecomposition":
                removeComposition(request,response);
                break;
            case "/compositions":
                getAllCompositions(request,response);
                break;
        }
    }

    private void getAllCompositions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("compositions", compositionService.getCompositionsWithAlbum());
        RequestDispatcher dispatcher = request.getRequestDispatcher("CompositionPages/AllCompositions.jsp");
        dispatcher.forward(request, response);
    }

    private void removeComposition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/compositionsByAlbum"+"?id="+compositionService.removeCompositionFromAlbum(request.getParameter("name"),request.getParameter("compositionname")));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/checkcomposition":
                checkComposition(request, response);
                break;
            case "/addcompositiontoalbum":
                addCompositionToAlbum(request, response);
                break;

        }
    }

    private void addCompositionToAlbum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        compositionService.addCompositionToAlbumById(request.getParameter("id"), request.getParameter("CompositionName"),request.getParameter("CompositionDuration"),request.getParameter("CompositionId"));
        response.sendRedirect(request.getContextPath() + "/compositionsByAlbum"+"?id="+request.getParameter("id"));

    }

    private void checkComposition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print(compositionService.isNameExists(request.getParameter("compositionName"), request.getParameter("compositionId")));
        printWriter.close();
    }


    private void getCompositionEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("album", albumService.getAlbumByName(request.getParameter("name")));
        RequestDispatcher dispatcher = request.getRequestDispatcher("CompositionPages/CompositionForm.jsp");
        dispatcher.forward(request, response);
    }
    private void getCompositionEditWithData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("album", albumService.getAlbumByName(request.getParameter("name")));
        request.setAttribute("composition",compositionService.getCompositionByName(request.getParameter("compositionname")));
        RequestDispatcher dispatcher = request.getRequestDispatcher("CompositionPages/CompositionForm.jsp");
        dispatcher.forward(request, response);
    }


    public void init() {

    }
    public void destroy() {
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }

    private void getCompositionsByAlbum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("album", compositionService.getCompositionsByAlbumId(request.getParameter("id")));
        RequestDispatcher dispatcher = request.getRequestDispatcher("CompositionPages/CompositionsByAlbum.jsp");
        dispatcher.forward(request, response);
    }
}
