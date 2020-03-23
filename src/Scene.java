import javafx.util.Pair;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Scene {
    private List<Point> vertexList;
    private List<Edge> edgesList;

    public Scene() throws Exception{
        this.edgesList = new ArrayList<>();
        this.vertexList = new ArrayList<>();
        initializeLists("C:\\Users\\shaia\\IdeaProjects\\ComputerGraphicsEx1\\Resources\\ex0.scn");
    }
    public void initializeLists(String filePath) throws Exception{
        List<Pair<Integer,Integer>> pointsIndex = new ArrayList<>();
        int lineNumber = 0, edgesNum, vertexNum = 0, i;
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            if (lineNumber == 0) {
                vertexNum = Integer.parseInt(line);
                lineNumber++;
                continue;
            } else if (lineNumber == 1 + vertexNum) {
                edgesNum = Integer.parseInt(line);
                line = br.readLine();
                lineNumber++;
                for (i = 0; i < edgesNum; i++) {
                    String[] edges = line.split(" ");
                    pointsIndex.add(new Pair<Integer, Integer>(Integer.parseInt(edges[0]), Integer.parseInt(edges[1])));
                    line = br.readLine();
                    lineNumber++;
                }
                continue;
            }
            for (i = 0; i < vertexNum; i++) {
                String[] point = line.split(" ");
                Point pt = new Point(Double.parseDouble(point[0]), Double.parseDouble(point[1]));
                this.vertexList.add(pt);
                if (i + 1 == vertexNum) {
                    continue;
                }
                line = br.readLine();
                lineNumber++;
            }
            lineNumber++;
        }
        createEdgesList(pointsIndex);
    }

    public void createEdgesList(List<Pair<Integer,Integer>> pointsIndex) {
        ListIterator<Pair<Integer,Integer>> iterator = pointsIndex.listIterator();
        while (iterator.hasNext()) {
            Pair<Integer, Integer> pair = iterator.next();
            Edge edge = new Edge(this.vertexList.get(pair.getKey()),
                    this.vertexList.get(pair.getValue()));
            this.edgesList.add(edge);
        }
    }
}


