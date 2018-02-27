package logic;

import com.google.gson.Gson;
import gui.Label;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GsonHandler {
    private static final String FILE_SUFFIX = "_labels.json";

    public GsonHandler(HashMap<Integer, ArrayList<Label>> allLabels) {
        // For each image/ entry in hashmap, save a JSON
        Iterator it = allLabels.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ArrayList<Label> labels = (ArrayList<Label>) (pair.getValue());

            Gson gson = new Gson();
            String jsonString = gson.toJson(labels);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(i + FILE_SUFFIX));
                writer.write(jsonString);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
