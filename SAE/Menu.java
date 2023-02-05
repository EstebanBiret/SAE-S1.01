
/**
 * Exemple de menu
 *
 * 
 */

import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.text.*;

public class Menu
{

    //texte du menu
    public static String [][] promo = null;
    public static int nbItemMenu = 2;
    public static String texteMenu = "\n/**********************************************/\n"+"\t\t\tVeuillez choisir une option :\n\t\t1 - Tirer au sort un étudiant aléatoirement\n"
        +"\t\t2 - Afficher la promo\n"+ "\t\t0 - Quitter\n"+"/**********************************************/\n\n"
    ; /* structure du menu*/

    /**  permet de retourner une valeur entiere saisie au clavier comprise entre pfBorneInf et pfBorneSup
     *@param pfBorneSup In la borne sup
     *@param pfBorneInf In la borne inf
     *@param pfMessage In message à afficher
     *@return valeur entiere comprise entre pfBorneInf et pfBorneSup
     **/
    public static int saisieIntC (int pfBorneInf,  int pfBorneSup, String pfMessage){
        int valeur;
        Scanner clavier = new Scanner(System.in) ;
        System.out.print(pfMessage); 

        valeur=clavier.nextInt();
        while (valeur<pfBorneInf || valeur>pfBorneSup){
            System.out.println(pfMessage);
            System.out.print("Erreur ! Donnez une valeur comprise " + pfBorneInf +" et "+pfBorneSup+ "."); /* message d'erreur si l'on saisit un nombre qui n'est pas dans l'intervalle.*/
            valeur=clavier.nextInt();
        }
        return valeur;
    }

    public static void afficherPromo () {
        for (int i=0;i<ListeEtudiants.nbEtudiant(promo);i++){

            System.out.println("Etudiant n°" + (i+1) + ":" + " "+promo [i][0]+" " + promo [i][1]);
        }

    }

