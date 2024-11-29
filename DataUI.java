import src.LoadData;
import src.SaveData;
import src.J48DecisionTree;
import src.J48DecisionTreeEva;
import src.J48TreeVisualizer;
import src.RandomForestClassifier;
import src.RandomForestEva;
import src.NaiveBayess;
import src.NaiveBayesEva;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DataUI extends JFrame {
    private JTextArea textArea;
    private LoadData loadData;
    private J48DecisionTree j48DecisionTree;
    private J48DecisionTreeEva j48DecisionTreeEva;
    private J48TreeVisualizer j48TreeVisualizer;
    private RandomForestClassifier randomForestClassifier;
    private RandomForestEva randomForestEva;
    private NaiveBayess naiveBayess;
    private NaiveBayesEva naiveBayesEva;
    private SaveData saveData;

    public DataUI() {
        loadData = new LoadData();
        j48DecisionTree = new J48DecisionTree();
        j48DecisionTreeEva = new J48DecisionTreeEva();
        j48TreeVisualizer = new J48TreeVisualizer();
        randomForestClassifier = new RandomForestClassifier();
        randomForestEva = new RandomForestEva();
        naiveBayess = new NaiveBayess();
        naiveBayesEva = new NaiveBayesEva();
        saveData = new SaveData();

        initUI();
    }

    private void initUI() {
        setTitle("Data Loader and Classifiers");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and set up the panel
        JPanel panel = new JPanel(new BorderLayout());

        // Create a text area for messages
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create a button to load data
        JButton loadButton = new JButton("Load Data");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadData.loadData("lib/newdata.arff"); // Adjust the path to your ARFF file
                    saveData.setNewData(loadData.getNewData()); // Set the loaded data into saveData
                    textArea.append("Data loaded and converted successfully!\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    textArea.append("Error loading data!\n");
                }
            }
        });

        // Create a button to save data
        JButton saveButton = new JButton("Save Data");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String outputFilePath = "lib/transferedData.arff"; 
                    // Adjust the path to your output ARFF file 
                    saveData.saveData(outputFilePath); 
                    textArea.append("Data saved successfully!\n"); 
                } catch (Exception ex) { 
                    ex.printStackTrace(); 
                    textArea.append("Error saving data!\n");
                }
            }
        });        

        // Create a button to run the J48 decision tree
        JButton runJ48Button = new JButton("Run J48 Decision Tree");
        runJ48Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loadData.getNewData() != null) {
                    try {
                        String result = j48DecisionTree.run(loadData.getNewData());
                        textArea.append(result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        textArea.append("Error running J48 Decision Tree!\n");
                    }
                } else {
                    textArea.append("Please load the data first!\n");
                }
            }
        });

        // Create a button to run the J48 decision tree
        JButton runJ48EvaButton = new JButton("J48 Decision Tree Evaluation");
        runJ48EvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loadData.getNewData() != null) {
                    try {
                        String result = j48DecisionTreeEva.run(loadData.getNewData());
                        textArea.append(result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        textArea.append("Error running J48 Decision Tree!\n");
                    }
                } else {
                    textArea.append("Please load the data first and run J48 Decision Tree Model! \n");
                }
            }
        });

        // Create a button to visualize J48 tree
        JButton J48visualizeButton = new JButton("Visualize J48");
        J48visualizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    j48TreeVisualizer.run(loadData.getNewData()); // Visualize the J48 tree
                    textArea.append("J48 Tree visualized successfully!\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    textArea.append("Error visualizing J48 tree!\n");
                }
            }
        });

        //  Create a button to run the Random Forest classifier
        JButton runRFButton = new JButton("Run Random Forest Classifier");
        runRFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loadData.getNewData() != null) {
                    try {
                        String result = randomForestClassifier.run(loadData.getNewData());
                        textArea.append(result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        textArea.append("Error running Random Forest Classifier!\n");
                    }
                } else {
                    textArea.append("Please load the data first!\n");
                }
            }
        });

        //  Create a button to run the Random Forest classifier
        JButton runRFEvaButton = new JButton("Random Forest Evaluation");
        runRFEvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loadData.getNewData() != null) {
                    try {
                        String result = randomForestEva.run(loadData.getNewData());
                        textArea.append(result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        textArea.append("Error running Random Forest Evaluation!\n");
                    }
                } else {
                    textArea.append("Please load the data first!\n");
                }
            }
        });

        //  Create a button to run the Naive Bayes classifier
        JButton runNBButton = new JButton("Run Naive Bayes Classifier");
        runNBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loadData.getNewData() != null) {
                    try {
                        String result = naiveBayess.run(loadData.getNewData());
                        textArea.append(result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        textArea.append("Error running Naive Bayes Classifier!\n");
                    }
                } else {
                    textArea.append("Please load the data first!\n");
                }
            }
        });

        //  Create a button to run the Naive Bayes Evaluation
        JButton runNBEvaButton = new JButton("Naive Bayes Evaluation");
        runNBEvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loadData.getNewData() != null) {
                    try {
                        String result = naiveBayesEva.run(loadData.getNewData());
                        textArea.append(result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        textArea.append("Error running Naive Bayes Evaluation!\n");
                    }
                } else {
                    textArea.append("Please load the data first!\n");
                }
            }
        });

        // Add buttons to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(runNBButton); //This one for Naive Bayes model
        buttonPanel.add(runNBEvaButton); //This one for Naive Bayes model
        buttonPanel.add(runJ48Button); //This one for Decision Tree (J48) model
        buttonPanel.add(J48visualizeButton); //This one for visualizing J48 tree
        buttonPanel.add(runJ48EvaButton); //This one for Decision Tree (J48) evaluation
        buttonPanel.add(runRFButton); //This one for Random forest model
        buttonPanel.add(runRFEvaButton); //This one for Random forest model
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            DataUI ex = new DataUI();
            ex.setVisible(true);
        });
    }
}
