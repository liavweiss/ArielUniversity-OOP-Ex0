package ex0;

import java.util.Collection;

import java.util.HashMap;

/**
 * This class implements the interface that represents the set of operations applicable on a
 * node (vertex) in an (undirectional) unweighted graph.
 * @author liav.weiss
 *
 */
public class NodeData implements node_data{

    /**
     * @object key - Represents the serial number of each node.
     * @object neighbor - Represents the list of neighbors of each node.
     * @object meta_data - Represents the information of each node.
     * @object tag - Represents the tag of each node.
     */

    private int key;
    private HashMap<Integer,node_data> neighbor;
    private String meta_data;
    private int tag;
    private static int counter=0;


    /**
     * default constructor
     */
    public NodeData(){
        this.key=counter++;
        this.neighbor=new HashMap<Integer,node_data>();
        this.meta_data="white";
        this.tag=-1;
    }

    /** deep copy constructor
     *
     * @param node
     */
    public NodeData(node_data node){
        this.key=node.getKey();
        this.neighbor=new HashMap<Integer,node_data>();
        this.meta_data=new String(node.getInfo());
        this.tag=node.getTag();
    }

    /**
     * Return the key (the serial number of this node).
     * @return
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * This method returns a collection with all the Neighbor nodes of this node_data
     */
    @Override
    public Collection<node_data> getNi() {
        return neighbor.values();
    }

    /**
     * return true if key is on the list of neighbors of this node.
     * @param key
     * @return
     */
    @Override
    public boolean hasNi(int key) {
        return this.neighbor.containsKey(key);
    }

    /**
     * This method adds the node_data (t) to this node_data.
     * checking if this node==@param t and if not adds it to his neighbors list.
     */
    @Override
    public void addNi(node_data t) {
        if(this.key==t.getKey()){return;}
        this.neighbor.put(t.getKey(),t);
    }

    /**
     * Removes the edge this-key,
     * @param node
     * checking if this node exist in the neighbor list and remove from the list.
     */
    @Override
    public void removeNode(node_data node) {
        if(this.neighbor.containsKey(node.getKey())) {
            this.neighbor.remove(node.getKey());
        }
        else {
            throw new RuntimeException("this collection does not contains this node");
        }
    }
    /**
     * return the remark (meta data) associated with this node.
     * @return
     */
    @Override
    public String getInfo() {
        return this.meta_data;
    }
    /**
     * A function that allows changing the remark (meta data) associated with this node.
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.meta_data=s;
    }
    /**
     *Temporary data on each node.
     * @return
     */
    @Override
    public int getTag() {
        return this.tag;
    }
    /**
     * A function that allows you to temporarily mark each node.
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag=t;
    }

    /** toString function that string each node with his neighbor.
     *
     * @return
     */
    @Override
    public String toString() {
        String s ="";
        int counter=1;
        s+= "NodeData{" +
                "key=" + this.key +
                ", meta_data='" + this.meta_data + '\'' +
                ", tag=" + this.tag;
        for(node_data n :this.neighbor.values()){
            s+=", niehbor"+counter+":"+ "key=" + n.getKey() +
                    ", meta_data='" + n.getInfo() + '\'' +
                    ", tag=" + n.getTag();
            counter++;
        }
        s+= '}';
        return s;
    }

}


