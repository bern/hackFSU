package creator.end.api;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Class for containing a "piece of knowledge". Contains dependencies and
 * tutorial for this piece of knowledge.
 * @author coriakin
 *
 */
public class KnowledgeNode {

	/**
	 * The name of the "piece of knowledge"
	 */
	private String name;
	
	/**
	 * Unique id for the node. Primary key for database.
	 */
	private int id;
	
	/**
	 * The actual description. Secretly html.
	 */
	private String body;
	
	/**
	 * The things you need to know to know this.
	 */
	private Map<Integer,KnowledgeNode> dependencies = new HashMap<Integer,KnowledgeNode>();
	
	/**
	 * A brief description of why you need to know a given dependency.
	 */
	private Map<Integer,String> dependencyDescription = new HashMap<Integer,String>();
	
	/**
	 * Represents whether the user "knows" this topic. Defaults to true.
	 */
	boolean understood=true;

	public int getId(){
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns a list of the immediate dependencies of this knowledge node.
	 * @return
	 */
	public List<KnowledgeNode> getDependencies(){
		Set<Entry<Integer,KnowledgeNode>> dSet = dependencies.entrySet();
	    List<KnowledgeNode> dependencyList = new ArrayList<KnowledgeNode>();
	    for(Entry<Integer,KnowledgeNode> entry : dSet){
	    	dependencyList.add(entry.getValue());
	    }
	    return dependencyList;
	}
	
	public void addDependency(KnowledgeNode node){
		dependencies.put(node.getId(), node);
	}
	
	public void removeDependency(KnowledgeNode node){
		dependencies.remove(node);
	}
	
	public void addDescription(KnowledgeNode node,String description){
		Integer key = node.getId();
		dependencyDescription.put(key, description);
	}
	
	public String getDescription(KnowledgeNode node){
		String description = dependencyDescription.get(node);
		return description;
	}
	
	public void markUnderstood(){
		understood = true;
	}
	
	public void markNotUnderstood(){
		understood =  false;
	}
	
	public boolean isUnderstood(){
		return understood;
	}
}
