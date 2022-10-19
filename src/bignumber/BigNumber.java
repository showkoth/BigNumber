package bignumber;

/**
 * BigNumber interface defines the main functions involved in the computation
 * of BigNumbers, the implementation of this interface would support multiple other
 * util methods which would serve complete functional use cases.
 */
public interface BigNumber {

  int length();

  void shiftLeft(int numberOfShifts);

  void shiftRight(int numberOfShifts);

  void addDigit(int digit);

  int getDigitAt(int position);

  BigNumber copy();

  BigNumber add(BigNumber bigNumber);

  int compareTo(BigNumber other);

  boolean isEqual(BigNumber bigNumber);

}
