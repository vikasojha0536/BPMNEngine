package com.bpmn.parser;

import com.bpmn.model.BPMNElement;
import com.bpmn.model.BPMNEvent;
import com.bpmn.model.BPMNGateway;
import com.bpmn.model.BPMNProcess;
import com.bpmn.model.BPMNTask;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BPMNParser {

    public static BPMNProcess parseBPMN(String bpmnFilePath) throws Exception {
        File file = new File(bpmnFilePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList processList = document.getElementsByTagName("bpmn2:process");
        Node processNode = processList.item(0); // Assuming there is only one process in the file
        Element processElement = (Element) processNode;

        BPMNProcess process = new BPMNProcess();
        process.setId(processElement.getAttribute("id"));
        process.setName(processElement.getAttribute("name"));

        NodeList elementNodes = processElement.getChildNodes();
        List<BPMNElement> elements = new ArrayList<>();
        
        for (int i = 0; i < elementNodes.getLength(); i++) {
            Node node = elementNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                BPMNElement bpmnElement = null;

                // Check for Task
                if (element.getTagName().equals("bpmn2:userTask")) {
                    bpmnElement = new BPMNTask(element.getAttribute("id"), element.getAttribute("name"));
                }
                // Check for Gateway
                else if (element.getTagName().equals("bpmn2:exclusiveGateway")) {
                    bpmnElement = new BPMNGateway(element.getAttribute("id"), element.getAttribute("name"));
                }
                // Check for Event
                else if (element.getTagName().equals("bpmn2:startEvent") || element.getTagName().equals("bpmn2:endEvent")) {
                    bpmnElement = new BPMNEvent(element.getAttribute("id"), element.getAttribute("name"));
                }

                if (bpmnElement != null) {
                    elements.add(bpmnElement);
                }
            }
        }

        process.setElements(elements);
        return process;
    }
}
