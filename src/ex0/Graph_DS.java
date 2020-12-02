package ex0;

import java.util.Collection;
import java.util.HashMap;


/**
 * This class implements the interface that represents an undirectional unweighted graph.
 * It support a large number of nodes.
 * The implementation based on an efficient compact representation.
 */
public class Graph_DS implements graph{

    /**
     * @object graph - Represents the list of node_data (the vertexes).
     * @object node_size - Represents the number of nodes in the graph.
     * @object edge_size - Represents the number of edges in the graph.
     * @object mc - Represents the number of changes in the graph.
     */
    private HashMap<Integer, node_data> graph;
    private int node_size;
    private int edge_size;
    private int mc;


    /**
     * default constructor.
     */
    public Graph_DS(){
        this.graph=new HashMap<Integer, node_data>();
        this.node_size=0;
        this.edge_size=0;
    }


    /** deep copy constructor
     * This function takes the list(collection) of nodes and their
     * neighbors and Go through it with forEach and copy a deep copy.
     * @param graph
     */
    public Graph_DS(graph graph){
        this.graph=new HashMap<Integer,node_data>();

        for(node_data node : graph.getV()){
            this.graph.put(node.getKey(),new NodeData(node));
        }
        Collection<node_data> theVertex = graph.getV();
        for(node_data node:theVertex){
            Collection<node_data> theNeighbor = graph.getV(node.getKey());
            for(node_data neighbor : theNeighbor){
                this.connect(node.getKey(),neighbor.getKey());
            }
        }
        this.node_size=graph.nodeSize();
        this.edge_size=graph.edgeSize();
    }


    /**
     * return the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        if(graph.containsKey(key)) { return graph.get(key);}
       return null;
    }

    /**
     * return true if and only if there is an edge between node1 and node2.
     * @param node1
     * @param node2
     * @return
     * run time: O(1).
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        return (getNode(node1).hasNi(node2));
    }

    /**
     * add a new node to the graph with the given node_data.
     * Checks if the node is not null and if not it adds it.
     * @param n
     * run time: O(1).
     */
    @Override
    public void addNode(node_data n) {
        if(n==null){ return;}
        this.graph.put(n.getKey() , n);
        this.node_size++;
        this.mc++;
    }

    /**
     * Connect an edge between node1 and node2.
     * checks if node1==node2 and if the edge node1-node2 not exists it is add
     * to each node the other node to his neighbor list.
     * else the function simply does nothing.
     * run time: O(1).
     */
    @Override
    public void connect(int node1, int node2) {
        if(!hasEdge(node1,node2) && node1!=node2) {
            getNode(node1).addNi(getNode(node2));
            getNode(node2).addNi(getNode(node1));

            this.edge_size++;
            this.mc++;
        }
        else{return;}
    }

    /**
     * This method return a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * @return Collection<node_data>
     * run time:O(1).
     */
    @Override
    public Collection<node_data> getV() {
        return this.graph.values();
    }

    /**
     * This method return a collection of  the
     * collection representing all the nodes connected to node_id.
     * @return Collection<node_data>
     * run time:O(1).
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        return getNode(node_id).getNi();
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * By going through the list of neighbors of the node and
     * deleting the node from the list of neighbors of the neighbor.
     * @return the data of the removed node (null if none).
     * @param key
     * run time:O(n), |V|=n.
     */
    @Override
    public node_data removeNode(int key) {
      if(!graph.containsKey(key)){
          return null;
      }
        node_data n = getNode(key);
        if (!n.getNi().isEmpty()) {
            for (node_data ni : n.getNi()) {
                ni.removeNode(n);
                this.edge_size--;
                this.mc++;
            }
        }
        graph.remove(key);
        this.mc++;
        this.node_size--;
      return n;
    }

    /**
     * Delete the edge from the graph by checking if
     * there is no edge between them and if there is, remove.
     * @param node1
     * @param node2
     * run time:O(1).
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if(!hasEdge(node1, node2)) {return;}
        getNode(node1).removeNode(getNode(node2));
        getNode(node2).removeNode(getNode(node1));

       this.edge_size--;
        this.mc++;
    }

    /**
     * return the number of vertices (nodes) in the graph.
     * @return
     * run time:O(1).
     */
    @Override
    public int nodeSize() {
        return node_size;
    }

    /**
     * return the number of edges (undirectional graph).
     * @return
     * run time:O(1).
     */
    @Override
    public int edgeSize() {
        return edge_size;
    }

    /**
     * return the Mode Count (mc)- for testing changes in the graph.
     * Any change in the inner state of the graph will cause an increment in the ModeCount
     * @return
     */
    @Override
    public int getMC() {
        return mc;
    }

    /**
     *  toString function
     * @return
     */
    @Override
    public String toString() {
        String s="";
        s+= "Graph_DS{" +
                "graph=" + this.graph + '}';
        return s;
    }

}

