package transpositionMusicale;


/**
 * Cette classe regroupe les méthodes utilisées.
 * @author domduf
 *
 */
public class Methodes {
	
	/**
	 * Juste pour présenter le programme à l'utilisateur
	 */
	public static void bonjour(){
		Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.ecrireStringln("||  Bonjour, ce programme doit vous permettre de "
				+ "transposer une partition.  ||");
		Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.sautDeLigne();
	}
	
	
	/**
	 * affichage du Menu et simultanément de la Récapitulation des diverses variables.
	 * 
	 * @param codeCleDepart
	 * @param codeTonDepart
	 * @param codeTonInstruDepart
	 * @param codeTonInstruArrive
	 * @param codeTonFinal
	 */
	public static void menuRecapitulationVariables(int codeCleDepart, int codeTonDepart, int codeTonInstruDepart,
			                                       int codeTonInstruArrive, int codeCleTranspo, int codeTonFinal) {
		Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.ecrireStringln("||                               Menu / Récapitulation:                     ||");
		Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.ecrireStringln("||- 0 -|   Remise à zéro des variables                                      ||");
		Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.ecrireStringln("||- 1 -|   Partition de Départ:       Cle       --> "/*+codeCleDepart*/+ "Clé de "+conversionCodeCleVersCle(codeCleDepart));
		Terminal.ecrireStringln("||- 2 -|                              Armure    --> "/*+codeTonDepart*/+tonEnClair(codeTonDepart));
		//Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.ecrireStringln("||- 3 -|   Instrument de Depart:      Tonalité  --> " +tonEnLettres(codeTonInstruDepart));
		//Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.ecrireStringln("||- 4 -|   Instrument d' Arrivée:     Tonalité  --> " + tonEnLettres(codeTonInstruArrive));
		Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.ecrireStringln("||- 5 -|   Sortir du programme");
		Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.ecrireStringln("||              Transposition à vue avec clé de --> "+ conversionCodeCleVersCle(codeCleTranspo));
		Terminal.ecrireStringln("||              Armure de la Partition Finale   --> "+tonEnClair(codeTonFinal));
		Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.ecrireString("                                Faites un choix (0-5)");
		//Terminal.sautDeLigne();
	
	}

	/**
	 * Permet d'entrer et de filtrer une option de menu.
	 * @return int choix entre 0 et 5
	 */
	public static int entreeMenu(){
		
		while (true){
		
		Terminal.ecrireString(" SVP:");
		char n= Terminal.lireString().charAt(0);// on ne tient compte que du 1er caractere de la chaine entrée
		if (n<48||n>53){ //filtre entre 0 et 5 
			Terminal.ecrireString("Merci d'entrer une valeur correcte..");
		} else return ((int)n - 48); // retourne la valeur correspondante au caractère (ascii)
		}
	}
	
	/**
	 * Cette méthode devra réinitialiser les variables
	 */
	public static void razVariables (){
		//TODO
		// comment faire un RAZ dans une methode si les variables n'ont pas de portée à l'exterieur ?
	}
	
	/**
	 * Methode d'entrée d'une clé.
	 * 
	 * @return codeCle
	 */
	public static int entreeCle() {
		//Terminal.ecrireStringln("methode \"entreeCle\". ");
		Terminal.ecrireStringln("Entrez la clé qui débute votre morceau en respectant ce code: ");
		Terminal.ecrireStringln("u3 ou U3 signifie cle d\'Ut 3ème ligne,");
		Terminal.ecrireStringln("F3 signifie clé de Fa 3eme ligne, F et G pour clé de Fa et Sol, etc");

		int codeCle = 0;
		String cleDepart = "";
		char lettreCleDepart = ' ';
		int chiffreCleDepart = 0;

		//ici le format d'entrée est testé, et doit correspondre à un nombre, une altération
		while (!validationCle(lettreCleDepart, chiffreCleDepart)) {//appel de la fonctionde test
			Terminal.ecrireString("Entrez votre Clé (format \"G\" ou \"U2\" par exemple): ");
			cleDepart = Terminal.lireString();
			int nbCaracteres = cleDepart.length();

			if (nbCaracteres == 1) {
				lettreCleDepart = cleDepart.charAt(0);
				chiffreCleDepart = 0;
			} else if (nbCaracteres == 0) { //par défaut (aucune entrée), clé de Sol
				lettreCleDepart = 'g';
				chiffreCleDepart = 2;
			}
			else {
				lettreCleDepart = cleDepart.charAt(0);
				chiffreCleDepart = cleDepart.charAt(1) - 48;// converti le code
															// unicode en int
			}
			// Affichage de la clé de départ,
			// lettreCleDepart et chiffreCleDepart serviront d'indices à un
			// futur tableau..
			//Terminal.ecrireStringln("lettre: " + lettreCleDepart + " chiffre: " + chiffreCleDepart);
		}
		//appel de la fonction de codage de clé sur un chiffre
		codeCle = nombreCodeCle(lettreCleDepart, chiffreCleDepart);
		
		return codeCle;
	}

