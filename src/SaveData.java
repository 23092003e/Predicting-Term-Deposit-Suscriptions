package src;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import java.io.File;

public class SaveData {

    private Instances newData;

    public void setNewData(Instances data) {
        this.newData = data;
    }

    public void saveData(String outputFilePath) throws Exception {
        // Ensure that newData is not null
        if (newData == null) {
            throw new IllegalStateException("Data has not been loaded and processed yet.");
        }

        // Create an ArffSaver object
        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);

        // Set the destination file (output ARFF file)
        saver.setFile(new File(outputFilePath));
        saver.writeBatch();
    }
}
