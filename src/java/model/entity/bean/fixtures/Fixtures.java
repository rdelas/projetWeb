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
        Adresse ad2 = adSvcs.createAdresse("1645 Route des Lucioles", null, "06410", "Biot", 43.616679, 7.063735);
        
        Campus c1 = cSvcs.createCampus("Campus des Sophia Tech", ad1);
        Campus c2 = cSvcs.createCampus("Campus des Lucioles", ad2);
        
        
        //Chargement des L3 Miage
        File l3Csv =  new File(Fixtures.class.getResource("/SLZI5IG.csv").getFile());        
        //Chargement des M1 Miage
        File m1Csv = new File(Fixtures.class.getResource("/SM1MIA-120.csv").getFile());
        loadFromCSV(l3Csv, c2);
        loadFromCSV(m1Csv, c2);
        
        
        Utilisateur u1 = uSvcs.creeUtilisateur("Delas", "Romain", "r.delas01@gmail.com", "testPWD1", null, "0667760038",  c2);
        
        Annonce an1 = anSvcs.creerAnnonce("Vends des trucs", "Vends des trucs en test", TypeAnnonce.VENTE, CateAnnonce.VETEMENT, null, 75.5, u1);
        Annonce an2 = anSvcs.creerAnnonce("Vend d'autres trucs", "Vends des trucs en test qui font de la musique", TypeAnnonce.VENTE, CateAnnonce.MUSIQUE, null, 2500.47, u1);
        Annonce an3 = anSvcs.creerAnnonce("Achete des trucs", "Achete des trucs en test", TypeAnnonce.ACHAT,CateAnnonce.MEUBLE, null, 75.5, u1);
        Annonce an4 = anSvcs.creerAnnonce("Achete d'autres trucs", "Achete des trucs en test qui sont multimédia", TypeAnnonce.ACHAT,CateAnnonce.MULTIMEDIA, null, 2500.47, u1);

    }
    
    public void loadFromCSV(File csvFile, Campus c){
                try(FileReader fr = new FileReader(csvFile);
            CSVParser csvp = new CSVParser(fr, CSVFormat.EXCEL.withDelimiter(';').withHeader("nom", "prenom", "mail"));) {
            
            List<CSVRecord> records = csvp.getRecords();
            records.stream().forEach((CSVRecord record) -> {
                String nom = record.get("nom");
                String prenom = record.get("prenom");
                String mail = record.get("mail");
                
                uSvcs.creeUtilisateur(nom, prenom, mail, "password", null, null,  c);
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fixtures.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Fixtures.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
