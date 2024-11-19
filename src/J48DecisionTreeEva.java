package src;

import weka.classifiers.trees.J48;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

public class J48DecisionTreeEva {

    public String run(Instances data) throws Exception {
        // Initialize the J48 classifier
        J48 tree = new J48();
        tree.buildClassifier(data);

        // Evaluate the model
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(tree, data, 10, new Random(1));

        // Get the evaluation results as a string
        StringBuilder results = new StringBuilder();
        results.append(eval.toSummaryString("\nResults of DecisionTree(J48) Evaluation: \n", false)); 
        
        return results.toString();
    }
}
