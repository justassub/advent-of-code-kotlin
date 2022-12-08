package advent.of.code.year2022.day8

data class Tree(val x: Int, val y: Int, val height: Int)


fun List<Tree>.findTopNeighbours(tree: Tree): List<Tree> {
    return this.filter { it.x == tree.x && it.y < tree.y }
}

fun List<Tree>.findBottomNeighbours(tree: Tree): List<Tree> {
    return this.filter { it.x == tree.x && it.y > tree.y }
}

fun List<Tree>.findLeftNeighbours(tree: Tree): List<Tree> {
    return this.filter { it.y == tree.y && it.x < tree.x }
}

fun List<Tree>.findRightNeighbours(tree: Tree): List<Tree> {
    return this.filter { it.y == tree.y && it.x > tree.x }
}
