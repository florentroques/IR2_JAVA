package fr.upem.java_avance.td4.queue;

public class Tree {
	private final int id;
	private final Tree left; // nullable
	private final Tree right; // nullable

	public Tree(int id) {
		this(id, null, null);
	}

	public Tree(int id, Tree left, Tree right) {
		this.id = id;
		this.left = left;
		this.right = right;
	}

	public int getId() {
		return id;
	}

	public Tree getLeft() {
		return left;
	}

	public Tree getRight() {
		return right;
	}

	@Override
	public String toString() {
		return toString(this, new StringBuilder()).toString();
	}

	private static StringBuilder toString(Tree tree, StringBuilder builder) {
		if (tree == null)
			return builder.append("!");
		return toString(
				tree.right,
				toString(tree.left, builder.append(tree.id).append('('))
						.append(", ")).append(')');
	}
}