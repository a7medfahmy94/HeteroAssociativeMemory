package heteroAssociativeMemory;

import java.util.ArrayList;

import Exceptions.UncompatibleDimensions;
import Matrix.Matrix;

public class HeteroAssociativeMemory {
	Matrix memory;
	public HeteroAssociativeMemory() {
		memory = null;
	}
	public void save(Matrix xVector,Matrix yVector){
		
		yVector.transpose();
		Matrix mResult;
		try{
			mResult = Matrix.multiply(yVector, xVector);
			if(memory == null){
				memory = mResult;
			}else{
				memory = Matrix.add(memory, mResult);
			}
		}catch(UncompatibleDimensions e){
			System.out.println(e.what());
		}
	}
	public Matrix[] recallWithX(Matrix xVector)throws UncompatibleDimensions{
		xVector.transpose();
		Matrix yVector = Matrix.multiply(memory, xVector);
		xVector.transpose();
		yVector.transpose();
		yVector.bipolarSpaceTranslation();
		Matrix previousX = null, previousY = null;
		while(true){
			if(previousX != null && (!previousX.equals(xVector) && !previousY.equals(yVector)))break;
			previousX = new Matrix(xVector);
			previousY = new Matrix(yVector);
			xVector = Matrix.multiply(yVector, memory);
			xVector.transpose();
			yVector = Matrix.multiply(memory, xVector);
			xVector.transpose();
			yVector.transpose();
			xVector.bipolarSpaceTranslation();
			yVector.bipolarSpaceTranslation();
		}
		return new Matrix[]{xVector,yVector};
	}
	
	public Matrix getCurrentMemory(){
		return memory;
	}
	
}
