package com.bpmn.engine;

import com.bpmn.model.BPMNElement;
import com.bpmn.model.BPMNProcess;

import java.util.Iterator;

public class BPMNEngine {

    private BPMNProcess process;

    public BPMNEngine(BPMNProcess process) {
        this.process = process;
    }

    public void execute() {
        System.out.println("Starting Process: " + process.getName());

        Iterator<BPMNElement> iterator = process.getElements().iterator();
        while (iterator.hasNext()) {
            BPMNElement element = iterator.next();
            element.execute(); // Execute each element in the process
        }

        System.out.println("Process " + process.getName() + " completed.");
    }
}
