public class GeneraCaso{
	public static int [] generaVector(int tamaño, boolean modo){
		int[] res = new int[tamaño];
		if(modo){
			for(int i=0; i<tamaño; i++)
				res[i] = i+1;
			java.util.Random r= new java.util.Random();
			for(int i=0; i<tamaño; i++){
				int pos1 = r.nextInt(tamaño);
				int pos2 = r.nextInt(tamaño);
				int aux = res[pos1];
				res[pos1] = res[pos2];
				res[pos2] = aux;
			}
		}else{
			for(int i=0; i<tamaño; i++)
				res[i] = tamaño-i;
		}
		return res;
	}
}