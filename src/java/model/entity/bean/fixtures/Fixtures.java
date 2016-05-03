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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import model.entity.bean.Adresse;
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
    
    private static final List<TypeAnnonce> TYPE_VALUES = Collections.unmodifiableList(Arrays.asList(TypeAnnonce.values()));
            
    private static final List<CateAnnonce> CATE_VALUES =  Collections.unmodifiableList(Arrays.asList(CateAnnonce.values()));

    private static final Random RANDOM = new Random();
    
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

        Utilisateur u = uSvcs.creeUtilisateur("Delas", "Romain", "r.delas01@gmail.com", "password", null, null, c1);
        
        anSvcs.creerAnnonce("Album JJG", "Lorem Ipsum", TypeAnnonce.VENTE, CateAnnonce.MUSIQUE, null, 50.0, u);
                
    }

    public void loadFromCSV(File csvFile, Campus c) {
        Logger.getLogger(Fixtures.class.getName()).log(Level.INFO, csvFile.getName(), csvFile);
        
        try (FileReader fr = new FileReader(csvFile);
                CSVParser csvp = new CSVParser(fr, CSVFormat.EXCEL.withDelimiter(';').withHeader("nom", "prenom", "mail"));) {

            List<CSVRecord> records = csvp.getRecords();
            
            NumberFormat formatter = new DecimalFormat("#0.00");
            Integer max = records.size();
            IntStream.range(0, max).forEach(index -> {
                CSVRecord record = records.get(index);
                String nom = record.get("nom");
                String prenom = record.get("prenom");
                String mail = record.get("mail");
                
                Utilisateur u = null;
                Integer i = 1;
                while((u=uSvcs.getUserByMail(mail)) != null){
                    mail = prenom.concat(nom).concat(i.toString()).concat("@etu.unice.fr");
                    i++;
                }
                
                u = uSvcs.creeUtilisateur(nom, prenom, mail, "password", null, "0667760038", c);          
                
                for(int j=0; i<4; i++){
                    
                    TypeAnnonce type = TYPE_VALUES.get(new Random().nextInt(TYPE_VALUES.size()));
                    anSvcs.creerAnnonce(type+" de trucs", "Lorem Ipsum", type, CATE_VALUES.get(new Random().nextInt(CATE_VALUES.size())), null, RANDOM.nextDouble()*1000, u);
                }
                
                System.out.print(csvFile.getName()+ " : "+formatter.format((index/max.doubleValue())*100.0)+"%");
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fixtures.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Fixtures.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
