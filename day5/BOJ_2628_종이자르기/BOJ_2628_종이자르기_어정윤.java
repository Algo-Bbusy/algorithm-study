import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2628_종이자르기_어정윤 {

    private static final int NORTH = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int width = Integer.parseInt(stringTokenizer.nextToken());
        int height = Integer.parseInt(stringTokenizer.nextToken());
        int edge = height * 2 + width * 2;
        int marketNum = Integer.parseInt(bufferedReader.readLine());

        List<Integer> space = new ArrayList<>();
        for (int i = 0; i < marketNum+1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            int position = Integer.parseInt(stringTokenizer.nextToken());
            space.add(getDistance(width, height, direction, position));
        }
        int donggeun = space.get(space.size()-1);
        int result = 0;
        for (int i = 0; i < marketNum; i++) {
            int wayToMarket = Math.abs(donggeun - space.get(i));
            result += Math.min(wayToMarket, edge - wayToMarket);
        }
        System.out.println(result);
    }

    private static int getDistance(int width, int height, int direction, int position) {
        int distance = 0;
        if (direction == NORTH) {
            distance = position;
        } else if (direction == SOUTH) {
            distance = width * 2 + height - position;
        } else if (direction == WEST) {
            distance = width * 2 + height * 2 - position;
        } else {
            distance = width + position;
        }
        return distance;
    }
}
