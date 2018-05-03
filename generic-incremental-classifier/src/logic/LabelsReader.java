package logic;

import gui.Label;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

public class LabelsReader {
    public static HashMap<String, ArrayList<Label>> readLabels() {
        HashMap<String, ArrayList<Label>> labels = new HashMap<>();

        try {
            Reader in = new FileReader("./src/svm_classifier/labels.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            // All the labels for 1 image
            ArrayList<Label> thisImageLabels = new ArrayList<>();
            // Keeps track of the last images filename to see if it has changed
            String oldFilename = null;

            for (CSVRecord record : records) {
                Label newLabel = new Label();
                int x = Integer.parseInt(record.get(0));
                int y = Integer.parseInt(record.get(1));
                int width = Integer.parseInt(record.get(2));
                int height = Integer.parseInt(record.get(3));

                String filename = record.get(4);
                // Happens for the first file
                if (oldFilename == null) {
                    oldFilename = filename;
                }

                newLabel.setTop(y + height);
                newLabel.setBottom(y);
                newLabel.setRight(x + width);
                newLabel.setLeft(x);

                if (filename != oldFilename){
                    labels.put(filename, thisImageLabels);
                    thisImageLabels = new ArrayList<>();
                }

                thisImageLabels.add(newLabel);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return labels;
    }
}
