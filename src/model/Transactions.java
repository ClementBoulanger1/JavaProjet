package model;

import java.util.ArrayList;
import java.util.Date;

public class Transactions {
    private String idTransaction;
    private String mailClient;
    private Date date;
    private String adresse;
    private String matriculeemploye;
    private ArrayList<String> Achat = new ArrayList();
    private ArrayList<Integer> NbrAchat = new ArrayList();
    private int nbrtotalachat;

    Transactions(String idTransaction, String matriculeemploye, String mailClient, String adresse, String idproduit_1, int nbr) {
        this.idTransaction = idTransaction;
        this.matriculeemploye = matriculeemploye;
        this.mailClient = mailClient;
        //on met la date uniquement une fois que la transaction est finalisée, si date = NULL alors
        this.adresse = adresse;
        this.Achat.add(idproduit_1);
        this.NbrAchat.add(nbr);
        nbrtotalachat = nbr;
    }

    public int getNbrtotalachat() {
        return nbrtotalachat;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public String getAdresseStore(){
        return adresse;
    }

    public ArrayList<String> getAchat(){
        return Achat;
    }

    public ArrayList<Integer> getNbrAchat(){
        return NbrAchat;
    }

    public void AddAchat(String idProduit, int nbr) {
        if (nbr > 0) {
            this.Achat.add(idProduit);
            this.NbrAchat.add(nbr);
            nbrtotalachat+=nbr;
        }
    }

    public void SetNbrAchat(int index, int nbr) {
        if(index > this.NbrAchat.size()-1 || index<0) {
            System.out.println("Erreur : Index out of range");
        }
        else if (nbr<=0) {
            Achat.remove(index);
            NbrAchat.remove(index);
        }
        else if(nbr>0) {
            int nbrdebut = this.NbrAchat.get(index);
            this.NbrAchat.set(index, nbr);
            nbrtotalachat-=(nbrdebut-nbr);
        }
    }

    public ArrayList<String> GetAchat(){
        return this.Achat;
    }

    public ArrayList<Integer> GetNbrAchat(){
        return this.NbrAchat;
    }

    public void DeleteAchat(int index) {
        index--;
        if(index < 0 || index >= this.NbrAchat.size()) {
            System.out.println("Erreur : index dépasse la limite");
        }
        else {
            Achat.remove(index);
            nbrtotalachat -= NbrAchat.get(index);
            NbrAchat.remove(index);
        }
    }

    public String getMailClient() {
        return mailClient;
    }

    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }

    public void FinirTransaction() {
        this.date = new Date();
        //ajouter un moyen de calculer le prix à l'avenir
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCodeAdresseClient() {
        return this.adresse;
    }

    public void setCodeAdresseClient(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        if (this.date == null)
            return "\nCette transaction pas encore terminé a été lancée par notre employé matricule : " + this.matriculeemploye + ", elle contient " + nbrtotalachat + " produits.\nMail du client : " + this.mailClient + "\nAdresse magasin : " + this.adresse + "\n" + this.Achat + "\n" + this.NbrAchat + "\n";
        else
            return "\nCette transaction daté du " + this.date + " a été lancée par notre employé matricule : " + this.matriculeemploye + ", elle contient " + nbrtotalachat + " produits.\nMail du client : " + this.mailClient + "\nAdresse magasin : " + this.adresse + "\n" + this.Achat + "\n" + this.NbrAchat + "\n";
    }

    public static void main(String[] args) {
        Transactions transaction = new Transactions("abcdef", "fedcba", "luc.at@gmail.com", "rue de l'archipel 48", "tyson", 3);
        System.out.println(transaction.toString());
        transaction.AddAchat("devof", 4);
        transaction.AddAchat("moumoute", 5);
        transaction.AddAchat("besoz", 0);
        System.out.println(transaction.toString());
        System.out.println(transaction.getDate());
        System.out.println(transaction.getMailClient());
        System.out.println(transaction.getCodeAdresseClient());
        System.out.println(transaction.getClass());
        System.out.println(transaction.getNbrAchat());
        System.out.println(transaction.getIdTransaction());
        System.out.println(transaction.getAdresseStore());
        transaction.DeleteAchat(3);
        transaction.FinirTransaction();
        System.out.println(transaction.getDate());
        System.out.println(transaction.toString());
    }
}
