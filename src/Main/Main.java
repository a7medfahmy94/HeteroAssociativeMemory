package Main;

import heteroAssociativeMemory.HeteroAssociativeMemory;

import java.util.Scanner;

import Exceptions.UncompatibleDimensions;
import Matrix.Matrix;

public class Main {
	private static Scanner in = new Scanner(System.in);
	private static HeteroAssociativeMemory memory = new HeteroAssociativeMemory();
	private static int xVectorDimensions, yVectorDimensions;

	public static void main(String[] args) {
		readXYDimensions();

		System.out.println("How Many Examples you wish to enter:");
		int numExamples = in.nextInt();
		for (int i = 0; i < numExamples; ++i) {
			Matrix mOne = readVector(xVectorDimensions);
			Matrix mTwo = readVector(yVectorDimensions);
			;
			memory.save(mOne, mTwo);
		}
		System.out.println("Recall...");
		while (true) {
			Matrix mOne = readVector(xVectorDimensions);
			try {
				Matrix results[] = memory.recallWithX(mOne);
				System.out.println("X");
				results[0].print();
				System.out.println("Y");
				results[1].print();
			} catch (UncompatibleDimensions e) {
				System.out.println(e.what());
			}
		}
	}

	public static void readXYDimensions() {
		System.out.println("Enter x's dimensions");
		xVectorDimensions = in.nextInt();
		System.out.println("Enter y's dimensions");
		yVectorDimensions = in.nextInt();
	}

	public static Matrix readVector(int dim) {
		System.out.println("Enter the vector");
		Matrix m = new Matrix(1, dim);
		for (int j = 0; j < dim; ++j) {
			double x = in.nextDouble();
			m.setValue(0, j, x);
		}
		return m;
	}
}
