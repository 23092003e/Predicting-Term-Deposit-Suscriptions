package src;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

public class NaiveBayesEva {

    public String run(Instances data) throws Exception {
        // Initialize the Naive Bayes classifier
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(data);

        // Evaluate the model
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(nb, data, 10, new Random(1));

        // Get the evaluation results as a string
        StringBuilder results = new StringBuilder();
        results.append(eval.toSummaryString("\nResults of Naive Bayes Evaluation: \n", false));  
        
        return results.toString();
    }
}
