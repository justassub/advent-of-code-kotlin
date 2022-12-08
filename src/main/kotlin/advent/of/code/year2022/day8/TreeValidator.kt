package advent.of.code.year2022.day8


object TreeValidator {
    fun getVisibleTrees(treesWithNeighbours: Map<Tree, List<Tree>>): Set<Tree> {
        return treesWithNeighbours.filter { isInternalTreeVisible(it) }.keys
    }

    private fun isInternalTreeVisible(entry: Map.Entry<Tree, List<Tree>>): Boolean {
        val tree = entry.key
        val neighbours = entry.value


        return isTopVisible(tree, neighbours) ||
                isBottomVisible(tree, neighbours) ||
                isRightVisible(tree, neighbours) ||
                isLeftVisible(tree, neighbours)
    }

    private fun isTopVisible(tree: Tree, neighbours: List<Tree>): Boolean {
        return !neighbours.findTopNeighbours(tree).any { it.height >= tree.height }
    }

    private fun isBottomVisible(tree: Tree, neighbours: List<Tree>): Boolean {
        return !neighbours.findBottomNeighbours(tree).any { it.height >= tree.height }
    }

    private fun isRightVisible(tree: Tree, neighbours: List<Tree>): Boolean {
        return !neighbours.findRightNeighbours(tree).any { it.height >= tree.height }
    }

    private fun isLeftVisible(tree: Tree, neighbours: List<Tree>): Boolean {
        return !neighbours.findLeftNeighbours(tree).any { it.height >= tree.height }
    }

}
