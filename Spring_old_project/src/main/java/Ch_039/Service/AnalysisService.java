package Ch_039.Service;

import   Ch_039.DAO.AnalysisDAO;
import   Ch_039.DAO.PatientDAO;
import   Ch_039.Model.Entity.Analysis;
import   Ch_039.Model.Entity.Patient;
import org.apache.log4j.Logger;

import javax.persistence.PersistenceException;
import java.util.List;

public class AnalysisService {

    //logger
    private static final Logger log = Logger.getLogger(AnalysisService.class);


    PatientDAO patientDAO = new PatientDAO();
    AnalysisDAO analysisDAO = new AnalysisDAO();

    //add analysis by patient
    public void addAnalysisByPatient(Analysis analysis, Patient patient){
        try {
            analysisDAO.addAnalysis(analysis, patient);
            log.info(analysisDAO);
            log.info("Analyzes was added!");
        } catch (PersistenceException e) {
            e.printStackTrace();
            log.info("Analyzes was not added!");
        }
    }

    //get all analyzes by patient id
    public List<Analysis> getAllAnalyzesByPatientId(long id){
        List<Analysis> analyzes;
        try{
            return analyzes = analysisDAO.getAllAnalyzesByPatientId(id);
        }catch (PersistenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    //delete analysis by id
    public void deleteAnalysis(long id){
        try{
            analysisDAO.deleteAnalysisById(id);
        } catch (PersistenceException e){
            e.printStackTrace();
        }
    }

}
