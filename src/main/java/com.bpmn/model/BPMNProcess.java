package com.bpmn.model;

import java.util.List;

public class BPMNProcess {
    private String id;
    private String name;
    private List<BPMNElement> elements;

    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<BPMNElement> getElements() {
        return elements;
    }
    public void setElements(List<BPMNElement> elements) {
        this.elements = elements;
    }
}

public interface BPMNElement {
    void execute();
}

public class BPMNTask implements BPMNElement {
    private String id;
    private String name;
    
    public BPMNTask(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("Executing Task: " + name);
    }
}

public class BPMNGateway implements BPMNElement {
    private String id;
    private String name;

    public BPMNGateway(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("Executing Gateway: " + name);
    }
}

public class BPMNEvent implements BPMNElement {
    private String id;
    private String name;

    public BPMNEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("Triggering Event: " + name);
    }
}
