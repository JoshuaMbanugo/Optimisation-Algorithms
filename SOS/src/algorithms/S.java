package algorithms;
//in this part you can import the functionalities that yuo need to use for implementing your algorithm
import static utils.algorithms.Misc.generateRandomSolution;
import static utils.MatLab.max;
import static utils.MatLab.min;
import static utils.algorithms.Misc.toro;

import utils.random.RandUtils;
import interfaces.Algorithm;
import interfaces.Problem;
import utils.RunAndStore.FTrend;
/**
 * Intelligent Single Particle Optimization
 */
public class S extends Algorithm //This class implements the algorithm. Every algorithm will have to contain its specific implementation within the method "execute". The latter will contain a main loop performing the iterations, and will have to return the fitness trend (including the final best) solution. Look at this examples before implementing your first algorithm.
{
    @Override
    //to implement a different algorithm you'll have to change the content of this function
    public FTrend execute(Problem problem, int maxEvaluations) throws Exception {
        // first, we need to define variables for storing the paramters of the algorithm
        //double xs = getParameter("p0");
        double Alpha = getParameter("p1");


        //we always need an object of the kynd FTrend (for storing the fitness trend), and variables for storing the dimesionality vlue and the bounds of the problem as showed below
        FTrend FT = new FTrend();
        int problemDimension = problem.getDimension();
        double[][] bounds = problem.getBounds();
        // particle (the solution, i.e. "x")
        double[] best = new double[problemDimension];
        double fBest; //fitness value, i.e. "f(x)"
        double oBest;
        double xsf [];

        int i = 0;
        // initial solution
        if (initialSolution != null) {

            best = initialSolution;
            fBest = initialFitness;

        } else//random intitial guess
        {
            best = generateRandomSolution(bounds, problemDimension);

            fBest = problem.f(best);
            i++;
        }
        //store the initital guess
        FT.add(0, fBest);

        oBest = fBest;
        xsf = best;

        double UpperBounds = bounds[1][1];
        double LowerBounds = bounds[0][0];
        double delta = Alpha * (UpperBounds - LowerBounds);


        //main loop
        while (i < maxEvaluations) {
            // ADD S Algs
            for (int j = 0; j < problemDimension && i < maxEvaluations; j++) {

                xsf[j] = best[j] - delta;
                xsf[j] = problem.f(best);

                i++;

                if (xsf[j] < fBest) {
                    best[j] = xsf[j];
                    fBest = xsf[j];
                } else {
                    xsf[j] = best[j];
                    xsf[j] = best[j] + delta / 2;
                }
                if (xsf[j] <= fBest) {
                    best[j] = xsf[j];
                } else {
                    xsf[j] = best[j];
                }
            }
                if ( oBest == fBest) {
                    delta += delta / 2;
                }
        }

        finalBest = best; //save the final best
        FT.add(i, fBest);//add it to the txt file (row data)

        return FT; //return the fitness trend

    }
}
