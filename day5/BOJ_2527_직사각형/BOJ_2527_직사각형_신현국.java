package sh1n.bj.ps2527직사각형;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2527직사각형/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<Rectangle> rectangle = new ArrayList<Rectangle>();
		for(int tc=1; tc<=4; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int input=0; input<2; input++) {
				rectangle.add(new Rectangle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
						, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			Collections.sort(rectangle);

			if( (rectangle.get(0).x2 < rectangle.get(1).x1)
					|| (rectangle.get(0).y2 < rectangle.get(1).y1) 
					|| (rectangle.get(0).y1 > rectangle.get(1).y2) ) {
				System.out.println("d");
				
			} else if ( (rectangle.get(0).x2 == rectangle.get(1).x1) && (rectangle.get(0).y2 == rectangle.get(1).y1)
					|| (rectangle.get(0).x2 == rectangle.get(1).x1) && (rectangle.get(0).y1 == rectangle.get(1).y2) ) {
				System.out.println("c");
				
			} else if ( ((rectangle.get(0).y1 == rectangle.get(1).y2) && (rectangle.get(0).x2 > rectangle.get(1).x1))
					|| ((rectangle.get(0).y2 == rectangle.get(1).y1) && (rectangle.get(0).x2 > rectangle.get(1).x1))
					|| (rectangle.get(0).x2 == rectangle.get(1).x1) && (rectangle.get(0).y1 < rectangle.get(1).y2) && (rectangle.get(0).y2 > rectangle.get(1).y1) ) {
				System.out.println("b");
				
			} else {
				System.out.println("a");
			}
			
			rectangle.clear();
		}
	} // end main()
}

class Rectangle implements Comparable<Rectangle> {
	int x1;
	int y1;
	int x2;
	int y2;
	
	public Rectangle(int x1, int y1, int x2, int y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public int compareTo(Rectangle r) {
		return this.x1-r.x1;
	}
}
