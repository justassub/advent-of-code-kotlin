package advent.of.code.util

data class Point3D(val x: Int, val y: Int, val z: Int)


fun Point3D.findNeighbours(): Set<Point3D> {
    return setOf(
        Point3D(this.x, this.y, this.z - 1),
        Point3D(this.x, this.y, this.z + 1),
        Point3D(this.x, this.y - 1, this.z),
        Point3D(this.x, this.y + 1, this.z),
        Point3D(this.x - 1, this.y, this.z),
        Point3D(this.x + 1, this.y, this.z)
    )
}

fun build3DPointFromLine(line: String, split: String): Point3D {
    return line.split(split)
        .let { Point3D(it.first().toInt(), it[1].toInt(), it.last().toInt()) }
}
