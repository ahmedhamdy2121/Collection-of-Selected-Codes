package MyLibrary;

public class BitsOperations
{
	
	public static void main(String[] args)
	{
		//System.out.println(testBit(16, 4));
		//System.out.println(setBit(0, 3));
		//System.out.println(clearBit(17, 0));
		System.out.println(toggleBit(3, 2));
	}
	
	/**
	 * Using AND
	 * Anything AND with 1 returns itself
	 * @param a
	 * @param bitPos
	 * @return true if bit is 1, false if 0
	 */
	public static boolean testBit (int a, int bitPos)
	{
		// Don't use == 1 !!! Use only (== 0) or (!= 0)
		return (((a & (1 << bitPos)) != 0)? true : false);
	}
	
	/**
	 * Using OR
	 * Anything OR with 1 returns 1
	 * @param a
	 * @param bitPos
	 * @return the new integer
	 */
	public static int setBit (int a, int bitPos)
	{
		return (a |= (1 << bitPos));
	}
	
	/**
	 * Using AND and NOT
	 * Anything AND with 1 returns itself
	 * Anything AND with 0 returns 0
	 * @param a
	 * @param bitPos
	 * @return the new integer
	 */
	public static int clearBit (int a, int bitPos)
	{
		return (a &= ~(1 << bitPos));
	}
	
	/**
	 * Using XOR
	 * If the bits are alike --> XOR = 0
	 * If the bits are different --> XOR = 1
	 * @param a
	 * @param bitPos
	 * @return the new integer
	 */
	public static int toggleBit (int a, int bitPos)
	{
		return (a ^= (1 << bitPos));
	}
}
