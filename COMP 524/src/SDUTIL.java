
import java.util.ArrayList;
import java.util.List;

import gradingTools.comp524f20.assignment1.WekaUtil;

public class SDUTIL{

	/**
	If the combination of the method parameters is safe, based on the given data,  the function returns true. Otherwise, it returns false.
	  */
	public static boolean isGivenSafe(int dist, int durat, int exhal)
	{
		
		for(int i=0; i<safeInteractions.size();i++)
		{
			if(safeInteractions.get(i)[0] == dist && safeInteractions.get(i)[1] == durat && safeInteractions.get(i)[2] == exhal) 
			{
				return true;
			}
		}
		return false;
	}

	
	/**
	 the three parameters represent distance, duration, and exhalation level. The
	function interpolates each of the parameters to a value in Table 2, and determines if the
	interpolated values are safe based on whether they occur in Table 2. Returns true if safe.
	 **/
	public static boolean isInterpolatedSafe(int dist,int durat,int exhal)
	{
		int dist_intrp; 
		int durat_intrp; 
		int exhal_intrp;
		//dist
		if(dist >= 6 && dist < 13) dist =6;
		else if(dist >= 13 && dist < 27) dist =13; 
		else if(dist >= 27) dist =27;
		else return false;
		//durat
		if(durat <=  15) durat =15;
		else if(durat <= 30 && durat > 15) durat =30;
		else if(durat <= 120 && durat > 30) durat =120;
		else return false;
		//exhal
		if(exhal <= 10) exhal =10;
		else if(exhal <= 30 && exhal > 10) exhal =30;
		else if(exhal <= 50 && exhal > 30) exhal =50;
		else return false;
		return isGivenSafe(dist, durat, exhal);
	}
	
	// two parameter
	public static boolean isInterpolatedSafe(int dist,int durat) 
	{return isInterpolatedSafe(dist, durat, 30);}
	
	// one parameter
	public static boolean isInterpolatdSafe(int dist) 
	{return isInterpolatedSafe(dist, 30);}
	
	/**
	It uses Math.random() function to generate a distance, duration, and exhalation level
	combination.
	It determines whether this combination is safe based on the isDerivedSafe() function
	described below
	It prints the (distance, duration, exhalation level, and safety) tuple, using a comma to
	separate the elements of the 4-tuple.
	**/
	public static void printGeneratedCombinationDerivedSafety() 
	{
		int dist = (int)(Math.random()*100);
		int durat = (int)(Math.random()*200);
		int exhal = (int)(Math.random()*100);
		
		String[] tuple = {Integer.toString(dist),Integer.toString(durat),Integer.toString(exhal),Boolean.toString(isDerivedSafe(dist, durat, exhal))};
		
		System.out.println(tuple[0]+","+tuple[1]+","+tuple[2]+","+tuple[3]);
	}
	
	/**
	It prints the following header:

	Distance,Duration,Exhalation,IsSafe

	For each 3-tuple combination in Table 2, it adds the Boolean true to create a 4-tuple combination and prints the 4-tuple, again using a comma to separate the elements of the tuple.

	It prints a separator line with one or more hyphens.

	It then calls printGeneratedCombinationDerivedSafety() ten times.

	**/
	public static void printGivenAndGeneratedCombinationsDerivedSafety() 
	{ 
		System.out.println("Distance,Duration,Exhalation,IsSafe");
		System.out.println("13,30,30,true");
		System.out.println("6,30,10,true");
		System.out.println("27,30,50,true");
		System.out.println("13,15,50,true");
		System.out.println("13,120,10,true");
		System.out.println("27,120,30,true");
		System.out.println("6,15,30,true");
		System.out.println("----------------");
		for (int i=0; i<10; i++) {
			printGeneratedCombinationDerivedSafety();
		}
	}
	
	/**
	The procedure computes a (possibly empty) list of given safe distance and duration pairs that are associated with an interpolation of the exhalation level in Table 2.

	Each pair is returned by a two-element array whose first element is the distance and second element is the duration.

	**/
	static List<Integer[]> safeInteractions = new ArrayList<Integer[]>(){{
        add(new Integer[]{13,30,30});
        add(new Integer[]{6,30,10});
        add(new Integer[]{27,30,50});
        add(new Integer[]{13,15,50});
        add(new Integer[]{13,120,10});
        add(new Integer[]{27,120,30});
        add(new Integer[]{6,15,30});
          }};
	
