
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a simple graph. In the costs matrix the edges possibility is already included.
 * @author jc161
 *
 */
public class Graph{
		/**
		 * Vertex
		 */
		public boolean [][] adj;
		/**
		 * New graph.<br>
		 * @param v Vertex
		 * @param c Matrix of costs
		 */
		@SuppressWarnings("unchecked")
		public Graph(boolean[][]c)
		{
			adj=c;
			
		}
		
		
	}