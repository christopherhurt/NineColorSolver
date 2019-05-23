package main;

public class DNA {
    
    private String[][] colorGrid;
    private int fitness;
    
    public DNA(String[][] colorGrid){
        this.colorGrid = colorGrid;
    }
    
    public int calcFitness(){
        fitness = 0;
        
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 9; j++){
                for(int k = j + 1; k < 9; k++){
                    if(colorGrid[i][j].equals(colorGrid[i][k])){
                        fitness++;
                    }
                }
            }
        }
        
        return fitness;
    }
    
    public void applyMutation(){
        double rand = Math.random();
        
        if(rand < 0.5){
            switchEdges();
        }else{
            switchCorners();
        }
    }
    
    private void switchEdges(){
        int edge1 = (int)(Math.random() * 12);
        int edge2 = (int)(Math.random() * 12);
        
        String e1 = getEdgeColor(edge1);
        String e2 = getEdgeColor(edge2);
        
        setEdgeColor(edge1, e2);
        setEdgeColor(edge2, e1);
    }
    
    private void switchCorners(){
        int corner1 = (int)(Math.random() * 8);
        int corner2 = (int)(Math.random() * 8);
        
        String c1 = getCornerColor(corner1);
        String c2 = getCornerColor(corner2);
        
        setCornerColor(corner1, c2);
        setCornerColor(corner2, c1);
    }
    
    private String getEdgeColor(int edge){
        switch(edge){
            case 0:
                return colorGrid[0][1];
            case 1:
                return colorGrid[0][3];
            case 2:
                return colorGrid[0][5];
            case 3:
                return colorGrid[0][7];
            case 4:
                return colorGrid[5][1];
            case 5:
                return colorGrid[5][3];
            case 6:
                return colorGrid[5][5];
            case 7:
                return colorGrid[5][7];
            case 8:
                return colorGrid[1][3];
            case 9:
                return colorGrid[1][5];
            case 10:
                return colorGrid[3][3];
            case 11:
                return colorGrid[3][5];
            default:
                throw new IllegalArgumentException("Invalid edge number");
        }
    }
    
    private String getCornerColor(int corner){
        switch(corner){
            case 0:
                return colorGrid[0][0];
            case 1:
                return colorGrid[0][2];
            case 2:
                return colorGrid[0][6];
            case 3:
                return colorGrid[0][8];
            case 4:
                return colorGrid[5][0];
            case 5:
                return colorGrid[5][2];
            case 6:
                return colorGrid[5][6];
            case 7:
                return colorGrid[5][8];
            default:
                throw new IllegalArgumentException("Invalid corner number");
        }
    }
    
    private void setEdgeColor(int edge, String color){
        switch(edge){
            case 0:
                colorGrid[0][1] = color;
                colorGrid[1][7] = color;
                break;
            case 1:
                colorGrid[0][3] = color;
                colorGrid[4][7] = color;
                break;
            case 2:
                colorGrid[0][5] = color;
                colorGrid[2][7] = color;
                break;
            case 3:
                colorGrid[0][7] = color;
                colorGrid[3][7] = color;
                break;
            case 4:
                colorGrid[5][1] = color;
                colorGrid[3][1] = color;
                break;
            case 5:
                colorGrid[5][3] = color;
                colorGrid[4][1] = color;
                break;
            case 6:
                colorGrid[5][5] = color;
                colorGrid[2][1] = color;
                break;
            case 7:
                colorGrid[5][7] = color;
                colorGrid[1][1] = color;
                break;
            case 8:
                colorGrid[1][3] = color;
                colorGrid[4][5] = color;
                break;
            case 9:
                colorGrid[1][5] = color;
                colorGrid[2][3] = color;
                break;
            case 10:
                colorGrid[3][3] = color;
                colorGrid[2][5] = color;
                break;
            case 11:
                colorGrid[3][5] = color;
                colorGrid[4][3] = color;
                break;
            default:
                throw new IllegalArgumentException("Invalid edge number");
        }
    }
    
    private void setCornerColor(int corner, String color){
        switch(corner){
            case 0:
                colorGrid[0][0] = color;
                colorGrid[1][6] = color;
                colorGrid[4][8] = color;
                break;
            case 1:
                colorGrid[0][2] = color;
                colorGrid[1][8] = color;
                colorGrid[2][6] = color;
                break;
            case 2:
                colorGrid[0][6] = color;
                colorGrid[3][8] = color;
                colorGrid[4][6] = color;
                break;
            case 3:
                colorGrid[0][8] = color;
                colorGrid[2][8] = color;
                colorGrid[3][6] = color;
                break;
            case 4:
                colorGrid[5][0] = color;
                colorGrid[3][2] = color;
                colorGrid[4][0] = color;
                break;
            case 5:
                colorGrid[5][2] = color;
                colorGrid[3][0] = color;
                colorGrid[2][2] = color;
                break;
            case 6:
                colorGrid[5][6] = color;
                colorGrid[1][0] = color;
                colorGrid[4][2] = color;
                break;
            case 7:
                colorGrid[5][8] = color;
                colorGrid[1][2] = color;
                colorGrid[2][0] = color;
                break;
            default:
                throw new IllegalArgumentException("Invalid corner number");
        }
    }
    
    public String[][] checkSolution(){
        if(calcFitness() == 0){
            return colorGrid;
        }else{
            return null;
        }
    }
    
    public int getFitness(){
        return fitness;
    }
    
    public DNA copy(){
        String[][] colorsCopy = new String[6][9];
        
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 9; j++){
                colorsCopy[i][j] = colorGrid[i][j];
            }
        }
        
        return new DNA(colorsCopy);
    }
    
}
