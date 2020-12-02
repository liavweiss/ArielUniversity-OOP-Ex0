package ex0;

import java.lang.reflect.Array;
import java.util.*;


/**
 * This class implements the interface that represents the "regular" Graph Theory algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected();
 * 3. int shortestPathDist(int src, int dest);
 * 4. List<Node> shortestPath(int src, int dest);
 *
 * @author liav.weiss
 *
 */
public class Graph_Algo implements graph_algorithms {

    /**
     * @object graph - Represents the graph.
     */
    private graph graph;

    /**
     * default constructor.
     */
    public Graph_Algo(){
        this.graph=new Graph_DS();
    }

    /**
     * Init the graph on which this set of algorithms will operate .
     * @param g
     */
    @Override
    public void init(graph g) {
        this.graph=g;
    }

    /** deep copy constructor.
     * Implemented by a copy function in NodeData and Graph_DS classes.
     * @return
     */
    @Override
    public graph copy() {
       this.graph=new Graph_DS(this.graph);
       return this.graph;
    }

    /**
     * Returns true if and only if there is a valid path from every node to each other node.
     * Do it by checking if BFS function return the number of nodes in this graph .
     * (An explanation of BFS is given in the function itself).
     * @return
     */
    @Override
    public boolean isConnected() {
        if(this.graph.nodeSize()==0||this.graph.nodeSize()==1) {return true;}
        return( BFS(this.graph) == this.graph.nodeSize());
    }

    /**
     * returns the length of the shortest path between src to dest.
     * do it by checking if src and dest are exist and sending it to ansBFSshortestPath function.
     * (An explanation of ansBFSshortestPath is given in the function itself)
     * @param src - start node
     * @param dest - end (target) node
     * @return
     * run time:O(V+E) v=vertexes , E=edges
     */
    @Override
    public int shortestPathDist(int src, int dest) {
        if (this.graph.getNode(src) == null || this.graph.getNode(dest) == null) {
            return -1;
        }
        return ansBFSshortestPath(src , dest);
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     *First we initialize 2 arrays one of the node_data type (for the parent of each vertex)
     *and the other of the int type (for the distance from the initial vertex),
     *And 2 node_data lists.
     *Sent to the BFS(An explanation of BFS is given in the function itself) function,
     * if the graph is connected - we will add the node dest to the
     *temporary list and run on any array of fathers and add to the list each time the new
     *parent of the node that is now in the list.
     *finally we will return the list.
     * @param src - start node
     * @param dest - end (target) node
     * @return
     * run time:O(V+E) v=vertexes , E=edges
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if (this.graph.getNode(src) == null || this.graph.getNode(dest) == null) {
            return null;
        }
        node_data[] fathers = new node_data[this.graph.nodeSize()];
        int[] dist = new int[this.graph.nodeSize()];
        List <node_data> ThePath = new LinkedList<>();
        List <node_data> templist = new LinkedList<>();
        if(BFS(fathers , dist, this.graph.getNode(src), this.graph.getNode(dest))){
            node_data end = this.graph.getNode(dest);
            templist.add(end);
            while(fathers[end.getTag()]!=null){
                templist.add(fathers[end.getTag()]);
                end=fathers[end.getTag()];
            }
        }
        //to flip the order of the list
       for(int i=0;i<templist.size();i++){
            ThePath.add(templist.get(i));
        }
        return ThePath;
    }

