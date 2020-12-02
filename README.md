#introduction:
My project deals with unweighted graph, in this project there are three classes that apply an unweighted graph. I would like to explain about each class written in this project.

###NodeData class:
This class implements the interface that represents the set of operations applicable on a
 . node (vertex) in an unweighted graph
This class creates a node, this node has neighbors we are implement this with a HashMap data structure <Integer,node_data>.
Each node has three variables:
His serial number – key.
Temporary variable – tag.
Node information – meta_data.
In this class we have two constructors and ten functions.
You can get the key and tag and the information of the node with a simple function.
You also can get the node's neighbor list or check if there is neighbor at all (this implement with HashMap function).
You can also add a node and remove it (it is also implemented by a HashMap function).
And finally toString function.

###Graph_DS class:
This class implements the interface that represents an unweighted graph.
This class creates a graph implemented by HashMap <Integer,node_data> , and three variables :
The size of the graph – node_zise.
The size of the edge in the graph – edge_size.
The number of the changes in the graph – mc.
In this class we have two constructors and twelve functions.
You can get the node size and the edge size and the mc with a simple function.
You can also get the node by his key its work with simple HashMap function, and also get a collection of the node's neighbor and the nodes in the graph (also work with simple HashMap function).
In this class you can add node to the graph and connected him to another node in the graph and if there is an edge the function does nothing.
Another function is removing node from the graph and this is explanation hoe its work:
going through the list of neighbors of the node and deleting the node from the list of neighbors of the neighbor.
Also, we have the remove edge function its work by checking if there is no edge between two nodes and if there is, remove the edge.
And finally, toString function.

###Graph_Algo class:
This class implements the interface that represents the "regular" Graph Theory algorithms including:
. clone(); (copy)
init(graph);
isConnected;()
int shortestPathDist(int src, int dest);
List<Node> shortestPath(int src, int dest);
In this class we have one constructor, five functions and three private functions.
I explain each function:
Init(graph graph) - Init the graph on which this set of algorithms will operate.
Copy()  - deep copy constructor Implemented by a copy function in NodeData and Graph_DS classes.
isConnect() - Returns true if and only if there is a valid path from every node to each other node Do it by checking if BFS function return the number of nodes in this graph.
shortestPathDist(int src, int dest) - returns the length of the shortest path between src to dest do it by checking if src and dest are exist and sending it to ansBFSshortestPath function.
List<Node> shortestPath(int src, int dest) - * returns the the shortest path between src to dest - as an ordered List of nodes:
 src--> n1-->n2-->...dest
First we initialize 2 arrays one of the node_data type (for the parent of each vertex)
and the other of the int type (for the distance from the initial vertex),
And 2 node_data lists.
Sent to the BFS(An explanation of BFS is given in the function itself) function,
 if the graph is connected - we will add the node dest to the
temporary list and run on any array of fathers and add to the list each time the new
parent of the node that is now in the list.
finally we will return the list.

parent of the node that is now in the list . finally we will return the list.
BFS (graph graph) -  First we get the first vertex of the graph and build a queue of type node_data We will initialize
 a counter that will tell us how many vertices there are in the graph.
 We will add the first vertex to the stack and change its meta_data to "black".
 We will create a while loop, which will run as long as the queue is not empty,
 and within it we will create a for loop, which will run on the neighbors of each vertex,
 and if its meta_data is "white" it will change the meta_data to "black" and do counter ++
 Finally we reset the meta_data and return the counter.
BFS (node_data[] fathers , int [] dist , node_data src , node_data dest ) - * First we initialize a false Boolean array and a dist array
In Max.Value
 We will define a counterplace int that will be responsible for the location of each node in the fathers array.
 we will add to the queue the src variable.
 We will initialize src.tag to be 0 and the array fathers instead of src.tag to be equal to 0* We will perform a loop while as long as the queue is not empty we will perform:* Run on the neighbors of the same vertex that is in the queue, and is asked if the tag of the current neighbor == - 1, if so
 We will update its tag to be counterplace and become counterplace ++.
 We are then asked whether in the Boolean array its value is equal to false and if so we will change its value to true And we will put it in the fathers array
By his tag. And we will put in his dist array his distance from src.
If we get to vertex dest return true.
else will return false.
ansBFSshortestPath(int src , int dest) -  First we initialize two arrays one of the type node_data (for the parent of each vertex)
 And the other int (from the initial vertex), sent to BFS and return the array of fathers instead of the dest.getTag.

This is all my project
liav.weiss




 











