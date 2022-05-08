
public class GaleShapley
{
    private int N, contadorCompromisos;
    private String[][] prefVarones;
    private String[][] prefMujeres;
    private String[] varones;
    private String[] mujeres;
    private String[] parejaMujer;
    private boolean[] varonComprometido;
 
    /** Funci�n constructor **/
    public GaleShapley(String[] v, String[] m, String[][] pv, String[][] pm)
    {
        N = pv.length;
        contadorCompromisos = 0;
        varones = v;
        mujeres = m;
        prefVarones = pv;
        prefMujeres = pm;
        varonComprometido = new boolean[N];
        parejaMujer = new String[N];
        calcularEmparejamientos();
    }
    /** Funci�n para calcular todas los emparejamientos **/
    private void calcularEmparejamientos()
    {
        while (contadorCompromisos < N)
        {
            int libre;
            for (libre = 0; libre < N; libre++)
                if (!varonComprometido[libre])
                    break;
 
            for (int i = 0; i < N && !varonComprometido[libre]; i++)
            {
                int index = indiceMujeres(prefVarones[libre][i]);
                if (parejaMujer[index] == null)
                {
                    parejaMujer[index] = varones[libre];
                    varonComprometido[libre] = true;
                    contadorCompromisos++;
                }
                else
                {
                    String parejaActual = parejaMujer[index];
                    if (otraPreferencia(parejaActual, varones[libre], index))
                    {
                        parejaMujer[index] = varones[libre];
                        varonComprometido[libre] = true;
                        varonComprometido[indiceVarones(parejaActual)] = false;
                    }
                }
            }            
        }
        imprimirParejas();
    }
    /** Funci�n para verificar si la mujer prefiere una nueva pareja en ves de su pareja antes asignada **/
    private boolean otraPreferencia(String parejaActua, String nuevaPareja, int index)
    {
        for (int i = 0; i < N; i++)
        {
            if (prefMujeres[index][i].equals(nuevaPareja))
                return true;
            if (prefMujeres[index][i].equals(parejaActua))
                return false;
        }
        return false;
    }
    /**Funci�n que extrae el indice de varon **/
    private int indiceVarones(String str)
    {
        for (int i = 0; i < N; i++)
            if (varones[i].equals(str))
                return i;
        return -1;
    }
    /** Funci�n que extrae el indice de mujer **/
    private int indiceMujeres(String str)
    {
        for (int i = 0; i < N; i++)
            if (mujeres[i].equals(str))
                return i;
        return -1;
    }
    /** Funci�n para imprimir las parejas en consola**/
    public void imprimirParejas()
    {
        System.out.println("Las Parejas son : ");
        for (int i = 0; i < N; i++)
        {
            System.out.println(parejaMujer[i] +" y "+ mujeres[i]);
        }
    }
    /** Funci�n principal **/
    public static void main(String[] args) 
    {
        System.out.println("Algoritmo de Gale Shapley \n");
        /** Lista de varones **/
        String[] m = {"Diego", "Jorge", "Mateo", "Pedro"};
        /** Lista de mujeres **/
        String[] w = {"Elena", "Laura", "Julia", "Paula"};
 
        /** Preferencia de los varones **/
        String[][] mp = {{"Julia", "Laura", "Elena", "Paula"}, 
                         {"Laura", "Paula", "Elena", "Julia"}, 
                         {"Julia", "Elena", "Paula", "Laura"}, 
                         {"Elena", "Laura", "Julia", "Paula"}};
        /** Preferencia de las mujeres **/                      
        String[][] wp = {{"Diego", "Pedro", "Jorge", "M1"}, 
                         {"Mateo", "Pedro", "Jorge", "Diego"}, 
                         {"Jorge", "Mateo", "Pedro", "Diego"},
                         {"Pedro", "Jorge", "Diego", "Mateo"},};
 
        GaleShapley gs = new GaleShapley(m, w, mp, wp);                        
    }
}
