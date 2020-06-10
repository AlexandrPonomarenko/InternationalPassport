package com.InternationalPassport.businessLayer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "photo", schema = "dev_schema", uniqueConstraints = @UniqueConstraint(columnNames = "photoId"))
public class Photo implements Serializable {
    @Id
    @SequenceGenerator(name = "ph_idph_seq", schema = "test_schema", sequenceName = "ph_idph_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ph_idph_seq")
    @Column(name = "photoId")
    private Integer id;

    @Column(name = "statusLoad")
    private boolean statusLoad = false;

    @Column(name = "pathFile")
    private String pathFile;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "rightPath")
    private String rightPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    private Customer customer;

    public Photo() {
    }

    public Photo(boolean statusLoad, String pathFile) {
        this.statusLoad = statusLoad;
        this.pathFile = pathFile;
    }

    public Photo(boolean statusLoad, String pathFile, Customer customer) {
        this.statusLoad = statusLoad;
        this.pathFile = pathFile;
        this.customer = customer;
    }

    public String getFileName() {
        return fileName;
    }



    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRightPath() {
        return rightPath;
    }

    public void setRightPath(String rightPath) {
        this.rightPath = rightPath;
    }

    public boolean isStatusLoad() {
        return statusLoad;
    }

    public void setStatusLoad(boolean statusLoad) {
        this.statusLoad = statusLoad;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) &&
                Objects.equals(pathFile, photo.pathFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pathFile);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", statusLoad=" + statusLoad +
                ", pathFile='" + pathFile + '\'' +
                '}';
    }
}