	/**
	 * Validation du format d'entree de clé. de la forme U2 pour Ut 2eme ligne
	 * ou G2 ou F U pour clé de Sol Fa Ut(4) usuelles ou F3 U1 etc
	 * 
	 * @param cle
	 * @param chiffre
	 * @return valid
	 */
	public static boolean validationCle(char cle, int chiffre) {
		//Terminal.ecrireStringln("methode \"validationCle\". ");
		
		boolean valid = false;
		
		//TODO filtrer les 7 clés usuelles: Sol 1 et 2 Fa 3 et 4 et Ut 1, 2, 3 et4 
		
		// test de cle
		char[] tab = { 'C', 'U', 'F', 'G', 'c', 'u', 'f', 'g' };
		int i = 0;
	
		do {
			if (cle == tab[i]) {
				valid = true;
			}
			i++;
	
		} while (i < tab.length);
		// test de chiffre compris entre 1 et 4 (lignes supportant les clés)
		if ((chiffre < 0) || (chiffre > 4)) {
			valid = false;
		}
		return valid;
	
	}

	/**
	 * Cette methode permet de coder une clé en calculant un chiffre compris
	 * entre 0 et 6. Ce chiffre correspond à un décalage par rapport à la note
	 * DO sous la portée en clé de Sol. Ainsi la clé d'UT 2 donne un décalage de
	 * 3 notes. Son code sera 3 (à la place de lire un do(en clé de Sol) on lira
	 * un fa(en clé d'Ut2).
	 * On entre une clé et une ligne
	 * 
	 * @param cle
	 * @param ligne
	 * @return nombreCodeCle
	 */
	public static int nombreCodeCle(char cle, int ligne) {
		//Terminal.ecrireStringln("methode \"nombreCodeCle\". ");
	
		int nombreCodeCle = 0;
		
		/*
		 * 1ere ligne: clé d'UT 2eme ligne: clé de Fa 3eme ligne: Clé de Sol
		 * chaque colonne correspond à: 1ere, 2eme, 3eme et 4eme ligne de la
		 * portee par exemple Clé Fa 4----> codeClé 2 car on décale de deux
		 * notes (le DO se lit maintenant MI)
		 */
		// déclaration du tableau
		int[][] tab = { { 6, 5, 3, 1, 6 }, { 2, 1, 6, 4, 2 }, { 0, 2, 0, 5, 3 } };
	
		// determination de la ligne du tableau par la clé entrée:
		int i = 0;
		if (cle == 'C' || cle == 'U' || cle == 'c' || cle == 'u') {i = 0;};
		if (cle == 'F' || cle == 'f') {i = 1;};
		if (cle == 'G' || cle == 'g') {i = 2;};
	
		nombreCodeCle = tab[i][ligne]; /*
										 * la ligne 1 sur la partition
										 * correspond à l'indice 1 du tableau
										 * l'indice 0 correspond à la clé sans
										 * indice specifié
										 */
		return nombreCodeCle;
	}

	/**
	 * Conversion du code clé vers le nom de la clé en toute lettre.
	 * @param codeCle
	 * @return nomDeLaCle
	 */
	public static String conversionCodeCleVersCle (int codeCle){
		//Terminal.ecrireStringln("methode \"conversionCodeCleVersCle\". ");
		String tabCle[]={"Sol","Ut3","Fa","Ut2","Fa3","Ut1","Ut4"};
		String nomDecle=tabCle[codeCle]; // simple recherche dans un tableau
		return nomDecle;
	}

