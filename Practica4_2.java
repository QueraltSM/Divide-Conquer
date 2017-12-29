import java.util.Scanner;

public class Practica4_2 {

    public static void main(String[] args) {
        /*int [] v1 = GeneraCaso.generaVector(100, true); // pruebas manuales
        int [] v2 = GeneraCaso.generaVector(200, false);
        int [] v3 = GeneraCaso.generaVector(500, true);
        int [] v4 = GeneraCaso.generaVector(1500, true);
        int [] v5 = GeneraCaso.generaVector(2500, true);
        int [] v6 = GeneraCaso.generaVector(3500, true);
        int pos1 = divideAndConquer(v1, 0, 99, 15);
        int pos2 = divideAndConquer(v2, 0, 199, 24);
        int pos3 = divideAndConquer(v3, 0, 499, 20);
        int pos4 = divideAndConquer(v4, 0, 1499, 102);
        int pos5 = divideAndConquer(v5, 0, 2499, 69);
        int pos6 = divideAndConquer(v6, 0, 3499, 76);
        mostrar(v1, pos1);
        mostrar(v2, pos2);
        mostrar(v3, pos3);
        mostrar(v4, pos4);
        mostrar(v5, pos5);
        mostrar(v6, pos6);*/
        Scanner sc = new Scanner(System.in); //Parte interactiva
        int v, pos, res;
        int [] vx;
        String d;
        while (true){
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Introduce el tamaño del vector: ");
            try{
                v = sc.nextInt();
            } catch (java.util.InputMismatchException e){               
                System.out.println("Se ha escrito un caracter no deseado");
                sc = new Scanner (System.in);
                continue;
            }
            if (v < 0) {
                System.out.println("El tamaño del vector tiene que ser 0 o positivo.");
                continue;
            }
            System.out.print("Introduce el valor del umbral: ");
            try{
                pos = sc.nextInt();
            } catch (java.util.InputMismatchException e){               
                System.out.println("Se ha escrito un caracter no deseado");
                sc = new Scanner (System.in);
                continue;
            }
            if (pos >= v || pos < 0){
                System.out.println("El umbral debe ser menor que el tamaño del vector, y debe ser 0 o positivo");
                continue;
            }
            vx = GeneraCaso.generaVector(v, true);
            res = divideAndConquer(vx, 0, v-1, pos);
            mostrar(vx, res);
            System.out.println("Introduzca 'Y' para generar otro caso, o cualquier otro caracter para salir.");
            d = sc.next();
            if (d.equals("Y") || d.equals("y")) continue;
            break;
            
        }
    }
    
    public static int divideAndConquer(int[] vec, int linf, int lsup, int valor){
        int [] p = new int[2];
        if (linf > lsup){
            System.out.println("Error en el paso de parámetros");
            return -1;
        } else if (linf == lsup){
            return linf;
        }
        p = division(vec, linf, lsup);
        if (vec[p[1]+1] == valor) return p[1]+1;
        if (vec[p[1]+1] > valor) return divideAndConquer(vec, linf, p[1], valor);
        return divideAndConquer(vec, p[0], lsup, valor);
    }
    
    public static int[] division(int [] vec, int linf, int lsup){
        int piv = (lsup+linf)/2;
        int pini = linf; int pfin = lsup; int aux;
        while (pini <= pfin){
            while ((pini <= lsup) && (vec[pini] <= vec[piv])){
                pini++;
            }
            while ((pfin >= linf) && (vec[pfin] >= vec[piv])){
                pfin--;
            }
            if (pini < pfin){
                aux = vec[pini];
                vec[pini] = vec[pfin];
                vec[pfin] = aux;
                pini++;
                pfin--;                     
            }
        }
        if (pini < piv){
            aux = vec[pini];
            vec[pini] = vec[piv];
            vec[piv] = aux;
            pini++;
        } else if (pfin > piv){
            aux = vec[piv];
            vec[piv] = vec[pfin];
            vec[pfin] = aux;
            pfin--;
        }
        int [] res = new int[2];
        res[0] = pini;
        res[1] = pfin;
        return res;
    }
    
    public static void mostrar(int [] vec, int pos){
        int[] most = new int[pos];
        System.arraycopy(vec, 0, most, 0, pos);
        most = ordena(most, pos);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Para el vector de tamaño " + vec.length + " los elementos menores que " + (pos==0 ? pos : pos + 1) + " son: ");
        int cont = 0;
        for (int i = pos-1; i >= 0; i--) {
            System.out.print(most[i] + " ");
            cont++;
            if (cont == 27) {
                System.out.println("");
                cont = 0;
            } 
        }
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------");
    }
    
    public static int[] ordena(int [] vec, int pos){
        int[] vecs = new int[vec.length];
        int[] frec = new int[pos+2];
        for (int i = 0; i < frec.length; i++) {
            frec[i] = 0;
        }
        
        for (int i = 0; i < vec.length; i++) {
            frec[vec[i]+1] = frec[vec[i]+1]+1;
        }
        
        frec[0] = 1;
        
        for (int i = 1; i < frec.length-1; i++) {
            frec[i] = frec[i] + frec[i-1];
        }
        
        for (int i = 0; i < vec.length; i++) {
            vecs[frec[vec[i]]-1] = vec[i];
            frec[vec[i]] = frec[vec[i]]+1;
        }
        return vecs;
    }
}
