package creator.end.parsing;

import java.util.ArrayList;
import java.util.List;

import creator.end.api.KnowledgeNode;

public class KnowledgeNodeBeanPicker {

	String dataDirectory;
	
	/**
	 * Will return a list of all the knowledge nodes in the
	 * database. The knowledge nodes returned will be
	 * partial knowledge nodes! They will all appear as
	 * leaves. To get a full knowledge node, use the
	 * method populateNode(KnowledgeNode node)
	 */
	public List<KnowledgeNode> getAllKnowledgeNodes(){
		List<KnowledgeNode> allNodes = new ArrayList<KnowledgeNode>();
		
		//fun stuff...
		return allNodes;
	}
	
	public KnowledgeNode getKnowledgeNodeAsLeaf(int id){
		//TODO
		return null;
	}
	
}
