package main;

import java.util.ArrayList;
import java.util.List;

public class Population {
    
    private static final String[] EDGES = {"W", "Y", "B", "LB", "DB", "O", "R", "R", "R", "P", "P", "P"};
    private static final String[] CENTERS = {"W", "B", "Y", "O", "LB", "DB"};
    private static final String[] CORNERS = {"W", "O", "G", "G", "LB", "DB", "Y", "B"};
    
    private static final int POP_SIZE = 100;
    private static final double MUTATION_RATE = 0.25;
    
    private List<DNA> pop;
    private int bestFitness;
    
    public Population(){
        pop = new ArrayList<>();
        bestFitness = 0;
        
        for(int i = 0; i < POP_SIZE; i++){
            pop.add(genDNA());
        }
    }
    
    private DNA genDNA(){
        List<String> edgePool = initPool(EDGES);
        List<String> cornerPool = initPool(CORNERS);
        
        String[][] colors = new String[6][9];
        
        String e0 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e1 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e2 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e3 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e4 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e5 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e6 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e7 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e8 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e9 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e10 = edgePool.remove((int)(Math.random() * edgePool.size()));
        String e11 = edgePool.remove((int)(Math.random() * edgePool.size()));
        
        String c0 = cornerPool.remove((int)(Math.random() * cornerPool.size()));
        String c1 = cornerPool.remove((int)(Math.random() * cornerPool.size()));
        String c2 = cornerPool.remove((int)(Math.random() * cornerPool.size()));
        String c3 = cornerPool.remove((int)(Math.random() * cornerPool.size()));
        String c4 = cornerPool.remove((int)(Math.random() * cornerPool.size()));
        String c5 = cornerPool.remove((int)(Math.random() * cornerPool.size()));
        String c6 = cornerPool.remove((int)(Math.random() * cornerPool.size()));
        String c7 = cornerPool.remove((int)(Math.random() * cornerPool.size()));
        
        // Centers
        colors[0][4] = CENTERS[0];
        colors[1][4] = CENTERS[4];
        colors[2][4] = CENTERS[2];
        colors[3][4] = CENTERS[5];
        colors[4][4] = CENTERS[3];
        colors[5][4] = CENTERS[1];
        
        // Corner 0
        colors[0][0] = c0;
        colors[1][6] = c0;
        colors[4][8] = c0;
        
        // Corner 1
        colors[0][2] = c1;
        colors[1][8] = c1;
        colors[2][6] = c1;
        
        // Corner 2
        colors[0][6] = c2;
        colors[3][8] = c2;
        colors[4][6] = c2;
        
        // Corner 3
        colors[0][8] = c3;
        colors[2][8] = c3;
        colors[3][6] = c3;

        // Corner 4
        colors[5][0] = c4;
        colors[3][2] = c4;
        colors[4][0] = c4;
        
        // Corner 5
        colors[5][2] = c5;
        colors[3][0] = c5;
        colors[2][2] = c5;
        
        // Corner 6
        colors[5][6] = c6;
        colors[1][0] = c6;
        colors[4][2] = c6;
        
        // Corner 7
        colors[5][8] = c7;
        colors[1][2] = c7;
        colors[2][0] = c7;
        
        // Edge 0
        colors[0][1] = e0;
        colors[1][7] = e0;
        
        // Edge 1
        colors[0][3] = e1;
        colors[4][7] = e1;
        
        // Edge 2
        colors[0][5] = e2;
        colors[2][7] = e2;
        
        // Edge 3
        colors[0][7] = e3;
        colors[3][7] = e3;
        
        // Edge 4
        colors[5][1] = e4;
        colors[3][1] = e4;
        
        // Edge 5
        colors[5][3] = e5;
        colors[4][1] = e5;
        
        // Edge 6
        colors[5][5] = e6;
        colors[2][1] = e6;
        
        // Edge 7
        colors[5][7] = e7;
        colors[1][1] = e7;
        
        // Edge 8
        colors[1][3] = e8;
        colors[4][5] = e8;
        
        // Edge 9
        colors[1][5] = e9;
        colors[2][3] = e9;
        
        // Edge 10
        colors[3][3] = e10;
        colors[2][5] = e10;
        
        // Edge 11
        colors[3][5] = e11;
        colors[4][3] = e11;
        
        return new DNA(colors);
    }
    
    private List<String> initPool(String[] pool){
        List<String> list = new ArrayList<>();
        
        for(int i = 0; i < pool.length; i++){
            list.add(pool[i]);
        }
        
        return list;
    }
    
    public String[][] executeGeneration(){
        int totalFitness = 0;
        bestFitness = Integer.MAX_VALUE;
        
        for(DNA dna : pop){
            if(Math.random() < MUTATION_RATE){
                dna.applyMutation();
            }
            
            int fitness = dna.calcFitness();
            
            if(fitness < bestFitness){
                bestFitness = fitness;
            }
            
            if(fitness == 0){
                return dna.checkSolution();
            }
            
            totalFitness += fitness;
        }
        
        List<DNA> newPop = new ArrayList<>();
        
        for(DNA dna : pop){
            if(dna.checkSolution() == null){
                int copies = (int)(totalFitness / dna.getFitness());
                
                for(int i = 0; i < copies; i++){
                    newPop.add(dna.copy());
                }
            }
        }
        
        while(newPop.size() != POP_SIZE){
            if(newPop.size() > POP_SIZE){
                newPop.remove((int)(Math.random() * newPop.size()));
            }else{
                newPop.add(genDNA());
            }
        }
        
        pop = newPop;
        return null;
    }
    
    public int getBestFitness(){
        return bestFitness;
    }
    
}
