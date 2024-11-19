package src;

import weka.classifiers.functions.Logistic;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

public class Logistic_Regression extends LoadData {
    public static void main(String[] args) {
        try {
            Logistic_Regression example = new Logistic_Regression();
            String datasetPath = "lib/newdata.arff"; // Adjust the path to your ARFF file
            example.loadData(datasetPath);

            Instances data = example.getNewData();
            System.out.println("Data loaded and converted successfully!");
            System.out.println("Number of instances: " + data.numInstances());
            System.out.println("Number of attributes: " + data.numAttributes());

            if (data != null) {
                // Profile memory usage before initialization
                Runtime runtime = Runtime.getRuntime();
                System.out.println("Total Memory (before): " + runtime.totalMemory());
                System.out.println("Free Memory (before): " + runtime.freeMemory());

                // Initialize the Logistic classifier
                System.out.println("Initializing Logistic Regression model...");
                Logistic logistic = new Logistic();
                logistic.setMaxIts(100);
                logistic.setRidge(1e-8);
                logistic.setDebug(true);
                try {
                    logistic.buildClassifier(data);
                    System.out.println("Logistic Regression model built successfully!");

                    // Profile memory usage after initialization
                    System.out.println("Total Memory (after): " + runtime.totalMemory());
                    System.out.println("Free Memory (after): " + runtime.freeMemory());
                } catch (Exception e) {
                    System.err.println("Error building Logistic Regression model: " + e.getMessage());
                    e.printStackTrace();
                }

                // Evaluate the model if it was successfully built
                if (logistic != null) {
                    try {
                        System.out.println("Starting model evaluation...");
                        Evaluation eval = new Evaluation(data);
                        eval.crossValidateModel(logistic, data, 10, new Random(1));
                        System.out.println("Model evaluation completed!");

                        // Output the evaluation results
                        System.out.println(eval.toSummaryString("\nResults of Logistic Regression model:\n", false));
                        System.out.println(eval.toClassDetailsString());
                        System.out.println(eval.toMatrixString());
                    } catch (Exception e) {
                        System.err.println("Error during model evaluation: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Data is null after loading.");
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