    public static void traiterChoix1(){
        /*initialisation des variables*/
        Scanner clavier = new Scanner (System.in);
        int etu[] = new int[promo.length]; /* tableau permettant de voir si un étudiant est déjà passé ou non (priorité en fonction du nombre de passage de chaque étudiant*/
        int e = -1; /* variable temporaire qui stocke l'étudiant passé, afin d'éviter qu'un même étudiant passe 2x de suite*/
        int choixUtilisateur ;
        int nbPassage = 0; /*nombre de fois qu'un étudiant a été tiré au sort*/
        int randomNumetu = (int) (Math.random()*promo.length); /*permet de tirer un nombre aléatoire entre 0 et la taille du tableau contenant tout les noms de la promo*/
        boolean choix = true; /* booléen qui permet de décider si l'on continue à tirer des étudiants ou si l'on veut revenir au menu*/

        /*début du code*/

        System.out.println ("~ " + promo [randomNumetu][0]+ " " + promo [randomNumetu][1] + " ~" +"\n");
        etu[randomNumetu]=1;
        e=randomNumetu;
        nbPassage = nbPassage + 1;
        System.out.println ("Voulez-vous continuer ? (1 pour oui et 0 pour quitter)\n");
        choixUtilisateur = clavier.nextInt();
        while (choixUtilisateur!=1 && choixUtilisateur!=0){ /*boucle demandant à l'utilisateur de saisir une valeur tant que celle ci est différente de 1 ou de 0.*/
            System.out.print("Erreur ! Veuillez saisir 1 ou 0.");
            choixUtilisateur = clavier.nextInt();

        }
        if(choixUtilisateur==1){
            choix=true;

        }
        else {
            choix=false;

        }

        while (choix==true){ /* boucle permettant de continuer à tirer des étudiants tant que l'utilisateur en a envie*/
            randomNumetu = (int) (Math.random()*promo.length); 
            if (nbPassage==promo.length){ /* boucle vérifiant si tout les étudiants sont déjà passés*/
                System.out.println ("Tous les étudiants sont passés, voulez-vous continuer ? (1 pour oui et 0 pour quitter)" + "\n");
                choixUtilisateur = clavier.nextInt();
                while (choixUtilisateur!=1 && choixUtilisateur!=0){ /*boucle demandant à l'utilisateur de saisir une valeur tant que celle ci est différente de 1 ou de 0.*/
                    System.out.print("Erreur ! Veuillez saisir 1 ou 0.");
                    choixUtilisateur = clavier.nextInt();
                }
                if (choixUtilisateur==1){ /* si l'utilisateur décide de continuer, on remet toutes les valeurs du tableau etu à 0, afin que les étudiants puissent à nouveau être choisis*/
                    for (int a = 0; a < promo.length; a++){ /* on parcourt toutes les cases du tableau en les remettants à 0*/
                        etu[a] = 0;
                    }
                    nbPassage = 0; /* naturellement, on remet la variable comptant le nombre de passages à 0 (pour permettre de continuer)*/
                }else 
                    break;
            }

            while (randomNumetu==e || etu[randomNumetu]==1 ){ /* boucle permettant d'éviter qu'un même étudiant soit tiré au sort 2x de suite,ou qu'un étudiant ai déjà été tiré au sort, on retire donc un nombre aléatoire jusqu'à qu'il ne soit pas le même que celui de l'étudiant qui est passé en dernier, ou que son indice dans le tableau etu ne soit pas égal à 1 */
                randomNumetu = (int) (Math.random()*promo.length);
            }

            nbPassage = nbPassage + 1; /* on actualise le nombre de passage*/
            etu[randomNumetu]=1; /* on attribue la valeur 1 à la case du tableau etu correspondant à l'indice du nombre aléatoire.*/
            e=randomNumetu; /* on stocke le nombre aléatoire dans la variable permettant de vérifier si l'étudiant qui va être affiché n'a pas déjà été choisi au tirage précédent.*/
            System.out.println ( "~ " + promo [randomNumetu][0]+ " " + promo [randomNumetu][1] +" ~" + "\n");

            System.out.println ("Voulez-vous continuer ? (1 pour oui et 0 pour quitter)" + "\n");
            choixUtilisateur = clavier.nextInt();
            while (choixUtilisateur!=1 && choixUtilisateur!=0){ /*boucle demandant à l'utilisateur de saisir une valeur tant que celle ci est différente de 1 ou de 0.*/
                System.out.print("Erreur ! Veuillez saisir 1 ou 0.");
                choixUtilisateur = clavier.nextInt();}
            if(choixUtilisateur==1){
                choix=true;
            }
            else{
                choix=false;

            }

        }
    }

    /**  affiche le menu et exécute les choix...**/

    public static void testMenu(){

        int choixUtilisateur ;
        String etu[];
        boolean choix=true;
        do
        {
            System.out.println(texteMenu);
            choixUtilisateur = saisieIntC ( 0, nbItemMenu, "Choisir une option");

            try {
                switch (choixUtilisateur)
                {

                    case 2 :

                    /* permet d'afficher la promo */
                    afficherPromo ();

                    break ;

                    case 1 :

                    /* permet de tirer au sort un étudiant en respectant les différentes contraintes imposées*/

                    traiterChoix1() ;

                    break ; 

                    case 0 :

                    choix=false;

                    /*permet de revenir au menu, ou de quitter le programme si l'on se trouve déjà dans le menu*/

                    System.out.println ("AU REVOIR ...   ...\n");

                    break ;
                    default :
                    System.out.println("\n\n\nBIZARRE ... \n\n\n");
                    break;
                } 
            }catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
        while (choixUtilisateur != 0);

    }

    public static void main(String arguments[]) {
        try {
            promo = ListeEtudiants.getListe("listenomssansaccent.csv", ","); //appel du sous programme précédé du nom de la classe où elle est définie
            System.out.println("Il y a : " + ListeEtudiants.nbEtudiant(promo) + " etudiants"); 
            testMenu(); /* on appelle ici le sous programme test menu, codé au dessus*/

        }
        catch (Exception e) {  
            System.out.println("Erreur : "+e.getMessage());

        } 

    } // fin main
}

