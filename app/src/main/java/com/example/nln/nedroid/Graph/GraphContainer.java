package com.example.nln.nedroid.Graph;

import java.util.Map;

/**
 * Created by AHSAN on 6/14/2017.
 */

public class GraphContainer {
    Map<String, String> values;

    public GraphContainer(Map<String, String> values) {
        this.values = values;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}
