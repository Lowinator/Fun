/* weighted quick union with path compression (WQUPC)
 * 
 * worst case runtime: N + M * lg* N
 * (M union-find operations on a set of N objects)
 * */
public class WQuickUnionPC {
	
	private int[] id;	// id[i] is parent of i
	private int[] weight;	// stores the size of a component (number of elements)
	
	/**
	 * Creates a new Union-Find Data structure with n components.
	 * @param n amount of components in the beginning.
	 */
	public WQuickUnionPC(int n) {
		id = new int[n];
		weight = new int[n];
		
		for (int i = 0; i < n; i++) {
			id[i] = i;		
			weight[i] = 1;	// all components have a size of 1 in the beginning
		}
	}
	
	/** 
	 * Returns the component identifier of a given site {@code a}
	 * 
	 * @param a element whose root we are looking for
	 * @return the root of a
	 */
	private int find(int a) {
		int root = a;
		while (root != id[root]) {
			root = id[root];
		}
		
		int aNew;			// path compression happens here
		while (a != root) {
			aNew = id[a];
			id[a] = root;
			a = aNew;
		}
		
		return root;		
	}
	
	/**
	 * Checks if two given sites are connected.
	 * 
	 * @param a int representing one site
	 * @param b int representing other site
	 * @return {@code true} if the sites are part of the same component, else {@code false}
	 */
	public boolean connected(int a, int b) {
		return (find(a) == find(b));
	}

	/**
	 * Merges the components of two given sites a and b.
	 * 
	 * @param a first site
	 * @param b second site
	 */
	public void union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		
		if (weight[a] < weight[b]) {
			id[aroot] = broot;
			weight[b] += weight[a];
		} else {
			id[broot] = aroot;
			weight[a] += weight[b];
		}
	
	}
	
	public static void main(String[] args) {
		
		WQuickUnionPC test = new WQuickUnionPC(10);
		
		test.union(1,2);
		
		test.union(3,4);
		
		test.union(5,6);
		
		test.union(1, 4);
		
		if (test.connected(1,3))
			System.out.println("The sites 1 and 3 are connected!");
		
		if (test.connected(3,4))
			System.out.println("The sites 3 and 4 are connected!");		
		
		if (test.connected(5,5))
			System.out.println("The sites 5 and 5 are connected!");		
		
		if (test.connected(5,6))
			System.out.println("The sites 5 and 6 are connected!");		
		
		if (test.connected(5,7))
			System.out.println("The sites 5 and 7 are connected!");	

	}

}
