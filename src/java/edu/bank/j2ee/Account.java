/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author 58lalo53
 */
@Entity
@Table(name = "ACCOUNT", catalog = "", schema = "BANKING")
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccNum", query = "SELECT a FROM Account a WHERE a.accNum = :accNum"),
    @NamedQuery(name = "Account.findByType", query = "SELECT a FROM Account a WHERE a.type = :type"),
    @NamedQuery(name = "Account.findByDescription", query = "SELECT a FROM Account a WHERE a.description = :description"),
    @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance"),
    @NamedQuery(name = "Account.findByBeginBal", query = "SELECT a FROM Account a WHERE a.beginBal = :beginBal"),
    @NamedQuery(name = "Account.findByCreditLim", query = "SELECT a FROM Account a WHERE a.creditLim = :creditLim")})
public class Account implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accId", fetch = FetchType.EAGER)
    private List<Transactions> transactionsList;
    @Column(name = "TIME_STAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp = CurDate.now();
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACC_NUM")
    private Integer accNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BALANCE")
    private BigDecimal balance = BigDecimal.ZERO;
    @Column(name = "BEGIN_BAL")
    private BigDecimal beginBal;
    @Column(name = "CREDIT_LIM")
    private BigDecimal creditLim;
    @JoinColumn(name = "CUST_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer custId;
    @OneToMany(mappedBy = "accNum", fetch = FetchType.EAGER)
    private List<Customer> customerList;

    public Account() {
    }

    public Account(Integer accNum) {
        this.accNum = accNum;
    }

    public Account(Integer accNum, String type) {
        this.accNum = accNum;
        this.type = type;
    }
    
    public Account(String type, String description){
        this.type = type;
        this.description = description;
    }
    
    public Account(String type){
        this.type = type;
    }

    public Integer getAccNum() {
        return accNum;
    }
    

    public void setAccNum(Integer accNum) {
        this.accNum = accNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBeginBal() {
        return beginBal;
    }

    public void setBeginBal(BigDecimal beginBal) {
        this.beginBal = beginBal;
    }

    public BigDecimal getCreditLim() {
        return creditLim;
    }

    public void setCreditLim(BigDecimal creditLim) {
        this.creditLim = creditLim;
    }

    public Customer getCustId() {
        return custId;
    }

    public void setCustId(Customer custId) {
        this.custId = custId;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accNum != null ? accNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accNum == null && other.accNum != null) || (this.accNum != null && !this.accNum.equals(other.accNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bank.j2ee.Account[ accNum=" + accNum + " ]";
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }
    
}
