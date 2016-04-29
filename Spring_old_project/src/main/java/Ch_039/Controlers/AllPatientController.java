package Ch_039.Controlers;

import   Ch_039.Model.Entity.Patient;
import   Ch_039.Service.PatientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/AllPatientController")
public class AllPatientController extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String laboratory = request.getParameter("laboratory");

        //get all patients
        List<Patient> patients = new PatientService().getAllPatients();

        //define previous page
        String direction;
        if(laboratory==null){
            direction ="AllPatients.jsp";
        }else{
            direction = "Laboratory.jsp";
        }

        request.setAttribute("patients", patients);
        RequestDispatcher dispatcher = request.getRequestDispatcher(direction);
        dispatcher.forward(request, response);
    }
}
