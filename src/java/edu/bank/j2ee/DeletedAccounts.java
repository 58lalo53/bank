/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lpz_l_000
 */
@Entity
@Table(name = "DELETED_ACCOUNTS", catalog = "", schema = "BANKING")
@NamedQueries({
    @NamedQuery(name = "DeletedAccounts.findAll", query = "SELECT d FROM DeletedAccounts d"),
    @NamedQuery(name = "DeletedAccounts.findByType", query = "SELECT d FROM DeletedAccounts d WHERE d.type = :type"),
    @NamedQuery(name = "DeletedAccounts.findByStatus", query = "SELECT d FROM DeletedAccounts d WHERE d.status = :status"),
    @NamedQuery(name = "DeletedAccounts.findByDescription", query = "SELECT d FROM DeletedAccounts d WHERE d.description = :description"),
    @NamedQuery(name = "DeletedAccounts.findByOpenedDate", query = "SELECT d FROM DeletedAccounts d WHERE d.openedDate = :openedDate"),
    @NamedQuery(name = "DeletedAccounts.findByClosedDate", query = "SELECT d FROM DeletedAccounts d WHERE d.closedDate = :closedDate"),
    @NamedQuery(name = "DeletedAccounts.findByAccNum", query = "SELECT d FROM DeletedAccounts d WHERE d.accNum = :accNum"),
    @NamedQuery(name = "DeletedAccounts.findById", query = "SELECT d FROM DeletedAccounts d WHERE d.id = :id")})
public class DeletedAccounts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STATUS")
    private String status = "CLOSED";
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "OPENED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date openedDate;
    @Column(name = "CLOSED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedDate = CurDate.now();
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACC_NUM")
    private int accNum;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "CUST_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Customer custId;

    public DeletedAccounts() {
    }

    public DeletedAccounts(Integer id) {
        this.id = id;
    }

    public DeletedAccounts( String type, String description, Customer custId, int accNum, Date openedDate) {
        this.description = description;
        this.type = type;
        this.custId = custId;
        this.accNum = accNum;
        this.openedDate = openedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(Date openedDate) {
        this.openedDate = openedDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public int getAccNum() {
        return accNum;
    }

    public void setAccNum(int accNum) {
        this.accNum = accNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustId() {
        return custId;
    }

    public void setCustId(Customer custId) {
        this.custId = custId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeletedAccounts)) {
            return false;
        }
        DeletedAccounts other = (DeletedAccounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bank.j2ee.DeletedAccounts[ id=" + id + " ]";
    }
    
}