	/**
	 * Cette methode code une tonalité sur un entier. La reference est Do Majeur
	 * codé 0. Fa Majeur(1b) sera codé -1; Lab Majeur(4b) -4; Mi Maj 4; etc.
	 * 
	 * @return codeTon
	 */
	public static int entreeTon() {
		// Terminal.ecrireStringln("methode \"entreeTon\". ");
		int codeTon = 0;
		String tonDepart = " ";
		int nombreAlterations = 0;
		char typeAlteration = ' ';

		while (!validationTon(nombreAlterations, typeAlteration)) {
			Terminal.ecrireString("Indiquez le nombre d'altérations ( \"3b\" ou \"5#\" par exemple): ");
			tonDepart = Terminal.lireString();
			int nbCaracteres = tonDepart.length();
			// Terminal.ecrireStringln("longeur de l'entrée:  " + nbCaracteres);

			if (nbCaracteres == 0) { // comprend "Tonalité de Do" si rien en
										// entrée
				nombreAlterations = 0;
				typeAlteration = '#';

			} else if (nbCaracteres != 2) {
				nombreAlterations = 99; // 99 "trompe" le test pour qu'il
										// échoue..

			}

			else {
				nombreAlterations = tonDepart.charAt(0) - 48;// converti le code
																// unicode en
																// int
																// exploitable
				typeAlteration = tonDepart.charAt(1);
			}
			// TODO methode de conversion tonalité "en clair" par exemple Fa,
			// vers nb codant celle-ci

		}
		
		// appel de la fonction de codage sur un entier simple
		codeTon = nombreCodeTonalite(nombreAlterations, typeAlteration); 
	
		return codeTon;
	}
	
	/**
	 * Methode retournant une enum,  un type "Tons"
	 * @return un Tons
	 */
	public static Tons entreeTonEnum(){
		Tons ton= Tons.DO;
		String tonDepart = " ";
		int nombreAlterations = 0;
		char typeAlteration = ' ';
		int nombreBemol=0;
		int nombreDiese=0;
		
		
		
		while (!validationTon(nombreAlterations, typeAlteration)) {
			//Terminal.ecrireStringln("dans entreeTonEnum");
			Terminal.ecrireString("Indiquez le nombre d'altérations ( \"3b\" ou \"5#\" par exemple): ");
			
			// entrée de l'armure par l'utilisateur
			tonDepart = Terminal.lireString(); 
			int nbCaracteres = tonDepart.length();
			// Terminal.ecrireStringln("longeur de l'entrée:  " + nbCaracteres);
	
			if (nbCaracteres == 0) { // comprend "Tonalité de Do" si rien en
										// entrée
				return Tons.DO;
	
			} else if (nbCaracteres != 2) {
				nombreAlterations = 99; // 99 "trompe" le test pour qu'il
										// échoue..
	
			  }
	
			else {
				nombreAlterations = tonDepart.charAt(0) - 48;// converti le code
																// unicode en
																// int
																// exploitable
				typeAlteration = tonDepart.charAt(1);
				
				if (typeAlteration=='b'){nombreBemol=nombreAlterations;}; // on joue sur les b
				if (typeAlteration=='#'){nombreDiese=nombreAlterations;}; // on joue sur les #
				
				
				// on va chercher dans tous les Tons celui qui correspond aux b et #
				for (Tons tonCalc : Tons.values()){ // il doit y avoir plus simple...
					
					if((tonCalc.getBemol()==nombreBemol)&(tonCalc.getDiese()==nombreDiese)){
						Terminal.ecrireStringln("toncalc= "+tonCalc);
						return tonCalc;
						
					}
					
				}
				
			}
			// TODO methode de conversion tonalité "en clair" par exemple Fa,
			// vers nb codant celle-ci
	
		}
		
		
		
		return ton;
	}


	/**
	 * Cette methode valide une entrée de tonalité soit vide: Do Majeur,
	 * soit deux caractères: premier représente le nombre (compris entre 0 et 8),
	 *  second represente le type (# ou b).
	 *  
	 * @param nombre
	 * @param type
	 * @return boolean
	 */
	public static boolean validationTon(int nombre, char type) {
		// TODO definir les rèles de validation de tonalité
		//Terminal.ecrireStringln("methode \"validationTon\". ");

		//validation du type d'altération
		if (type != '#' && type != 'b') {
			//Terminal.ecrireStringln("faux type");
			return false;
		}
		
		//validation du nombre 	d'altérations	
		if (nombre > 8 || nombre < -8) {
			Terminal.ecrireStringln("faux nombre");
			return false;
		}
		return true;
	}

