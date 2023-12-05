package advent.of.code.year2022.day8

object TreeScoreCalculator {

    fun calculateTreeWithScores(treesWithNeighbours: Map<Tree, List<Tree>>): Map<Tree, Int> {

        return treesWithNeighbours
            .map { Pair(it.key, calculateTreeScore(it.key, it.value)) }
            .toMap()
    }

    private fun calculateTreeScore(tree: Tree, neighbours: List<Tree>): Int {
        return calculateTopScore(tree, neighbours) *
                calculateBottomScore(tree, neighbours) *
                calculateRightScore(tree, neighbours) *
                calculateLeftScore(tree, neighbours)
    }

    private fun calculateTopScore(tree: Tree, neighbours: List<Tree>): Int {
        return neighbours.findTopNeighbours(tree)
            .sortedByDescending { it.y }
            .calculateScore(tree)

    }

    private fun calculateBottomScore(tree: Tree, neighbours: List<Tree>): Int {
        return neighbours.findBottomNeighbours(tree)
            .sortedBy { it.y }
            .calculateScore(tree)

    }

    private fun calculateRightScore(tree: Tree, neighbours: List<Tree>): Int {
        return neighbours.findRightNeighbours(tree)
            .sortedBy { it.x }
            .calculateScore(tree)
    }

    private fun calculateLeftScore(tree: Tree, neighbours: List<Tree>): Int {
        return neighbours.findLeftNeighbours(tree)
            .sortedByDescending { it.x }
            .calculateScore(tree)
    }

}

fun <T> Iterable<T>.takeWhileInclusive(predicate: (T) -> Boolean) = sequence {
    with(iterator()) {
        while (hasNext()) {
            val next = next()
            yield(next)
            if (!predicate(next)) break
        }
    }
}

private fun List<Tree>.calculateScore(tree: Tree): Int {
    return this.takeWhileInclusive { it.height < tree.height }.count()
}
