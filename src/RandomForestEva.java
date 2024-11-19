package src;

import weka.classifiers.trees.RandomForest;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

public class RandomForestEva {

    public String run(Instances data) throws Exception {
        // Initialize the Random Forest classifier
        RandomForest forest = new RandomForest();
        forest.buildClassifier(data);

        // Evaluate the model
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(forest, data, 10, new Random(1));

        // Get the evaluation results as a string
        StringBuilder results = new StringBuilder();
        results.append(eval.toSummaryString("\nResults of Random Forest Evaluation: \n", false)); 
        
        return results.toString();
    }

}
