import bignumber.BigNumber;
import bignumber.BigNumberImpl;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

/** Unit testing for bignumber.BigNumber Implementation. */
public class BigNumberTest {

  /** Test bignumber.BigNumber Constructor and ToString() Method. */
  @Test
  public void testConstructorAndToString() {
    BigNumber bigNumber = new BigNumberImpl();
    BigNumber bigNumber1 = new BigNumberImpl("-123");
    Assert.assertEquals(bigNumber.toString(), "0");
    Assert.assertEquals(bigNumber1.toString(), "-123");
  }

  /** Test the length of the bignumber.BigNumber after init. */
  @Test
  public void testLengthOfNumber() {
    BigNumber bigNumber = new BigNumberImpl("123");
    BigNumber bigNumber2 = new BigNumberImpl();
    Assert.assertEquals(bigNumber.length(), 3);
    Assert.assertEquals(bigNumber2.length(), 1);
  }

  /** Test to add one Digit to the bignumber.BigNumber. */
  @Test
  public void testAddDigit() {
    BigNumber bigNumber = new BigNumberImpl("12323456784567890234567543");
    bigNumber.addDigit(9); // 124
    Assert.assertEquals(bigNumber.toString(), "12323456784567890234567552");

    BigNumber bigNumber2 = new BigNumberImpl();
    bigNumber2.addDigit(9); // 124
    Assert.assertEquals(bigNumber2.toString(), "9");
  }

  /** Test Adding an Invalid Digit. */
  @Test(expected = IllegalArgumentException.class)
  public void testAddDigitInvalidDigit() {
    BigNumber bigNumber = new BigNumberImpl("123");
    bigNumber.addDigit(11);
  }

  /** Test Adding an Invalid Digit. */
  @Test(expected = IllegalArgumentException.class)
  public void testAddDigitInvalidDigit2() {
    BigNumber bigNumber = new BigNumberImpl("123");
    bigNumber.addDigit(-1);
  }

  @Test
  public void testGetDigitAt() {
    BigNumber bigNumber = new BigNumberImpl("1123");
    Assert.assertEquals(bigNumber.getDigitAt(9), 0);
    Assert.assertEquals(bigNumber.getDigitAt(1), 2);
    Assert.assertEquals(bigNumber.getDigitAt(2), 1);
    Assert.assertEquals(bigNumber.getDigitAt(3), 1);
  }

  /** Test to get a Digit Position which doesn't exist Will through an IllegalArgumentException. */
  @Test(expected = IllegalArgumentException.class)
  public void testGetDigitAtInvalidPosition() {
    BigNumber bigNumber = new BigNumberImpl("1123");
    Assert.assertEquals(bigNumber.getDigitAt(-1), 1);
  }

  /** Test to shift to the Left. */
  @Test
  public void testLeftShift() {
    BigNumber bigNumber = new BigNumberImpl("124");
    bigNumber.shiftLeft(10);
    Assert.assertEquals(bigNumber.toString(), "1240000000000");
  }

  /** Test for shift to the Right. */
  @Test
  public void testRightShift() {
    BigNumber bigNumber = new BigNumberImpl("124");
    bigNumber.shiftRight(1); // 12
    Assert.assertEquals(bigNumber.toString(), "12");
    bigNumber.shiftRight(5);
    Assert.assertEquals(bigNumber.toString(), "0");
  }

  /** Test for Negative Left Shift. */
  @Test
  public void testNegativeLeftShift() {
    BigNumber bigNumber = new BigNumberImpl("124");
    bigNumber.shiftLeft(-1);
    Assert.assertEquals(bigNumber.toString(), "12");
  }

  /** Test for Negative Right Shift. */
  @Test
  public void testNegativeRightShift() {
    BigNumber bigNumber = new BigNumberImpl("124");
    bigNumber.shiftRight(-1); // 1240
    Assert.assertEquals(bigNumber.toString(), "1240");
  }

  /** Test adding two BigNumbers. */
  @Test
  public void testAdd() {
    BigNumber bigNumber = new BigNumberImpl("1000");
    BigNumber bigNumber1 = new BigNumberImpl("123");
    BigNumber sum = bigNumber.add(bigNumber1);
    Assert.assertEquals(sum.toString(), "1123");
  }

  /** Test a copy of bignumber.BigNumber is equal to the Original. */
  @Test
  public void testCopyAndIsEqual() {
    BigNumber bigNumber = new BigNumberImpl("1000");
    BigNumber bigNumber1 = bigNumber.copy();
    Assert.assertEquals(bigNumber.isEqual(bigNumber1), true);
  }

  @Test
  public void testRandomAdd() {
    BigInteger one = new BigInteger(1000, new Random());
    BigInteger two = new BigInteger(1000, new Random());
    BigNumber bigNumber1 = new BigNumberImpl(one.toString());
    BigNumber bigNumber2 = new BigNumberImpl(two.toString());
    Assert.assertEquals(bigNumber2.add(bigNumber1).toString(), one.add(two).toString());
  }

  @Test
  public void testCompare() {
    BigInteger one = new BigInteger(1000, new Random());
    BigInteger two = new BigInteger(1000, new Random());
    BigNumberImpl bigNumber1 = new BigNumberImpl(one.toString());
    BigNumberImpl bigNumber2 = new BigNumberImpl(two.toString());
    Assert.assertEquals(bigNumber1.compareTo(bigNumber2), one.compareTo(two));
  }
}
