package Matrix;

import Exceptions.UncompatibleDimensions;

public class Matrix {
	private double matrix[][];
	public Matrix() {
		matrix = new double[1][1];
	}
	public Matrix(int numRows,int numCols){
		matrix = new double[numRows][numCols];
	}
	public Matrix(Matrix m){
		matrix = new double[m.getNumRows()][m.getNumCols()];
		for(int i = 0 ; i < m.getNumRows(); ++i){
			for(int j = 0 ; j < m.getNumCols(); ++j){
				matrix[i][j] = m.getValue(i, j);
			}
		}
	}
	public static Matrix multiply(Matrix mOne,Matrix mTwo)
			throws UncompatibleDimensions{
		if(mOne.getNumCols() != mTwo.getNumRows()){
			throw new UncompatibleDimensions();
		}
		Matrix mResult = new Matrix(mOne.getNumRows(),mTwo.getNumCols());
		for(int i = 0 ; i < mResult.getNumRows(); ++i){
			for(int j = 0 ; j < mResult.getNumCols(); ++j){
				double sum = 0.0;
				for(int k = 0 ; k < mOne.getNumCols(); ++k){
					sum += mOne.getValue(i, k)*mTwo.getValue(k, j);
				}
				mResult.setValue(i, j, sum);
			}
		}
		return mResult;
	}
	
	public static Matrix add(Matrix mOne,Matrix mTwo)
			throws UncompatibleDimensions{
		
		if(mOne.getNumCols() != mTwo.getNumCols() || 
				mOne.getNumRows() != mTwo.getNumRows()){
			throw new UncompatibleDimensions();
		}
		
		Matrix mResult = new Matrix(mOne.getNumRows(),mOne.getNumCols());
		for(int i = 0 ; i < mResult.getNumRows() ; ++i){
			for(int j = 0 ; j < mResult.getNumCols(); ++j){
				double mOneValue = mOne.getValue(i, j);
				double mTwoValue = mTwo.getValue(i, j);
				mResult.setValue(i, j, mOneValue+mTwoValue);
			}
		}
		
		return mResult;
	}
	public boolean equals(Matrix m){
		if(m.getNumRows() != getNumCols())return false;
		if(m.getNumRows() != getNumRows())return false;
		for(int i = 0 ; i < getNumRows(); ++i){
			for(int j = 0 ; j < getNumCols(); ++j){
				if(matrix[i][j] != m.getValue(i, j))return false;
			}
		}
		return true;
	}
	public void transpose(){
		double tmp[][] = new double[getNumCols()][getNumRows()];
		for(int i = 0 ; i < getNumRows(); ++i){
			for(int j = 0 ; j < getNumCols(); ++j){
				tmp[j][i] = matrix[i][j];
			}
		}
		matrix = tmp;
	}
	public void bipolarSpaceTranslation(){
		for(int i = 0 ; i < matrix.length; ++i){
			for(int j = 0 ; j < matrix[i].length; ++j){
				if(matrix[i][j] > 0)matrix[i][j] = 1;
				if(matrix[i][j] < 0)matrix[i][j] = -1;
			}
		}
	}
	public int getNumRows(){
		return matrix.length;
	}
	public int getNumCols(){
		return matrix[0].length;
	}
	public void setValue(int rowIdx,int colIdx,double val){
		matrix[rowIdx][colIdx] = val;
	}
	public double getValue(int rowIdx,int colIdx){
		return matrix[rowIdx][colIdx];
	}
	public void print(){
		for(double[] row: matrix){
			for(double element: row){
				System.out.print(element+"   ");
			}
			System.out.println();
		}
	}
}
