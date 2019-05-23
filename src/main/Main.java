package main;

public class Main {
    
    public static void main(String[] args){
        Population pop = new Population();
        String[][] solution = null;
        int generation = 1;
        
        while(solution == null){
            solution = pop.executeGeneration();
            System.out.println("Generation " + generation++ + " passed, best fitness is " + pop.getBestFitness());
        }
        
        printSolution(solution, --generation);
    }
    
    private static void printSolution(String[][] sol, int gens){
        System.out.println();
        System.out.println("Solution found in " + gens + " generations!");
        System.out.println();
        
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
