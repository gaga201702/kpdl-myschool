package irismining;

import java.io.*;
import java.util.*;

public class IrisDataParser {

    private List<Iris> trainList = new ArrayList<>();
    private List<Iris> testList = new ArrayList<>();

    public static void main(String[] args) {
    	long startTime = System.nanoTime();
    	
        IrisDataParser dataParser = new IrisDataParser();
        dataParser.parse();
        IrisKNN kNN = new IrisKNN(dataParser.trainList, dataParser.testList, 3);
        kNN.classify();
        
        System.out.println("\n \n");
        System.out.println("==========================TEST RESULTS===========================");
        int countWrong = 0, countRight = 0,j=0;
        
        for (Iris ir: dataParser.testList) {
            int i = ir.isClassificationRight() ? ++countRight : ++countWrong;
            j++;
        	System.out.print("sample "+j+"> ");
            System.out.println(ir);
        }
        
        System.out.println("\n \n");
        System.out.println("==========================RESULTS===========================");
        System.out.println("% of right types: " + (countRight*100/dataParser.testList.size())
                            + "% , " + "% of wrong types: " + (100-countRight*100/dataParser.testList.size()) + "%");
        long endTime = System.nanoTime();

        long duration = (endTime - startTime)/1000000;
        System.out.println("time :" +duration +" milliseconds");
    
    }

    private void parse() {
        Set<Integer> idxTestSet = new HashSet<>();  // indexes for trainList
        Random random = new Random();

        // random numbers [0, 149]
        do {
            idxTestSet.add(random.nextInt(149));
        } while (idxTestSet.size() != 45);

        // parse data
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream("./data/iris/data.txt")))) {
            int idx = -1;
            String str;
            String[] array;

            while ((str = bReader.readLine()) != null) {
                ++idx;
                array = str.split(",");
                if (idxTestSet.contains(idx)) {
                    testList.add(new Iris(Double.parseDouble(array[0]), Double.parseDouble(array[1]),
                            Double.parseDouble(array[2]), Double.parseDouble(array[3]), array[4]));
                } else {
                    trainList.add(new Iris(Double.parseDouble(array[0]), Double.parseDouble(array[1]),
                            Double.parseDouble(array[2]), Double.parseDouble(array[3]), array[4]));
                }
            }

            System.out.println("==========================INPUT TRAIN DATA===========================");
            int i=0;
            for (Iris ir: trainList) {	
            	i++;
            	System.out.print("sample "+i+"> ");
                System.out.println(ir.show());
            }
            System.out.println("==========================INPUT TEST DATA===========================");
            i =0 ;
            for (Iris ir: testList) {
            	i++;
            	System.out.print("sample "+i+"> ");
                System.out.println(ir.show());
            }

            // normalize data
            normalize();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void normalize() {
        Iris irisMax1 = getMax(trainList);
        Iris irisMax2 = getMax(testList);
       
        for (Iris ir: trainList) {
            ir.normalize(irisMax1.getSepalLength(),irisMax1.getSepalWidth(),irisMax1.getPetalLength(),irisMax1.getSepalWidth());
        }

        for (Iris ir: testList) {
        	ir.normalize(irisMax2.getSepalLength(),irisMax2.getSepalWidth(),irisMax2.getPetalLength(),irisMax2.getSepalWidth());
        }

        System.out.println("\n \n");
        System.out.println("==========================NORM TRAIN DATA===========================");
        int i =0;
        for (Iris ir: trainList) {
        	i++;
        	System.out.print("sample "+i+"> ");
            System.out.println(ir.normToString());
        }
        System.out.println("==========================NORM TEST DATA===========================");
        i =0;
        for (Iris ir: testList) {
        	i++;
        	System.out.print("sample "+i+"> ");
            System.out.println(ir.normToString());
        }
    }

    public List<Iris> getTrainList() {
        return trainList;
    }

    public List<Iris> getTestList() {
        return testList;
    }

    public Iris getMax(List<Iris> list) {
    	Iris maxIris = new Iris(0,0,0,0,"");  	
    	for(int i=0;i<list.size();i++) {
    		if(maxIris.getPetalLength()<list.get(i).getPetalLength()) {
    			maxIris.setPetalLength(list.get(i).getPetalLength());
    		}
    		if(maxIris.getPetalWidth()<list.get(i).getPetalWidth()) {
    			maxIris.setPetalWidth(list.get(i).getPetalWidth());
    		}
    		
    		if(maxIris.getSepalLength()<list.get(i).getSepalLength()) {
    			maxIris.setSepalLength(list.get(i).getSepalLength());
    		}
    		
    		if(maxIris.getSepalWidth()<list.get(i).getSepalWidth()) {
    			maxIris.setSepalWidth(list.get(i).getSepalWidth());
    		}
    	}
    	
    	return maxIris;
    }
    
    
}