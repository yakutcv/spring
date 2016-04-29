package Ch_039.DAO;

import   Ch_039.Model.Entity.Patient;

import javax.persistence.*;
import java.util.*;

public class PatientDAO {
    private EntityManager manager = Persistence.createEntityManagerFactory("HOSPITAL").createEntityManager();
    private String TABLE_NAME = "Patient";

    public synchronized boolean addPatient(Patient patient) throws PersistenceException{
        try {
            manager.getTransaction().begin();
            manager.persist(patient);
            manager.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Couldn't add patient!");
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return false;
    }

    public boolean deletePatientById(long id) throws PersistenceException{
        try{
            manager.getTransaction().begin();
            manager.remove(manager.getReference(Patient.class, id));
            manager.getTransaction().commit();
            return true;
        }catch (PersistenceException e) {
            System.out.println("Couldn't delete patient with id: " + id);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return false;
    }

    public boolean updatePatient (Patient patient) throws PersistenceException{
        try{
            manager.getTransaction().begin();
            manager.merge(patient);
            manager.getTransaction().commit();
            return true;
        }catch (PersistenceException e) {
            System.out.println("Couldn't update patient " + patient);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return false;
    }

    public Patient getPatientById(long id) throws PersistenceException{
        Patient patient = null;
        try{
            manager.getTransaction().begin();
            patient = manager.getReference(Patient.class, id);
            manager.getTransaction().commit();
        }catch (PersistenceException e) {
            System.out.println("Couldn't find patient with id: " + id);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return patient;
    }

    public List<Patient> getAllPatients() throws PersistenceException{
        List<Patient> patients = new ArrayList<>();
        try{
            manager.getTransaction().begin();
            Query query = manager.createNamedQuery("GET_ALL_PATIENTS", Patient.class);
            patients = query.getResultList();
            manager.getTransaction().commit();
        }catch (PersistenceException e) {
            System.out.println("Couldn't read patients from table " + TABLE_NAME);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        Collections.sort(patients);
        return patients;
    }

    public boolean changeStatusPatientToFalse(Patient patient)throws PersistenceException{
        try{
            manager.getTransaction().begin();
            patient.setStatus(false);
            manager.merge(patient);
            manager.getTransaction().commit();
            return true;
        }catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("Couldn't update patient status " + patient);
            manager.getTransaction().rollback();
        }
        return false;
    }

    public Patient getPatientByIdWithAllAnalyzes(long id)throws PersistenceException {
        Patient patient = null;
        try{
            manager.getTransaction().begin();
            EntityGraph query = manager.createEntityGraph("graph.Patient.listAnalyzes");
            Map map = new HashMap<>();
            map.put("javax.persistence.fetchgraph", query);
            patient = manager.find(Patient.class, id, map);
            manager.getTransaction().commit();
        }catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("Couldn't find patient with id " + id);
            manager.getTransaction().rollback();
        }
        return patient;
    }

    //admin methods
    public List<Patient> getAllPatientsA() throws PersistenceException{
        List<Patient> patients = new ArrayList<>();
        try{
            manager.getTransaction().begin();
            Query query = manager.createNamedQuery("GET_ALL_PATIENTS_ADMIN", Patient.class);
            patients = query.getResultList();
            manager.getTransaction().commit();
        }catch (PersistenceException e) {
            System.out.println("Couldn't read patients from table " + TABLE_NAME);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        Collections.sort(patients);
        return patients;
    }

    public List<Patient> getAllPatientsWithStatusFalse() throws PersistenceException{
        List<Patient> patients = new ArrayList<>();
        try{
            manager.getTransaction().begin();
            Query query = manager.createNamedQuery("GET_ALL_PATIENTS_WITH_STATUS_FALSE", Patient.class);
            patients = query.getResultList();
            manager.getTransaction().commit();
        }catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("Couldn't read patients from table " + TABLE_NAME);
            manager.getTransaction().rollback();
        }
        Collections.sort(patients);
        return patients;
    }
}