	public static List<Integer[]> generateSafeDistancesAndDurations(int exhal)
	{
		if(exhal <= 10) exhal =10;
		else if(exhal <= 30 && exhal > 10) exhal =30;
		else if(exhal <= 50 && exhal > 30) exhal =50;
		List<Integer[]> pairs = new ArrayList<Integer[]>();
		for(int i=0; i<safeInteractions.size();i++)
		{
			if(exhal == safeInteractions.get(i)[2]) {
			Integer[] resetL = {safeInteractions.get(i)[0],safeInteractions.get(i)[1]};
				pairs.add(resetL);
			}
		}
		return pairs;
	}
	
	/**
	Helper method to print out the the multiple pairs of distance and duration into one big string and return it
	**/
	public static String toString(List<Integer[]> pairs)
	{
		String ts = "[";
		for(int i = 0; i < pairs.size(); i++)
		{
			ts += "{" + pairs.get(i)[0].toString() + "," + pairs.get(i)[1].toString()+ "}";
        }
		return ts += "]";
		
	}
	
	/**
	The parameter is an exhalation-level. It uses the method above to determine the list of safe distances and durations for the passed exhalation-level and 
	prints the passed exhalation level together with the returned list using the format below:

	<exhalation level>, [{<destination,duration1}, … {<destination>, <duration>}]

	**/
	public static void printSafeDistancesAndDurations(int exhal) 
	{
		System.out.println(exhal + "," + toString(generateSafeDistancesAndDurations(exhal)));
	}
	
	/**
	It returns true if the combination of these three parameters is safer than at least one of the combinations in the table.
	**/
	public static boolean isDerivedSafe(int dist, int durat, int exhal)
	{	
		if(exhal <= 10) exhal =10;
		else if(exhal <= 30 && exhal > 10) exhal =30;
		else if(exhal <= 50 && exhal > 30) exhal =50;
		List<Integer[]> pairs = generateSafeDistancesAndDurations(exhal);
		for(int i = 0; i < pairs.size(); i++)
		{
			if(dist >= pairs.get(i)[0] && durat <= pairs.get(i)[1])
			{
				return true;
			}
        }
		return false;
		
		
	}
	
	public static Boolean isInferredSafe(int dist, int durat, int exhal) {
		final String TRUE = "true";
		final String FALSE = "false";
		final String[] featureNames = {"distance", "duration","exhalation"};
		final String resultAttributeName = "safe";
		final String[] resultValueNames = {TRUE,FALSE};
		double[] inputValues = {dist, durat, exhal};
		String resultString = WekaUtil.predictString(ClassifierFactory.getSingleton(), featureNames, inputValues, resultAttributeName, resultValueNames);
		return TRUE.equals(resultString)?true:false;
	}
	
	public static void printGeneratedCombinationInferredSafety() 
	{
		int dist = (int)(Math.random()*100);
		int durat = (int)(Math.random()*200);
		int exhal = (int)(Math.random()*100);
		
		String[] tuple = {Integer.toString(dist),Integer.toString(durat),Integer.toString(exhal),Boolean.toString(isInferredSafe(dist, durat, exhal))};
		
		System.out.println(tuple[0]+","+tuple[1]+","+tuple[2]+","+tuple[3]);
	}
	
	public static void printGivenAndGeneratedCombinationsInferredSafety()
	{
		System.out.println("Distance,Duration,Exhalation,IsSafe");
		System.out.println("13,30,30,true");
		System.out.println("6,30,10,true");
		System.out.println("27,30,50,true");
		System.out.println("13,15,50,true");
		System.out.println("13,120,10,true");
		System.out.println("27,120,30,true");
		System.out.println("6,15,30,true");
		System.out.println("----------------");
		for (int i=0; i<10; i++) {
			printGeneratedCombinationInferredSafety();
		}
	}
	
	public static void printGeneratedCombination()
	{
		int dist = (int)(Math.random()*100);
		int durat = (int)(Math.random()*200);
		int exhal = (int)(Math.random()*100);
		
		String[] tuple = {Integer.toString(dist),Integer.toString(durat),Integer.toString(exhal),Boolean.toString(isDerivedSafe(dist, durat, exhal)),Boolean.toString(isInferredSafe(dist, durat, exhal))};
		
		System.out.println(tuple[0]+","+tuple[1]+","+tuple[2]+","+tuple[3]+","+tuple[4]);
	}
	
	public static void compareSafetyComputations()
	{
		System.out.println("Distance,Duration,Exhalation,Derived,Inferred");
		for (int i=0; i<10; i++) {
			printGeneratedCombination();
		}
	}
	
	
	
	
	
	
	
	
	
	
}