	/**
	 * Cette methode renvoie un nombre qui code une tonalité. La tonalité neutre
	 * est le DO Majeur, et est codée 0. SOL (1#) sera 1, RE (2#); FA(1b) sera
	 * -1, SIb -2, etc.
	 * On entre deux parametres: le nombre et le type d'altération
	 * 
	 * @param nombre
	 * @param alteration
	 * @return nombreCodeTonalite
	 */
	public static int nombreCodeTonalite(int nombre, char alteration) {
		//Terminal.ecrireStringln("methode \"nombreCodeTonalité\". ");
		if (alteration=='b'){nombre=-nombre;};	
		int nombreCodeTonalite = nombre;
		return nombreCodeTonalite;
	}
	
	
	/**
	 * Calcul du code de la tonalité du morceau transposé.
	 * 
	 * @param tonMorceauDebut
	 * @param tonInstruDebut
	 * @param tonInstruFin
	 * @return tonFinal
	 */
	public static int calculTonFinal(int tonMorceauDebut, int tonInstruDebut,
			int tonInstruFin) {
		int tonFinal = 0;
		tonFinal = tonMorceauDebut + tonInstruDebut - tonInstruFin; // simple décalage
		return tonFinal;
	}
	
	
	/**
	 * Methode de calcul du code de la cle de transposition.
	 * 
	 * @param cleDepart
	 * @param tonInstruDepart
	 * @param tonInstruArrive
	 * @return codeCleTranspo
	 */
	public static int transpoDeCle (int cleDepart, int tonInstruDepart, int tonInstruArrive){
		int codeCleTranspo=0;
		int codeCleDueTonalite=0;
		int indice=0;
		int tabCorrespondanceCle[]={0,4,1,5,2,6,3}; // correspond à l'ordre des quintes (1+4) 
													//(les toniques de DoM et SolM sont espacés d'une quinte
			
		indice = (tonInstruDepart-tonInstruArrive)%7; 	//calcul du decalage due à la difference de tonalite. 
														//on calcule d'abord un indice de lecture du tableau 
		if (indice <0){									// Complement à 7 si indice négatif
			indice=7+indice; 							
		}
		
		codeCleDueTonalite=tabCorrespondanceCle[indice]; 	// recherche du décalage dans le tableau 
		
		codeCleTranspo= (codeCleDueTonalite+cleDepart)%7; 	// et on ajoute le décalage due à la clé de départ
		
		//Terminal.ecrireStringln("clé calculée "+codeCleTranspo);
		
		return codeCleTranspo; //retour au demandeur !
	}

	/**
	 * Cette méthode convertit un code tonalité (exemple -2) en armure lisible par
	 * l'utilisateur (2 bémols).
	 * 
	 * @param codeTon
	 * @return tonEnClair
	 */
	public static String tonEnClair(int codeTon) {
		String tonEnClair = "avec bemols ";
		char alteration = ' ';
		
		if (codeTon < 0) {
			codeTon = -codeTon;
			alteration = 'b';
			tonEnClair = String.valueOf(codeTon) + " bemols. ";
			return tonEnClair;
		} else if (codeTon > 0) {
			tonEnClair = String.valueOf(codeTon) + " dieses. ";
		} else {
			tonEnClair = "Ni diese ni bémol.";
		}
		return tonEnClair;
	}
	
	/**
	 * Transforme le code de la tonalité avec le nom de la Tonalité Majeure correspondante
	 * 
	 * @param codeTon
	 * @return tonEnLettres
	 */
	public static String tonEnLettres(int codeTon){
		String tonEnLettres = "";
		// on fait correspondre un indice avec une valeur du tableau.
		String tableauTonalite[]= {"Do bémol","Sol bémol","Ré bémol",
				"La bémol","Mi bémol","Si bémol",
				"Fa","Do","Sol","Ré","La","Mi","Si","Fa#","Do#"};
		tonEnLettres = tableauTonalite[codeTon+7]+" Maj."; 
		
		return tonEnLettres;
	}

	/**
	 * CETTE METHODE N'EST PLUS UTILISEE
	 * Methode d'entrée du choix de lecture à vue ou non.
	 * 
	 * @return boolean
	 */
	public static boolean choixTranspoVue() {
		// "Voulez-vous transposer à vue ? (o/n)"
		// Terminal.ecrireStringln("Transpo à vue: "+ transpoVue);
		Terminal.ecrireStringln("------------------------------------------------------------------------------");
		Terminal.ecrireStringln("||                       Voulez-vous transposer à vue ?                     ||");
		Terminal.ecrireStringln("------------------------------------------------------------------------------");

		Terminal.ecrireString("Entrez votre choix: (\"o\" pour oui) ");
		char valid = Terminal.lireChar();

		return (valid == 79 || valid == 111); // si 'O' ou 'o'
	}
	

}
