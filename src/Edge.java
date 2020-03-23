public class Edge {
    private Point start;
    private Point end;

    /**
     * Construct a line given the starting point and the ending point.
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Edge(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    public Point getStart() {
        return this.start;
    }

    /**
     * end method returns the ending point of the line.
     * @return the ending point.
     */
    public Point getEnd() {
        return this.end;
    }
}
