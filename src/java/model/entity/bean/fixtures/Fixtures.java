/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.bean.fixtures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import model.entity.bean.Adresse;
import model.entity.bean.Annonce;
import model.entity.bean.Campus;
import model.entity.bean.CateAnnonce;
import model.entity.bean.TypeAnnonce;
import model.entity.bean.Utilisateur;
import model.entity.services.AdresseServices;
import model.entity.services.AnnonceServices;
import model.entity.services.CampusServices;
import model.entity.services.UtilisateurServices;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Delas
 */
@Singleton
@Startup
public class Fixtures {

    @EJB
    private AdresseServices adSvcs;

    @EJB
    private AnnonceServices anSvcs;

    @EJB
    private CampusServices cSvcs;

    @EJB
    private UtilisateurServices uSvcs;

    @PostConstruct
    public void initDataBase() {

        Adresse ad1 = adSvcs.createAdresse("930 Route des Colles", null, "06410", "Biot", 43.616069, 7.072132);
        Adresse ad2 = adSvcs.createAdresse("Avenue du Doyen Louis Trotabas", null, "06050", "Nice", 43.695760, 7.245440);
        Adresse ad3 = adSvcs.createAdresse("24 Avenue des Diables Bleus", null, "06357", "Nice", 43.709278, 7.288667);
        Adresse ad4 = adSvcs.createAdresse("98 Boulevard Edouard Herriot", null, "06200", "Nice", 43.692331, 7.237365);
        Adresse ad5 = adSvcs.createAdresse("28 avenue Valrose", null, "06108", "Nice", 43.715780, 7.267425);

        Campus c1 = cSvcs.createCampus("Campus Sophia Tech", ad1);
        Campus c2 = cSvcs.createCampus("Campus Trotabas", ad2);
        Campus c3 = cSvcs.createCampus("Pôle Universitaire Saint-Jean d'Angély", ad3);
        Campus c4 = cSvcs.createCampus("Campus Carlone", ad4);
        Campus c5 = cSvcs.createCampus("Campus Valrose", ad5);

        //Chargement des étudiants
//        File f1 = new File(Fixtures.class.getResource("/csv/CFVU_Coll.USAGERS_Trotabas.csv").getFile());
//        loadFromCSV(f1, c2);
//
//        File f2 = new File(Fixtures.class.getResource("/csv/CFVU_College_Usagers_DEG_SophiaTech_initiale.csv").getFile());
//        loadFromCSV(f2, c1);
//
//        File f3 = new File(Fixtures.class.getResource("/csv/CFVU_College_Usagers_Sante_SophiaTech_initiale.csv").getFile());
//        loadFromCSV(f3, c1);

        File f4 = new File(Fixtures.class.getResource("/csv/CFVU_College_Usagers_SetT_SophiaTech_initiale.csv").getFile());
        loadFromCSV(f4, c1);
        
//        File f5 = new File(Fixtures.class.getResource("/csv/CFVU_College+Etudiants_+Sante_SJA_Initiale.csv").getFile());
//        loadFromCSV(f5, c3);
//        
//        File f6 = new File(Fixtures.class.getResource("/csv/CFVU_College+USAGERS_DEG_IDPD_initiale.csv").getFile());
//        loadFromCSV(f6, c2);
//
//        File f7 = new File(Fixtures.class.getResource("/csv/CFVU_College+etudiants_SHS_Carlone_initiale.csv").getFile());
//        loadFromCSV(f7, c4);
//
//        File f8 = new File(Fixtures.class.getResource("/csv/CFVU_USAGERS_Sciences_Technologies_CARLONE.csv").getFile());
//        loadFromCSV(f8, c4);
//
//        File f9 = new File(Fixtures.class.getResource("/csv/CFVU_Usagers_Sante_Valrose_initialex.csv").getFile());
//        loadFromCSV(f9, c5);
//        
//        File f10 = new File(Fixtures.class.getResource("/csv/CFVU_Usagers_ScTechno_Valrose_initiale-Copiex.csv").getFile());
//        loadFromCSV(f10, c5);

//        Utilisateur u1 = uSvcs.creeUtilisateur("Delas", "Romain", "r.delas01@gmail.com", "testPWD1", null, "0667760038", c1);

//        Annonce an1 = anSvcs.creerAnnonce("Vends des trucs", "Vends des trucs en test", TypeAnnonce.VENTE, CateAnnonce.VETEMENT, null, 75.5, u1);
//        Annonce an2 = anSvcs.creerAnnonce("Vend d'autres trucs", "Vends des trucs en test qui font de la musique", TypeAnnonce.VENTE, CateAnnonce.MUSIQUE, null, 2500.47, u1);
//        Annonce an3 = anSvcs.creerAnnonce("Achete des trucs", "Achete des trucs en test", TypeAnnonce.ACHAT,CateAnnonce.MEUBLE, null, 75.5, u1);
//        Annonce an4 = anSvcs.creerAnnonce("Achete d'autres trucs", "Achete des trucs en test qui sont multimédia", TypeAnnonce.ACHAT,CateAnnonce.MULTIMEDIA, null, 2500.47, u1);

    }

    public void loadFromCSV(File csvFile, Campus c) {
        Logger.getLogger(Fixtures.class.getName()).log(Level.INFO, csvFile.getName(), csvFile);
        
        try (FileReader fr = new FileReader(csvFile);
                CSVParser csvp = new CSVParser(fr, CSVFormat.EXCEL.withDelimiter(';').withHeader("nom", "prenom", "mail"));) {

            List<CSVRecord> records = csvp.getRecords();
            records.stream().forEach((CSVRecord record) -> {
                String nom = record.get("nom");
                String prenom = record.get("prenom");
                String mail = record.get("mail");
                
                Utilisateur u = null;
                Integer i = 1;
                while((u=uSvcs.getUserByMail(mail)) != null){
                    mail = prenom.concat(nom).concat(i.toString()).concat("@etu.unice.fr");
                    i++;
                }
                
                uSvcs.creeUtilisateur(nom, prenom, mail, "password", null, null, c);
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fixtures.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Fixtures.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
