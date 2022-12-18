package com.example.OOP8.Servlets;
import com.example.OOP8.Service.AlbumService;
import com.example.OOP8.Service.SingerService;
import com.example.OOP8.ServiceImplementation.AlbumServiceImplementation;
import com.example.OOP8.ServiceImplementation.SingerServiceImplementation;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/albums","/insertalbum","/updatealbum","/editalbum","/editalbumform","/deletealbum", "/addalbum", "/ajaxrequest", "/ajaxhandler", "/checkname","/createalbum","/singeralbums","/topalbumwithsong" ,""})
public class AlbumServlet extends HttpServlet {
    private final AlbumService albumService = new AlbumServiceImplementation();
    private final SingerService singerService = new SingerServiceImplementation();

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/albums":
                getAllAlbums(request, response);
                break;
            case "/editalbum":
                getEditAlbumForm(request, response);
                break;
            case "/ajaxrequest":
                getAuthorById(request, response);
                break;
            case "/ajaxhandler":
                getAuthorsLike(request, response);
                break;
            case "/deletealbum":
                deleteAlbum(request, response);
                break;
            case "/createalbum":
                createAlbum(request, response);
                break;
            case "/addalbum":
                addAlbum(request, response);
                break;
            case "/singeralbums":
                getSingerAlbums(request,response);
                    break;
            case "/topalbumwithsong":
                getTopFiveAlbums(request,response);
            default:
                getAllAlbums(request, response);
                break;
        }
    }

    private void getTopFiveAlbums(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("albums", albumService.getTopAlubms());
        RequestDispatcher dispatcher = request.getRequestDispatcher("AlbumPages/TopAlbums.jsp");
        dispatcher.forward(request, response);
    }

    private void getSingerAlbums(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("albums", albumService.getAllAlbumsByAuthorId(request.getParameter("id")));
        RequestDispatcher dispatcher = request.getRequestDispatcher("AlbumPages/SingerAlbums.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/updatealbum":
                updateAlbum(request, response);
                break;
            case "/checkname":
                checkName(request, response);
                break;
            case "/createalbum":
                insertAlbum(request, response);
                break;
            default:
                getAllAlbums(request, response);
                break;
        }
    }

    public void destroy() {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }

    private void getAllAlbums(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("albums", albumService.getAlbums());
        RequestDispatcher dispatcher = request.getRequestDispatcher("AlbumPages/Album.jsp");
        dispatcher.forward(request, response);
    }

    private void getEditAlbumForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("album", albumService.getAlbumWithSingerById(request.getParameter("id")));
        RequestDispatcher dispatcher = request.getRequestDispatcher("AlbumPages/AlbumForm.jsp");
        dispatcher.forward(request, response);
    }

    private void getAuthorById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("singerId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (id > 0) {
            OutputStream outStream = response.getOutputStream();
            outStream.write(singerService.getSingerById(id).getName().getBytes("UTF-8"));
            outStream.flush();
            outStream.close();
        } else {
            OutputStream outStream = response.getOutputStream();
            outStream.write("Введите нормальное значение".getBytes("UTF-8"));
            outStream.flush();
            outStream.close();
        }

    }

    private void getAuthorsLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String input = request.getParameter("input");
        String names = singerService.getAllSingerNamesLike(input);
        OutputStream outStream = response.getOutputStream();
        outStream.write(names.getBytes("UTF-8"));
        outStream.flush();
        outStream.close();
    }

    private void deleteAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        albumService.deleteAlbum(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/albums");
    }

    private void createAlbum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AlbumPages/AlbumForm.jsp");
        dispatcher.forward(request, response);
    }

    private void addAlbum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AlbumPages/AlbumAdd.jsp");
        dispatcher.forward(request, response);
    }

    private void updateAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        albumService.updateAlbum(request.getParameter("id"), request.getParameter("AlbumName"), request.getParameter("AlbumGenre"), request.getParameter("SingerName"));
        response.sendRedirect(request.getContextPath() + "/albums");
    }

    private void checkName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(albumService.isNameExists(request.getParameter("AlbumName"), request.getParameter("albumId")));
            printWriter.close();
        }
    }

    private void insertAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        albumService.insertAlbum(request.getParameter("AlbumName"), request.getParameter("AlbumGenre"), request.getParameter("SingerName"));
        response.sendRedirect(request.getContextPath() + "/albums");
    }
}