def bfs_expanded_nodes(branching_factor, depth):
    """Calculates the number of nodes expanded by Breadth-First Search (BFS)."""
    return sum(branching_factor**i for i in range(depth + 1))

def ids_expanded_nodes(branching_factor, depth):
    """Calculates the number of nodes expanded by Iterative Deepening Search (IDS)."""
    return sum(bfs_expanded_nodes(branching_factor, d) for d in range(depth + 1))

# Given problems
scenarios = [
    {"branching_factor": 2, "depth": 10},  # BFS & IDS for b = 2, depth = 10
    {"branching_factor": 10, "depth": 7}   # BFS & IDS for b = 10, depth = 7
]

# Compute results and print
for scenario in scenarios:
    b = scenario["branching_factor"]
    d = scenario["depth"]
    bfs_nodes = bfs_expanded_nodes(b, d)
    ids_nodes = ids_expanded_nodes(b, d)
    
    print(f"BFS (b={b}, d={d}): {bfs_nodes}")
    print(f"IDS (b={b}, d={d}): {ids_nodes}")
