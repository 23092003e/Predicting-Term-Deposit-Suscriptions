package src;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

public class LoadData {
    protected Instances newData;

    public void loadData(String datasetPath) throws Exception {
        DataSource source = new DataSource(datasetPath);
        Instances data = source.getDataSet();

        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }

        NumericToNominal convert = new NumericToNominal();
        convert.setAttributeIndices("" + (data.classIndex() + 1));
        convert.setInputFormat(data);
        newData = Filter.useFilter(data, convert);
    }

    public Instances getNewData() {
        return newData;
    }
}
