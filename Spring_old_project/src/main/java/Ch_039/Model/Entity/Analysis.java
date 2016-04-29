package Ch_039.Model.Entity;

import Ch_039.Adapters.DateTimeForJPAAnalysisAdapter;
import Ch_039.Adapters.DateTimeForJSONAdapter;
import Ch_039.Adapters.DateTimeForXmlAdapter;
import com.google.gson.annotations.JsonAdapter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

@Entity
@XmlRootElement(name="Analysis")
@XmlType(propOrder = {
        "type",
        "date",
        "report"
})

@NamedQueries({
        @NamedQuery(name = "GET_ALL_ANALYZES", query = "SELECT a FROM Analysis a"),
        @NamedQuery(name = "GET_ANALYZES_BY_PATIENT", query ="SELECT a FROM Analysis a WHERE a.patient =:patient"),
        @NamedQuery(name = "GET_ANALYZES_BY_PATIENT_ID", query ="SELECT a FROM Analysis a WHERE a.patient.id =:id"),
        @NamedQuery(name = "DELETE_ALL_ANALYZES_BY_PATIENT_ID", query ="DELETE FROM Analysis a WHERE a.patient =:patient")
})

public class Analysis implements Comparable<Analysis>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(name="id")
    private long id=1;

    @JsonAdapter(DateTimeForJSONAdapter.class)
    @Column
    @Basic(optional = true)
    @Convert(converter = DateTimeForJPAAnalysisAdapter.class)
    @XmlJavaTypeAdapter(DateTimeForXmlAdapter.class)
    @Temporal(TemporalType.DATE)
    @XmlElement(name = "date")
    private DateTime date;

    @Column
    @Basic(optional = false)
    @XmlElement(name="report")
    @Lob
    private String report;

    @Column
    @NotNull
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @XmlElement(name="type")
    private AnalysisType type;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Transient
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");

    //default constructor
    public Analysis(){
    }

    //getters and setters
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = formatter.parseDateTime(date);
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setType(AnalysisType type) {
        this.type = type;
    }

    public DateTime getDate() {
        return date;
    }

    public String getDateInString() {
        return date.toString(formatter);
    }

    public long getId() {
        return id;
    }

    public String getReport() {
        return report;
    }

    public AnalysisType getType() {
        return type;
    }

    //Patient builder
    public static AnalysisBuilder newAnalysisBuilder () {
        return new Analysis().new AnalysisBuilder();
    }

    @Override
    public int compareTo(Analysis o) {
        return this.getDate().compareTo(o.getDate());
    }

    public class AnalysisBuilder {

        private AnalysisBuilder(){}

        public AnalysisBuilder setReport(String report) {
            Analysis.this.report = report;
            return this;
        }

        public AnalysisBuilder setType(AnalysisType type) {
            Analysis.this.type = type;
            return this;
        }

        public AnalysisBuilder setDate(String date) {
            Analysis.this.date = formatter.parseDateTime(date);
            return this;
        }

        public AnalysisBuilder setId(long id) {
            Analysis.this.id = id;
            return this;
        }

        public Analysis build() {
            return Analysis.this;
        }
    }

    @Override
    public String toString() {
        return "Type of analysis - " + type +  ", Date - " + date.toString(formatter) + ", Report - " + report + "." +"\n";
    }

}
