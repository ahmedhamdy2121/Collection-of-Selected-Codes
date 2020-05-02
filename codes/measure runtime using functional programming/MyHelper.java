package MyLibrary;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author ahmed hamdy
 *
 */
public class MyHelper
{
	/**
	 * <b>Call it with:</b></br>
	 * 		<pre>int res = measureRunTime(ob::foo, in);</pre>
	 * @param toRun the function you want to run
	 * @param input input to the function (T)
	 * @return the function output (R)
	 */
	public static<T, R> R measureRunTime (Function<T, R> toRun, T input)
	{
		long start = System.nanoTime();
		R res = toRun.apply(input);
		long end = System.nanoTime();
		System.out.println("Run Time: " + (end - start) / 1000000.0 + " millisecond");
		return res;
	}
	
	/**
	 * Use it when your function has two parameters </br>
	 * <b>Call it with:</b></br>
	 * 		<pre>int res = measureRunTime(ob::foo, in1, in2);</pre>
	 * @param toRun the function you want to run
	 * @param input1 first input (T)
	 * @param input2 second input (U)
	 * @return the function output (R)
	 */
	public static<T, U, R> R measureRunTime (BiFunction<T, U, R> toRun, T input1, U input2)
	{
		long start = System.nanoTime();
		R res = toRun.apply(input1, input2);
		long end = System.nanoTime();
		System.out.println("Run Time: " + (end - start) / 1000000.0 + " millisecond");
		return res;
	}
	
	/**
	 * Use it when your function has three parameters </br>
	 * <b>Call it with:</b></br>
	 * 		<pre>int res = measureRunTime(ob::foo, in1, in2, in3);</pre>
	 * @param toRun the function you want to run
	 * @param input1 first input (T)
	 * @param input2 second input (U)
	 * @param input3 third input (V)
	 * @return the function output
	 */
	public static<T, U, V, R> R measureRunTime (TriFunction<T, U, V, R> toRun, T input1, U input2, V input3)
	{
		long start = System.nanoTime();
		R res = toRun.apply(input1, input2, input3);
		long end = System.nanoTime();
		System.out.println("Run Time: " + (end - start) / 1000000.0 + " millisecond");
		return res;
	}
	
	@FunctionalInterface
	public interface TriFunction<T, U, V, R>
	{
		public R apply(T input1, U input2, V input3);
	}
}
