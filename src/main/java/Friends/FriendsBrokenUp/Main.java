package Friends.FriendsBrokenUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// https://onlinejudge.org/external/106/10608.pdf
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("src/main/java/Friends/FriendsBrokenUp/input.txt");
        Scanner scanner = new Scanner(inputFile);

        int numberOfDatasets = 0;
        int lineCounter = 0;
        ArrayList<String[]> rawData = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (lineCounter == 0) {
                numberOfDatasets = Integer.parseInt(line);
            } else {
                String[] splitLine = line.split("\\s+");
                rawData.add(splitLine);
            }
            lineCounter++;
        }
        scanner.close();

        ArrayList<int[]> data = new ArrayList<>();
        for (String[] line : rawData) {
            data.add(new int[]{
                    Integer.parseInt(line[0]), Integer.parseInt(line[1])
            });
        }

        int start = 0;
        for (int i = 0; i < numberOfDatasets; i++) {
            ArrayList<int[]> datasetData = new ArrayList<>();
            int m = data.get(start)[1];

            for (int j = 1; j <= m; j++) {
                datasetData.add(data.get(start + j));
            }

            start += datasetData.size() + 1;

            Solver solver = new Solver();
            int result = solver.solve(datasetData);
            System.out.println(result);
        }
    }
}