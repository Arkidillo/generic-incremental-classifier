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
            String filename = null;
            String oldFilename = null;
            Label newLabel;

            for (CSVRecord record : records) {
                newLabel = new Label();
                int x = Integer.parseInt(record.get(0));
                int y = Integer.parseInt(record.get(1));
                int width = Integer.parseInt(record.get(2));
                int height = Integer.parseInt(record.get(3));

                filename = record.get(4);
                // Happens for the first file
                if (oldFilename == null) {
                    oldFilename = filename;
                }

                newLabel.setTop(y);
                newLabel.setLeft(x);
                newLabel.setBottom(y + height);
                newLabel.setRight(x + width);

                newLabel.setPlacedByModel(true);

                if (!filename.equals(oldFilename)){
                    labels.put(oldFilename, thisImageLabels);
                    oldFilename = filename;
                    thisImageLabels = new ArrayList<>();
                }

                thisImageLabels.add(newLabel);
            }

            if (filename == null) {
                System.err.println("No files found in csv");
                System.exit(1);
            }
            // For the last image
            labels.put(filename, thisImageLabels);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return labels;
    }
}
