package irismining;

class Iris {

    // input data
    private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;
    private String type;

    // normalize data
    private double normSepalLength;
    private double normSepalWidth;
    private double normPetalLength;
    private double normPetalWidth;

    // results data
    private String classificationType;
    private boolean isClassificationRight;

    // constructor
    public Iris(double sepL, double sepW, double petL, double petW, String t){
        sepalLength = sepL;
        sepalWidth = sepW;
        petalLength = petL;
        petalWidth = petW;
        type = t;
    }

    public Iris() {
		// TODO Auto-generated constructor stub
	}

	// Input data getters
    public double getSepalLength() {
        return sepalLength;
    }
    public double getSepalWidth() {
        return sepalWidth;
    }
    public double getPetalLength() {
        return petalLength;
    }
    public double getPetalWidth() {
        return petalWidth;
    }
    public String getType() {
        return type;
    }

    // normalize data getters
    public double getNormSepalLength() {
        return normSepalLength;
    }
    public double getNormSepalWidth() {
        return normSepalWidth;
    }
    public double getNormPetalLength() {
        return normPetalLength;
    }
    public double getNormPetalWidth() {
        return normPetalWidth;
    }

    public void setSepalLength(double sepalLength) {
		this.sepalLength = sepalLength;
	}

	public void setSepalWidth(double sepalWidth) {
		this.sepalWidth = sepalWidth;
	}

	public void setPetalLength(double petalLength) {
		this.petalLength = petalLength;
	}

	public void setPetalWidth(double petalWidth) {
		this.petalWidth = petalWidth;
	}

	// Getters for result data
    public String getClassificationType() {
        return classificationType;
    }
    public boolean isClassificationRight() {
        return isClassificationRight;
    }

    // Setters
    public void setClassificationType(String classificationType) {
        this.classificationType = classificationType;
    }

    public void normalize( double maxSL,  double maxSW, double maxPL, double maxPW) {
        normSepalLength = (double) (sepalLength/maxSL);
        normSepalWidth = (double) (sepalWidth/maxSW) ;
        normPetalLength = (double)(petalLength/maxPL);
        normPetalWidth = (double) (petalWidth/maxPW);
    }
    

    // testing result
    public void testClassification() {
        isClassificationRight = type.equals(classificationType);
    }

    @Override
    public String toString() {
        return "  type: " + type + " | Classification Type: " + classificationType + " | Classification is "
                            + (isClassificationRight ? "right" : "wrong");
    }
    
    public String show() {
    	return "sepal length: " + sepalLength + " | sepal width: " + sepalWidth
                + " | petal length: " + petalLength + " | petal width: " + petalWidth
                    + " | type: " + type ;
    	
    }

    public String normToString() {
        return String.format("sepal length: " + "%.2f | sepal width: " + "%.2f | petal length: " + "%.2f | petal width: "
                        + "%.2f | type: " + "%s "
                , normSepalLength, normSepalWidth, normPetalLength, normPetalWidth, type);
    }
}