package com.delgado.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A class that performs simple algebra operations.
 * 
 * @author Delgado
 */
public class MathLogic {
    /**
     * The instance of a singleton MathLogic.
     */
    public static final MathLogic INSTANCE = new MathLogic();

    private final int MAX = (int) (1e6 + 1);
    private List<Integer> primesList;

    /**
     * Checks whether the set (a, b, c) is a Pythagorean triple.
     * 
     * @param a
     *            The first number.
     * @param b
     *            The second number.
     * @param c
     *            The third number.
     * @return true, if (a, b, c) is a Pythagorean triple, and false, if
     *         otherwise.
     */
    public static boolean checkPythagoreanTriple(long a, long b, long c) {
	long[] ar = { a, b, c };
	Arrays.sort(ar);
	return (ar[0] * ar[0] + ar[1] * ar[1] == ar[2] * ar[2]);
    }

    /**
     * Converts a number from one numeric system to another.
     * 
     * @param number
     *            The number to convert.
     * @param baseIn
     *            Base of the initial numeric system.
     * @param baseOut
     *            Base of the desirable numeric system.
     * @return The number in another numeric system.
     */
    public static String convertNumber(long number, int baseIn, int baseOut) {
	return convertFromDecimal(
		convertToDecimal(Long.toString(number), baseIn), baseOut);
    }

    /**
     * Converts a number from one numeric system to another.
     * 
     * @param number
     *            String representation of the number to convert.
     * @param baseIn
     *            Base of the initial numeric system.
     * @param baseOut
     *            Base of the desirable numeric system.
     * @return The number in another numeric system.
     */
    public static String convertNumber(String number, int baseIn, int baseOut) {
	return convertFromDecimal(convertToDecimal(number, baseIn), baseOut);
    }

    /**
     * Calculates the factorial.
     * 
     * @param a
     *            The number.
     * @return The factorial.
     */
    public static long factorial(long a) {
	long ans = 1;
	for (long i = a; i > 1; --i) {
	    ans *= i;
	}
	return ans;
    }

    /**
     * Calculates the greatest common divisor.
     * 
     * @param a
     *            The first number.
     * @param b
     *            The second number.
     * @return The GCD.
     */
    public static long gcd(long a, long b) {
	return (b != 0 ? gcd(b, a % b) : Math.abs(a));
    }

    /**
     * Calculates the binominal coefficient (n,k).
     * 
     * @param n
     * @param k
     * @return The binominal coefficient.
     */
    public static BigInteger choose(long n, long k) {
	if (k < 0 || k > n)
	    return BigInteger.ZERO;
	if (k == 0 || k == n)
	    return BigInteger.ONE;

	BigInteger ans = BigInteger.ONE;
	for (long i = n - k + 1; i <= n; i++) {
	    ans = ans.multiply(BigInteger.valueOf(i));
	}
	for (long j = 1; j <= k; j++) {
	    ans = ans.divide(BigInteger.valueOf(j));
	}
	return ans;
    }

    /**
     * Finds one root of the equation ax+by=gcd(a,b) and gcd(a,b).
     * 
     * @param a
     * @param b
     * @return {x0, y0, gcd(a,b)}
     */
    public static Triple<Long, Long, Long> egcd(long a, long b) {
	if (a == 0) {
	    return new Triple<Long, Long, Long>(0L, 1L, b);
	}
	Triple<Long, Long, Long> output1 = egcd(b % a, a);
	Triple<Long, Long, Long> output = new Triple<Long, Long, Long>(
		output1.get2() - (b / a) * output1.get1(), output1.get1(),
		output1.get3());
	return output;
    }
    
    /**
     * Provides a list of prime numbers.
     * @return A list o prime numbers.
     */
    public List<Integer> getPrimesList() {
	if (this.primesList == null)
	    buildPrimesList();
	return this.primesList;
    }

    /**
     * Checks whether the number is prime.
     * 
     * @param a
     *            The number.
     * @return true, if the number is prime, or false, if otherwise.
     * @throws Exception
     *             An exception that occurs when the input number is bigger than
     *             the class can manage.
     */
    public boolean isPrime(int a) throws Exception {
	if (this.primesList == null)
	    buildPrimesList();
	return (Collections.binarySearch(primesList, a) >= 0);
    }

    /**
     * Factorises the number into prime divisors.
     * 
     * @param num
     *            The number.
     * @return List of pairs of format {divisor, exponent}.
     */
    public List<Pair<Integer, Integer>> factorise(long num) {
	if (this.primesList == null)
	    buildPrimesList();
	List<Pair<Integer, Integer>> factorisationList = new ArrayList<Pair<Integer, Integer>>();
	if (num < 0)
	    num = -num;
	if (num < 2) {
	    factorisationList.add(new Pair<Integer, Integer>((int) num, 1));
	    return factorisationList;
	}
	int currentPrimeIndex = 0;
	while (num > 1) {
	    int currentPrime = primesList.get(currentPrimeIndex);
	    int exponent = 0;
	    while (num % currentPrime == 0) {
		++exponent;
		num /= currentPrime;
	    }
	    if (exponent > 0)
		factorisationList.add(new Pair<Integer, Integer>(currentPrime,
			exponent));
	    ++currentPrimeIndex;
	}
	return factorisationList;
    }