    /**
     * First we get the first vertex of the graph and build a queue of type node_data We will initialize
     * a counter that will tell us how many vertices there are in the graph.
     * We will add the first vertex to the stack and change its meta_data to "black".
     * We will create a while loop, which will run as long as the queue is not empty,
     * and within it we will create a for loop, which will run on the neighbors of each vertex,
     * and if its meta_data is "white" it will change the meta_data to "black" and do counter ++
     * Finally we reset the meta_data and return the counter.
     * @param graph
     * @return
     * run time:O(V+E) v=vertexes , E=edges
     */
    private int BFS (graph graph){
        node_data node = this.graph.getV().iterator().next();
        Queue<node_data> QueueOfVertexes = new LinkedList<node_data>();
        int counter=1;
        QueueOfVertexes.add(node);
        node.setInfo("black");

        while(!QueueOfVertexes.isEmpty()){
            node_data temp = QueueOfVertexes.poll();
            Collection<node_data> neighbor = temp.getNi();
            for(node_data nodeNext : neighbor){
                if(nodeNext.getInfo().equals("white")) {
                    QueueOfVertexes.add(nodeNext);
                    nodeNext.setInfo("black");
                    counter++;
                }
            }
        }
        //for initialize the meta_data to white again.
        Collection<node_data> vertexes = graph.getV();
        for(node_data vertex : vertexes){
            vertex.setInfo("white");
        }
        return counter;
    }

    /**
     * First we initialize a false Boolean array and a dist array
     * In Max.Value
     * We will define a counterplace int that will be responsible for the location of each node in the fathers array.
     * we will add to the queue the src variable.
     * We will initialize src.tag to be 0 and the array fathers instead of src.tag to be equal to 0
     * We will perform a loop while as long as the queue is not empty we will perform:
     * Run on the neighbors of the same vertex that is in the queue, and is asked if the tag of the current neighbor == - 1, if so
     * We will update its tag to be counterplace and become counterplace ++.
     * We are then asked whether in the Boolean array its value is equal to false and if so we will change its value to true
     * And we will put it in the fathers array
     * By his tag.
     * And we will put in his dist array his distance from src.
     * If we get to vertex dest return true.
     * else will return false.
     * @param  fathers,dist,src,dest
     * @return
     * run time:O(V+E) v=vertexes , E=edges
     */
    private boolean BFS (node_data[] fathers , int [] dist , node_data src , node_data dest ) {
        boolean[] visit = new boolean[this.graph.nodeSize()];
        Queue<node_data> QueueOfVertexes = new LinkedList<node_data>();
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            visit[i]=false;
        }
        int counterPlace = 1;
        src.setTag(0);
        visit[src.getTag()] = true;
        dist[src.getTag()] = 0;
        QueueOfVertexes.add(src);
        while (!QueueOfVertexes.isEmpty()) {
            node_data father = QueueOfVertexes.poll();
            Collection<node_data> neighbor = father.getNi();
            for(node_data nodeNext : neighbor){
               if(nodeNext.getTag()==-1) {
                   nodeNext.setTag(counterPlace);
                   counterPlace++;
               }
                if(visit[nodeNext.getTag()]==false){
                    visit[nodeNext.getTag()]=true;
                    fathers[nodeNext.getTag()]=father;
                    QueueOfVertexes.add(nodeNext);
                    dist[nodeNext.getTag()]=dist[father.getTag()]+1;
                }

                if(nodeNext.getKey()==dest.getKey()) {return true;}
            }

        }
        return false;
    }
   /**
    * First we initialize two arrays one of the type node_data (for the parent of each vertex)
    * And the other int (from the initial vertex), sent to BFS and return the array of fathers instead of the dest.getTag.
    * @param src,dest
    * @return
    * run time:O(V+E) v=vertexes , E=edges
    */
    private int ansBFSshortestPath(int src , int dest){
        node_data[] fathers = new node_data[this.graph.nodeSize()];
        int[] dist = new int[this.graph.nodeSize()];
        int ans=-1;
        if(BFS(fathers, dist, this.graph.getNode(src), this.graph.getNode(dest))){
            ans = dist[this.graph.getNode(dest).getTag()];
        }
        ////for initialize the tag to white again.
        Collection<node_data> vertexes = graph.getV();
        for(node_data vertex : vertexes){
            vertex.setTag(-1);
        }
            return ans;
        }

    }
