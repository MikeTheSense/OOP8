package com.example.OOP8.Servlets;
import com.example.OOP8.Service.SingerService;
import com.example.OOP8.ServiceImplementation.SingerServiceImplementation;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns ={"/allsingers","/checksingername","/addsinger","/insertsinger","/editsinger","/deletesinger","/singertop"})
public class SingerServlet extends HttpServlet {
    private final SingerService singerService = new SingerServiceImplementation();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/allsingers":
                getAllUsersForm(request,response);
                break;
            case "/addsinger":
                getEditSinger(request,response);
                break;
            case "/editsinger":
                getEditSingerWithData(request,response);
                break;
            case "/singertop":
                getSingerTop(request,response);
                break;
            case "/deletesinger":
                deleteSinger(request,response);
                break;
        }
    }

    private void getSingerTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("singers", singerService.getTopVerstaliteSingers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("SingerPages/SingerTop.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteSinger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        singerService.deleteSinger(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/allsingers");
    }

    private void getEditSingerWithData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        try{
            id = Integer.parseInt((request.getParameter("id")));
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        request.setAttribute("singer",singerService.getSingerById(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("SingerPages/SingerForm.jsp");
        dispatcher.forward(request, response);
    }

    private void getEditSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("SingerPages/SingerForm.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/checksingername": checkSingerName(request,response);
                break;
            case "/insertsinger": insertSinger(request,response);
            break;
        }
    }

    private void insertSinger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        singerService.insertOrUpdateSinger(request.getParameter("singerId"),request.getParameter("SingerName"));
        response.sendRedirect(request.getContextPath() + "/allsingers");
    }

    private void checkSingerName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print(singerService.isSingerNameUnique(request.getParameter("singerId"),request.getParameter("singerName")));
        printWriter.close();
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

    private void getAllUsersForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("singers", singerService.getAllSingers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("SingerPages/Singer.jsp");
        dispatcher.forward(request, response);
    }
}
