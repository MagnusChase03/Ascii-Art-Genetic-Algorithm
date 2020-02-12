package test;

import java.util.ArrayList;

public class AlphaGenetic {

//	  /\
//   /--\
//  /    \
	
	private final String[] answer = {"  /\\  ", " /--\\ ", "/    \\"};
	private ArrayList<String[]> generation;
	private ArrayList<Integer> fitness; 
	
	void populate(ArrayList<String[]> generation) {
	
		for (int i = 0; i < 100; i++) {
			
			String[] member = new String[3];
			for (int y = 0; y < member.length; y++) {
				
				member[y] = "";
				for (int x = 0; x < 6; x++) {
					
					char c = (char) ((Math.random() * 95) + 32);
					member[y] += c;
					
				}
				
			}
			
			generation.add(member);
			
		}
		
	}
	
	void getFitness(ArrayList<String[]> generation, ArrayList<Integer> fitness) {
		
		for (int i = 0; i < generation.size(); i++) {
			
			int fit = 0;
			for (int k = 0; k < generation.get(i).length; k++) {
				
				for (int l = 0; l < generation.get(i)[k].length(); l++) {
					
					char character = generation.get(i)[k].charAt(l);
					if (character == answer[k].charAt(l)) {
						
						fit++;
						
					}
					
				}
				
			}
			
			fitness.add(fit);
			
		}
		
	}
	
	ArrayList<String[]> createNewPop(ArrayList<String[]> generation, ArrayList<Integer> fitness) {
		
		ArrayList<String[]> newGen = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			
			int bestIndex = i;
			for (int k = 0; k < generation.size(); k++) {
				
					
				if (fitness.get(k) > fitness.get(bestIndex)) {
					
					bestIndex = k;
					
				}
				
			}
			
			newGen.add(generation.get(bestIndex));
			generation.remove(bestIndex);
			fitness.remove(bestIndex);
			
		}
		
		for (int i = 10; i < 100; i++) {
			
			String[] parent1 = newGen.get((int) (Math.random() * 10));
			String[] parent2 = newGen.get((int) (Math.random() * 10));
			
			String[] son = {"", "", ""};
			for (int k = 0; k < parent1.length; k++) {
				
				int randSplit = (int) (Math.random() * 6);
				son[k] = parent1[k].substring(0, randSplit) + parent2[k].substring(randSplit);
				
				for (int c = 0; c < son[k].length(); c++) {
					
					if (Math.random() < 0.05) {
						
						son[k] = son[k].substring(0, c) + (char) (Math.random() * 95 + 32) + son[k].substring(c + 1);
						
					}
					
				}
				
			}
			
			newGen.add(son);
			
		}
		
		return newGen;
		
	}
	
	AlphaGenetic() {
	
		generation = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
	
			fitness = new ArrayList<>();
			
			populate(generation);
			getFitness(generation, fitness);
			generation = createNewPop(generation, fitness);
		
		}
		
		System.out.println(generation.get(0)[0] + "\n" + generation.get(0)[1]+ "\n" + generation.get(0)[2]);
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		
		new AlphaGenetic();
		
	}
	
}
