package creator.end.parsing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import creator.end.api.KnowledgeNode;
import creator.end.api.NodeIndex;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class KnowledgeNodeBeanPicker {
	
	public static final String ELNAME_NAME = "name";
	public static final String ELNAME_ID = "id";
	public static final String ELNAME_DEPENDENCIES = "dependencies";
	public static final String ELNAME_DEPENDENCY = "dependency";
	public static final String ELNAME_DEPENDENCYID = "id";
	public static final String ELNAME_DESCRIPTION = "description";
	public static final String ELNAME_BODY = "body";

	String dataDirectory;
	NodeIndex nodeIndex = null;
	
	/**
	 * Will return a list of all the knowledge nodes in the
	 * database. The knowledge nodes returned will be
	 * partial knowledge nodes! They will all appear as
	 * leaves. To get a full knowledge node, use the
	 * method populateNode(KnowledgeNode node)
	 */
	public List<KnowledgeNode> getAllKnowledgeNodes(){
		List<KnowledgeNode> allNodes = new ArrayList<KnowledgeNode>();
		
		initializeNodeIndex(false);
		
		
		List<Integer> allIds = nodeIndex.getAllIds();
		for(int id : allIds){
			allNodes.add(getKnowledgeNodeAsLeaf(id));
		}
		
		//fun stuff...
		return allNodes;
	}
	
	private void initializeNodeIndex(boolean update) {
		if((!update) && (nodeIndex!=null)){
			return;
		}
		NodeIndexBeanPicker niBeanPicker = new NodeIndexBeanPicker();
		
		niBeanPicker.setDataDirectory(dataDirectory+"/nodeIndex.xml");
		niBeanPicker.parseDataDirectory();
		nodeIndex = niBeanPicker.getNodeIndex();
	}
	
	private void validateNodeIndexExists(){
		if(nodeIndex==null){
			throw new RuntimeException("NodeIndex has not been initialized yet!");
		}
	}

	public KnowledgeNode getKnowledgeNodeAsLeaf(int id){
		validateNodeIndexExists();
		
		String filename = nodeIndex.getFilename(id);
		String filepath = nodeIndex.getURL()+"/"+filename;
		
		return getKnowledgeNodeAsLeaf(filename);
	}

	private KnowledgeNode getKnowledgeNodeAsLeaf(String filename) {
		try{
			//get the doc loaded up. Overhead, overhead...
			File fXmlFile = new File(dataDirectory+"/"+filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			Element rootElement = doc.getDocumentElement();
			
			//The thing that will be returned.
			KnowledgeNode knowledgeNode = new KnowledgeNode();
			
			String name = getNameFromRootNode(rootElement);
			int id = getIdFromRootNode(rootElement);
			String body = getBodyFromRootNode(rootElement);
			
			knowledgeNode.setName(name);
			knowledgeNode.setId(id);
			knowledgeNode.setBody(body);
			loadDependencies(rootElement,knowledgeNode);
			
			return knowledgeNode;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not parse knowledgeNode.",e);
		} catch (SAXException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not parse knowledgeNode.",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not parse knowledgeNode.",e);
		}
	}

	private void loadDependencies(Element rootElement,
			KnowledgeNode knowledgeNode) {
		Node dependenciesNode = rootElement.getElementsByTagName(ELNAME_DEPENDENCIES).item(0);
		if(dependenciesNode==null){return;}
		NodeList dependencyNodes = ((Element)dependenciesNode).getElementsByTagName(ELNAME_DEPENDENCY);
		if(dependencyNodes==null){return;}
		int numDependencies = dependencyNodes.getLength();

		Node dependencyNode;
		for(int i=0;i<numDependencies;i++){
			dependencyNode = dependencyNodes.item(i);
			loadDependencyNode(dependencyNode,knowledgeNode);
		}
	}

	private void loadDependencyNode(Node dependencyNode,
			KnowledgeNode knowledgeNode) {
		KnowledgeNode dependencyKnowledgeNode = new KnowledgeNode();
		String sId = ((Element)dependencyNode).getElementsByTagName(ELNAME_DEPENDENCYID).item(0).getTextContent();
		int id = Integer.parseInt(sId);
		String description = ((Element)dependencyNode).getElementsByTagName(ELNAME_DESCRIPTION).item(0).getTextContent();
	
		dependencyKnowledgeNode.setId(id);
		knowledgeNode.addDescription(dependencyKnowledgeNode, description);
		knowledgeNode.addDependency(dependencyKnowledgeNode);
	}
	
	/**
	 * Takes a "stub" knowledgeNode with only empty children
	 * and turns it into a fully populated directed graph.
	 * @param node
	 */
	public void populateNode(KnowledgeNode node){
		List<KnowledgeNode> allNodesList = this.getAllKnowledgeNodes();
		Map<Integer,KnowledgeNode> idToCompleteKnowledgeNodes = createIdsToCompleteKnowledgeNodes(allNodesList);
		
		//get the dependencies of the knowledgeNode
		List<KnowledgeNode> dependencies = node.getDependencies();
		node.removeAllDependencies();
		for(KnowledgeNode dependencyNode : dependencies){
			KnowledgeNode completeKnowledgeNode = idToCompleteKnowledgeNodes.get(dependencyNode.getId());
			populateNode(completeKnowledgeNode);
			node.addDependency(completeKnowledgeNode);
		}
		
	}

	private Map<Integer, KnowledgeNode> createIdsToCompleteKnowledgeNodes(
			List<KnowledgeNode> allNodesList) {
		Map<Integer,KnowledgeNode> idsToCompleteKnowledgeNodes = new HashMap<Integer,KnowledgeNode>();
		for(KnowledgeNode knowledgeNode : allNodesList){
			idsToCompleteKnowledgeNodes.put(knowledgeNode.getId(),knowledgeNode);
		}
		return idsToCompleteKnowledgeNodes;
	}

	private String getBodyFromRootNode(Element rootElement) {
		return rootElement.getElementsByTagName(ELNAME_BODY).item(0).getTextContent();
	}

	private String getNameFromRootNode(Element rootElement) {
		return rootElement.getElementsByTagName(ELNAME_NAME).item(0).getTextContent();
	}
	
	private int getIdFromRootNode(Element rootElement){
		String sId = rootElement.getElementsByTagName(ELNAME_ID).item(0).getTextContent();
		return Integer.parseInt(sId);
	}

	public String getDataDirectory() {
		return dataDirectory;
	}

	public void setDataDirectory(String dataDirectory) {
		this.dataDirectory = dataDirectory;
	}
	
}
