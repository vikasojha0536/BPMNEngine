package com.bpmn;

import com.bpmn.engine.BPMNEngine;
import com.bpmn.model.BPMNProcess;
import com.bpmn.parser.BPMNParser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        // Specify the path to your BPMN XML file
        String bpmnFilePath = "path_to_your_bpmn_file.bpmn";

        // Parse the BPMN file
        BPMNProcess process = BPMNParser.parseBPMN(bpmnFilePath);

        // Create the BPMN engine and execute the process
        BPMNEngine engine = new BPMNEngine(process);
        engine.execute();
    }
}
