package src;

import weka.classifiers.trees.RandomForest;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

public class RandomForestClassifier {

    public String run(Instances data) throws Exception {
        // Initialize the Random Forest classifier
        RandomForest forest = new RandomForest();
        forest.buildClassifier(data);

        // Evaluate the model
        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(forest, data);

        // Calculate additional metrics
        double AUC = eval.areaUnderROC(1);
        double kappa = eval.kappa();
        double MAE = eval.meanAbsoluteError();
        double RMSE = eval.rootMeanSquaredError();
        double RAE = eval.relativeAbsoluteError();
        double RRSE = eval.rootRelativeSquaredError();
        double correct = eval.pctCorrect() / 100.0;
        double incorrect = eval.pctIncorrect() / 100.0;
        double precision = eval.precision(1);
        double recall = eval.recall(1);
        double errorRate = eval.errorRate();
        double f1 = eval.fMeasure(1);


        // Get the evaluation results as a string
        StringBuilder results = new StringBuilder();
        results.append(String.format("Results of Random Forest Model: \n"));
        results.append(forest.getCapabilities().toString());
        results.append(String.format("Correct: %.2f%%\n", correct * 100));
        results.append(String.format("Incorrect: %.2f%%\n", incorrect * 100));
        results.append(String.format("Precision: %.2f%%\n", precision * 100));
        results.append(String.format("Recall: %.2f%%\n", recall * 100));
        results.append(String.format("F1 Score: %.2f%%\n", f1 * 100));
        results.append(String.format("Error Rate: %.2f%%\n", errorRate * 100));
        results.append(String.format("AUC: %.2f\n", AUC));
        results.append(String.format("Kappa: %.2f\n", kappa));
        results.append(String.format("Mean Absolute Error: %.4f\n", MAE));
        results.append(String.format("Root Mean Squared Error: %.4f\n", RMSE));
        results.append(String.format("Relative Absolute Error: %.2f%%\n", RAE));
        results.append(String.format("Root Relative Squared Error: %.2f%%\n", RRSE));        
        results.append(eval.toMatrixString("=== Overall Confusion Matrix ===\n")); 

        return results.toString();
    }
}