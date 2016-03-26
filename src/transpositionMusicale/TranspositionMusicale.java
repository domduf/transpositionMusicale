package transpositionMusicale;

import java.io.IOException;


/**
 * 
 * 
 * 
 * @version 13 janvier 2016
 * @author Dominique Dufour NFA 031 année 2014-2015
 *  
 */
public class TranspositionMusicale {
	
	/**
	 * Ce projet doit permettre de transposer une armure musicale vers une autre. 
	 * On utilisera au debut une description des clés sous la forme U1 pour Ut 1 etG2 pour Sol 2ème ligne.
	 * 
	 * Main
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// déclaration des variables
		int codeCleDepart = 0; 		// code de la clé du morceau de départ
		int codeCleTranspo=0;		// code de la clé du morceau pour lire à vue
		int codeTonDepart = 0;		// code de la tonalité du morceau de départ
		int codeTonInstruDepart=0; 	// l'instrument qui joue le morceau de départ
									// est dans cette tonalité
		int codeTonInstruArrive=0; 	// l'instrument qui devra jouer le morceau
									// transposé est dans cette tonalité
		int codeTonFinal=0;			//Ton transposé.
		boolean fin = false;

		// Bonjour !!!
		Methodes.bonjour();

		// Boucle tant que le choix de l'option de menu n'est pas '5'
		while (!fin) {
			
			//Calcul du code du Ton Final et de la clé de transposition à vue.
			codeTonFinal= Methodes.calculTonFinal(codeTonDepart, codeTonInstruDepart, codeTonInstruArrive );
			codeCleTranspo = Methodes.transpoDeCle(codeCleDepart,codeTonInstruDepart, codeTonInstruArrive);
			
			// menu et recapitulation des variables:
			Methodes.menuRecapitulationVariables(codeCleDepart, codeTonDepart,
					codeTonInstruDepart, codeTonInstruArrive, codeCleTranspo,
					codeTonFinal);
			
			// entrée de l'option du menu
			int choix = Methodes.entreeMenu();
			
			
			if (choix==0){ // RAZ des variables
				codeCleDepart = 0;codeCleTranspo=0;codeTonDepart = 0;codeTonInstruDepart=0;codeTonInstruArrive=0;
			}
			else if (choix==1){
				codeCleDepart = Methodes.entreeCle();
			}
			else if (choix==2){
				Terminal.ecrireStringln("Entrez l'armure du morceau de départ.");
				//codeTonDepart = Methodes.entreeTon();
				codeTonDepart=Methodes.entreeTonEnum().codage(); // va lire directement le code dans la 
					//	caracteristique du Tons
			}
			else if (choix==3){
				Terminal.ecrireStringln("Entrez l'armure du ton de l'instrument d'origine.");
				//codeTonInstruDepart = Methodes.entreeTon();
				codeTonInstruDepart=Methodes.entreeTonEnum().codage();// va lire directement le code dans la 
				 //	caracteristique du Tons
			}
			else if (choix==4){	
				Terminal.ecrireStringln("Entrez l'armure du ton de l'instrument final.");
				//codeTonInstruArrive = Methodes.entreeTon();
				codeTonInstruArrive=Methodes.entreeTonEnum().codage();// va lire directement le code dans la 
				 //	caracteristique du Tons
			}
			else if (choix==5){ // sortie si choix 5
				fin=true;
			}
		}	
	}
}
