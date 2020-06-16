package com.mygdx.game.attack1;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SortilegesData {
	
	
    private static int nbSorts; //nb de sorts dans le fichier texte, ie nb de ligne
    private static Random random;
    static String[] sortilegeArray; //tableau des sortilèges
    static FileHandle listSorts = Gdx.files.internal("Sorts.txt"); //notre fichier texte
    public static String sortAleatoire;
    
    
    public SortilegesData() {
    	random = new Random();
    	sortilegeArray = readFromFile(listSorts);
		int Sort = random.nextInt(nbSorts); //nb aléatoire pour choisir un sort
		sortAleatoire = (sortilegeArray[Sort].substring(0, sortilegeArray[Sort].length() -1 )); 
    }
   
	
	 public static String[] readFromFile(FileHandle file) {
	       String sortilege = file.readString(); //lit tout le fichier
	       String [] sortArray = sortilege.split("\n"); //créer un tableau de sortilèges
	       nbSorts = sortArray.length;
	       return sortArray;
	    }

}
