package creator.end.api;

import java.util.HashMap;
import java.util.Map;

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
	String name;
	
	/**
	 * Unique id for the node. Primary key for database.
	 */
	int id;
	
	/**
	 * The actual description. Secretly html.
	 */
	String body;
	
	/**
	 * The things you need to know to know this.
	 */
	Map<Integer,KnowledgeNode> dependencies = new HashMap<Integer,KnowledgeNode>();
	
	
	
}
