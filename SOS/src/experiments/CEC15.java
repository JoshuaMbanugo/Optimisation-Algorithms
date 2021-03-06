package experiments;

import interfaces.Experiment;
import interfaces.Algorithm;
import benchmarks.CEC2015;
import algorithms.ISPO; 



public class CEC15 extends Experiment
{
	
	public CEC15(int probDim) throws Exception
	{
		//super(probDim,"cec2015allDim");
		super(probDim,5000,"testCEC15");
		setNrRuns(10);


		Algorithm a;// ///< A generic optimiser.
	    //Problem p;// ///< A generic problem.

		a = new ISPO();
		a.setParameter("p0", 1.0);
		a.setParameter("p1", 10.0);
		a.setParameter("p2", 2.0);
		a.setParameter("p3", 4.0);
		a.setParameter("p4", 1e-5);
		a.setParameter("p5", 30.0);
		add(a);

//		a = new S();
//		//a.setParameter("p0", 1.0);
//		a.setParameter("p1", 0.5);
//		add(a);


	
		for(int i = 1; i<=15; i++)
			add(new CEC2015(probDim, i));

	}
}
