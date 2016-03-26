package transpositionMusicale;


public enum Tons {
	//objets
	DOB ("Dob Maj.",-7,7,0,"bémols"),
	SOLB ("Solb Maj.",-6,6,0,"bémols"),
	REB ("Réb Maj.",-5,5,0,"bémols"),
	LAB ("Lab Maj.",-4,4,0,"bémols"),
	MIB ("Mib Maj.",-3,3,0,"bémols"),
	SIB ("Sib Maj.",-2,2,0,"2 bémols"),
	FA ("Fa Maj.",-1,1,0,"1 bémol"),
	DO ("Do Maj.", 0,0,0, "rien"),
	SOL ("Sol Maj.",1,0,1,"1 dièse"),
	RE ("Ré Maj.",2,0,2,"2 dièses"),
	LA ("La Maj.",3,0,3,"3 dièses"),
	MI ("Mi Maj.",4,0,4,"4 dièses"),
	SI ("Si Maj.",5,0,5,"5 dièses"),
	FAD ("Fa# Maj.",6,0,6,"6 dièses"),
	DOD ("Do# Maj.",7,0,7,"7 dièses");
	
	private String name = "";
	private int code =0 ;
	private int nbBemol=0;
	private int nbDiese=0;
	private String armure = "";
	
	//Constructeur
	Tons(String name, int code, int nbBemol, int nbDiese, String armure){
		this.name=name;
		this.code=code;
		this.nbBemol=nbBemol;
		this.nbDiese=nbDiese;
		this.armure=armure;
	}
	
	// methodes:
	
	/**
	 * nom usuel du Ton
	 */
	public String toString(){
	return name;
	}
	
	/**
	 * affiche l'armure du Ton
	 */
	public void getArmure(){
		Terminal.ecrireString("" + armure + "");
	}	
	
	/**
	 * 
	 * @return code du Ton
	 */
	public int codage(){
		return code;
	}
	
	/**
	 * 
	 * @return nombre de bémols
	 */
	public int getBemol(){
		return nbBemol;
	
	}
	
	/**
	 * 
	 * @return nombre de dièses
	 */
	public int getDiese(){
		return nbDiese;
	
	}
	
	/**
	 * ici j'ai voulu faire une méthode où l'on puisse entrer directement le nb de # et
	 * de bémols et qui renvoie le Ton directement.
	 * @param b (bémols)
	 * @param d (dièses)
	 * @return le ton qui correspond
	 */
	public  Tons bemolsDieses (int b, int d){
		// on va chercher dans tous les Tons celui qui correspond aux b et #
		for (Tons tonCalc : Tons.values()){ // il doit y avoir plus simple...
			
			if((tonCalc.getBemol()==b)&(tonCalc.getDiese()==d)){
				Terminal.ecrireStringln("tonclac= "+tonCalc);
				//return Tons.valueOf(tonCalc);
				
			}
			
		}
		
			return Tons.FA;	
		
		
	}
	

}