    /**
     * Factorises the number into prime divisors and returns the string
     * representation.
     * 
     * @param num
     *            The number.
     * @return A string of format
     *         "[divisor1]^[exponent1] + ... + [divisorN]^[exponentN]"
     */
    public String factoriseToString(long num) {
	List<Pair<Integer, Integer>> factorisation = factorise(num);
	String s = "";
	for (Pair<Integer, Integer> elem : factorisation) {
	    s = s + elem.getL() + "^" + elem.getR() + " + ";
	}
	return s.substring(0, s.length() - 3);
    }

    /**
     * Calculates the least common multiple of two numbers.
     * 
     * @param a
     *            The first number.
     * @param b
     *            The second number.
     * @return The LCM.
     */
    public static long lcm(long a, long b) {
	return (a / gcd(a, b) * b);
    }

    /**
     * Gives all solutions of a Diophatine equation ax+by=c.
     * 
     * @param a
     * @param b
     * @param c
     * @return {{x0, k}, {y0, l}} where x = x0 + k*z, y = y0 + l*z (z is an
     *         integer number)
     */
    public static Pair<Pair<Long, Long>, Pair<Long, Long>> solveDiophEqAll(
	    long a, long b, long c) {
	Triple<Long, Long, Long> o1 = solveDiophEqOne(a, b, c);
	if (o1 == null)
	    return null;
	long x0 = o1.get1();
	long y0 = o1.get2();
	long g = o1.get3();
	Pair<Pair<Long, Long>, Pair<Long, Long>> output = new Pair<Pair<Long, Long>, Pair<Long, Long>>(
		new Pair<Long, Long>(x0, b / g), new Pair<Long, Long>(y0, -a
			/ g));
	return output;
    }

    /**
     * Gives all solutions of a Diophatine equation ax+by=c represented in
     * strings.
     * 
     * @param a
     * @param b
     * @param c
     * @return {x, y} or null if there are no solutions.
     */
    public static Pair<String, String> solveDiophEqAllString(long a, long b,
	    long c) {
	Pair<Pair<Long, Long>, Pair<Long, Long>> o = solveDiophEqAll(a, b, c);
	if (o == null)
	    return null;
	return new Pair<String, String>(String.format("x = %d %c %d * k", o
		.getL().getL(), (o.getL().getR() > 0 ? '+' : '-'), Math.abs(o
		.getL().getR())), String.format("y = %d %c %d * k", o.getR()
		.getL(), (o.getR().getR() > 0 ? '+' : '-'), Math.abs(o.getR()
		.getR())));
    }

    /**
     * Gives one solution of a Diophatine equation ax+by=c.
     * 
     * @param a
     * @param b
     * @param c
     * @return {x0, y0, gcd(a,b)} or null if there are no solutions.
     */
    public static Triple<Long, Long, Long> solveDiophEqOne(long a, long b,
	    long c) {
	if ((a == 0) && (b == 0))
	    return null;
	Triple<Long, Long, Long> egcdResult = egcd(Math.abs(a), Math.abs(b));
	long x0 = egcdResult.get1();
	long y0 = egcdResult.get2();
	long g = egcdResult.get3();
	if (c % g != 0)
	    return null;
	x0 *= c / g;
	y0 *= c / g;
	if (a < 0)
	    x0 *= -1;
	if (b < 0)
	    y0 *= -1;
	return new Triple<Long, Long, Long>(x0, y0, g);
    }

    /**
     * Solves the congruence a*x=b (mod m).
     * 
     * @param a
     * @param b
     * @param m
     * @return The root or (-m-1) if there is no root.
     */
    public static long solveCongruence(long a, long b, long m) {
	if (gcd(a, m) != 1)
	    return -m - 1;
	Triple<Long, Long, Long> d = solveDiophEqOne(a, -m, b);
	if (d != null) {
	    return d.get1();
	} else
	    return -m - 1;
    }

    // ------------------------------------------------------------------------------------

    private void buildPrimesList() {
	int upperBound = MAX;
	this.primesList = new ArrayList<Integer>();
	boolean[] composite = new boolean[upperBound];
	composite[1] = true;
	for (int i = 2; i < upperBound; ++i)
	    composite[i] = false;
	for (int i = 2; i < upperBound / 2; ++i) {
	    if (!composite[i]) {
		this.primesList.add(i);
		int j = 2 * i;
		while (j < upperBound) {
		    composite[j] = true;
		    j += i;
		}
	    }
	}
    }

    private static String convertFromDecimal(long num, int baseOut) {
	if (baseOut == 10)
	    return Long.toString(num);
	String s = "";
	String d = "!0123456789ABCDEF";
	do {
	    s = d.charAt((int) ((num % baseOut) + 1)) + s;
	    num /= baseOut;
	} while (num != 0);
	return s;
    }

    private static long convertToDecimal(String num, int baseIn) {
	if (baseIn == 10)
	    return Long.parseLong(num);
	String d = "!0123456789ABCDEF";
	long m = 0;
	int start = 0;
	int pos = 0;
	while (num.charAt(0) == '0')
	    ++start;
	for (int i = start; i < num.length(); i++) {
	    for (int j = 0; j < d.length(); j++) {
		if (d.charAt(j) == num.charAt(i)) {
		    pos = j;
		    break;
		}
	    }
	    m = m * baseIn + pos - 1;
	}
	return m;
    }
}
