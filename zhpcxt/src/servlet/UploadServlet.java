package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Validate;
import bean.InsertUpdateDelBean;
import bean.StuService;
import bean.studentInfoUpload;
import bean.scoreUpload;
import bean.rateUpload;

public class UploadServlet extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public UploadServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     * <p/>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     * <p/>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getSession().getServletContext().getRealPath(request.getRequestURI());

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("fileUpload") != null) {
            String lujing = (File) request.getAttribute("fileUpload") + request.getParameter("fileUpload");
//        	String filepath=new File(lujing).getAbsoluteFile();
            StuService stu = new StuService();
            stu.upload(lujing);
        } else if (request.getParameter("fileUpload1") != null) {
            String lujing = "/Users/Anly.Z/Documents/" + request.getParameter("fileUpload1");
            String class_id = (String) request.getSession().getAttribute("class_id");
            studentInfoUpload stu = new studentInfoUpload();
            stu.upload(lujing, class_id);
            request.setAttribute("message", "'上传成功！'");
            request.getRequestDispatcher("/counsellor/studentInfoUpload.jsp").forward(request, response);
        } else if (request.getParameter("fileUpload2") != null) {
            String lujing = "/Users/Anly.Z/Documents/" + request.getParameter("fileUpload2");
            scoreUpload stu = new scoreUpload();
            String cScoreTableName = (String) request.getSession().getAttribute("class_id");
            String school_year = request.getParameter("schoolYear");
            String term = request.getParameter("term");
            stu.upload(lujing, cScoreTableName, school_year, term);
            request.setAttribute("message", "'成绩上传成功！'");
//            response.sendRedirect(request.getContextPath() + "/counsellor/classScore.jsp");
            //("/counsellor/classScore.jsp").forward(request, response);
            request.getRequestDispatcher("/counsellor/classScore.jsp").forward(request, response);
        } else if (request.getParameter("rating") != null) {
            String lujing = "/Users/Anly.Z/Documents/" + request.getParameter("rating");
            rateUpload rate = new rateUpload();
            String cScoreTableName = (String) request.getSession().getAttribute("class_id");
            String school_year = request.getParameter("schoolYear");
            rate.upload(lujing, cScoreTableName, school_year);
            request.setAttribute("message", "'上传成功！'");
            response.sendRedirect(request.getContextPath() + "/counsellor/ratingScore.jsp");
            //("/counsellor/classScore.jsp").forward(request, response);


        }


    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}