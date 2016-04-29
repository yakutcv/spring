package Ch_039.Model.Logic;

import   Ch_039.Model.Entity.Analysis;
import   Ch_039.Model.Entity.AnalysisType;
import   Ch_039.Model.Entity.Patient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@XmlRootElement(name="Hospital")

public class Hospital implements Serializable{

    private Set<Patient> patients = new HashSet<Patient>();

    @XmlElementWrapper(name="Patients")
    @XmlElement(name="Patient")
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    //add new patient
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    //get by age
    public List<Patient> getByAge(int age) {
        List<Patient> pat = new ArrayList<Patient>(patients);
        Iterator<Patient> i = pat.iterator();
        while (i.hasNext()) {
            if (!(i.next().getAge() == (age))) i.remove();
        }
        return pat;
    }

    //get by name
    public List<Patient> getByName(String name) {
        List<Patient> pat = new ArrayList<Patient>(patients);
        Iterator<Patient> i = pat.iterator();
        while (i.hasNext()) {
            if (!(i.next().getName().equals(name))) i.remove();
        }
        return pat;
    }

    //sort by age
    public List<Patient> sortByAge() {
        List<Patient> pat = new ArrayList<Patient>(patients);
        Collections.sort(pat, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        return pat;
    }

    //sort by lasName
    public List<Patient> sortByLastName() {
        List<Patient> pat = new ArrayList<Patient>(patients);
        Collections.sort(pat, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return pat;
    }
    //get by AnalysisType stage 3
    public List<Patient> getByAnalisisType(AnalysisType type) {
        List<Patient> pat = new ArrayList<>();
        for (Patient p : patients) {
            List<Analysis> a = p.getListAnalyzes();
            for (Analysis b : a) {
                if (b.getType().equals(type)) {
                    pat.add(p);
                }
            }
        }
        return pat;
    }
    //not passed Analyzes
    public void checkForPassedAnalysis(AnalysisType type) {
        boolean flag = true;
        String s="";
        m:{
            for (Patient p : patients) {
                List<Analysis> a = p.getListAnalyzes();
                for (Analysis b : a) {
                    if(b.getType().equals(type)) {
                        s="Analysis " + type + "  passed.";
                        break m;
                    }
                }
            }
            s="Analysis " + type + " don't passed any patient";
        }
        System.out.println(s);
    }

    public void checkUnpassedAnalysis(){
        String s="";
        for (Patient p : patients) {
            List<Analysis> a = p.getListAnalyzes();
            for (Analysis b : a) {
                for(AnalysisType aType : AnalysisType.values()){
                    if(b.getType().equals(aType))break;
                    s = "Analysis " + b.getType() + " Never passed";
                    break;
                }
            }
        }
        System.out.println(s);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Patient value: patients) {
            builder.append(value);
        }
        String patient = builder.toString();

        return "patients" + "\n" +
                patient;
    }
}
